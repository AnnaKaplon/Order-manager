package com.annakaplon;

import com.annakaplon.open.Loader;
import com.annakaplon.open.Request;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static List<Request> requestList;

    public static void main(String[] args){
        try {
            requestList = new ArrayList<>();
            Loader loader = new Loader();
            loader.loadRequests(args);
            ReportMaker report = new ReportMaker();
            Scanner input = new Scanner(System.in);
            String answer = "Y";
            while ("Y".equalsIgnoreCase(answer)) {
                report.generateReport();
                System.out.println("Would you like to generate another report? (Y/N)");
                answer = input.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
