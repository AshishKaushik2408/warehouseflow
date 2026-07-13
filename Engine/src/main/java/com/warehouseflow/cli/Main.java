package com.warehouseflow.cli;

import com.warehouseflow.engine.PathFinder;
import com.warehouseflow.engine.Route;
import com.warehouseflow.engine.Slotter;
import com.warehouseflow.model.Item;
import com.warehouseflow.model.Slot;
import com.warehouseflow.model.Warehouse;
import com.warehouseflow.sim.OrderGenerator;
import com.warehouseflow.sim.SimulationResult;
import com.warehouseflow.sim.SimulationRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Warehouse warehouse = new Warehouse(5, 5);
        warehouse.printGrid();

        List<Item> catalog = buildCatalog();
        OrderGenerator orderGenerator = new OrderGenerator(catalog);
        List<List<Item>> orders = orderGenerator.generateOrders(200, 4);

        Slotter slotter = new Slotter();
        SimulationRunner runner = new SimulationRunner(warehouse);

        slotter.randomSlotting(warehouse, catalog);
        SimulationResult before = runner.run(orders);

        slotter.optimizeSlotting(warehouse, catalog);
        SimulationResult after = runner.run(orders);

        double improvementPct = 100.0 * (before.getTotalDistance() - after.getTotalDistance())
                / before.getTotalDistance();

        System.out.println("\n--- Slotting comparison (200 simulated orders) ---");
        System.out.println("Before (random):    " + before);
        System.out.println("After (optimized):  " + after);
        System.out.printf("Improvement: %.1f%%%n", improvementPct);
    }

    private static List<Item> buildCatalog() {
        List<Item> catalog = new ArrayList<>();
        Random random = new Random();
        for (int i = 1; i <= 24; i++) {
            catalog.add(new Item("SKU-" + i, "Item " + i, 1.0, random.nextInt(100) + 1));
        }
        return catalog;
    }
}