import java.util.ArrayList;

public class ThingHolder {
    private String name;
    private String description;
    private int capacity;
    private int currentLoad;
    private ArrayList<Item> inventory;

    public ThingHolder(String name, String description, int capacity) {
        this.name = name;
        this.description = description;
        this.capacity = capacity;
        this.currentLoad = 0;
        this.inventory = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getCurrentLoad() {
        return currentLoad;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public void addToInventory(Item item) {
        if (checkCapacity(item)) {
            inventory.add(item);
            currentLoad += item.getWeight();
        } else {
            System.out.println("Cannot add " + item.getName() + " to inventory. Not enough capacity.");
        }
    }
public void displayInventory() {
        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty.");
        } else {
            System.out.println("Inventory:");
            for (Item item : inventory) {
                System.out.println("- " + item.getName() + ": " + item.getDescription() + " (Weight: " + item.getWeight() + ")");
            }
        }
    }
    public void removeFromInventory(Item item) {
        if (inventory.contains(item)) {
            inventory.remove(item);
            currentLoad -= item.getWeight();
        } else {
        }
    }

    public boolean checkCapacity(Item item) {
        return currentLoad + item.getWeight() <= capacity;
    }
}
