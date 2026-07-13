package com.warehouseflow.model;

public class Warehouse {
    private final Slot[][] grid;
    private final int width;
    private final int height;

    public Warehouse(int width, int height) {
        this.width = width;
        this.height = height;
        this.grid = new Slot[width][height];
        int id = 0;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                grid[x][y] = new Slot(id++, x, y, 10); // capacity 10 for now

            }


        }
    }

    public Slot getSlot(int x, int y) {
        return grid[x][y];
    }
    public Slot findSlotForItem(Item item) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (grid[x][y].getCurrentItem() == item) {
                    return grid[x][y];
                }
            }
        }
        return null;
    }

    public int getWidth() { return width; }
    public int getHeight() { return height; }

    public void printGrid() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                System.out.print(grid[x][y].isEmpty() ? ". " : "# ");
            }
            System.out.println();
        }
    }
}