package com.annakaplon.reports;

/**
 * Abstract base class representing capabilities of all reports.
 */
public abstract class Report {

    public abstract void generateOnScreen();

    public abstract void generateOnScreen(String clientId);

    public abstract void generateCSV();

    public abstract void generateCSV(String clientId);
}
