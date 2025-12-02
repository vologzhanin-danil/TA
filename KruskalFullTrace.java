import java.util.*;

public class KruskalFullTrace {

    static class Edge {
        int u, v, w;
        Edge(int u, int v, int w){ this.u=u; this.v=v; this.w=w; }
    }

    static int[] parent = new int[20];
    static int[] rank = new int[20];

    static int find(int x){
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    static void union(int a, int b){
        a = find(a);
        b = find(b);
        if (a != b){
            if (rank[a] < rank[b]) parent[a] = b;
            else if (rank[b] < rank[a]) parent[b] = a;
            else { parent[b] = a; rank[a]++; }
        }
    }

    public static void main(String[] args) {

        List<Edge> edges = new ArrayList<>();

        edges.add(new Edge(1,7,1));
        edges.add(new Edge(6,8,1));
        edges.add(new Edge(1,2,2));
        edges.add(new Edge(3,5,3));
        edges.add(new Edge(5,6,3));
        edges.add(new Edge(2,5,4));
        edges.add(new Edge(4,6,4));
        edges.add(new Edge(1,4,5));
        edges.add(new Edge(3,6,5));
        edges.add(new Edge(4,5,6));
        edges.add(new Edge(7,8,6));
        edges.add(new Edge(1,3,7));
        edges.add(new Edge(3,7,9));

        for (int i = 1; i <= 8; i++){
            parent[i] = i;
            rank[i] = 0;
        }

        edges.sort(Comparator.comparingInt(e -> e.w));

        System.out.println("==== ТРАСУВАННЯ АЛГОРИТМУ КРУСКАЛА ====\n");

        int total = 0;
        List<Edge> mst = new ArrayList<>();

        for (Edge e : edges){
            int fu = find(e.u);
            int fv = find(e.v);

            System.out.println("Перевіряємо ребро: " + e.u + "-" + e.v + " (вага = " + e.w + ")");

            if (fu != fv){
                System.out.println("  Беремо ребро: " + e.u + "-" + e.v);
                mst.add(e);
                total += e.w;
                union(fu, fv);
            } else {
                System.out.println("  Пропускаємо (утворює цикл)");
            }
        }

        System.out.println("\n==== РЕЗУЛЬТАТ МКД (КРУСКАЛ) ====");
        for (Edge e : mst){
            System.out.println(e.u + "-" + e.v + " = " + e.w);
        }
        System.out.println("Сумарна вага = " + total);
    }
}
