package com.annakaplon.reports;

import java.io.FileWriter;

/**
 * Class provides methods to generate coma separated CSV files.
 */
class CSVGenerator {

    private String DELIMITER = ",";
    private String LINE_SEPARATOR = "\n";
    private FileWriter writer;

    /**
     * Creates new file with given headers.
     * @param fileName name of file to create
     * @param headers list of headers
     */
    void generateFile(String fileName, String[] headers) {
        try {
            writer = new FileWriter(fileName);
            for (int i = 0; i < headers.length - 1; i++){
                writer.append(headers[i]);
                writer.append(DELIMITER);
            }
            writer.append(headers[headers.length - 1]);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Adds line to the existing file.
     * @param fields list of fields to add to file
     */
    void addNewLine(String[] fields) {
        try {
            writer.append(LINE_SEPARATOR);
            for (int i = 0; i < fields.length - 1; i++){
                writer.append(fields[i]);
                writer.append(DELIMITER);
            }
            writer.append(fields[fields.length - 1]);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Closes existing file.
     */
    void closeFile() {
        try {
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
