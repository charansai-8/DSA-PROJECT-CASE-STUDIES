
import java.util.*;

public class UberBellmanFord {

    static class Edge {
        int src, dest, weight;

        Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {

        String[] zones = {
                "IND", "KOR", "MGR",
                "HSR", "BTM", "JPN", "EC"
        };

        int V = 7;

        List<Edge> edges = new ArrayList<>();

        edges.add(new Edge(0, 1, 8));
        edges.add(new Edge(0, 3, 5));
        edges.add(new Edge(1, 2, 7));
        edges.add(new Edge(1, 4, -5));
        edges.add(new Edge(3, 4, 6));
        edges.add(new Edge(3, 5, -3));
        edges.add(new Edge(2, 4, 1));
        edges.add(new Edge(2, 6, 10));
        edges.add(new Edge(4, 6, 8));
        edges.add(new Edge(5, 6, 14));

        int[] dist = new int[V];

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;

        System.out.println("Initial Distances");

        for (int i = 0; i < V; i++) {
            System.out.print(zones[i] + "=" +
                    (dist[i] == Integer.MAX_VALUE ? "INF" : dist[i]) + "  ");
        }

        System.out.println("\n");

        for (int i = 1; i <= V - 1; i++) {

            boolean updated = false;

            for (Edge e : edges) {

                if (dist[e.src] != Integer.MAX_VALUE &&
                        dist[e.src] + e.weight < dist[e.dest]) {

                    dist[e.dest] = dist[e.src] + e.weight;
                    updated = true;
                }
            }

            System.out.println("Iteration " + i);

            for (int j = 0; j < V; j++) {
                System.out.print(zones[j] + "=" +
                        (dist[j] == Integer.MAX_VALUE ? "INF" : dist[j]) + "  ");
            }

            System.out.println("\n");

            if (!updated) {
                System.out.println("Converged Early at Iteration " + i);
                break;
            }
        }

        boolean negativeCycle = false;

        for (Edge e : edges) {
            if (dist[e.src] != Integer.MAX_VALUE &&
                    dist[e.src] + e.weight < dist[e.dest]) {
                negativeCycle = true;
                break;
            }
        }

        if (negativeCycle)
            System.out.println("Negative Cycle Detected");
        else
            System.out.println("No Negative Cycle Found");

        System.out.println("\nShortest Revenue Cost IND -> EC = " + dist[6]);
    }
}
