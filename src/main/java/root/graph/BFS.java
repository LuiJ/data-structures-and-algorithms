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
        Map<String, List<String>> graph = Map.of(
            "A", List.of("B"),
            "B", List.of("C", "D", "F"),
            "C", List.of("E"),
            "D", List.of("F"),
            "E", List.of("F"),
            "F", List.of("G")
        );
        List<String> shortestPath = findShortestPath("A", "G", graph);
        System.out.println("The shortest path from A to G is " + shortestPath);
    }

    private static List<String> findShortestPath(String startPoint,
                                                 String targetPoint,
                                                 Map<String, List<String>> graph) {
        Queue<LinkedList<String>> queue = new LinkedList<>();
        queue.offer(asList(startPoint));
        while (!queue.isEmpty()) {
            LinkedList<String> pathToCurrentPoint = queue.poll();
            String currentPoint = pathToCurrentPoint.getLast();
            if (currentPoint.equals(targetPoint)) {
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

    private static LinkedList<String> asList(String item) {
        return new LinkedList<>() {{
            add(item);
        }};
    }
}
