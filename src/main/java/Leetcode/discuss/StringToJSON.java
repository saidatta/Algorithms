package Leetcode.discuss;

/**
 * https://discuss.leetcode.com/topic/249/print-json-format-string
 *
 * Created by venkatamunnangi on 3/29/17.
 */
public class StringToJSON {

    // formatted JSON STring needs to be produced.
    void printJSON(String str) {
        //TODO

    }

    public static void main(String[] args) {

        StringToJSON stringToJSON = new StringToJSON();
        String json = "{\"id\": \"0001\", \"type\": \"donut\", \"name\": \"Cake\", \"ppu\": 0.55, \"batters\":{\"batter\":[{ \"id\": \"1001\", \"type\": \"Regular\" },{ \"id\": \"1002\", \"type\": \"Chocolate\" }]},\"topping\":[{ \"id\": \"5001\", \"type\": \"None\" },{ \"id\": \"5002\", \"type\": \"Glazed\" }]}";
        stringToJSON.printJSON(json);
    }

}
