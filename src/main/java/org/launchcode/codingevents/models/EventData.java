package org.launchcode.codingevents.models;

import java.util.ArrayList;

public class EventData {

    static ArrayList<Event> events = new ArrayList<>();

    public static ArrayList<Event> getAll() {
        return events;
    }

    public static void create(Event newEvent) {
        events.add(newEvent);
    }

    public static void remove(int id) {
        Event eventToRemove = getById(id);
        events.remove(eventToRemove);
    }

    public static Event getById(int id) {

        Event theEvent = null;

        for (Event event : events) {
            if (event.getEventId() == id) {
                theEvent = event;
            }
        }

        return theEvent;
    }
}
