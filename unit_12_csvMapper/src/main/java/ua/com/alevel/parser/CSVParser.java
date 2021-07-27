package ua.com.alevel.parser;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CSVParser {
    public Map <String, Integer> header(){
        Map<String, Integer> head;
        try (CSVReader reader = new CSVReader(new FileReader("mapper.csv"))) {
            List<String[]> lines = reader.readAll();
            head = new HashMap<>();
            int count = 0;
            for (String s : lines.get(0)) {
                head.put(s, count++);
            }
        } catch (IOException|CsvException e) {
            throw new RuntimeException();
        }
        return head;
    }

    public List <String[]> body(){
        List<String[]> b;
        try (CSVReader reader = new CSVReader(new FileReader("mapper.csv"))) {
            List<String[]> lines = reader.readAll();
            b = lines.stream().skip(1).collect(Collectors.toList());
        } catch (IOException|CsvException e) {
            throw new RuntimeException();
        }
        return b;
    }
}

