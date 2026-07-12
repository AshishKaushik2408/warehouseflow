
    package com.warehouseflow.model;

    public class Slot {
        private final int id;
        private final int x;
        private final int y;
        private final int capacity;
        private Item currentItem; // null when empty

        public Slot(int id, int x, int y, int capacity) {
            this.id = id;
            this.x = x;
            this.y = y;
            this.capacity = capacity;
            this.currentItem = null;
        }

        public int getId() { return id; }
        public int getX() { return x; }
        public int getY() { return y; }
        public int getCapacity() { return capacity; }
        public Item getCurrentItem() { return currentItem; }
        public void setCurrentItem(Item item) { this.currentItem = item; }
        public boolean isEmpty() { return currentItem == null; }
    }

