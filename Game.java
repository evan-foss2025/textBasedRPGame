import java.util.*;

public class Game {
    public static HashMap<String, Room> rooms;
    private Player player;

    public Game() {
        System.out.println("Welcome to Mall Explorer! APCSA Final Project!");
        System.out.println("Type 'help' for a list of commands.");
        rooms = new HashMap<>();
        createRooms();
        player = new Player("Player", "The game player", 3000, rooms.get("road"));
        printRoomDetails(player.getLocation());
    }
 private void createRooms() {
     // Format: new Item("Name", "Description", weight, uses, damage, healingAmount);
        Item scrapMetal = new Item("Scrap Metal", "Some old scrap metal.", 5, 1, 0, 0);
        Item glassBottle = new Item("Glass Bottle", "An empty glass bottle.", 2, 1, 0, 0);
        Item oldTire = new Item("Old Tire", "An old, worn-out tire.", 10, 1, 0, 0);
        Item abandonedCar = new Item("Abandoned Car", "A rusty old car, too heavy to move.", 600, 1, 0, -1000);
        Item newspaper = new Item("Old Newspaper", "Some old newspapers.", 1, 1, 0, 0);
        Item toiletPaper = new Item("Toilet Paper", "A roll of toilet paper.", 1, 1, 0, 0);
        Item wrench = new Item("Wrench", "A heavy wrench.", 2, 10, 5, 0);
        Item screwdriver = new Item("Screwdriver", "A useful screwdriver.", 2, 10, 3, 0);
        Item flashlight = new Item("Flashlight", "A small flashlight.", 1, 10, 0, 0);
        Item knife = new Item("Knife", "A sharp knife.", 1, 10, 15, 0);
        Item gasCan = new Item("Gas Can", "A can of gasoline.", 10, 1, 0, 0);
        Item cannedTuna = new Item("Canned Tuna", "A can of tuna.", 1, 1, 0, 20);
        Item brokenGlassShards = new Item("Broken Glass Shards", "Sharp shards of broken glass.", 1, 5, 5, 0);
        Item gun = new Item("Old Handgun", "A rusty old handgun", 15, 12, 100, 0);

        // Format: ("Name", "Desc", Health, Damage)
        Zombie zombie1 = new Zombie("Zombie", "A terrifying zombie.", 50, 10);
        Zombie zombie2 = new Zombie("Zombie", "A scary zombie.", 50, 10);

        // Format: ("Name", "Desc", MaxNumOfItems, ArrayOfItems)
        Room road = new Room("Road", "A road with an abandoned car.", 50, new ArrayList<>(Arrays.asList(abandonedCar)));
        Room parkingLot = new Room("Parking Lot", "A deserted parking lot with some scrap items.", 50, new ArrayList<>(Arrays.asList(scrapMetal, glassBottle, oldTire)));
        Room mallEntrance = new Room("Mall Entrance", "The entrance to the mall.", 50, new ArrayList<>(Arrays.asList(newspaper)));
        Room toolStore = new Room("Tool Store", "An abandoned tool store.", 50, new ArrayList<>(Arrays.asList(wrench, screwdriver, flashlight)));
        Room balcony = new Room("Balcony", "A balcony overlooking the mall entrance.", 50, new ArrayList<>(Arrays.asList(knife)));
        Room gasStation = new Room("Gas Station", "A gas station with some useful items.", 50, new ArrayList<>(Arrays.asList(gasCan, cannedTuna, brokenGlassShards, gun)));
        Room bathroom = new Room("Bathroom", "A run down mall bathroom", 50, new ArrayList<>(Arrays.asList(toiletPaper, brokenGlassShards)));

        road.setNorth(parkingLot);
        road.setEast(gasStation);
        parkingLot.setSouth(road);
        parkingLot.setNorth(mallEntrance);
        mallEntrance.setSouth(parkingLot);
        mallEntrance.setWest(toolStore);
        mallEntrance.setEast(bathroom); 
        mallEntrance.setUp(balcony);
        toolStore.setEast(mallEntrance);
        bathroom.setWest(mallEntrance);
        balcony.setDown(mallEntrance);
        gasStation.setWest(road);

        parkingLot.addZombie(zombie1);
        mallEntrance.addZombie(zombie2);

        rooms.put("road", road);
        rooms.put("parkingLot", parkingLot);
        rooms.put("mallEntrance", mallEntrance);
        rooms.put("toolStore", toolStore);
        rooms.put("balcony", balcony);
        rooms.put("gasStation", gasStation);
    }
    private void printRoomDetails(Room room) {
        System.out.println(room.getDescription());
        System.out.println("Items in this room:");
        for (Item item : room.getItems()) {
            System.out.println("- " + item.getName() + ": " + item.getDescription() + " (Weight: " + item.getWeight() + ")");
        }
        System.out.println("You can go:");
        if (room.getNorth() != null) System.out.println("- North: " + room.getNorth().getName());
        if (room.getSouth() != null) System.out.println("- South: " + room.getSouth().getName());
        if (room.getEast() != null) System.out.println("- East: " + room.getEast().getName());
        if (room.getWest() != null) System.out.println("- West: " + room.getWest().getName());
        if (room.getUp() != null) System.out.println("- Up: " + room.getUp().getName());
        if (room.getDown() != null) System.out.println("- Down: " + room.getDown().getName());
        if (room.getZombies().size() > 0) {
            System.out.println("Zombies in this room:");
            for (Zombie zombie : room.getZombies()) {
                System.out.println("- " + zombie.getName() + ": " + zombie.getDescription() + " (Health: " + zombie.getHealth() + ", Damage: " + zombie.getDamage() + ")");
            }
        }
    }
public void wait(int time){
 try{
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            
            }
        }
    public void start() {
        Scanner scanner = new Scanner(System.in);
        String command;

        while (true) {
            System.out.print("\n> ");
            command = scanner.nextLine().trim().toLowerCase();

            command = CommandParser.normalizeCommand(command);

            if (command.equals("quit")) {
                System.out.println("Thanks for playing!");
                break;
            } else if (command.equals("help")) {
                printHelp();
                 } else if (command.equals("kill yourself")) {
            System.out.println("Why...?");
            wait(1000);
            System.out.println("You have died by your own hand");
            wait(1000);
            System.out.println("That's almost as shameful as trying to eat a rusty METAL car!");
            wait(1000);
            System.out.println("People like you make me loose faith in humanity.");
            break;
            } else if (command.startsWith("go ") || command.startsWith("move ")) {
                String direction = command.substring(command.indexOf(" ") + 1);
                player.move(direction);
                printRoomDetails(player.getLocation());
            } else if (command.startsWith("take all")) {
                player.takeAllItems();
            } else if (command.startsWith("take ")) {
                String itemName = command.substring(5);
                Item item = findItemByName(player.getLocation(), itemName);
                if (item != null) {
                    player.takeItem(item);
                } else {
                    System.out.println("Item not found in this room.");
                }
            } else if (command.startsWith("drop ")) {
                String itemName = command.substring(5);
                Item item = findItemByNameInInventory(player, itemName);
                if (item != null) {
                    player.dropItem(item);
                } else {
                    System.out.println("You don't have that item.");
                }
            } else if (command.equals("look") || command.equals("look around")) {
                printRoomDetails(player.getLocation());
            } else if (command.equals("inventory")) {
                player.displayInventory();
                System.out.println("Your health: " + player.getHealth());
                System.out.println("Your hunger: " + player.getHunger());
            } else if (command.startsWith("consume ")) {
                String itemName = command.substring(8);
                Item item = findItemByNameInInventory(player, itemName);
                if (item != null) {
                    player.consumeFood(item);
                } else {
                    System.out.println("You don't have that item.");
                }
            } else if (command.startsWith("attack ")) {
                if (command.contains(" with ")) {
                    String[] parts = command.split(" with ");
                    if (parts.length == 2) {
                        String targetName = parts[0].substring(7).trim();
                        String weaponName = parts[1].trim();
                        player.attackWith(targetName, weaponName);
                    } else {
                        System.out.println("Specify the weapon to use.");
                    }
                } else {
                    System.out.println("Specify the weapon to use.");
                }
            } else {
                System.out.println("Unknown command. Type 'help' for a list of commands.");
            }

            if (!player.getIsAlive()) {
                System.out.println("Game Over!");
                break;
            }
        }
    }

