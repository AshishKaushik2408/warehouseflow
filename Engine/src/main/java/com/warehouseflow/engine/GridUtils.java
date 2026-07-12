package com.warehouseflow.engine;

import com.warehouseflow.model.Slot;

public class GridUtils {
    // Manhattan distance — workers walk aisles, not diagonally, so this is more
    // realistic than straight-line distance for a warehouse.
    public static int distance(Slot a, Slot b) {
        return Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY());
    }
}