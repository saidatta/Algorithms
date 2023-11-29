package leetcode.backtracking.matrix;

import java.util.Arrays;

// https://leetcode.com/problems/maximum-compatibility-score-sum/description/
public class MaxCompatibilityScoreSum {
    public int maxCompatibilitySum(int[][] students, int[][] mentors) {
        return backtrack(students, mentors, new boolean[mentors.length], 0);
    }

    private int backtrack(int[][] students, int[][] mentors, boolean[] paired, int studentIndex) {
        if (studentIndex == students.length) {
            return 0;
        }

        int maxScore = 0;
        for (int i = 0; i < mentors.length; i++) {
            if (!paired[i]) {
                paired[i] = true;
                int score =
                        calculateScore(students[studentIndex], mentors[i]) +
                        backtrack(students, mentors, paired, studentIndex + 1);
                maxScore = Math.max(maxScore, score);
                paired[i] = false;
            }
        }
        return maxScore;
    }

    private int calculateScore(int[] student, int[] mentor) {
        int score = 0;
        for (int i = 0; i < student.length; i++) {
            if (student[i] == mentor[i]) {
                score++;
            }
        }
        return score;
    }

    public static void main(String[] args) {
        MaxCompatibilityScoreSum solution = new MaxCompatibilityScoreSum();
        int[][] students = {{1, 1, 0}, {1, 0, 1}, {0, 0, 1}};
        int[][] mentors = {{1, 0, 0}, {0, 0, 1}, {1, 1, 0}};
        System.out.println(solution.maxCompatibilitySum(students, mentors));  // Output: 8
    }

    // https://leetcode.com/problems/maximum-compatibility-score-sum/solutions/1361588/javascript-optimal-hungarian-algorithm-in-o-m-m-n/
    // https://www.youtube.com/watch?v=cQ5MsiGaDY8
    class KuhnMunkres {
        public int maxCompatibilitySum(int[][] students, int[][] mentors) {
            return sln1(students, mentors);
        }

        private int sln1(int[][] sts, int[][] mts){
            int m = sts.length;
            int n = sts[0].length;
            int[][] arr = new int[m][m];
            for(int i = 0;i<m;i++){
                for(int j = 0;j< m;j++){
                    int score = 0;
                    for(int k = 0;k < n;k++){
                        score += 1^sts[i][k]^mts[j][k];
                    }
                    arr[i][j] = score;
                }
            }

            return km(arr);
        }

        private int km(int[][] w){
            int n = w.length;
            int m = w[0].length;
            int[] lx = new int[n];
            int[] ly = new int[m];
            int[] from = new int[m];
            int[] to = new int[n];

            Arrays.fill(from, -1);
            Arrays.fill(to, -1);

            for(int i=0;i<n;i++)//初始化标杆
                for(int j=0;j<m;j++)
                    lx[i]=Math.max(lx[i],w[i][j]);
            for(int i=0;i<n;i++) {//x边每个点的匹配
                while(true) {
                    boolean[] s = new boolean[n];
                    boolean[] t = new boolean[m];
                    if(find(i, s, t, from, to, lx, ly, w)) break;
                    else update(s, t, from, to, lx, ly, w);
                }
            }

            int ans=0;
            for(int i=0;i<n;i++)//计算答案
                ans+=w[i][to[i]];

            return ans;
        }

        private boolean find(int x, boolean[] s, boolean[] t, int[] from, int[] to, int[] lx, int[] ly, int[][] w) { //匈牙利算法
            int m = t.length;
            s[x]=true;
            for(int i=0;i<m;i++){
                if(lx[x]+ly[i]==w[x][i]&&!t[i])  {
                    t[i]=true;
                    if(from[i]<0 || find(from[i], s, t, from, to, lx, ly, w)) {
                        from[i]=x;
                        to[x]=i;
                        return true;
                    }
                }
            }
            return false;
        }

        private void update(boolean[] s, boolean[] t, int[] from, int[] to, int[] lx, int[] ly, int[][] w)//更新标杆
        {
            int n = s.length;
            int m = t.length;
            int d = Integer.MAX_VALUE;
            for(int i=0;i<n;i++){
                if(s[i]){
                    for(int j=0;j<m;j++){
                        if(!t[j]){
                            d=Math.min(d,lx[i]+ly[j]-w[i][j]);//按照上面给出的原则计算d
                        }
                    }
                }
            }
            for(int i=0;i<n;i++)//点集s中的点的标杆lx[i]-d
                if(s[i]) lx[i]-=d;
            for(int j=0;j<m;j++)//点集t中的点的标杆ly[j]+d
                if(t[j]) ly[j]+=d;
        }

    }
}


