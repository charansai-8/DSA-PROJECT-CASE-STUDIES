
import java.util.*;

public class MavenIterativeDFSTopologicalSort {

    static class State {
        String node;
        boolean leave;

        State(String node, boolean leave) {
            this.node = node;
            this.leave = leave;
        }
    }

    public static void main(String[] args) {

        Map<String, List<String>> graph = new HashMap<>();

        graph.put("app", Arrays.asList("core"));
        graph.put("core", Arrays.asList("logging", "util"));
        graph.put("util", Arrays.asList("math", "serial"));
        graph.put("math", Arrays.asList("bigint"));
        graph.put("serial", new ArrayList<>());
        graph.put("bigint", new ArrayList<>());
        graph.put("logging", Arrays.asList("filehandler"));
        graph.put("filehandler", Arrays.asList("log4j"));
        graph.put("log4j", new ArrayList<>());

        Map<String, String> color = new HashMap<>();

        for (String node : graph.keySet()) {
            color.put(node, "WHITE");
        }

        Stack<State> stack = new Stack<>();
        List<String> output = new ArrayList<>();

        stack.push(new State("app", false));

        while (!stack.isEmpty()) {

            State current = stack.pop();
            String node = current.node;

            if (current.leave) {
                color.put(node, "BLACK");
                output.add(node);
            } else {
                if (color.get(node).equals("WHITE")) {

                    color.put(node, "GREY");
                    stack.push(new State(node, true));

                    List<String> neighbors =
                            new ArrayList<>(graph.get(node));

                    Collections.sort(neighbors,
                            Collections.reverseOrder());

                    for (String neighbor : neighbors) {
                        if (color.get(neighbor).equals("WHITE")) {
                            stack.push(new State(neighbor, false));
                        }
                    }
                }
            }
        }

        Collections.reverse(output);

        System.out.println("Topological Order:");
        for (String node : output) {
            System.out.print(node + " ");
        }
    }
}