    private boolean playerHasWeapon() {
        for (Item item : player.getInventory()) {
            if (item.getName().toLowerCase().contains("knife") || item.getName().toLowerCase().contains("gun") || item.getName().toLowerCase().contains("axe") || item.getName().toLowerCase().contains("glass shard")) {
                return true;
            }
        }
        return false;
    }

    private Item findItemByName(Room room, String itemName) {
        String normalizedItemName = itemName.trim().toLowerCase();
        for (Item item : room.getItems()) {
            String normalizedCurrentItemName = item.getName().toLowerCase();
            if (normalizedCurrentItemName.contains(normalizedItemName)) {
                return item;
            }
        }
        return null;
    }

    private Item findItemByNameInInventory(Player player, String itemName) {
        String normalizedItemName = itemName.trim().toLowerCase();
        for (Item item : player.getInventory()) {
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
 
    private void printHelp() {
        System.out.println("Available commands:");
        System.out.println("- go [direction]: Move in a direction (up, down, north, east, south, west)");
        System.out.println("- take [item]: Take an item from the room");
        System.out.println("- drop [item]: Drop an item in the room");
        System.out.println("- look: Look around the room");
        System.out.println("- inventory: Show your inventory and health");
        System.out.println("- consume [item]: Consume a food item to restore hunger");
        System.out.println("- attack [target] with [weapon]: Attack a target with a specific weapon");
        System.out.println("- help: Show this help message");
        System.out.println("- map: Display the map");
        System.out.println("- quit: Quit the game");
        System.out.println("- take all: Take all items from the room, starting with the smallest.");
    }

    public Player getPlayer() {
        return player;
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
}
