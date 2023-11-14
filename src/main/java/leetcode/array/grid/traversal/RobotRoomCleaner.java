package leetcode.array.grid.traversal;

import java.util.HashSet;
import java.util.Set;

// https://leetcode.com/problems/robot-room-cleaner/solutions/151942/java-dfs-solution-with-detailed-explanation-and-6ms-99-solution/
class RobotRoomCleaner {
    final int[][] DIRECTIONS = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public void cleanRoom(Robot robot) {
        Set<RobotRoomNode> offset = new HashSet<>();
        find(robot, offset, 0, 0, 0);
    }

    private void find(Robot robot, Set<RobotRoomNode> visited, int currDirection, int row, int col) {
        RobotRoomNode robotRoomNode = new RobotRoomNode(row, col);
        visited.add(robotRoomNode);
        robot.clean();
        for (int i = 0; i < DIRECTIONS.length; ++i) {
            int direction = (currDirection + i) % DIRECTIONS.length;
            int[] next = DIRECTIONS[direction];
            int nextRow = row + next[0];
            int nextCol = col + next[1];
            robotRoomNode = new RobotRoomNode(nextRow, nextCol);
            if (!visited.contains(robotRoomNode) && robot.move()) {
                find(robot, visited, direction, nextRow, nextCol);
                robot.turnLeft();
                robot.turnLeft();
                robot.move();
                robot.turnLeft();
            } else {
                robot.turnRight();
            }
        }
    }
}

record RobotRoomNode(int row, int col) { }
interface Robot {
    // Returns true if the cell in front is open and robot moves into the cell.
    //  Returns false if the cell in front is blocked and robot stays in the current cell.
    boolean move();

    // Robot will stay in the same cell after calling turnLeft/turnRight.
    // Each turn will be 90 degrees.
    void turnLeft();

    void turnRight();

    // Clean the current cell.
    void clean();
}