package Int.mongodb;

import java.util.*;

public class Leaderboard {
    public static void main(String[] args) {
        List<LeaderboardUser> users = Arrays.asList(new LeaderboardUser(1, "qw"), new LeaderboardUser(2, "er"));
        List<LeaderboardLogin> logins = Arrays.asList(
                new LeaderboardLogin(1, 1),
                new LeaderboardLogin(1, 1),
                new LeaderboardLogin(1, 3),
                new LeaderboardLogin(2, 4),
                new LeaderboardLogin(2, 5),
                new LeaderboardLogin(1, 7)
        );

        Map<Integer, String> userIdToName = new HashMap<>();
        for (LeaderboardUser user : users) {
            userIdToName.put(user.userId, user.name);
        }

        Map<Integer, Set<Integer>> userToUniqueLogins = new HashMap<>();
        for (LeaderboardLogin login : logins) {
            userToUniqueLogins
                    .computeIfAbsent(login.userId, k -> new HashSet<>())
                    .add(login.timestamp);
        }

        List<Map.Entry<String, Integer>> leaderboard = new ArrayList<>();
        for (Map.Entry<Integer, Set<Integer>> entry : userToUniqueLogins.entrySet()) {
            leaderboard.add(new AbstractMap.SimpleEntry<>(userIdToName.get(entry.getKey()), entry.getValue().size()));
        }

        leaderboard.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        for (Map.Entry<String, Integer> entry : leaderboard) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    record LeaderboardUser(int userId, String name) {}
    record LeaderboardLogin(int userId, int timestamp) {}
}
