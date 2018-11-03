package com.annakaplon.reports;

import com.annakaplon.open.Request;

import static com.annakaplon.Main.requestList;

/**
 * Represents report with data about number of requests.
 * Provides methods necessary to create it.
 */
public class NumberOfRequestsReport extends Report {

    /**
     * Generates report corresponding to all request on screen.
     */
    @Override
    public void generateOnScreen() {
        System.out.println("Total number of requests is " + countAllRequests());
    }

    /**
     * Generates report corresponding to given client's request on screen.
     * @param clientId client's ID
     */
    @Override
    public void generateOnScreen(String clientId) {
        System.out.println("Total number of client's " +
                clientId + " requests is " + countOneClientRequests(clientId));
    }

    /**
     * Saves report corresponding to all requests in CSV file.
     */
    @Override
    public void generateCSV() {
        String[] headers = {"Total_requests_number"};
        String[] fields = {String.valueOf(countAllRequests())};

        CSVGenerator generator = new CSVGenerator();
        generator.generateFile("number_of_resuests.csv", headers);
        generator.addNewLine(fields);
        generator.closeFile();

    }

    /**
     * Saves report corresponding to given client's requests in CSV file.
     */
    @Override
    public void generateCSV(String clientId) {
        String[] headers = {"Client_Id", "Number_of_requests"};
        String[] fields = {clientId, String.valueOf(countOneClientRequests(clientId))};

        CSVGenerator generator = new CSVGenerator();
        generator.generateFile("number_of _" + clientId +
                "_requests.csv", headers);
        generator.addNewLine(fields);
        generator.closeFile();
    }

    /**
     * Counts all requests.
     * @return number of requests
     */
    static int countAllRequests(){
        return requestList.size();
    }

    /**
     * Counts given client's requests.
     * @param clientId client's ID
     * @return number of given client's requests
     */
    static int countOneClientRequests(String clientId) {
        int counter = 0;
        for (Request request : requestList){
            if (request.getClientId().equals(clientId)){
                counter += 1;
            }
        }
        return counter;
    }
}
