package com.taskscheduler.storage;
import com.taskscheduler.model.Task;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;

public class FileHandler {

    // Helper method to build a Gson instance with LocalDate support
    private static Gson createGson() {
        return new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, (JsonSerializer<LocalDate>) (src, typeOfSrc, context) ->
                        new JsonPrimitive(src.toString())) // Serializes LocalDate to "YYYY-MM-DD"
                .registerTypeAdapter(LocalDate.class, (JsonDeserializer<LocalDate>) (json, typeOfT, context) ->
                        LocalDate.parse(json.getAsString())) // Deserializes "YYYY-MM-DD" back to LocalDate
                .setPrettyPrinting() // Optional: formats the JSON file nicely
                .create();
    }

    public static void saveTasksToFile(List<Task> tasks) {
        Gson gson = createGson();
        String json = gson.toJson(tasks);

        try (FileWriter writer = new FileWriter("tasks.json")) {
            writer.write(json);
            System.out.println("Successfully saved tasks to tasks.json");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public static List<Task> loadTasksFromFile() {
        File file = new File("tasks.json");
        if (!file.exists()) {
            return new ArrayList<>();
        }

        Gson gson = createGson();
        try (FileReader reader = new FileReader(file)) {
            Type taskListType = new TypeToken<List<Task>>() {}.getType();
            List<Task> loadedTasks = gson.fromJson(reader, taskListType);
            return (loadedTasks != null) ? loadedTasks : new ArrayList<>();
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}