package com.rewards.rewards.dbutils;


import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.rewards.rewards.model.Transaction;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Component
public class TransactionRepository {

    public List<Transaction> getAllTransactions() {
        List<Transaction> transactions = new java.util.ArrayList<>(List.of());
        try (
                Reader reader = Files.newBufferedReader(Path.of(
                        Paths.get("").toAbsolutePath() + "/src/main/resources/TransactionRecords.csv"));
                CSVReader csvReader = new CSVReader(reader);
        ) {
            csvReader.readNext();
            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null) {
                transactions.add(new Transaction(Integer.parseInt(nextRecord[0]),(nextRecord[1]), Integer.parseInt(nextRecord[2]), Integer.parseInt(nextRecord[3])));
            }
        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException(e);
        }
        return transactions;
    }
}