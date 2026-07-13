package com.warehouseflow.sim;

import com.warehouseflow.engine.PathFinder;
import com.warehouseflow.engine.Route;
import com.warehouseflow.model.Item;
import com.warehouseflow.model.Slot;
import com.warehouseflow.model.Warehouse;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class SimulationRunner {

    private final Warehouse warehouse;
    private final PathFinder pathFinder;

    public SimulationRunner(Warehouse warehouse) {
        this.warehouse = warehouse;
        this.pathFinder = new PathFinder();
    }

    public SimulationResult run(List<List<Item>> orders) {
        Slot dock = warehouse.getSlot(0, 0);
        double totalDistance = 0;

        for (List<Item> orderedItems : orders) {
            // Needing 2 units of the same SKU is one physical stop, not two.
            Set<Slot> uniqueStops = new LinkedHashSet<>();
            for (Item item : orderedItems) {
                Slot slot = warehouse.findSlotForItem(item);
                if (slot != null) uniqueStops.add(slot);
            }
            Route route = pathFinder.findPath(dock, new ArrayList<>(uniqueStops));
            totalDistance += route.getTotalDistance();
        }

        double averageDistance = totalDistance / orders.size();
        return new SimulationResult(orders.size(), totalDistance, averageDistance);
    }
}