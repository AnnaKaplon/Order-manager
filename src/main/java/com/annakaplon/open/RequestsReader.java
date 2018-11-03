package com.annakaplon.open;

import java.io.IOException;

/**
 * Abstract base class for all Readers.
 */
public abstract class RequestsReader {

    /**
     * Defines the possibility of function to reads files.
     * @param fileName name of file to read
     * @throws IOException
     */
    public abstract void readFile(String fileName) throws IOException;
}
