package com.spuds.eventapp.Shared;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by tina on 4/16/16.
 */
public class Event implements Serializable {
    private String eventId;
    private String hostId;
    private String eventName;
    private String description;
    private String location;
    private String date;
    private int attendees;
    private String picFileName;
    private ArrayList<String> categories;
    private String hostName;

    public Event() {}

     public Event(String eventId, String hostId, String eventName, String description,
                  String location, String date, int attendees, String picFileName,
                  ArrayList<String>  categories, String hostName) {
         this.setEventId(eventId);
         this.setHostId(hostId);
         this.setEventName(eventName);
         this.setDescription(description);
         this.setLocation(location);
         this.setDate(date);
         this.setAttendees(attendees);
         this.setPicFileName(picFileName);
         this.setCategories(categories);
         this.setHostName(hostName);
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventId() {
        return eventId;
    }

    public String getHostId() {
        return hostId;
    }

    public void setHostId(String hostId) {
        this.hostId = hostId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getAttendees() {
        return attendees;
    }

    public void setAttendees(int attendees) {
        this.attendees = attendees;
    }

    public String getPicFileName() {
        return picFileName;
    }

    public void setPicFileName(String picFileName) {
        this.picFileName = picFileName;
    }

    public ArrayList<String> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<String> categories) {
        this.categories = categories;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }
}
