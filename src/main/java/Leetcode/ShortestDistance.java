package Leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * TODO
 * Created by venkatamunnangi on 13/11/16.
 */
public class ShortestDistance {
    public int shortestDistance(int[][] grid) {
        int m = grid.length;
        if(m == 0) return 0;
        int n = grid[0].length;
        int count1 = 0;
        int[][] levelSum = new int[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j] == 1){
                    count1++;
                    bfs(grid,levelSum,m,n,i,j);
                }
            }
        }
        int res = Integer.MAX_VALUE;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(levelSum[i][j] >= count1){
                    res = Math.min(res, levelSum[i][j]);
                }
            }
        }
        return res == Integer.MAX_VALUE? -1:res;
    }

    void bfs(int[][] grid,int[][] levelSum,int m,int n,int i,int j){
        Queue<Point> q = new LinkedList();
        Set<Point> visited = new HashSet();
        Point root = new Point(i,j);
        q.offer(root);
        visited.add(root);
        int level = 0;
        while(!q.isEmpty()){
            int size = q.size();
            for(int k=0;k<size;k++){
                Point node = q.poll();
                int x = node.x, y = node.y;
                levelSum[x][y] += level;
                if(x-1>=0){
                    Point newNode = new Point(x-1,y);
                    if(!visited.contains(newNode) && grid[x-1][y] == 0){
                        q.offer(newNode);
                        visited.add(newNode);
                    }
                }
                if(x+1<m){
                    Point newNode = new Point(x+1,y);
                    if(!visited.contains(newNode) && grid[x+1][y] == 0){
                        q.offer(newNode);
                        visited.add(newNode);
                    }
                }
                if(y-1>=0){
                    Point newNode = new Point(x,y-1);
                    if(!visited.contains(newNode) && grid[x][y-1] == 0){
                        q.offer(newNode);
                        visited.add(newNode);
                    }
                }
                if(y+1<n){
                    Point newNode = new Point(x,y+1);
                    if(!visited.contains(newNode) && grid[x][y+1] == 0){
                        q.offer(newNode);
                        visited.add(newNode);
                    }
                }
            }
            level++;
        }
    }

    class Point{
        int x;
        int y;
        Point(int x, int y){
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode(){
            int result = 7;
            result = result * 31 + x;
            result = result * 31 + y;
            return result;
        }

        @Override
        public boolean equals(Object obj){
            if(obj == null) return false;
            Point p = (Point) obj;
            if(p.x == this.x && p.y == this.y) return true;
            else return false;
        }
    }
}
