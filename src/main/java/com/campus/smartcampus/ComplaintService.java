
package com.campus.smartcampus;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.springframework.stereotype.Service;

@Service
public class ComplaintService {

    private final Queue<String> complaints = new LinkedList<>();

    public void addComplaint(String complaint) {
        if (complaint != null && !complaint.isBlank()) {
            complaints.offer(complaint.trim());
        }
    }

    public List<String> getComplaints() {
        return List.copyOf(complaints);
    }

    public int getComplaintCount() {
        return complaints.size();
    }
}
