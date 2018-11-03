package com.annakaplon.reports;

import com.annakaplon.open.Request;

import static com.annakaplon.Main.requestList;

/**
 * Represents report preparing requests list.
 * Provides methods necessary to create it.
 */
public class RequestsListReport extends Report {

    /**
     * Generates report corresponding to all request on screen.
     */
    @Override
    public void generateOnScreen() {
        for (Request request : requestList) {
            System.out.println(request);
        }
    }

    /**
     * Generates report corresponding to given client's request on screen.
     * @param clientId client's ID
     */
    @Override
    public void generateOnScreen(String clientId) {
        for (Request request : requestList) {
            if (request.getClientId().equals(clientId)) {
                System.out.println(request);
            }
        }
    }

    /**
     * Saves report corresponding to all requests in CSV file.
     */
    @Override
    public void generateCSV() {
        String[] headers = {"Client_Id", "Request_Id", "Name", "Quantity", "Price"};

        CSVGenerator generator = new CSVGenerator();
        generator.generateFile("all_requests.csv", headers);
        for (Request request : requestList){
            String[] fields = {request.getClientId(),
                    String.valueOf(request.getRequestId()),
                    request.getName(),
                    String.valueOf(request.getQuantity()),
                    String.valueOf(request.getPrice())};

            generator.addNewLine(fields);
        }
        generator.closeFile();
    }

    /**
     * Saves report corresponding to given client's requests in CSV file.
     */
    @Override
    public void generateCSV(String clientId) {
        String[] headers = {"Client_Id", "Request_Id", "Name", "Quantity", "Price"};

        CSVGenerator generator = new CSVGenerator();
        generator.generateFile(clientId + "_client_requests.csv", headers);
        for (Request request : requestList) {
            if (request.getClientId().equals(clientId)) {
                String[] fields = {request.getClientId(),
                        String.valueOf(request.getRequestId()),
                        request.getName(),
                        String.valueOf(request.getQuantity()),
                        String.valueOf(request.getPrice())};

                generator.addNewLine(fields);
            }
        }
        generator.closeFile();
    }
}
