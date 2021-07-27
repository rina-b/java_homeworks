package ua.com.alevel;

import ua.com.alevel.entity.BookShop;
import ua.com.alevel.mapper.CSVMapper;
import ua.com.alevel.parser.CSVParser;

import java.util.ArrayList;
import java.util.List;

public class CSVMapperMain {
    public static void main(String[] args) {
        CSVParser parser = new CSVParser();
        CSVMapper mapper = new CSVMapper();
        List<BookShop> list = new ArrayList<>();
        for (String[] line : parser.body()){
            BookShop bookShop = mapper.map(BookShop.class,parser.header(),line);
            list.add(bookShop);
        }
    }
}
