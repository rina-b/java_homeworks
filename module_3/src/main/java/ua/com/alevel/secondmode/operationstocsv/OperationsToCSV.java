package ua.com.alevel.secondmode.operationstocsv;

import com.opencsv.CSVWriter;
import ua.com.alevel.dto.OperationDto;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class OperationsToCSV {
    public void exportToCSV(String username, String password, Long balanceId, Instant from, Instant to) {
        Properties properties = loadProperties();
        String url = properties.getProperty("url");
        List<OperationDto> operations = new ArrayList<>();
        String[] header = new String[]{"Id", "Balance id", "Amount", "Description", "Timestamp"};
        List<String[]> csvData = new ArrayList<>();
        csvData.add(header);

        try (Connection connection = DriverManager.getConnection(url, username, password);
                CSVWriter writer = new CSVWriter(new FileWriter("operations.csv",true))) {

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM operation WHERE operation.balance_id = ? AND operation.timestamp BETWEEN ? AND ?");

            statement.setLong(1, balanceId);
            statement.setTimestamp(2, Timestamp.from(from));
            statement.setTimestamp(3, Timestamp.from(to));
            ResultSet result = statement.executeQuery();

            while (result.next()){
                OperationDto operation = new OperationDto();
                operation.setId(result.getLong(1));
                operation.setBalanceId(result.getLong(2));
                operation.setAmount(result.getLong(3));
                operation.setDescription(result.getString(4));
                operation.setTimestamp(result.getTimestamp(5).toInstant());
                operations.add(operation);
            }

            for(OperationDto o : operations){
                csvData.add(toStringArray(o));
            }
            writer.writeAll(csvData);
        } catch (SQLException | IOException e) {
            throw new RuntimeException("Can`t export operations to csv");
        }
    }

    private Properties loadProperties(){
        Properties properties = new Properties();
        try(InputStream in = getClass().getClassLoader().getResourceAsStream("jdbc.properties")){
            properties.load(in);
        } catch (IOException ex){
            throw new UncheckedIOException(ex);
        }
        return properties;
    }

    private String[] toStringArray(OperationDto operation){
        String[] strings = new String[5];
        strings[0] = String.valueOf(operation.getId());
        strings[1] = String.valueOf(operation.getBalanceId());
        strings[2] = String.valueOf(operation.getAmount());
        strings[3] = String.valueOf(operation.getDescription());
        strings[4] = operation.getTimestamp().toString();
        return strings;
    }
}
