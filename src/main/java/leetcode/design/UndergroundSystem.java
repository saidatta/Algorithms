package leetcode.design;

import java.util.HashMap;
import java.util.Map;


// https://leetcode.com/problems/design-underground-system/
class UndergroundSystem {
    // badgeId - checkinData
    private final Map<Integer, CheckInData> checkInMap;
    // stationName - routeData
    private final Map<String, RouteData> routeMap;

    public UndergroundSystem() {
        checkInMap = new HashMap<>();
        routeMap = new HashMap<>();
    }

    public void checkIn(int id, String stationName, int t) {
        checkInMap.put(id, new CheckInData(stationName, t));
    }

    public void checkOut(int id, String stationName, int t) {
        CheckInData checkInData = checkInMap.get(id);
        String routeKey = checkInData.stationName() + "-" + stationName;
        int travelTime = t - checkInData.time();

        RouteData routeData = routeMap.getOrDefault(routeKey, new RouteData(0, 0));
        routeMap.put(routeKey,
                new RouteData(routeData.totalTime() + travelTime, routeData.tripCount() + 1));
        // Remove the check-in data as it's no longer needed
        checkInMap.remove(id);
    }

    public double getAverageTime(String startStation, String endStation) {
        String routeKey = startStation + "-" + endStation;
        RouteData routeData = routeMap.get(routeKey);
        return routeData.totalTime() / routeData.tripCount();
    }

    // Defining CheckInData and RouteData as records
    private record CheckInData(String stationName, int time) { }
    private record RouteData(double totalTime, int tripCount) { }
}

