
package com.campus.smartcampus;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LostFoundController {

    private final LostFoundService lostFoundService;

    public LostFoundController(LostFoundService lostFoundService) {
        this.lostFoundService = lostFoundService;
    }

    @GetMapping("/lost-found")
    public String showItems(Model model) {
        model.addAttribute("items", lostFoundService.getItems());
        return "lost-found";
    }

    @PostMapping("/lost-found/add")
    public String addItem(
            @RequestParam("itemName") String itemName,
            @RequestParam("type") String type,
            @RequestParam("location") String location,
            RedirectAttributes redirectAttributes) {

        if (itemName == null || itemName.isBlank()
                || type == null || type.isBlank()
                || location == null || location.isBlank()) {

            redirectAttributes.addFlashAttribute(
                    "message", "Please fill in all fields.");

        } else {
            lostFoundService.addItem(itemName, type, location);

            redirectAttributes.addFlashAttribute(
                    "message", "Item added successfully!");
        }

        return "redirect:/lost-found";
    }
}
