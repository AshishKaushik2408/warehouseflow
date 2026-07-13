package com.warehouseflow.sim;

import com.warehouseflow.model.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OrderGenerator {

    private final List<Item> catalog;
    private final Random random = new Random();

    public OrderGenerator(List<Item> catalog) {
        this.catalog = catalog;
    }

    // One fixed batch of simulated demand, generated once and reused for
    // both slotting policies — so slotting is the only thing that changes
    // between "before" and "after".
    public List<List<Item>> generateOrders(int numberOfOrders, int itemsPerOrder) {
        List<List<Item>> orders = new ArrayList<>();
        for (int i = 0; i < numberOfOrders; i++) {
            orders.add(pickWeightedItems(itemsPerOrder));
        }
        return orders;
    }

    // Higher velocityScore = more likely to be picked, same as real demand
    // where a handful of SKUs make up most order volume.
    private List<Item> pickWeightedItems(int count) {
        int totalWeight = catalog.stream().mapToInt(Item::getVelocityScore).sum();
        List<Item> picked = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            int roll = random.nextInt(totalWeight);
            int cumulative = 0;
            for (Item item : catalog) {
                cumulative += item.getVelocityScore();
                if (roll < cumulative) {
                    picked.add(item);
                    break;
                }
            }
        }
        return picked;
    }
}