//import java.util.Arrays;
//
//public class KuhnMunkres {
//
//    // Calculate the maximum compatibility score sum
//    public int maxCompatibilitySum(int[][] students, int[][] mentors) {
//        int[][] scoreMatrix = createScoreMatrix(students, mentors);
//        return applyKuhnMunkres(scoreMatrix);
//    }
//
//    // Create a score matrix for students and mentors
//    private int[][] createScoreMatrix(int[][] students, int[][] mentors) {
//        int size = students.length;
//        int[][] scoreMatrix = new int[size][size];
//
//        for (int i = 0; i < size; i++) {
//            for (int j = 0; j < size; j++) {
//                scoreMatrix[i][j] = calculateCompatibility(students[i], mentors[j]);
//            }
//        }
//        return scoreMatrix;
//    }
//
//    // Calculate compatibility score between a student and a mentor
//    private int calculateCompatibility(int[] student, int[] mentor) {
//        int score = 0;
//        for (int i = 0; i < student.length; i++) {
//            if (student[i] == mentor[i]) {
//                score++;
//            }
//        }
//        return score;
//    }
//
//    // Apply the Kuhn-Munkres algorithm to the score matrix
//    private int applyKuhnMunkres(int[][] scoreMatrix) {
//        int size = scoreMatrix.length;
//        int[] lx = new int[size], ly = new int[size];
//        int[] matchY = new int[size];
//        Arrays.fill(matchY, -1);
//
//        // Initialize labels
//        for (int x = 0; x < size; x++) {
//            for (int y = 0; y < size; y++) {
//                lx[x] = Math.max(lx[x], scoreMatrix[x][y]);
//            }
//        }
//
//        // Find match for each x
//        for (int x = 0; x < size; x++) {
//            while (true) {
//                boolean[] visitedX = new boolean[size];
//                boolean[] visitedY = new boolean[size];
//                if (findMatch(x, visitedX, visitedY, matchY, lx, ly, scoreMatrix)) {
//                    break;
//                } else {
//                    updateLabels(visitedX, visitedY, lx, ly, scoreMatrix);
//                }
//            }
//        }
//
//        // Calculate the sum of scores for matches
//        int scoreSum = 0;
//        for (int x = 0; x < size; x++) {
//            scoreSum += scoreMatrix[x][matchY[x]];
//        }
//        return scoreSum;
//    }
//
//    // Recursive function to find a match for x
//    private boolean findMatch(int x, boolean[] visitedX, boolean[] visitedY, int[] matchY, int[] lx, int[] ly, int[][] scoreMatrix) {
//        visitedX[x] = true;
//        int size = scoreMatrix.length;
//
//        for (int y = 0; y < size; y++) {
//            if (lx[x] + ly[y] == scoreMatrix[x][y] && !visitedY[y]) {
//                visitedY[y] = true;
//                if (matchY[y] == -1 || findMatch(matchY[y], visitedX, visitedY, matchY, lx, ly, scoreMatrix)) {
//                    matchY[y] = x;
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
//
//    // Update labels lx and ly
//    private void updateLabels(boolean[] visitedX, boolean[] visitedY, int[] lx, int[] ly, int[][] scoreMatrix) {
//        int delta = Integer.MAX_VALUE;
//        int size = lx.length;
//
//        for (int x = 0; x < size; x++) {
//            if (visitedX[x]) {
//                for (int y = 0; y < size; y++) {
//                    if (!visitedY[y]) {
//                        delta = Math.min(delta, lx[x] + ly[y] - scoreMatrix[x][y]);
//                    }
//                }
//            }
//        }
//
//        for (int x = 0; x < size; x++) {
//            if (visitedX[x]) {
//                lx[x] -= delta;
//            }
//        }
//
//        for (int y = 0; y < size; y++) {
//            if (visitedY[y]) {
//                ly[y] += delta;
//            }
//        }
//    }
//
//    public static void main(String[] args) {
//        KuhnMunkres km = new KuhnMunkres();
//        int[][] students = {{1, 1, 0}, {1, 0, 1}, {0, 0, 1}};
//        int[][] mentors = {{1, 0, 0}, {0, 0, 1}, {1, 1, 0}};
//        System.out.println("Maximum Compatibility Score Sum: " + km.maxCompatibilitySum(students, mentors));
//    }
//}
