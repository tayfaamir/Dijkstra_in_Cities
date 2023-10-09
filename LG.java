import java.util.*;
public class LG {

    static class Node implements Comparable<Node> {
        int u;
        int d;
        public Node(int u, int d) {
            this.u = u;
            this.d = d;
        }
        public int compareTo(Node other) {
            return Integer.compare(d, other.d);
        }

        public int getU() {
            return u;
        }

        public void setU(int u) {
            this.u = u;
        }

        public int getD() {
            return d;
        }

        public void setD(int d) {
            this.d = d;
        }
    }

    private static final int INF = Integer.MAX_VALUE;


    private static int[] dijkstra(List<List<Edge>> adjList, int start) {
        int n = adjList.size();
        int[] dist = new int[n];
        Arrays.fill(dist, INF);
        dist[start] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            int u = curr.u;
            int d = curr.d;
            if (d > dist[u]) {
                continue;
            }
            for (Edge e : adjList.get(u)) {
                int v = e.a2;
                int w = e.a1;
                if (dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                    pq.offer(new Node(v, dist[v]));
                }
            }
        }
        return dist;
    }
    //  public static void farthestCity(int[][] graph, int start, int[] vertexes) {
    //
    //        for (int i = 0; i < vertexes.length; i++) {
    //            if (longestPath(graph, start, vertexes[i]) == farthest) numOFs++;
    //            if (longestPath(graph, start, vertexes[i]) > farthest && longestPath(graph, start, vertexes[i]) != Integer.MAX_VALUE) {
    //                farthest = longestPath(graph, start, vertexes[i]);
    //                numOFs = 0;
    //            }
    //        }
    //    }
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int q = scanner.nextInt();

        List<List<Edge>> adjList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }


        for (int i = 0; i < m; i++) {
            int u = scanner.nextInt() ;
            int v = scanner.nextInt() ;
            int w = scanner.nextInt();
            adjList.get(u).add(new Edge(v, w));
            adjList.get(v).add(new Edge(u, w));
        }


        for (int i = 0; i < q; i++) {
            int start = scanner.nextInt() ;
            int[] dist = dijkstra(adjList, start);
            int farthest = -1;
            int numFarthest = 0;
            for (int j = 0; j < n; j++) {
                if (dist[j] != INF) {
                    if (dist[j] > farthest) {
                        farthest = dist[j];
                        numFarthest = 1;
                    } else if (dist[j] == farthest) {
                        numFarthest++;
                    }
                }
            }
            System.out.println("(" + (start ) + ", " + farthest + ", " + numFarthest + ")");
        }

        scanner.close();
    }



    static class Edge {

        int a1;
        int a2;
        public Edge(int v, int w) {
            this.a2 = v;
            this.a1 = w;
        }

        public int getA1() {
            return a1;
        }

        public void setA1(int a1) {
            this.a1 = a1;
        }

        public int getA2() {
            return a2;
        }

        public void setA2(int a2) {
            this.a2 = a2;
        }
    }



}