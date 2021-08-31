package ua.com.alevel;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;


@WebServlet(name = "history-servlet", urlPatterns = "/history")
public class HistoryServlet extends HttpServlet {
    private final Map<String, String> requestHistory = new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter responseBody = resp.getWriter();
        resp.setContentType("text/html");
        String userIP = req.getRemoteHost();
        String userAgent = req.getHeader("User-Agent");

        requestHistory.put(userIP, userAgent);

        responseBody.println("<ul>");
        for(String request:requestHistory.keySet()){
            if (request.equals(userIP)) {
                responseBody.println("<li><b>" + request + " :: " + requestHistory.get(request) + "</b></li>");
            } else {
                responseBody.println("<li>"+request + " :: " + requestHistory.get(request)+"</li>");
            }
        }
        responseBody.println("</ul>");

    }
}
