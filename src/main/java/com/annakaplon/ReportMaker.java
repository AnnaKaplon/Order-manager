package com.annakaplon;

import com.annakaplon.open.Request;
import com.annakaplon.reports.*;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Class provides communication between program and Report objects to enable
 * creating reports by user.
 */
public class ReportMaker {

    private Scanner input;
    private static final Map<String, Report> reports = new TreeMap<>();

    static {
        reports.put("NumberOfRequests", new NumberOfRequestsReport());
        reports.put("MoneySpent", new MoneySpentReport());
        reports.put("RequestList", new RequestsListReport());
        reports.put("AverageValue", new AverageValueReport());
    }

    /**
     * Asks user about type and form of report and creates it.
     */
    public void generateReport(){
        Report reportType = chooseReportType();
        String clientId = askForClientId();
        String isCSVselected = askForCSV();

        if (reportType == null){
            System.out.println("Incorrect input");
            return;
        } else if (!Request.checkClientId(clientId) && !"-".equals(clientId)){
            System.out.println("Incorrect input");
            return;
        }

        if (!clientId.equals("-")) {
            if (isCSVselected.equals("Y")){
                reportType.generateCSV(clientId);
            } else if (isCSVselected.equals("N")) {
                reportType.generateOnScreen(clientId);
            } else {
                System.out.println("Incorrect input");;
            }
        } else {
            if (isCSVselected.equals("Y")) {
                reportType.generateCSV();
            } else if (isCSVselected.equals("N")) {
                reportType.generateOnScreen();
            } else {
                System.out.println("Incorrect input");
            }
        }
    }

    /**
     * Changes name of report typed by user into Report object.
     * @return Report object corresponding to report name chosen by user
     */
    private Report chooseReportType() {
        return reports.get(askForReportType());
    }

    /**
     * Asks user for type of report he wants to generate.
     * @return name of chosen report
     */
    private String askForReportType(){
        String instruction = String.join("\n",
                "Choose one of the following report types and type its name",
                "NumberOfRequests - information about number of all requests",
                "        or requests made by given customer",
                "MoneySpent - information about the amount of money spent by all",
                "        or by given customer",
                "RequestList - list of all requests or given client's requests",
                "AverageValue - information about average value of all requests",
                "        or requests made by given customer");

        input = new Scanner(System.in);
        System.out.println();
        System.out.println(instruction);
        return input.next();
    }

    /**
     * Asks user if wants specify the clientId
     * @return client's ID or - if no clientId was chosen
     */
    private String askForClientId() {
        System.out.println("Enter client ID or - if report" +
                " is to concern all data");
        return input.next();
    }

    /**
     * Asks user if wants generate report in CSV file.
     * @return Y if yes, N if no
     */
    private String askForCSV() {
        System.out.println("Would you like to save report as CSV" +
                " file? (Y/N)");
        return input.next();
    }
}
