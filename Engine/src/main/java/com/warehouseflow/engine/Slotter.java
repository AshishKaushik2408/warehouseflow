package com.warehouseflow.engine;

import com.warehouseflow.model.Item;
import com.warehouseflow.model.Slot;
import com.warehouseflow.model.Warehouse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Slotter {

    public void optimizeSlotting(Warehouse warehouse, List<Item> items) {
        Slot dock = warehouse.getSlot(0, 0);

        List<Item> byVelocityDesc = new ArrayList<>(items);
        byVelocityDesc.sort(Comparator.comparingInt(Item::getVelocityScore).reversed());

        List<Slot> byDistanceAsc = nonDockSlots(warehouse, dock);
        byDistanceAsc.sort(Comparator.comparingInt(slot -> GridUtils.distance(dock, slot)));

        assign(byDistanceAsc, byVelocityDesc);
    }

    public void randomSlotting(Warehouse warehouse, List<Item> items) {
        Slot dock = warehouse.getSlot(0, 0);
        List<Slot> shuffledSlots = nonDockSlots(warehouse, dock);
        Collections.shuffle(shuffledSlots);
        assign(shuffledSlots, items);
    }

    private List<Slot> nonDockSlots(Warehouse warehouse, Slot dock) {
        List<Slot> slots = new ArrayList<>();
        for (int x = 0; x < warehouse.getWidth(); x++) {
            for (int y = 0; y < warehouse.getHeight(); y++) {
                Slot slot = warehouse.getSlot(x, y);
                if (slot != dock) slots.add(slot);
            }
        }
        return slots;
    }

    private void assign(List<Slot> slots, List<Item> items) {
        for (Slot slot : slots) slot.setCurrentItem(null); // clear old assignment first
        int count = Math.min(slots.size(), items.size());
        for (int i = 0; i < count; i++) {
            slots.get(i).setCurrentItem(items.get(i));
        }
    }
}