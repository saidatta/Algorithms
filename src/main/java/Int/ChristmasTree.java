package Int;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Created by venkatamunnangi on 10/2/19.
 */
public class ChristmasTree {
    List<String> treeBranches = new ArrayList<>();
    /**
     * h = 4
     *           #
     *          ###
     *         #####
     *        #######
     *
     * width = i * 2 + 1
     * @param haight
     */

    public void generateTree(int haight) {
        int maxWidth = 2 * (haight - 1) + 1;
        IntStream.iterate(0, i -> i + 1).limit(haight).forEach(i -> {
            StringBuilder sb = new StringBuilder();
            IntStream.range(0, maxWidth - 1).forEach(j -> {
                if (j >= haight - 1 - i && j <= haight - 1 + i) {
                    sb.append("*");
                } else {
                    sb.append(" ");
                }
                treeBranches.add(sb.toString());
            });
        });
    }

    public void draw(int haight) {
        generateTree(haight);
        System.out.println(String.join("\n",treeBranches));
    }

    public void drawHollow(int haight) {
        generateTree(haight);

    }

    public void makeItHollow() {

    }
}
