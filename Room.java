import java.util.ArrayList;

public class Room extends ThingHolder {
    private Room up, down, north, east, south, west;
    private ArrayList<Item> items;
    private ArrayList<Zombie> zombies;

    public Room(String aName, String aDescription, int aCapacity, ArrayList<Item> items) {
        super(aName, aDescription, aCapacity);
        this.items = items;
        this.zombies = new ArrayList<>();
    }

    public Room getUp() {
        return up;
    }

    public void setUp(Room up) {
        this.up = up;
    }

    public Room getDown() {
        return down;
    }

    public void setDown(Room down) {
        this.down = down;
    }

    public Room getNorth() {
        return north;
    }

    public void setNorth(Room north) {
        this.north = north;
    }

    public Room getEast() {
        return east;
    }

    public void setEast(Room east) {
        this.east = east;
    }

    public Room getSouth() {
        return south;
    }

    public void setSouth(Room south) {
        this.south = south;
    }

    public Room getWest() {
        return west;
    }

    public void setWest(Room west) {
        this.west = west;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void addItem(Item item) {
        if (checkCapacity(item)) {
            items.add(item);
            addToInventory(item);
        } else {
            System.out.println("Not enough capacity to add " + item.getName());
        }
    }

    public void removeItem(Item item) {
        if (items.contains(item)) {
            items.remove(item);
            removeFromInventory(item);
        } else {
            System.out.println("Item not found in room: " + item.getName());
        }
    }

    public ArrayList<Zombie> getZombies() {
        return zombies;
    }

    public void addZombie(Zombie zombie) {
        zombies.add(zombie);
    }

    public void removeZombie(Zombie zombie) {
        if (zombies.contains(zombie)) {
            zombies.remove(zombie);
        } else {
            System.out.println("Zombie not found in room: " + zombie.getName());
        }
    }
}
