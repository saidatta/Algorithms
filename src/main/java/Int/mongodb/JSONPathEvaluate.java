package Int.mongodb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class JSONPathEvaluate {

    interface JSONComponent {
        List<Object> resolve(List<String> path, int pathIndex);
    }

    static class StringComponent implements JSONComponent {
        private String value;

        public StringComponent(String value) {
            this.value = value;
        }

        @Override
        public List<Object> resolve(List<String> path, int pathIndex) {
            if (pathIndex == path.size()) {
                return Collections.singletonList(value);
            }
            return new ArrayList<>();
        }
    }

    static class DictComponent implements JSONComponent {
        private Map<String, JSONComponent> value;

        public DictComponent(Map<String, JSONComponent> value) {
            this.value = value;
        }

        @Override
        public List<Object> resolve(List<String> path, int pathIndex) {
            if (pathIndex < path.size() && value.containsKey(path.get(pathIndex))) {
                return value.get(path.get(pathIndex)).resolve(path, pathIndex + 1);
            }
            return new ArrayList<>();
        }
    }

    static class ListComponent implements JSONComponent {
        private List<JSONComponent> value;

        public ListComponent(List<JSONComponent> value) {
            this.value = value;
        }

        @Override
        public List<Object> resolve(List<String> path, int pathIndex) {
            List<Object> results = new ArrayList<>();
            for (JSONComponent component : value) {
                results.addAll(component.resolve(path, pathIndex));
            }
            return results;
        }
    }

    public static class JSONResolver {
        private JSONComponent root;

        public JSONResolver(JSONComponent root) {
            this.root = root;
        }

        public List<Object> resolve(List<String> path) {
            return root.resolve(path, 0);
        }

        public static void main(String[] args) {
            JSONComponent data1 = new DictComponent(Map.of(
                    "a", new DictComponent(Map.of(
                            "b", new ListComponent(Arrays.asList(
                                    new DictComponent(Map.of(
                                            "c", new StringComponent("foo"),
                                            "d", new DictComponent(Collections.emptyMap()),
                                            "e", new StringComponent("bar")
                                    )),
                                    new DictComponent(Map.of(
                                            "c", new StringComponent("baz")
                                    ))
                            ))
                    ))
            ));

            JSONResolver resolver = new JSONResolver(data1);
            System.out.println(resolver.resolve(Arrays.asList("a", "b", "c")));  // Expected: [foo, baz]
        }
    }


}
