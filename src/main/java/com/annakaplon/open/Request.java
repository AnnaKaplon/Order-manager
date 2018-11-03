package com.annakaplon.open;

import com.annakaplon.exceptions.IncorrectDataException;
import org.apache.commons.lang3.StringUtils;

/**
 * Class stores information about each Request.
 */
public class Request {

    private String clientId;
    private long requestId;
    private String name;
    private int quantity;
    private double price;

    /**
     * Checks if data is correct and if so creates Request object.
     * @param clientId client ID, alphanumeric, maximum length is 6
     * @param requestId number of request
     * @param name name od product, alphanumeric with spaces, maximum length is 225
     * @param quantity amount of product bought
     * @param price price of product
     * @throws IncorrectDataException
     */
    public Request(String clientId, String requestId, String name,
                   String  quantity, String price) throws IncorrectDataException {

        boolean isClientIdCorrect = StringUtils.isAlphanumeric(clientId) &&
                !clientId.equals("") && clientId.length() <= 6;
        boolean isNameCorrect = StringUtils.isAlphanumericSpace(name) &&
                !name.equals("") && name.length() <= 225;

        if (isClientIdCorrect && isNameCorrect){
            this.clientId = clientId;
            this.name = name;
        } else{
            throw new IncorrectDataException();
        }

        try{
            this.requestId = Long.parseLong(requestId);
            this.quantity = Integer.parseInt(quantity);
            this.price = Double.parseDouble(price);
        }
        catch (NumberFormatException e){
            throw new IncorrectDataException();
        }
    }

    /**
     * Getter corresponding to the clientId field.
     * @return clientId field
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * Getter corresponding to the price field.
     * @return price field
     */
    public double getPrice() {
        return price;
    }

    /**
     * Getter corresponding to the quantity field.
     * @return quantity field
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Getter corresponding to the requestId field.
     * @return requestId field
     */
    public long getRequestId() {
        return requestId;
    }

    /**
     * Getter corresponding to the name field.
     * @return name field
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param clientId char sequence to check if it's correct clientId
     * @return true if clientId is correct and false in opposite case
     */
    public static boolean checkClientId(String clientId){
        return StringUtils.isAlphanumeric(clientId) &&
                !clientId.equals("") && clientId.length() <= 6;
    }

    /**
     * Returns a string representation of the object.
     * @return string representation of the object
     */
    @Override
    public String toString(){
        return "Request ID: " + requestId + " Client ID: " + clientId
                + " Product name: " + name + " Quatntiy: " + quantity
                + " Price: " + price;
    }
}
