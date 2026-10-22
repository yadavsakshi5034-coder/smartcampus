
package com.campus.smartcampus;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.springframework.stereotype.Service;

@Service
public class CampusEventManager {

    private final List<String> events = new ArrayList<>();
    private final Queue<String> registrationQueue = new LinkedList<>();

    public CampusEventManager() {
        events.add("Coding Competition");
        events.add("Project Exhibition");
        events.add("Tech Seminar");
        events.add("Cultural Fest");
    }

    public List<String> getEvents() {
        return new ArrayList<>(events);
    }

    public void addEvent(String eventName) {
        if (eventName != null && !eventName.isBlank()) {
            events.add(eventName.trim());
        }
    }

    public void registerStudent(String studentName) {
        if (studentName != null && !studentName.isBlank()) {
            registrationQueue.offer(studentName.trim());
        }
    }

    public List<String> getRegistrationQueue() {
        return new ArrayList<>(registrationQueue);
    }
}
