package graph;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Graph {
    public int nodes, edges;
    public Node[][] adjMat;
    public List<List<Node>> adjList;
    public Map<Integer, List<Node>> adjMap;

    private static class Node {
        public int id;
        public String name;

        public Node(int d, String name) {
            id = d; this.name = name;
        }

        public String print() {
            return ("(Id: " + id + ", Name: " + name + ")");
        }

        public static Node getNode(int id, String name) {
            return new Node(id, name);
        }
    }

    public Graph(int v) {
        this(v, 0);
    }

    public Graph(int v, int e) {
        nodes = v; edges = e;
        adjMat = new Node[v][v];
        // what below line does is create a single L and then add that same ref to v copies 
        // Collections.nCopies(n, obj) → same object repeated n times.
        //adjList = new ArrayList<>(Collections.nCopies(v, new ArrayList<Node>()));

        /* old way
        adjList = new ArrayList<>(v);
        for (int i = 0; i < v; i++) {
            adjList.add(new ArrayList<Node>());
        } */
        
        adjList = new ArrayList<>(IntStream.range(0, v)
            .mapToObj(i -> new ArrayList<Node>())
            .collect(Collectors.toList()));

        adjMap = new HashMap<>();
    }

    public void addUndirectedEdge(Node x, Node y) {
        addDirectedEdge(x, y); 
        addDirectedEdge(y, x);
    }

    public void addDirectedEdge(Node x, Node y) {
        edges++;
        adjMat[x.id][y.id] = y;

        adjList.get(x.id).add(y);

        if (!adjMap.containsKey(x.id)) {
            List<Node> te = new ArrayList<>();
            te.add(y);
            adjMap.put(x.id, te);
        } else {
            adjMap.get(x.id).add(y);
        }
    }

    public void print() {
        System.out.println("Graph using adjMat is : ");
        for (int i = 0; i < nodes; i++) {
            System.out.print("For " + i + "    ");
            for (int j = 0; j < nodes; j++) {
                String str = null;
                if (adjMat[i][j] == null)
                    str = "null";
                else str = adjMat[i][j].print();
                System.out.print(str + "    ");
            }
            System.out.println();
        }

        System.out.println("\n\nGraph using adjList is : ");
        adjList.stream().forEach(list -> {
            //System.out.print("For " + key + "    ");
            list.stream().forEach(node -> {
                System.out.print(node.print() + "    ");
            });
            System.out.println();
        });

        System.out.println("\n\nGraph using adjMap is : ");
        adjMap.forEach((key, list) -> {
            System.out.print("For " + key + "    ");
            list.stream().forEach(node -> {
                System.out.print(node.print() + "    ");
            });
            System.out.println();
        });
    }

    public void bfs(Node src) {
        if (src == null) return;
        boolean[] visited = new boolean[nodes];
        Queue<Node> q = new LinkedList<>();
        q.offer(src);
        visited[src.id] = true;

        System.out.print("\n\nBFS with src " + src.print() + " is    ");
        while (!q.isEmpty()) {
            Node n = q.poll();
            System.out.print(n.print() + "    ");

            adjMap.get(n.id).forEach(node -> {
                if (!visited[node.id]) {
                    visited[node.id] = true;
                    q.offer(node);
                }
            });
        }
        System.out.println();
    }

    public void dfs(Node src) {
        if (src == null) return;
        boolean[] visited = new boolean[nodes];
        Stack<Node> stack = new Stack<>();
        stack.push(src);
        visited[src.id] = true;

        System.out.print("\n\nDFS with src " + src.print() + " is    ");
        while (!stack.isEmpty()) {
            Node n = stack.pop();
            System.out.print(n.print() + "    ");

            adjMap.get(n.id).forEach(node -> {
                if (!visited[node.id]) {
                    visited[node.id] = true;
                    stack.push(node);
                }
            });
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        Graph graph = new Graph(3);
        Node pranav = Node.getNode(0, "Pranav");
        Node riya = Node.getNode(1, "Riya");
        Node priya =  Node.getNode(2, "Priya");

        graph.addUndirectedEdge(pranav, riya);
        graph.addDirectedEdge(pranav, priya);
        graph.addUndirectedEdge(riya, priya);

        graph.print();
        graph.bfs(priya);
        graph.bfs(pranav);
        graph.bfs(riya);

        graph.dfs(priya);
        graph.dfs(pranav);
        graph.dfs(riya);
    }
}
