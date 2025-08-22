package graph;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Comprehensive Graph Implementation
 * 
 * This class provides a complete graph data structure with multiple representations:
 * - Adjacency Matrix
 * - Adjacency List
 * - Adjacency Map
 * 
 * Supports both directed and undirected graphs with BFS and DFS traversal algorithms.
 * 
 * Time Complexity:
 * - Add Edge: O(1)
 * - BFS/DFS: O(V + E) where V is vertices and E is edges
 * - Space: O(V^2) for adjacency matrix, O(V + E) for adjacency list/map
 */
public class Graph {
    private int nodes, edges;
    private Node[][] adjMat;
    private List<List<Node>> adjList;
    private Map<Integer, List<Node>> adjMap;

    /**
     * Node class representing a vertex in the graph
     */
    public static class Node {
        private int id;
        private String name;

        public Node(int id, String name) {
            this.id = id; 
            this.name = name;
        }

        public int getId() { return id; }
        public String getName() { return name; }

        @Override
        public String toString() {
            return "(Id: " + id + ", Name: " + name + ")";
        }

        public static Node getNode(int id, String name) {
            return new Node(id, name);
        }
    }

    /**
     * Constructor for a graph with given number of vertices
     * @param vertices Number of vertices in the graph
     */
    public Graph(int vertices) {
        this(vertices, 0);
    }

    /**
     * Constructor for a graph with given number of vertices and edges
     * @param vertices Number of vertices in the graph
     * @param edges Number of edges (for documentation purposes)
     */
    public Graph(int vertices, int edges) {
        if (vertices < 0) {
            throw new IllegalArgumentException("Number of vertices cannot be negative");
        }
        
        this.nodes = vertices; 
        this.edges = 0; // Start with 0 edges
        
        // Initialize adjacency matrix
        adjMat = new Node[vertices][vertices];
        
        // Initialize adjacency list using streams for better performance
        adjList = new ArrayList<>(IntStream.range(0, vertices)
            .mapToObj(i -> new ArrayList<Node>())
            .collect(Collectors.toList()));

        // Initialize adjacency map
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
                else str = adjMat[i][j].toString();
                System.out.print(str + "    ");
            }
            System.out.println();
        }

        System.out.println("\n\nGraph using adjList is : ");
        adjList.stream().forEach(list -> {
            //System.out.print("For " + key + "    ");
            list.stream().forEach(node -> {
                System.out.print(node.toString() + "    ");
            });
            System.out.println();
        });

        System.out.println("\n\nGraph using adjMap is : ");
        adjMap.forEach((key, list) -> {
            System.out.print("For " + key + "    ");
            list.stream().forEach(node -> {
                System.out.print(node.toString() + "    ");
            });
            System.out.println();
        });
    }

    public void bfs(Node src) {
        if (src == null) return;
        boolean[] visited = new boolean[nodes];
        Queue<Node> q = new LinkedList<>();
        q.offer(src);
        visited[src.getId()] = true;

        System.out.print("\n\nBFS with src " + src.toString() + " is    ");
        while (!q.isEmpty()) {
            Node n = q.poll();
            System.out.print(n.toString() + "    ");

            adjMap.get(n.getId()).forEach(node -> {
                if (!visited[node.getId()]) {
                    visited[node.getId()] = true;
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
        visited[src.getId()] = true;

        System.out.print("\n\nDFS with src " + src.toString() + " is    ");
        while (!stack.isEmpty()) {
            Node n = stack.pop();
            System.out.print(n.toString() + "    ");

            adjMap.get(n.getId()).forEach(node -> {
                if (!visited[node.getId()]) {
                    visited[node.getId()] = true;
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
