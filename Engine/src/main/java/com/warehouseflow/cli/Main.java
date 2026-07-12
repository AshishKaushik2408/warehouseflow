package com.warehouseflow.cli;

import com.warehouseflow.model.Warehouse;

public class Main {
    public static void main(String[] args) {
        Warehouse w = new Warehouse(5, 5);
        w.printGrid();
    }
}
