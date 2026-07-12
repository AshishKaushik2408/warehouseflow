package com.warehouseflow.engine;

import com.warehouseflow.model.Slot;
import java.util.ArrayList;
import java.util.List;

public class PathFinder {

    public Route findPath(Slot start, List<Slot> stops) {
        List<Slot> unvisited = new ArrayList<>(stops);
        List<Slot> orderedRoute = new ArrayList<>();
        Slot current = start;
        double totalDistance = 0;

        while (!unvisited.isEmpty()) {
            Slot nearest = null;
            int nearestDistance = Integer.MAX_VALUE;

            for (Slot candidate : unvisited) {
                int d = GridUtils.distance(current, candidate);
                if (d < nearestDistance) {
                    nearest = candidate;
                    nearestDistance = d;
                }
            }

            orderedRoute.add(nearest);
            unvisited.remove(nearest);
            totalDistance += nearestDistance;
            current = nearest;
        }

        return new Route(orderedRoute, totalDistance);
    }
}