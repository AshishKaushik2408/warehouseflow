package com.warehouseflow.engine;

import com.warehouseflow.model.Slot;
import java.util.List;

public class Route {
    private final List<Slot> stops;
    private final double totalDistance;

    public Route(List<Slot> stops, double totalDistance) {
        this.stops = stops;
        this.totalDistance = totalDistance;
    }

    public List<Slot> getStops() { return stops; }
    public double getTotalDistance() { return totalDistance; }
}