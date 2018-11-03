package com.annakaplon.reports;

import com.annakaplon.open.Request;

import static com.annakaplon.Main.requestList;

/**
 * Represents report with data about money spent on requests.
 * Provides methods necessary to create it.
 */
public class MoneySpentReport extends Report {

    /**
     * Generates report corresponding to all request on screen.
     */
    @Override
    public void generateOnScreen() {
        System.out.println("Total amount of money spent is " + countPrices());
    }

    /**
     * Generates report corresponding to given client's request on screen.
     * @param clientId client's ID
     */
    @Override
    public void generateOnScreen(String clientId) {
        System.out.println("Total amount of money spent by " + clientId +
                " client is " + countOneClientPrices(clientId));
    }

    /**
     * Saves report corresponding to all requests in CSV file.
     */
    @Override
    public void generateCSV() {
        String[] headers = {"Money_spent"};
        String[] fields = {String.valueOf(countPrices())};

        CSVGenerator generator = new CSVGenerator();
        generator.generateFile("money_spent.csv", headers);
        generator.addNewLine(fields);
        generator.closeFile();
    }

    /**
     * Saves report corresponding to given client's requests in CSV file.
     */
    @Override
    public void generateCSV(String clientId) {
        String[] headers = {"Client_Id", "Money_spent"};
        String[] fields = {clientId, String.valueOf(countOneClientPrices(clientId))};

        CSVGenerator generator = new CSVGenerator();
        generator.generateFile(clientId + "_money_spent.csv", headers);
        generator.addNewLine(fields);
        generator.closeFile();
    }

    /**
     * Counts all money spent on requests.
     * @return all money spent on requests
     */
    static double countPrices(){
        double totalCost = 0;
        for (Request request : requestList) {
            totalCost += request.getPrice()*request.getQuantity();
        }
        return totalCost;
    }

    /**
     * Counts money spent by give client.
     * @param clientId client's ID
     * @return money spent by give client
     */
    static double countOneClientPrices(String clientId) {
        double oneClientCost = 0;
        for (Request request : requestList) {
            if (request.getClientId().equals(clientId)) {
                oneClientCost += request.getPrice()*request.getQuantity();
            }
        }
        return oneClientCost;
    }
}
