
package com.campus.smartcampus;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ComplaintController {

    private final ComplaintService complaintService;

    public ComplaintController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    @GetMapping("/complaints")
    public String showComplaints(Model model) {
        model.addAttribute("complaints",
                complaintService.getComplaints());
        model.addAttribute("count",
                complaintService.getComplaintCount());

        return "complaints";
    }

    @PostMapping("/complaints/add")
    public String addComplaint(
            @RequestParam("complaint") String complaint,
            RedirectAttributes redirectAttributes) {

        if (complaint == null || complaint.isBlank()) {
            redirectAttributes.addFlashAttribute(
                    "message", "Please enter a complaint.");
        } else {
            complaintService.addComplaint(complaint);
            redirectAttributes.addFlashAttribute(
                    "message", "Complaint submitted successfully!");
        }

        return "redirect:/complaints";
    }
}
