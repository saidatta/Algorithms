package Leetcode;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class TaskScheduler {

    public static void main(String [] args) {
        TaskScheduler taskScheduler = new TaskScheduler();
        System.out.println(taskScheduler.leastInterval(new char[]{'A','A','A','B','B','B'}, 2));
        String json = "[{\"dc\":\"us-east-1\",\"token\":\"1383429731\",\"ip\":\"dyno1\",\"hostname\":\"dyno1\",\"zone\":\"us-east-1d\",\"port\":\"8102\"}\"," +
                "\"{\"dc\":\"us-east-1\",\"token\":\"12345678\",\"ip\":\"10.156.175.173\",\"hostname\":\"10.156.175.173\",\"zone\":\"us-east-1c\",\"port\":\"8102\"}]\"";

        taskScheduler.parseTokenListFromJson(json);
    }

    public int leastInterval(char[] tasks, int n) {

        int[] c = new int[26];
        for(char t : tasks){
            c[t - 'A']++;
        }
        Arrays.sort(c);
        int i = 25;
        while(i >= 0 && c[i] == c[25]) {
            i--;
        }

        //
        return Math.max(tasks.length, (c[25] - 1) * (n + 1) + 25 - i);
    }

    List<String> parseTokenListFromJson(String json) {

        List<String> hostTokens = new ArrayList<String>();

        JSONParser parser = new JSONParser();
        try {
            JSONArray arr = (JSONArray) parser.parse(json);

            Iterator<?> iter = arr.iterator();
            while (iter.hasNext()) {

                Object item = iter.next();
                if (!(item instanceof JSONObject)) {
                    continue;
                }
                JSONObject jItem = (JSONObject) item;
                Long token = Long.parseLong((String) jItem.get("token"));
                String hostname = (String) jItem.get("hostname");
                String ipAddress = (String) jItem.get("ip");
                String zone = (String) jItem.get("zone");
                String datacenter = (String) jItem.get("dc");
                String portStr = (String) jItem.get("port");
                int port = 8000;
                if (portStr != null) {
                    port = Integer.valueOf(portStr);
                }


                System.out.println("hostname -"+hostname);
                System.out.println("ipAddress -"+ipAddress);
                System.out.println("port -"+port);
                System.out.println("zone -"+zone);
                System.out.println("datacenter -"+datacenter);
                System.out.println("token -"+token);
                System.out.println("-------------------\n");
//                System.out.println("host -"+host);

//                Host host = new Host(hostname, ipAddress, port, zone, datacenter, Status.Up);
//
//                if (isLocalDatacenterHost(host)) {
//                    HostToken hostToken = new HostToken(token, host);
//                    hostTokens.add(hostToken);
//                }
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return hostTokens;
    }
}
