
package com.campus.smartcampus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import org.springframework.stereotype.Service;

@Service
public class CampusGraph {

    private final Map<String, List<String>> graph = new HashMap<>();

    public CampusGraph() {
        graph.put("Gate", Arrays.asList("Library", "Admin"));
        graph.put("Library", Arrays.asList("Gate", "Canteen", "Lab"));
        graph.put("Canteen", Arrays.asList("Library", "Admin"));
        graph.put("Admin", Arrays.asList("Gate", "Canteen", "Lab"));
        graph.put("Lab", Arrays.asList("Library", "Admin"));
    }

    public List<String> findShortestRoute(String start, String destination) {

        Queue<List<String>> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.offer(new ArrayList<>(List.of(start)));
        visited.add(start);

        while (!queue.isEmpty()) {
            List<String> path = queue.poll();
            String current = path.get(path.size() - 1);

            if (current.equals(destination)) {
                return path;
            }

            for (String neighbour : graph.getOrDefault(current, Collections.emptyList())) {
                if (!visited.contains(neighbour)) {
                    visited.add(neighbour);

                    List<String> newPath = new ArrayList<>(path);
                    newPath.add(neighbour);

                    queue.offer(newPath);
                }
            }
        }

        return Collections.emptyList();
    }
}
