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
        Map<String, Set<String>> graph = Map.of(
            "A", Set.of("B"),
            "B", Set.of("C", "D", "F"),
            "C", Set.of("E"),
            "D", Set.of("F"),
            "E", Set.of("F"),
            "F", Set.of("G")
        );
        List<String> shortestPath = findShortestPath("A", "G", graph);
        System.out.println("The shortest path from A to G is " + shortestPath);
    }

    private static List<String> findShortestPath(String startPoint,
                                                 String targetPoint,
                                                 Map<String, Set<String>> graph) {
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
