package ua.com.alevel.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.firstmode.addoperation.AddOperation;
import ua.com.alevel.secondmode.operationstocsv.OperationsToCSV;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;


public class Controller {
    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public void run(Long userID, String username, String password) {
        LOGGER_INFO.info("Start app");
        System.out.println("Choose the option, what you like:\n" +
                "1 Create new operation\n" +
                "2 Export operations to csv\n" +
                "0 Exit");

        try {
            int choice = Integer.parseInt(reader.readLine());
            switch (choice){
                case 1:
                    createOperation(userID, username, password);
                    break;
                case 2:
                    exportCSV(username,password);
                    break;
                case 0:
                    System.exit(0);
            }
        } catch (IOException e) {
            LOGGER_ERROR.error("Enter incorrect option`s number");
            throw  new RuntimeException("Wrong choice");
        }
    }

    private void createOperation(Long userId, String username, String password) {
        try {
            LOGGER_INFO.info("Start creating operation");
            System.out.println("Enter your balance id");
            Long balanceId = Long.parseLong(reader.readLine());
            System.out.println("Enter the operation`s amount: positive number for income or negative for consumption");
            Long amount = Long.parseLong(reader.readLine());
            if (amount == 0) {
                LOGGER_ERROR.error("amount can`t be 0");
                throw new RuntimeException("amount can`t be 0");
            }
            System.out.println("Enter the operation`s description");
            String description = reader.readLine();
            Instant timestamp = Instant.now();
            AddOperation addOperation = new AddOperation();
            addOperation.addOperation(userId, username, password, amount, description, timestamp, balanceId);
            LOGGER_INFO.info("Operation created");
        } catch (IOException e) {
            LOGGER_ERROR.error("Creation failed");
            throw new RuntimeException("Incorrect input");
        }
    }

    private void exportCSV(String username, String password) {
        try {
            LOGGER_INFO.info("Start export to csv");
            System.out.println("Enter your balance id");
            Long balanceId = Long.parseLong(reader.readLine());
            System.out.println("Enter from date in format 05/30/2018 05:46");
            String dateFrom = reader.readLine();
            Date dateF = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(dateFrom);
            Instant from = dateF.toInstant();
            System.out.println("Enter to date in format 05/30/2018 05:46");
            String dateTo = reader.readLine();
            Date dateT = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(dateTo);
            Instant to = dateT.toInstant();
            OperationsToCSV operationsToCSV = new OperationsToCSV();
            operationsToCSV.exportToCSV(username, password, balanceId,from,to);
            LOGGER_INFO.info("Export is done");
        } catch (IOException | ParseException e) {
            LOGGER_ERROR.error("Can`t export to csv");
            throw new RuntimeException("Can`t export to csv");
        }
    }
}
