package Leetcode;

import Leetcode.Tree.NestedInteger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by venkatamunnangi on 12/13/16.
 */
public class GeneralMainer {
    public static void main(String [] args) {

    }

    protected static void testNestedWeight2() {
        NestedWeight2 nw2 = new NestedWeight2();

        ArrayList al3 = new ArrayList();
        al3.add(6);

        ArrayList al2 = new ArrayList();
        al2.add(4);
        al2.add(al3);

        ArrayList<TestNestedInteger> test = new ArrayList<>();
        test.add(new TestNestedInteger(1));
        test.add(new TestNestedInteger(al2));
        System.out.println(nw2.depthSumInverse(test));
    }


    static class TestNestedInteger implements NestedInteger {
        Object value;

        TestNestedInteger(Object obj) {
            value = obj;
        }

        @Override
        public boolean isInteger() {
            return value instanceof Integer;
        }

        @Override
        public Integer getInteger() {
            if(!isInteger()) {
                return -1;
            }

            return (Integer) value;
        }

        @Override
        public List<NestedInteger> getList() {
            return (List) value;
        }
    }
}
