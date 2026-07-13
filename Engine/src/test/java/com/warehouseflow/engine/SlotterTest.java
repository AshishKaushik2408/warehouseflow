package com.warehouseflow.engine;

import com.warehouseflow.model.Item;
import com.warehouseflow.model.Slot;
import com.warehouseflow.model.Warehouse;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class SlotterTest {

    @Test
    void optimizedSlottingPutsHighVelocityItemsCloserToDock() {
        Warehouse warehouse = new Warehouse(5, 5);
        Slot dock = warehouse.getSlot(0, 0);

        Item fastMover = new Item("SKU-FAST", "Fast Mover", 1.0, 100);
        Item slowMover = new Item("SKU-SLOW", "Slow Mover", 1.0, 1);

        Slotter slotter = new Slotter();
        slotter.optimizeSlotting(warehouse, List.of(fastMover, slowMover));

        int fastMoverDistance = GridUtils.distance(dock, warehouse.findSlotForItem(fastMover));
        int slowMoverDistance = GridUtils.distance(dock, warehouse.findSlotForItem(slowMover));

        assertTrue(fastMoverDistance <= slowMoverDistance,
                "High-velocity item should end up at least as close to the dock as the low-velocity item");
    }
}