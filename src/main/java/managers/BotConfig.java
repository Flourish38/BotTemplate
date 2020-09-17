package managers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Please add your own stuff to this! Sometimes, Guild IDs are particularly useful to store here.
 * If you feel the need to make this not be an interface so you can change fields/add methods, go for it!
 */
public abstract class BotConfig {
    private static Set<String> requiredKeys;
    private static Map<String, String> config;

    /**
     * Initializes the config map from config.txt. Creates new file config.txt if it does not exist.
     *
     * @return true if successful, false if it had to create the file config.txt
     */
    public static boolean init(){
        File file = new File("config.txt");
        if(!file.exists()){
            create(file);
            return false;
        }
        Scanner scanner;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        config = new HashMap<>();
        Set<String> emptyConfigValues = new HashSet<>();
        scanner.useDelimiter("\n");
        scanner.forEachRemaining((configLine) -> {
            if(!configLine.contains(":")) return;
            configLine = configLine.replaceFirst("\\s*:\\s*", ":");
            int splitIndex = configLine.indexOf(':');
            String key = configLine.substring(0, splitIndex);
            if(splitIndex >= configLine.length() - 1){
                emptyConfigValues.add(key);
            }
            String value = configLine.substring(splitIndex + 1);
            config.putIfAbsent(key, value);
        });
        boolean valid = emptyConfigValues.isEmpty();
        for(String key : emptyConfigValues){
            System.out.println('"' + key + "\" has no value in config.txt.");
        }

        FileWriter fw = null;
        for(String key : requiredKeys){
            if(!config.containsKey(key.split("\\s*:\\s*")[0])){
                valid = false;
                if(fw == null){
                    try {
                        fw = new FileWriter(file, true);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return false;
                    }
                }
                try {
                    fw.append("\n").append(key).append(key.contains(":") ? "" : ": ");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("adding config value \"" + key + '"');
            }
        }
        if(fw != null) {
            try {
                fw.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(!valid){
            System.out.println("One or more missing entries in config.txt.");
        }
        return valid;
    }

    private static void create(File file){
        FileWriter fw;
        try {
            if(!file.createNewFile()){
                // This should be impossible, since this is only ever called if file does not exist.
                System.out.println("Something is very weird. Attempting to proceed normally...");
            }
            fw = new FileWriter(file);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        boolean first = true;
        for(String key : requiredKeys){
            try {
                fw.append(first ? "" : "\n").append(key).append(key.contains(":") ? "" : ": ");
            } catch (IOException e) {
                e.printStackTrace();
            }
            first = false;
        }
        try {
            fw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String get(String key){
        return config.getOrDefault(key, null);
    }

    public static void setKeys(String[] keys) {
        requiredKeys = new HashSet<>(Arrays.asList(keys));
    }
}
