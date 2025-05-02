import java.util.*;

public class Dijkstra {

    public static int shortestPath(Map<Integer, List<Integer>> graph, int start, int end) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        Set<Integer> visited = new HashSet<>();
        Map<Integer, Integer> distances = new HashMap<>();

        for (Integer node : graph.keySet()) {
            distances.put(node, Integer.MAX_VALUE);
        }
        distances.put(start, 0);
        pq.offer(new int[]{0, start});

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int currentDistance = current[0];
            int currentNode = current[1];

            if (visited.contains(currentNode)) continue;
            visited.add(currentNode);

            if (currentNode == end) return currentDistance;

            for (int neighbor : graph.getOrDefault(currentNode, new ArrayList<>())) {
                if (!visited.contains(neighbor)) {
                    int newDist = currentDistance + 1;
                    if (newDist < distances.get(neighbor)) {
                        distances.put(neighbor, newDist);
                        pq.offer(new int[]{newDist, neighbor});
                    }
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        graph.put(1, Arrays.asList(2, 3, 5));
        graph.put(2, Collections.singletonList(4));
        graph.put(3, Collections.singletonList(5));
        graph.put(4, Collections.singletonList(3));
        graph.put(5, new ArrayList<>());

        System.out.println(shortestPath(graph, 1, 2)); // 1
        System.out.println(shortestPath(graph, 1, 5)); // 1
        System.out.println(shortestPath(graph, 2, 5)); // 3
        System.out.println(shortestPath(graph, 5, 1)); // -1
        System.out.println(shortestPath(graph, 2, 1)); // -1
    }
}
