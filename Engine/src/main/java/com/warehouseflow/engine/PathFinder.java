package com.warehouseflow.engine;

import com.warehouseflow.model.Slot;

import java.util.ArrayList;
import java.util.List;

public class PathFinder {

    public Route findPath(Slot start, List<Slot> stops) {
        Route nearestNeighborRoute = nearestNeighbor(start, stops);
        return twoOptImprove(start, nearestNeighborRoute);
    }

    private Route nearestNeighbor(Slot start, List<Slot> stops) {
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

    // Nearest-neighbor is fast but can leave obvious inefficient crossings in
    // the route. 2-opt fixes this: try reversing every possible sub-segment
    // of the route, and keep the reversal only if it actually shortens the
    // total distance. Repeat full passes until one pass makes no improvement.
    private Route twoOptImprove(Slot start, Route initial) {
        List<Slot> route = new ArrayList<>(initial.getStops());
        boolean improved = true;

        while (improved) {
            improved = false;
            for (int i = 0; i < route.size() - 1; i++) {
                for (int j = i + 1; j < route.size(); j++) {
                    double before = routeDistance(start, route);
                    reverseSegment(route, i, j);
                    double after = routeDistance(start, route);

                    if (after < before) {
                        improved = true; // shorter — keep the reversal
                    } else {
                        reverseSegment(route, i, j); // no better — undo it
                    }
                }
            }
        }

        return new Route(route, routeDistance(start, route));
    }

    private void reverseSegment(List<Slot> route, int i, int j) {
        while (i < j) {
            Slot temp = route.get(i);
            route.set(i, route.get(j));
            route.set(j, temp);
            i++;
            j--;
        }
    }

    private double routeDistance(Slot start, List<Slot> route) {
        double total = 0;
        Slot current = start;
        for (Slot next : route) {
            total += GridUtils.distance(current, next);
            current = next;
        }
        return total;
    }
}