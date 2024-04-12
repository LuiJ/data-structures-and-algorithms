package root.tasks;

import lombok.Value;

import java.util.LinkedList;
import java.util.Optional;

public class IsolatedZonesCounter {

    private static final int PROCESSED_POINT_VALUE = -1;

    public static void main(String[] args) {
        int[][] picture1 = new int[][] {
                {1,1,1,2,1},
                {1,2,1,2,1},
                {1,1,1,3,1}
        };
        System.out.println(countIsolatedZones(picture1)); // expected 5

        int[][] picture2 = new int[][] {
                {2,2,2,1},
                {1,2,2,1},
                {1,3,1,1},
                {1,1,1,3}
        };
        System.out.println(countIsolatedZones(picture2)); // expected 4
    }

    /*

    Solution description:

    Iterate over points of picture from top-left to right-bottom and:
    1) Take non-processed point and mark it as processed.
    2) Mark all points which are in the same zone with the point as processed.
    3) Increase counter of zones.
    4) Repeat 1-3 until all points of picture will be marked as processed.

     */

    private static int countIsolatedZones(int[][] picture) {
        int counter = 0;
        int height = picture.length;
        int width = picture[0].length;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                var pointValue = picture[y][x];
                if (pointValue == PROCESSED_POINT_VALUE) {
                    continue;
                }
                var point = new Point(x, y, pointValue);
                processPoint(point, picture);
                counter++;
            }
        }
        return counter;
    }

    private static void processPoint(Point point, int[][] picture) {
        picture[point.y][point.x] = PROCESSED_POINT_VALUE;
        var queueOfConnectedPoints = new LinkedList<Point>();
        getTopPointFor(point, picture).filter(point::hasSameValue).ifPresent(queueOfConnectedPoints::push);
        getBottomPointFor(point, picture).filter(point::hasSameValue).ifPresent(queueOfConnectedPoints::push);
        getLeftPointFor(point, picture).filter(point::hasSameValue).ifPresent(queueOfConnectedPoints::push);
        getRightPointFor(point, picture).filter(point::hasSameValue).ifPresent(queueOfConnectedPoints::push);
        while (!queueOfConnectedPoints.isEmpty()) {
            processPoint(queueOfConnectedPoints.poll(), picture);
        }
    }

    private static Optional<Point> getTopPointFor(Point point, int[][] picture) {
        var x = point.x;
        var y = point.y - 1;
        return getPoint(x, y, picture);
    }

    private static Optional<Point> getBottomPointFor(Point point, int[][] picture) {
        var x = point.x;
        var y = point.y + 1;
        return getPoint(x, y, picture);
    }

    private static Optional<Point> getLeftPointFor(Point point, int[][] picture) {
        var x = point.x - 1;
        var y = point.y;
        return getPoint(x, y, picture);
    }

    private static Optional<Point> getRightPointFor(Point point, int[][] picture) {
        var x = point.x + 1;
        var y = point.y;
        return getPoint(x, y, picture);
    }

    private static Optional<Point> getPoint(int x, int y, int[][] picture) {
        try {
            return Optional.of(new Point(x, y, picture[y][x]));
        } catch (ArrayIndexOutOfBoundsException e) {
            return Optional.empty();
        }
    }


    @Value
    private static class Point {
        int x;
        int y;
        int value;

        public boolean hasSameValue(Point p) {
            return value == p.value;
        }
    }
}
