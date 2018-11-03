package com.annakaplon.open;

import com.annakaplon.exceptions.IncorrectDataException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.IOException;
import java.io.Reader;

import static com.annakaplon.Main.requestList;

/**
 * CSVReader provides methods necessary to turn data saved in CSV files
 * into Request objects.
 */
public class CSVReader extends RequestsReader {

    /**
     * Reads (from given CSV file) data about requests, converts it
     * into Request objects and adds to the global list.
     *
     * @param fileName name of file
     * @throws IOException
     */
    public void readFile(String fileName) throws IOException{
        File f = new File(fileName);
        Reader in = new java.io.FileReader(f);
        CSVParser parser = CSVFormat.RFC4180.withHeader().parse(in);
        for (CSVRecord record : parser) {
            createReqest(fileName, record);
        }
    }

    /**
     * Reads data form given CSVRecord and convert it into Request object.
     * Ignores record and prints message if any filed is incorrect.
     * @param fileName name of file
     * @param record record from file
     */
    private void createReqest(String fileName, CSVRecord record){
        String clientId = record.get("Client_Id");
        String requestId = record.get("Request_id");
        String name = record.get("Name");
        String quantity = record.get("Quantity");
        String price = record.get("Price");

        try {
            requestList.add(new Request(clientId, requestId, name,
                    quantity, price));
        } catch (IncorrectDataException e){
            System.out.println("Incorect data in file " + fileName);
        }
    }
}
