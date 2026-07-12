package com.warehouseflow.sim;

import com.warehouseflow.engine.PathFinder;
import com.warehouseflow.engine.Route;
import com.warehouseflow.model.Slot;
import com.warehouseflow.model.Warehouse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SimulationRunner {

    private final Warehouse warehouse;
    private final PathFinder pathFinder;
    private final Random random;

    public SimulationRunner(Warehouse warehouse) {
        this.warehouse = warehouse;
        this.pathFinder = new PathFinder();
        this.random = new Random();
    }

    public SimulationResult run(int numberOfOrders, int stopsPerOrder) {
        Slot dock = warehouse.getSlot(0, 0);
        double totalDistance = 0;

        for (int i = 0; i < numberOfOrders; i++) {
            List<Slot> stops = randomStops(stopsPerOrder, dock);
            Route route = pathFinder.findPath(dock, stops);
            totalDistance += route.getTotalDistance();
        }

        double averageDistance = totalDistance / numberOfOrders;
        return new SimulationResult(numberOfOrders, totalDistance, averageDistance);
    }

    private List<Slot> randomStops(int count, Slot exclude) {
        List<Slot> allSlots = new ArrayList<>();
        for (int x = 0; x < warehouse.getWidth(); x++) {
            for (int y = 0; y < warehouse.getHeight(); y++) {
                Slot slot = warehouse.getSlot(x, y);
                if (slot != exclude) {
                    allSlots.add(slot);
                }
            }
        }
        Collections.shuffle(allSlots, random);
        return allSlots.subList(0, Math.min(count, allSlots.size()));
    }
}