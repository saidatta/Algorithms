package Leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/subdomain-visit-count/
 *
 * Created by venkatamunnangi on 9/24/19.
 */

//Input:
//        ["900 google.mail.com", "50 yahoo.com", "1 intel.mail.com",
// "5 wiki.org"]
//        Output:
//        ["901 mail.com","50 yahoo.com","900 google.mail.com","5 wiki.org",
// "5 org","1 intel.mail.com","951 com"]
public class SubdomainVisitCount {
    public List<String> subdomainVisits(String[] cpdomains) {
        Map<String, Integer> map = new HashMap<>();
        for (String cd : cpdomains) {
            int i = cd.indexOf(' ');
            int n = Integer.parseInt(cd.substring(0, i));
            String s = cd.substring(i + 1);
            for (i = 0; i < s.length(); ++i) {
                if (s.charAt(i) == '.') {
                    String d = s.substring(i + 1);
                    map.put(d, map.getOrDefault(d, 0) + n);
                }
            }
            map.put(s, map.getOrDefault(s, 0) + n);
        }

        List<String> res = new ArrayList();
        for (String d : map.keySet()) {
            res.add(map.get(d) + " " + d);
        }
        return res;
    }
}
