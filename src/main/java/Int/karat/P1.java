package Int.karat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.System.out;

/*
 * // We have some clickstream data that we gathered on our client's website. Using cookies, we collected snippets of users'
 * anonymized URL histories while they browsed the site. The histories are in chronological order and no URL was visited more than once per person.

// Write a function that takes two users' browsing histories as input and returns the longest contiguous sequence of URLs that appears in both.

// Sample input:

// user0 = ["/start", "/pink", "/register", "/orange", "/red", "a"]
// user1 = ["/start", "/green", "/blue", "/pink", "/register", "/orange", "/one/two"]
// user2 = ["a", "/one", "/two"]
// user3 = ["/red", "/orange", "/yellow", "/green", "/blue", "/purple", "/white", "/amber", "/HotRodPink", "/BritishRacingGreen"]
// user4 = ["/red", "/orange", "/amber", "/green", "/blue", "/purple", "/white", "/lavender", "/HotRodPink", "/BritishRacingGreen"]
// user5 = ["a"]

// Sample output:

// findContiguousHistory(user0, user1)
//    /pink
//    /register
//    /orange

// findContiguousHistory(user1, user2)
//    (empty)

// findContiguousHistory(user2, user0)
//    a

// findContiguousHistory(user5, user2)
//    a

// findContiguousHistory(user3, user4)
//    /green
//    /blue
//    /purple
//    /white

// findContiguousHistory(user4, user3)
//    /green
//    /blue
//    /purple
//    /white


 //user0 = ["/start", "/pink", "/register", "/orange", "/red", "a"]
// user1 = ["/start", "/green", "/blue", "/pink", "/register", "/orange", "/one/two"]


// loop thru user0
// HashSet  /start
 *
 *
 */



public class P1 {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String[] user0 = {"/start", "/pink", "/register", "/orange", "/red", "a"};
        String[] user1 = {"/start", "/green", "/blue", "/pink", "/register", "/orange", "/one/two"};
        String[] user2 = {"a", "/one", "/two"};
        String[] user3 = {"/red", "/orange", "/yellow", "/green", "/blue", "/purple", "/white", "/amber", "/HotRodPink", "/BritishRacingGreen"};
        String[] user4 = {"/red", "/orange", "/amber", "/green", "/blue", "/purple", "/white", "/lavender", "/HotRodPink", "/BritishRacingGreen"};
        String[] user5 = {"a"};

//        out.println(rawr(user0, user1));
//        out.println(rawr(user1, user2));
//        out.println(rawr(user2, user0));
//        out.println(rawr(user5, user2));
        out.println(rawr(user3, user4));
//        out.println(rawr(user4, user3));

        P1 c=new P1();
        c.findContiguousHistory(user0, user1);
        c.findContiguousHistory(user1, user2);
        c.findContiguousHistory(user2, user0);
        c.findContiguousHistory(user5, user2);
        c.findContiguousHistory(user3, user4);

        // below scenaro is tricky because amber match later and user3 string. We have to come back again.
        //  String[] user4 = {"/red", "/orange", "/amber", "/green", "/blue", "/purple", "/white", "/lavender", "/HotRodPink", "/BritishRacingGreen"};
        // String[] user3 = {"/red", "/orange", "/yellow", "/green", "/blue", "/purple", "/white", "/amber", "/HotRodPink", "/BritishRacingGreen"};

        c.findContiguousHistory(user4, user3);
    }

    public static List<String> rawr(String [] u1, String[] u2){

        Map<String, Integer> cache = new HashMap<>();
        // x -> index
        // x2 -> end

        for(int i = 0; i < u1.length; i++) {
            cache.put(u1[i], i);
        }

        List<String> dd = new ArrayList<>();
        List<String> dd2 = new ArrayList<>();

        for(int i = 0; i < u2.length; i++) {

            if(cache.containsKey(u2[i])) {
                dd2.add(u2[i]);
            } else {
                if(dd2.size() > dd.size()) {
                    dd.clear();
                    dd.addAll(dd2);
                    dd2.clear();
                }
            }
        }

        if(dd2.size() > dd.size()) {
            dd.clear();
            dd.addAll(dd2);
            dd2.clear();
        }

        return dd;

    }


    public void findContiguousHistory(String[] user1Arr, String[] user2Arr)
    {
        if(user1Arr.length==0 || user2Arr.length==0)
        {
            out.println("Nothing common");
        }

        List<String> result=new ArrayList<String>();


        List<String> resultSoFar=new ArrayList<String>();
        int i=0;int count=0;
        int j=0;
        boolean foundAtleatone=false;

        // loop for going thru both the string and move pointer one by one. Start from first user string and keep moving both the pointer until match
        // As soon as not match then check the so far length if it is greater then existing result and this will be new result
        // I have to take care special condition if some thing match in later part of string 2 so i was increamenting by two time so skipping one value
        //introduced new flage to see if any value found later because I already incremented to check the next value. It is not required i to increment again


        while( i<user1Arr.length && j<user2Arr.length)
        {
            if(user1Arr[i].equalsIgnoreCase(user2Arr[j]))
            {
                resultSoFar.add(user1Arr[i]);
                foundAtleatone=true;
                i++;
                j++;
                continue;
            }
            else
            {

                if(resultSoFar.size() > result.size())
                {
                    // System.out.println("resultSoFar " + resultSoFar.toString());
                    //System.out.println("result " + result.toString());
                    result.clear();
                    result.addAll(resultSoFar);
                    resultSoFar.clear();
                    count=j;
                    foundAtleatone=false;
                }
                else
                {
                    // clearing result list if length is smaller because it could be possible to have lesser match
                    // if not cleared then it will be added to final result

                    resultSoFar.clear();
                }
                j++;

                if(j == user2Arr.length)
                {
                    if(!foundAtleatone)
                        i++;
                    j=count;
                    foundAtleatone=false;
                }

            }
        }

        if(resultSoFar.size() > result.size())
        {
            result.clear();
            result.addAll(resultSoFar);
            resultSoFar.clear();
        }

        out.println("Final result " + result.toString());

    }
}
