import java.util.*;

public class PrimFullTrace {

    static class Edge {
        int v, w;
        Edge(int v, int w){this.v=v; this.w=w;}
    }

    public static void main(String[] args) {

        int n = 8;

        List<Edge>[] g = new ArrayList[n+1];
        for (int i=0;i<=n;i++) g[i] = new ArrayList<>();

        add(g,1,2,2);
        add(g,1,3,7);
        add(g,1,4,5);
        add(g,1,7,1);
        add(g,2,5,4);
        add(g,3,5,3);
        add(g,3,6,5);
        add(g,3,7,9);
        add(g,4,5,6);
        add(g,4,6,4);
        add(g,5,6,3);
        add(g,6,8,1);
        add(g,7,8,6);

        boolean[] used = new boolean[n+1];
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));

        List<int[]> mst = new ArrayList<>();
        int total = 0;

        used[1] = true;
        for (Edge e : g[1]) pq.add(new int[]{1, e.v, e.w});

        System.out.println("==== ПОВНА ТРАСУВАННЯ АЛГОРИТМУ ПРІМА ====\n");
        System.out.println("Початкова вершина: 1");
        System.out.println("Додаємо всі ребра, що виходять із 1:");
        printPQ(pq);

        while (!pq.isEmpty()) {

            int[] cur = pq.poll();
            int u = cur[0], v = cur[1], w = cur[2];

            System.out.println("\nРозглядаємо ребро: " + u + "-" + v + " (вага = " + w + ")");

            if (used[v]) {
                System.out.println("Відхиляємо: вершина " + v + " вже у дереві, це створить цикл.");
                continue;
            }

            System.out.println("Обираємо ребро: " + u + "-" + v + " (мінімальна вага серед доступних)");
            used[v] = true;
            mst.add(cur);
            total += w;

            System.out.print("Поточний набір вершин у МКД: ");
            for (int i=1;i<=n;i++)
                if (used[i]) System.out.print(i + " ");
            System.out.println();

            System.out.println("Додаємо всі ребра, що виходять із вершини " + v + ":");
            for (Edge e : g[v]) {
                if (!used[e.v]) {
                    pq.add(new int[]{v, e.v, e.w});
                    System.out.println("  + " + v + "-" + e.v + " (вага = " + e.w + ")");
                }
            }

            System.out.println("\nАктуальна черга кандидатів:");
            printPQ(pq);
        }

        System.out.println("\n==== РЕЗУЛЬТАТ МКД (ПРІМ) ====");
        for (int[] e : mst) {
            System.out.println(e[0] + "-" + e[1] + " = " + e[2]);
        }
        System.out.println("Загальна вага = " + total);
    }

    static void printPQ(PriorityQueue<int[]> pq){
        List<int[]> list = new ArrayList<>(pq);
        list.sort(Comparator.comparingInt(a -> a[2]));
        for (int[] x : list){
            System.out.println("  кандидат: " + x[0] + "-" + x[1] + " (вага = " + x[2] + ")");
        }
    }

    static void add(List<Edge>[] g, int a, int b, int w){
        g[a].add(new Edge(b,w));
        g[b].add(new Edge(a,w));
    }
}
