
package com.campus.smartcampus;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class EventController {

    private final CampusEventManager eventManager;

    public EventController(CampusEventManager eventManager) {
        this.eventManager = eventManager;
    }

    @GetMapping("/events")
    public String showEvents(Model model) {
        model.addAttribute("events", eventManager.getEvents());
        model.addAttribute(
                "registrationQueue",
                eventManager.getRegistrationQueue()
        );

        return "events";
    }

    @PostMapping("/events/add")
    public String addEvent(
            @RequestParam String eventName,
            RedirectAttributes redirectAttributes) {

        if (eventName == null || eventName.isBlank()) {
            redirectAttributes.addFlashAttribute(
                    "message", "Please enter an event name."
            );
        } else {
            eventManager.addEvent(eventName);
            redirectAttributes.addFlashAttribute(
                    "message", "Event added successfully!"
            );
        }

        return "redirect:/events";
    }

    @PostMapping("/events/register")
    public String registerStudent(
            @RequestParam String studentName,
            RedirectAttributes redirectAttributes) {

        if (studentName == null || studentName.isBlank()) {
            redirectAttributes.addFlashAttribute(
                    "message", "Please enter your name."
            );
        } else {
            eventManager.registerStudent(studentName);
            redirectAttributes.addFlashAttribute(
                    "message", "Registration added to the queue!"
            );
        }

        return "redirect:/events";
    }
}
