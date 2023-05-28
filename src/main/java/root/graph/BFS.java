package root.graph;

import java.util.*;

/*
 * Task:
 *
 * Given graph:
 *
 *         +----+(C)---+(E)
 *         |             |
 *         |             +
 * (A)---+(B)---+(D)---+(F)---+(G)
 *         |             +
 *         |             |
 *         +-------------+
 *
 * Find shortest path from (A) to (G)
 *
 */
public class BFS {

    public static void main(String[] args) {
        Map<String, List<String>> graph = new HashMap<String, List<String>>() {{
            put("A", Arrays.asList("B"));
            put("B", Arrays.asList("C", "D", "F"));
            put("C", Arrays.asList("E"));
            put("D", Arrays.asList("F"));
            put("E", Arrays.asList("F"));
            put("F", Arrays.asList("G"));
        }};
        List<String> shortestPath = findShortestPath("A", "G", graph);
        System.out.println("The shortest path from A to G is " + shortestPath);
    }

    private static List<String> findShortestPath(String startPoint,
                                                 String targetPoint,
                                                 Map<String, List<String>> graph) {
        Queue<LinkedList<String>> queue = new LinkedList<>();
        queue.offer(list(startPoint));
        while (!queue.isEmpty()) {
            LinkedList<String> pathToCurrentPoint = queue.poll();
            String currentPoint = pathToCurrentPoint.getLast();
            if (currentPoint == targetPoint) {
                return pathToCurrentPoint;
            }
            for (String connectedPoint : graph.get(currentPoint)) {
                LinkedList<String> pathToConnectedPoint = new LinkedList<>(pathToCurrentPoint);
                pathToConnectedPoint.add(connectedPoint);
                queue.offer(pathToConnectedPoint);
            }
        }
        throw new RuntimeException("There is no path from " + startPoint + " to " + targetPoint);
    }

    private static LinkedList<String> list(String... items) {
        LinkedList<String> list = new LinkedList<>();
        for (String item : items) {
            list.add(item);
        }
        return list;
    }
}
