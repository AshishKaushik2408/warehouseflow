package com.warehouseflow.engine;

import com.warehouseflow.model.Slot;
import com.warehouseflow.model.Warehouse;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PathFinderTest {

    @Test
    void nearestNeighborVisitsClosestStopFirst() {
        Warehouse warehouse = new Warehouse(5, 5);
        Slot start = warehouse.getSlot(0, 0);
        Slot near = warehouse.getSlot(1, 0);
        Slot far = warehouse.getSlot(4, 4);

        PathFinder finder = new PathFinder();
        Route route = finder.findPath(start, List.of(far, near));

        assertEquals(near, route.getStops().get(0),
                "Nearest-neighbor should visit the closer stop first");
    }
}