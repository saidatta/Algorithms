package Int;

/**
 * Created by venkatamunnangi on 6/19/17.
 */

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Here is the test:
 *   Two classes are given DictItem and StringWrapper. Both have a common interface, DictEntry,
 * which only has one boolean method, isDictItem, which tells whether or not the current Object
 * is a DictEntry or a StringWrapper.
 *      Given a single Object of either DictItem or StringWrapper type, produce a String that
 * looks like a Json Object but does not contain the quotes ("") a normal Json Object would
 * have.
 *   You can only modify the toString method or add new methods.
 *      For the record, I'm pretty sure this is a test of how well you know recursion and can deal
 * with bad code.
 */
public class AtlassianTest {
    // Map<String, Object> data -> map of strings or maps
    // { a : item_1, b : { c : item_2, d : { e: item_3 }, f : item_4 } }
    private interface DictEntry {
        public boolean isDictItem();
    }

    public static class DictItem implements DictEntry {
        private Map<String, Object> map;

        public DictItem() {
            map = new HashMap<>();
        }

        public void addItem(String key, Object value) {
            map.put(key, value);
        }

        @Override
        public boolean isDictItem() {
            return true;
        }

        public Set<String> getKeys() {
            return map.keySet();
        }

        public Object getValue(String key) {
            return map.get(key);
        }

    }

    public static class StringWrapper implements DictEntry {
        private String key;
        private String value;

        public StringWrapper(String key, String value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public boolean isDictItem() {
            return false;
        }

        public String getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }

    }

    public static void main(String[] args) {
        StringWrapper a = new StringWrapper("a", "item_1");
        StringWrapper c = new StringWrapper("c", "item_2");
        StringWrapper e = new StringWrapper("e", "item_3");
        StringWrapper f = new StringWrapper("f", "item_4");
        DictItem d = new DictItem();
        d.addItem("d", e);
        DictItem b = new DictItem();
        b.addItem("c", c);
        b.addItem("d", d);
        b.addItem("f", f);
        DictItem outer = new DictItem();
        outer.addItem("a", a);
        outer.addItem("b", b);
        //outer.addItem("f", f);

        StringBuilder builder = new StringBuilder();

        StringBuilder out = toString(builder, outer);
        System.out.println(removeWhiteSpace(out.toString()));
        System.out.println(removeWhiteSpace("{ a : item_1, b : { c : item_2, d : { e: item_3 }, f : item_4 } }"));
        System.out.println(removeWhiteSpace(out.toString()).equals(removeWhiteSpace("{ a : item_1, b : { c : item_2, d : { e: item_3 }, f : item_4 } }")));

    }

    private static String removeWhiteSpace(String s) {
        return s.replaceAll("\\s+","");
    }

    public static StringBuilder toString(StringBuilder builder, DictEntry entry) {
        builder.append("{");
        helper(builder, entry);

        sanitizeCommas(builder);

        return builder;
    }

    private static void sanitizeCommas(StringBuilder sb) {

        int i = sb.length()-1;
        while(i>=0) {
            if(sb.charAt(i) == '}' || sb.charAt(i) == ' ') {
                i--;
            } else if(sb.charAt(i) == ',') {
                sb.deleteCharAt(i);
                i--;
            } else {
                return;
            }
        }

    }

    public static void helper(StringBuilder builder, DictEntry entry) {
        if(entry instanceof StringWrapper) {
            StringWrapper sw = (StringWrapper) entry;
            builder.append(sw.getKey()).append(" ").append(":").append(" ").append(sw.getValue()).append(",");
            return;
        }

        DictItem di = (DictItem) entry;

        for(String key : di.getKeys()) {
            DictEntry de = (DictEntry) di.getValue(key);

            if(de instanceof StringWrapper) {
                helper(builder, (DictEntry) di.getValue(key));
            } else {
                helper(builder.append(key).append(" ").append(":").append(" ").append("{").append(" "), (DictEntry) di.getValue(key));
            }
        }

        builder.deleteCharAt(builder.length()-1);

        builder.append(" ").append("}").append(",").append(" ");
    }
}
