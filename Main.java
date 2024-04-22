public class Main {
    public static void main(String[] args) {
        ArrayMap<String, Integer> arrayMap = new ArrayMap<>();

        // Put some values in the map
        arrayMap.put("One", 1);
        arrayMap.put("Two", 2);
        arrayMap.put("Three", 3);
        arrayMap.put("Four", 4);
        arrayMap.put("Five", 5);
        arrayMap.put("Six", 6);

        arrayMap.put("Three", 3);
        arrayMap.put("Three", 3);

        // Get and print the values
        System.out.println(arrayMap.get("One")); // Output: 1
        System.out.println(arrayMap.get("Two")); // Output: 2
        System.out.println(arrayMap.get("Three")); // Output: 3

        // Print the size of the map
        System.out.println("Size of map: " + arrayMap.size()); // Output: 3

        // Remove a value and print the size again
        arrayMap.remove("Two");
        System.out.println("Size of map after removal: " + arrayMap.size()); // Output: 2
    }
}