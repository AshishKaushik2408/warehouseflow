package com.warehouseflow.model;

public class Item {
    private final String sku;
    private final String name;
    private final double weight;
    private int velocityScore;

    public Item(String sku, String name, double weight, int velocityScore) {
        this.sku = sku;
        this.name = name;
        this.weight = weight;
        this.velocityScore = velocityScore;
    }

    public String getSku() { return sku; }
    public String getName() { return name; }
    public double getWeight() { return weight; }
    public int getVelocityScore() { return velocityScore; }
    public void setVelocityScore(int velocityScore) { this.velocityScore = velocityScore; }
}