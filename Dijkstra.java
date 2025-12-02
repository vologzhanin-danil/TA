import java.util.Arrays;

public class Dijkstra {

    static final int INF = Integer.MAX_VALUE;

    public static void dijkstra(int[][] graph, int start) {
        int n = graph.length;
        int[] dist = new int[n];
        boolean[] used = new boolean[n];
        int[] pred = new int[n];

        Arrays.fill(dist, INF);
        Arrays.fill(pred, -1);
        dist[start] = 0;

        for (int step = 0; step < n; step++) {

            int v = -1;
            for (int i = 0; i < n; i++) {
                if (!used[i] && (v == -1 || dist[i] < dist[v])) {
                    v = i;
                }
            }

            if (dist[v] == INF) break;
            used[v] = true;

            for (int u = 0; u < n; u++) {
                if (graph[v][u] > 0) {
                    if (dist[v] + graph[v][u] < dist[u]) {
                        dist[u] = dist[v] + graph[v][u];
                        pred[u] = v;
                    }
                }
            }
        }

        System.out.println("dist: " + Arrays.toString(dist));
        System.out.println("pred: " + Arrays.toString(pred));
    }

    public static void main(String[] args) {

        int[][] G = {
            {0,2,7,5,0,0,1,0},
            {2,0,0,0,4,0,0,0},
            {7,0,0,0,3,5,9,0},
            {5,0,0,0,6,4,0,0},
            {0,4,3,6,0,3,0,0},
            {0,0,5,4,3,0,0,1},
            {1,0,9,0,0,0,0,6},
            {0,0,0,0,0,1,6,0}
        };

        dijkstra(G, 0); // старт с вершины 1
    }
}
