package com.warehouseflow.model;

import java.time.LocalDateTime;
import java.util.List;

public class Order {
    public enum Priority { STANDARD, RUSH }

    private final int orderId;
    private final Priority priority;
    private final List<OrderLine> lines;
    private final LocalDateTime createdAt;

    public Order(int orderId, Priority priority, List<OrderLine> lines, LocalDateTime createdAt) {
        this.orderId = orderId;
        this.priority = priority;
        this.lines = lines;
        this.createdAt = createdAt;
    }

    public int getOrderId() { return orderId; }
    public Priority getPriority() { return priority; }
    public List<OrderLine> getLines() { return lines; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}