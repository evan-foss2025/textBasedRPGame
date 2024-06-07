import java.util.ArrayList;
import java.util.Comparator;

public class Player extends ThingHolder {
    private Room location;
    private boolean isAlive;
    private int health;
    private int hunger;
    private ArrayList<Item> inventory;

    public Player(String aName, String aDescription, int aCapacity, Room aLocation) {
        super(aName, aDescription, aCapacity);
        location = aLocation;
        isAlive = true;
        health = 100;
        hunger = 20;
        inventory = new ArrayList<>();
    }

    public void setLocation(Room newRoom) {
        location = newRoom;
    }

    public Room getLocation() {
        return location;
    }

    public boolean getIsAlive() {
        return isAlive;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
        if (this.health <= 0) {
            isAlive = false;
            System.out.println("You have died.");
        }
    }

    public void takeDamage(int damage) {
        setHealth(health - damage);
        System.out.println("Health is now: " + health);
    }

    public void heal(int amount) {
        setHealth(health + amount);
        System.out.println("You healed " + amount + " points. Health is now: " + health);
    }

    public void takeItem(Item item) {
        if (location.getItems().contains(item)) {
            if (checkCapacity(item)) {
                location.removeItem(item);
                addToInventory(item);
                System.out.println("You have taken " + item.getName());
            } else {
                System.out.println("You can't carry that much weight.");
            }
        } else {
            System.out.println("Item not found in this room.");
        }
    }

    public void dropItem(Item item) {
        if (getInventory().contains(item)) {
            removeFromInventory(item);
            location.addItem(item);
            System.out.println("You have dropped " + item.getName());
        } else {
            System.out.println("You don't have that item.");
        }
    }

    public int getHunger() {
        return hunger;
    }

    public void setHunger(int hunger) {
        this.hunger = hunger;
        if (this.hunger <= 0) {
            setHealth(0);
            System.out.println("You starved!");
        }
    }

    public void decreaseHunger() {
        setHunger(hunger - 1);
        System.out.println("Your hunger level is now: " + hunger);
    }

    public void increaseHunger(int amount) {
        setHunger(hunger + amount);
        heal(amount / 2);
    }

    public void move(String direction) {
        Room newLocation = null;
        switch (direction.toLowerCase()) {
            case "up":
                newLocation = location.getUp();
                break;
            case "down":
                newLocation = location.getDown();
                break;
            case "north":
                newLocation = location.getNorth();
                break;
            case "east":
                newLocation = location.getEast();
                break;
            case "south":
                newLocation = location.getSouth();
                break;
            case "west":
                newLocation = location.getWest();
                break;
            default:
                System.out.println("Invalid direction.");
                break;
        }
        if (newLocation != null) {
            setLocation(newLocation);
            decreaseHunger(); 
            System.out.println("You moved to " + newLocation.getName());
        } else {
            System.out.println("You can't go " + direction + " from here.");
        }
    }
public void wait(int time){
 try{
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            
            }

}
    public void consumeFood(Item item) {
        if (item.getHealingAmount() > 0) {
            increaseHunger(item.getHealingAmount());
            removeFromInventory(item);
            System.out.println("You consumed " + item.getName() + " and gained " + item.getHealingAmount() + " hunger.");
        } else if (item.getHealingAmount() < 0) {
            System.out.println("You fool! Why would you try to eat an " + item.getName() + " you now have serious internal bleeding");
            wait(1000);
            System.out.println("IT HURTHS!");
            wait(1000);
            System.out.println("Argh!!!");
            wait(1000);
            System.out.println("You have large internal laceratoins!");
            wait(1000);
            removeFromInventory(item);
            takeDamage(item.getHealingAmount()*-1);
            wait(1000);
            System.out.println(".");
            wait(500);
            System.out.println("..");
            wait(500);
            System.out.println("...");
            wait(1000);
            System.out.println("Your stupidity has led you to being less than dead...");
            System.out.println("In the name of all that exists, HOW IS THAT POSSIBLE?!?!?!?!");

        } else {
            System.out.println("This item is not food.");
        }
    }

    public void attackWith(String targetName, String weaponName) {
        Item weapon = findItemByNameInInventory(weaponName);
        if (weapon == null) {
            System.out.println("You don't have a " + weaponName);
            return;
        }

        Zombie zombie = findZombieByName(location, targetName);
        if (zombie == null) {
            System.out.println("There is no " + targetName + " here.");
            return;
        }

        if (weapon.getUses() <= 0) {
            System.out.println("Your " + weapon.getName() + " is out of uses.");
            return;
        }

        zombie.takeDamage(weapon.getDamage());
        weapon.useItem();

        if (zombie.getHealth() <= 0) {
            location.removeZombie(zombie);
        } else {
            zombie.attack(this);
        }
    }

    private Item findItemByNameInInventory(String itemName) {
        String normalizedItemName = itemName.trim().toLowerCase();

        for (Item item : getInventory()) {
            String normalizedCurrentItemName = item.getName().toLowerCase();
            if (normalizedCurrentItemName.contains(normalizedItemName)) {
                return item;
            }
        }
        return null;
    }

    private Zombie findZombieByName(Room room, String zombieName) {
        String normalizedZombieName = zombieName.trim().toLowerCase();

        for (Zombie zombie : room.getZombies()) {
            String normalizedCurrentZombieName = zombie.getName().toLowerCase();
            if (normalizedCurrentZombieName.contains(normalizedZombieName)) {
                return zombie;
            }
        }
        return null;
    }

    public void takeAllItems() {
        ArrayList<Item> items = new ArrayList<>(location.getItems());
        items.sort(Comparator.comparingInt(Item::getWeight));

        ArrayList<Item> takenItems = new ArrayList<>();
        ArrayList<Item> leftItems = new ArrayList<>();

        for (Item item : items) {
            if (checkCapacity(item)) {
                location.removeItem(item);
                addToInventory(item);
                takenItems.add(item);
            } else {
                leftItems.add(item);
            }
        }

        System.out.println("You have taken the following items:");
        for (Item item : takenItems) {
            System.out.println("- " + item.getName());
        }

        if (!leftItems.isEmpty()) {
            System.out.println("The following items were too heavy to take:");
            for (Item item : leftItems) {
                System.out.println("- " + item.getName());
            }
        }
    }
}
