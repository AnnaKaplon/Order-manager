package com.annakaplon.open;

import org.apache.commons.io.FilenameUtils;

import java.io.IOException;

/**
 * Class provides communication between program and RequestsReaders.
 */
public class Loader {

    /**
     * For each file chooses the right RequestReader and reads requests data.
     * @param fileNames list of files with data
     * @throws IOException
     */
    public void loadRequests(String[] fileNames) throws IOException {
        for (String fileName : fileNames) {
            try {
                RequestsReader reader = chooseReader(fileName);
                reader.readFile(fileName);
            } catch (Exception e){
                continue;
            }
        }
    }

    /**
     * Checks file extension and selects right RequestsReader based on it.
     * @param fileName name of file
     * @return CSVReader or XMLReader
     * @throws IllegalArgumentException
     */
    private RequestsReader chooseReader(String fileName) throws IllegalArgumentException {
        String extension = FilenameUtils.getExtension(fileName);
        if ("csv".equalsIgnoreCase(extension)){
            return new CSVReader();
        } else if ("xml".equalsIgnoreCase(extension)){
            return new XMLReader();
        } else{
            throw new IllegalArgumentException("Unacceptable file format");
        }
    }
}
