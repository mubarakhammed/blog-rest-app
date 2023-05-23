package com.mubarak.blogrestapi.payload;

import java.util.Date;

public class Errordetails {
    private Date timeStamp;
    private String message;
    private String details;

    public Errordetails(Date timeStamp, String message, String details) {
        this.timeStamp = timeStamp;
        this.message = message;
        this.details = details;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
}
