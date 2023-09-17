package leetcode.array.priorityQueue;

import java.util.*;

// https://leetcode.com/problems/the-skyline-problem/
class SkylineProblem {
    static class Point {
        int x, y, index;
        boolean isOpen;

        public Point(int x, int y, int index, boolean isOpen) {
            this.x = x;
            this.y = y;
            this.index = index;
            this.isOpen = isOpen;
        }
    }

    static class Range {
        int start, end, height;

        public Range(int start, int end, int height) {
            this.start = start;
            this.end = end;
            this.height = height;
        }
    }

    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<Point> points = createPointList(buildings);
        List<Range> ranges = getRanges(points, buildings.length);
        List<Range> processedRanges = mergeSimilarRanges(ranges);
        return convertRangesToResult(processedRanges);
    }

    private List<Point> createPointList(int[][] buildings) {
        List<Point> points = new ArrayList<>();
        int index = 0;
        for (int[] building : buildings) {
            points.add(new Point(building[0], building[2], index, true));
            points.add(new Point(building[1], building[2], index, false));
            index++;
        }
        points.sort(Comparator.comparingInt(x -> x.x));
        return points;
    }

    private List<Range> getRanges(List<Point> points, int buildingCount) {
        PriorityQueue<Point> opening = new PriorityQueue<>((a, b) -> Integer.compare(b.y, a.y));
        boolean[] closed = new boolean[buildingCount];
        List<Range> ranges = new ArrayList<>();
        Point last = null;

        for (Point point : points) {
            if (last == null) {
                opening.add(point);
                last = point;
                continue;
            }
            // Remove closed ranges
            while (!opening.isEmpty() && closed[opening.peek().index]) {
                opening.poll();
            }
            // Get top range
            Point top = opening.isEmpty() ? null : opening.peek();
            if (last.x != point.x) {
                ranges.add(new Range(last.x, point.x, top == null ? 0 : top.y));
            }
            // Close or open range
            if (point.isOpen) {
                opening.add(point);
            } else {
                closed[point.index] = true;
            }
            last = point;
        }

        return ranges;
    }

    private List<Range> mergeSimilarRanges(List<Range> ranges) {
        List<Range> processedRanges = new ArrayList<>();
        int i = 0;
        while (i < ranges.size()) {
            int j = i + 1;
            while (j < ranges.size() && ranges.get(j).height == ranges.get(i).height) {
                j++;
            }
            processedRanges.add(new Range(ranges.get(i).start, ranges.get(j - 1).end, ranges.get(i).height));
            i = j;
        }
        return processedRanges;
    }

    private List<List<Integer>> convertRangesToResult(List<Range> processedRanges) {
        List<List<Integer>> result = new ArrayList<>();
        for (Range range : processedRanges) {
            result.add(Arrays.asList(range.start, range.height));
        }
        result.add(Arrays.asList(processedRanges.get(processedRanges.size() - 1).end, 0));
        return result;
    }
}
