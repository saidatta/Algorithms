package Leetcode.Design;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.NavigableMap;
import java.util.TreeMap;

// https://leetcode.com/problems/design-log-storage-system/description/
// In a real-world scenario (Which the interviewer will insist on) there may not be an upper bound and lower bound.
// Like in the question (2000 <= Year <= 2017). A lot of the solutions in the Discuss section operate around this
// assumption (Which is valid since this is given in the question).

// This is just my attempt of approaching this without having the bounds and seeing if i can use the Java.time features.
//  NOTE: It does require you to know some of the functionalities of Java.Time but these are quite extensible to any
//  language and most of the production code will use such helper libraries. Of course - "The interviewer should not
//  expect you to know this, so its alright if you assume methods that have those capabilities. But it does give you
//  extra points if you know some.
public class LogSystem {
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy:MM:dd:HH:mm:ss");
    private final TreeMap<LocalDateTime, Integer> treeMap;

    public LogSystem() {
        treeMap = new TreeMap<>();
    }

    public void put(int id, String timeStamp) {
        LocalDateTime time = LocalDateTime.from(dateTimeFormatter.parse(timeStamp));
        treeMap.put(time, id);
    }

    public List<Integer> retrieve(String start, String end, String granularity) {
        LocalDateTime startTime = getDate(start, granularity, false);
        LocalDateTime endTime = getDate(end, granularity, true);

        List<Integer> retrievedLogs = new ArrayList<>();

        NavigableMap<LocalDateTime, Integer> map =
                treeMap.subMap(startTime, true, endTime, false);

        map.forEach((localDateTime, integer) -> retrievedLogs.add(integer));

        return retrievedLogs;
    }

    private LocalDateTime getDate(String timeStamp, String granularity, boolean isEnd) {

        LocalDateTime targetedTime = LocalDateTime.from(dateTimeFormatter.parse(timeStamp));
        targetedTime = switch (granularity) {
            case "Year" ->
                    (isEnd)
                            ? targetedTime.plusYears(1).withDayOfYear(1).toLocalDate().atStartOfDay()
                            : targetedTime.withDayOfYear(1).toLocalDate().atStartOfDay();
            case "Month" ->
                    (isEnd)
                            ? targetedTime.plusMonths(1).withDayOfMonth(1).toLocalDate().atStartOfDay()
                            : targetedTime.withDayOfMonth(1).toLocalDate().atStartOfDay();
            case "Day" ->
                    (isEnd)
                            ? targetedTime.toLocalDate().plusDays(1).atStartOfDay()
                            : targetedTime.toLocalDate().atStartOfDay();
            case "Hour" ->
                    (isEnd)
                            ? targetedTime.plusHours(1).truncatedTo(ChronoUnit.HOURS)
                            : targetedTime.truncatedTo(ChronoUnit.HOURS);
            case "Minute" ->
                    (isEnd)
                            ? targetedTime.plusMinutes(1).truncatedTo(ChronoUnit.MINUTES)
                            : targetedTime.truncatedTo(ChronoUnit.MINUTES);
            case "Second" ->
                    (isEnd)
                            ? targetedTime.plusSeconds(1).truncatedTo(ChronoUnit.SECONDS)
                            : targetedTime.truncatedTo(ChronoUnit.SECONDS);
            default -> LocalDateTime.from(dateTimeFormatter.parse(timeStamp));
        };

        return targetedTime;
    }
}
