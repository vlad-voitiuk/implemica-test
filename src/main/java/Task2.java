import java.util.*;
import java.io.*;

public class Task2 {
    public void start() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of test cases: ");
        int testCases = Integer.parseInt(scanner.nextLine());

        while (testCases-- > 0) {
            System.out.print("Enter number of cities: ");
            int numberOfCities = Integer.parseInt(scanner.nextLine());

            Map<String, Integer> cityIndexMap = new HashMap<>();
            //Storing each city member and their corresponding costs
            List<List<int[]>> graph = new ArrayList<>(numberOfCities);

            for (int i = 0; i < numberOfCities; i++) {
                System.out.print("Enter city name: ");
                String cityName = scanner.nextLine();
                if (cityName.length() > 10) {
                    System.out.println("Error: City name exceeds the maximum allowed length of 10 characters.");
                    return;
                }
                cityIndexMap.put(cityName, i);
                graph.add(new ArrayList<>());

                System.out.print("Enter neighbors: ");
                int neighbors = Integer.parseInt(scanner.nextLine());
                for (int j = 0; j < neighbors; j++) {
                    System.out.print("Enter neighbor index and cost: ");
                    String[] indexAndCost = scanner.nextLine().split(" ");
                    int neighborIndex = Integer.parseInt(indexAndCost[0]) - 1; // Convert to 0-based index
                    int cost = Integer.parseInt(indexAndCost[1]);
                    graph.get(i).add(new int[]{neighborIndex, cost});
                }
            }

            System.out.print("Enter number of paths to find: ");
            int paths = Integer.parseInt(scanner.nextLine());
            while (paths-- > 0) {
                System.out.print("Enter source city and destination: ");
                String[] query = scanner.nextLine().split(" ");
                String sourceCity = query[0];
                String destinationCity = query[1];

                // Get the indices of the source and destination cities
                int source = cityIndexMap.get(sourceCity);
                int destination = cityIndexMap.get(destinationCity);

                // Find the minimum cost using Dijkstra's algorithm
                int result = dijkstra(graph, source, destination);

                System.out.println(result);
            }

            if (scanner.hasNextLine()) {
                scanner.nextLine(); // Consume the empty line between test cases
            }
        }

        scanner.close();
    }

    private int dijkstra(List<List<int[]>> graph, int source, int destination) {
        // Get the number of nodes (cities) in the graph
        int n = graph.size();

        // Create an array to store the minimum distance from the source to each city
        int[] dist = new int[n];

        // Initialize all distances to a very large number (Infinity)
        Arrays.fill(dist, Integer.MAX_VALUE);

        // The distance to the source city is always 0 (because it's the starting point)
        dist[source] = 0;

        // Priority queue (min-heap) to explore cities with the smallest known distance
        // The queue stores pairs of the form {cityIndex, currentDistance}
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));

        // Add the source city to the priority queue with a distance of 0
        pq.offer(new int[]{source, 0});

        // Loop while there are cities in the priority queue to process
        while (!pq.isEmpty()) {
            // Extract the city with the smallest known distance from the queue
            int[] current = pq.poll();
            int node = current[0];   // The current city index
            int cost = current[1];   // The current known distance to this city

            // If we have reached the destination city return the cost
            if (node == destination) {
                return cost; // Found the shortest path to the destination
            }

            // For each neighboring city of the current city process the edge
            for (int[] neighbor : graph.get(node)) {
                int nextNode = neighbor[0];   // Index of the neighboring city
                int edgeCost = neighbor[1];   // Transportation cost to this neighbor

                // Calculate the new potential distance to this neighboring city
                int newDistance = dist[node] + edgeCost;

                // If the new distance exceeds the specified limit of 200,000 skip this neighbor
                if (newDistance > 200000) {
                    continue; // Skip this neighbor as the cost exceeds the limit
                }

                // If we found a shorter path to the neighboring city update its distance
                // and add it to the priority queue to be processed next
                if (newDistance < dist[nextNode]) {
                    dist[nextNode] = newDistance;
                    pq.offer(new int[]{nextNode, newDistance});  // Add the neighbor to the queue
                }
            }
        }

        // If there's no path from source to destination (for example destination is unreachable)
        return -1; // Return -1 to indicate no path exists
    }

}
