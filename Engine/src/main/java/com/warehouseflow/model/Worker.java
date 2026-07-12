package com.warehouseflow.model;

public class Worker {
    private final int id;
    private int shiftMinutesRemaining;
    private final double avgSpeed;

    public Worker(int id, int shiftMinutesRemaining, double avgSpeed) {
        this.id = id;
        this.shiftMinutesRemaining = shiftMinutesRemaining;
        this.avgSpeed = avgSpeed;
    }

    public int getId() { return id; }
    public int getShiftMinutesRemaining() { return shiftMinutesRemaining; }
    public void setShiftMinutesRemaining(int shiftMinutesRemaining) { this.shiftMinutesRemaining = shiftMinutesRemaining; }
    public double getAvgSpeed() { return avgSpeed; }
}
