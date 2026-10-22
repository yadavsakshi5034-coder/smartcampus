
package com.campus.smartcampus;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NavigationController {

    private final CampusGraph campusGraph;

    public NavigationController(CampusGraph campusGraph) {
        this.campusGraph = campusGraph;
    }

    @GetMapping("/navigation")
    public String navigation(
            @RequestParam(required = false) String start,
            @RequestParam(required = false) String end,
            Model model) {

        Map<String, String> names = Map.of(
            "Gate", "Main Gate",
            "Library", "Library",
            "Canteen", "Canteen",
            "Admin", "Admin Office",
            "Lab", "Computer Lab"
        );

        model.addAttribute("locations", names);
        model.addAttribute("selectedStart", start);
        model.addAttribute("selectedEnd", end);

        if (start != null && end != null
                && names.containsKey(start)
                && names.containsKey(end)) {

            List<String> route =
                campusGraph.findShortestRoute(start, end);

            String routeText = route.stream()
                .map(names::get)
                .reduce((a, b) -> a + " → " + b)
                .orElse("No route found");

            model.addAttribute("route", routeText);
            model.addAttribute("stops",
                route.isEmpty() ? 0 : route.size() - 1);
            model.addAttribute("searched", true);
        }

        return "navigation";
    }
}
