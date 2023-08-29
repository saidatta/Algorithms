package leetcode.dp;

import java.util.Arrays;

// https://leetcode.com/problems/number-of-music-playlists/
public class NumMusicPlaylists {
    private static final int MOD = (int) 1e9 + 7;
    private static final int MAXN = 101;
    private static final long[][] dp = new long[MAXN][MAXN];

//    The main idea is to create a dp table where dp[i][j] represents the number of possible playlists for i songs of
//    total length j. We fill this table by taking into account the two possibilities:
//
//    The song that we are going to add to the playlist is a new song.
//    The song that we are going to add to the playlist is a song that we have already listened to.
    public static int numMusicPlaylists(int n, int goal, int k) {
//        This code  fills the dp table using the two possibilities
//        as mentioned above. For each possible playlist length i and for each possible number of unique songs j in the
//        playlist, it computes the number of playlists. If j > k, then it is possible to include a song that was
//        already played. Finally, it returns the number of possible playlists of length goal with exactly n unique
//        songs, modulo 1e9 + 7.

        Arrays.fill(dp[0], 0);
        dp[0][0] = 1;

        for (int i = 1; i <= goal; i++) {
            for (int j = 1; j <= n; j++) {
                // Add a new song
                dp[i][j] = dp[i - 1][j - 1] * (n - j + 1) % MOD;
                if (j > k) {
                    // Add a song that we have already listened to
                    dp[i][j] = (dp[i][j] + dp[i - 1][j] * (j - k) % MOD) % MOD;
                }
            }
        }

        return (int) dp[goal][n];
    }

    public static void main(String[] args) {
        System.out.println(numMusicPlaylists(3, 3, 1)); // Output: 6
        System.out.println(numMusicPlaylists(2, 3, 0)); // Output: 6
        System.out.println(numMusicPlaylists(2, 3, 1)); // Output: 2
    }

//    dp[i][j] = dp[i - 1][j - 1] * (n - j + 1) % MOD;
//    In this line, we're adding a new unique song to our playlist. The number of ways to do this is the number of ways
//    to form a playlist of i - 1 songs containing j - 1 unique songs (represented by dp[i - 1][j - 1]), multiplied by
//    the number of new songs we could add, which is (n - j + 1).
//    To break down (n - j + 1): n represents the total number of different songs, j represents the number of unique
//    songs we have already put in our playlist. So, n - j will give us the number of songs that we haven't yet put in
//    our playlist. We add one because we are in the ith iteration, where we are adding the next song to the playlist.
//    The result is then taken modulo MOD to ensure the result does not exceed the limit for integer values, and to meet
//    the problem's requirement of returning the result modulo 10^9 + 7.
//    dp[i][j] = (dp[i][j] + dp[i - 1][j] * (j - k) % MOD) % MOD;
//    This line handles the case where we're adding a song that we've already played before. To avoid boredom (as stated
//    in the problem), we can only repeat a song if at least k other songs have been played since we last played it.
//    So, we can only add a song that we've played before if j > k.
//    The number of ways to do this is the number of ways to form a playlist of i - 1 songs containing j unique songs
//    (represented by dp[i - 1][j]), multiplied by the number of songs we could repeat, which is (j - k).
//    This new number of playlists is then added to the existing number of playlists dp[i][j], and again we take this
//    modulo MOD to maintain the constraints of the problem.
}
