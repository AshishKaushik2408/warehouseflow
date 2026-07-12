package com.warehouseflow.cli;

import com.warehouseflow.model.Slot;
import com.warehouseflow.model.Warehouse;
import com.warehouseflow.engine.PathFinder;
import com.warehouseflow.engine.Route;

import java.util.List;
import com.warehouseflow.sim.SimulationRunner;
import com.warehouseflow.sim.SimulationResult;

public class Main {
    public static void main(String[] args) {
        Warehouse warehouse = new Warehouse(5, 5);
        warehouse.printGrid();

        Slot start = warehouse.getSlot(0, 0);
        List<Slot> stops = List.of(
                warehouse.getSlot(4, 4),
                warehouse.getSlot(1, 0),
                warehouse.getSlot(2, 3),
                warehouse.getSlot(0, 1)
        );

        PathFinder pathFinder = new PathFinder();
        Route route = pathFinder.findPath(start, stops);

        System.out.println("\nRoute order:");
        for (Slot slot : route.getStops()) {
            System.out.println("  -> (" + slot.getX() + ", " + slot.getY() + ")");
        }
        System.out.println("\nTotal distance: " + route.getTotalDistance());

        SimulationRunner runner = new SimulationRunner(warehouse);
        SimulationResult result = runner.run(50, 4);
        System.out.println("\n" + result);
    }
}
