import java.util.*;
public class CommandParser {
    private static final Map<String, String> commandVariations = new HashMap<>();

    static {
        commandVariations.put("take", "take");
        commandVariations.put("grab", "take");
        commandVariations.put("pick", "take");
        commandVariations.put("store", "take");
        commandVariations.put("collect", "take");
        commandVariations.put("get", "take");
        commandVariations.put("seize", "take");
        commandVariations.put("drop", "drop");
        commandVariations.put("discard", "drop");
        commandVariations.put("leave", "drop");
        commandVariations.put("release", "drop");
        commandVariations.put("unload", "drop");
        commandVariations.put("move", "go");
        commandVariations.put("go", "go");
        commandVariations.put("walk", "go");
        commandVariations.put("run", "go");
        commandVariations.put("proceed", "go");
        commandVariations.put("advance", "go");
        commandVariations.put("step", "go");
        commandVariations.put("north", "north");
        commandVariations.put("forward", "north");
        commandVariations.put("upward", "north");
        commandVariations.put("ahead", "north");
        commandVariations.put("south", "south");
        commandVariations.put("backward", "south");
        commandVariations.put("downward", "south");
        commandVariations.put("back", "south");
        commandVariations.put("east", "east");
        commandVariations.put("right", "east");
        commandVariations.put("west", "west");
        commandVariations.put("left", "west");
        commandVariations.put("up", "up");
        commandVariations.put("ascend", "up");
        commandVariations.put("climb", "up");
        commandVariations.put("down", "down");
        commandVariations.put("descend", "down");
        commandVariations.put("look", "look");
        commandVariations.put("examine", "look");
        commandVariations.put("inspect", "look");
        commandVariations.put("view", "look");
        commandVariations.put("observe", "look");
        commandVariations.put("scan", "look");
        commandVariations.put("eat", "consume");
        commandVariations.put("inventory", "inventory");
        commandVariations.put("items", "inventory");
        commandVariations.put("possessions", "inventory");
        commandVariations.put("belongings", "inventory");
        commandVariations.put("gear", "inventory");
        commandVariations.put("help", "help");
        commandVariations.put("assist", "help");
        commandVariations.put("aid", "help");
        commandVariations.put("info", "help");
        commandVariations.put("information", "help");
        commandVariations.put("quit", "quit");
         commandVariations.put("give up", "quit");
        commandVariations.put("exit", "quit");
        commandVariations.put("leave", "quit");
        commandVariations.put("end", "quit");
        commandVariations.put("terminate", "quit");
    }

    public static String normalizeCommand(String command) {
        String[] parts = command.split(" ");
        StringBuilder normalizedCommand = new StringBuilder();

        for (String part : parts) {
            if (commandVariations.containsKey(part)) {
                normalizedCommand.append(commandVariations.get(part)).append(" ");
            } else {
                normalizedCommand.append(part).append(" ");
            }
        }

        String result = normalizedCommand.toString().trim();

        if (result.equals("north") || result.equals("south") || result.equals("east") || 
            result.equals("west") || result.equals("up") || result.equals("down")) {
            result = "go " + result;
        }

        return result;
    }
}
