package utils;

import java.util.HashMap;

public class DataStore<T> {
    private final HashMap<Integer, T> data;
    private final String modelLabel;

    public DataStore(String modelLabel) {
        this.data = new HashMap<>();
        this.modelLabel = modelLabel;
    }

    public void create(int id, T item) {
        data.put(id, item);
    }

    public T read(int id) {
        return data.get(id);
    }

    public java.util.Map<Integer, T> readAll() {
        return new HashMap<>(data);
    }

    public void update(int id, T item) {
        if (data.containsKey(id)) {
            data.put(id, item);
        } else {
            System.out.println("Item with ID " + id + " not found.");
        }
    }

    public T delete(int id) {
        if(data.containsKey(id)) {
            return data.remove(id);
        } else {
            System.out.println("Item with ID " + id + " not found.");
            return null;
        }
    }

    public void printAll() {
//        data.values().forEach(System.out::println);
        var allTs = readAll();
        if (allTs.isEmpty()) {
            System.out.println("No Ts in library.");
            return;
        }
        System.out.println("\n--- All " + modelLabel + "s ---");
        allTs.values().forEach(System.out::println);
    }
}
