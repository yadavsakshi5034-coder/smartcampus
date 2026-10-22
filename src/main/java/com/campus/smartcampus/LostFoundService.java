
package com.campus.smartcampus;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class LostFoundService {

    private final Map<Integer, LostItem> items = new LinkedHashMap<>();
    private int nextId = 1;

    public void addItem(String itemName, String type, String location) {
        if (itemName == null || itemName.isBlank()
                || type == null || type.isBlank()
                || location == null || location.isBlank()) {
            return;
        }

        LostItem item = new LostItem(
                nextId, itemName.trim(), type.trim(), location.trim()
        );

        items.put(nextId, item);
        nextId++;
    }

    public Map<Integer, LostItem> getItems() {
        return new LinkedHashMap<>(items);
    }

    public static class LostItem {
        private final int id;
        private final String itemName;
        private final String type;
        private final String location;

        public LostItem(int id, String itemName, String type, String location) {
            this.id = id;
            this.itemName = itemName;
            this.type = type;
            this.location = location;
        }

        public int getId() {
            return id;
        }

        public String getItemName() {
            return itemName;
        }

        public String getType() {
            return type;
        }

        public String getLocation() {
            return location;
        }
    }
}
