package com.warehouseflow.sim;

public class SimulationResult {
    private final int numberOfOrders;
    private final double totalDistance;
    private final double averageDistance;

    public SimulationResult(int numberOfOrders, double totalDistance, double averageDistance) {
        this.numberOfOrders = numberOfOrders;
        this.totalDistance = totalDistance;
        this.averageDistance = averageDistance;
    }

    public int getNumberOfOrders() { return numberOfOrders; }
    public double getTotalDistance() { return totalDistance; }
    public double getAverageDistance() { return averageDistance; }

    @Override
    public String toString() {
        return String.format("Simulated %d orders — total distance: %.1f, average per order: %.2f",
                numberOfOrders, totalDistance, averageDistance);
    }
}
