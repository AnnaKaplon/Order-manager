package com.annakaplon.reports;

import com.annakaplon.exceptions.NoElementsException;

/**
 * Represents report with data about average value of requests.
 * Provides methods necessary to create it.
 */
public class AverageValueReport extends Report {

    /**
     * Generates report corresponding to all request on screen.
     */
    @Override
    public void generateOnScreen() {
        try {
            System.out.println("Average requests value is " +
                    countAverageRequestValue());
        } catch (NoElementsException e){
            System.out.println("No requests to calculate average value");
        }
    }

    /**
     * Generates report corresponding to given client's request on screen.
     * @param clientId client's ID
     */
    @Override
    public void generateOnScreen(String clientId) {
        try {
            System.out.println("Average value of client's " + clientId +
                    " requests is " + countOneClientAverageRequestValue(clientId));
        } catch (NoElementsException e) {
            System.out.println("No requests to calculate average value");
        }
    }

    /**
     * Saves report corresponding to all requests in CSV file.
     */
    @Override
    public void generateCSV() {
        double value;
        try{
            value = countAverageRequestValue();
        } catch (NoElementsException e){
            System.out.println("No requests to calculate average value");
            return;
        }

        String[] headers = {"Average_value"};
        String[] fields = {String.valueOf(value)};

        CSVGenerator generator = new CSVGenerator();
        generator.generateFile("average_value.csv", headers);
        generator.addNewLine(fields);
        generator.closeFile();
    }

    /**
     * Saves report corresponding to given client's requests in CSV file.
     */
    @Override
    public void generateCSV(String clientId) {
        double value;
        try {
            value = countOneClientAverageRequestValue(clientId);
        } catch (NoElementsException e) {
            System.out.println("No requests to calculate average value");
            return;
        }
        String[] headers = {"Client_Id", "Average_value"};
        String[] fields = {clientId, String.valueOf(value)};

        CSVGenerator generator = new CSVGenerator();
        generator.generateFile(clientId + "_average_value.csv", headers);
        generator.addNewLine(fields);
        generator.closeFile();
    }

    /**
     * Calculate average value of all requests.
     * @return average value of requests
     */
    private double countAverageRequestValue() throws NoElementsException {
        double cost = MoneySpentReport.countPrices();
        int numberOfRequests = NumberOfRequestsReport.countAllRequests();
        if (numberOfRequests == 0) {
            throw new NoElementsException();
        }
        return cost/numberOfRequests;
    }

    /**
     * Calculate average value of given client's requests.
     * @param clientId client's ID
     * @return average value of given client's requests
     */
    private double countOneClientAverageRequestValue(String clientId) throws NoElementsException {
        double cost = MoneySpentReport.countOneClientPrices(clientId);
        int numberOfRequests = NumberOfRequestsReport.countOneClientRequests(clientId);
        if (numberOfRequests == 0) {
            throw new NoElementsException();
        }
        return cost/numberOfRequests;
    }
}
