package com.philipporter.request;

/**
 * @author Philip Porter
 */
public class Request {
    
    private String id;
    private String type;
    private int amount;

    public Request() {
        
    }
    
    public Request(String id, String type, int amount) {
        this.id = id;
        this.type = type;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}