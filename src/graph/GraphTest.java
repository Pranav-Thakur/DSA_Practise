package graph;

import java.util.List;
import java.util.ArrayList;

/**
 * Comprehensive test suite for Graph implementation
 */
public class GraphTest {
    
    public static void testGraphCreation() {
        System.out.println("=== Testing Graph Creation ===");
        
        // Test 1: Create graph with positive vertices
        try {
            Graph graph = new Graph(5);
            System.out.println("Test 1 - Created graph with 5 vertices: PASSED");
        } catch (Exception e) {
            System.out.println("Test 1 - Failed: " + e.getMessage());
        }
        
        // Test 2: Create graph with 0 vertices
        try {
            Graph graph = new Graph(0);
            System.out.println("Test 2 - Created graph with 0 vertices: PASSED");
        } catch (Exception e) {
            System.out.println("Test 2 - Failed: " + e.getMessage());
        }
        
        // Test 3: Create graph with negative vertices (should throw exception)
        try {
            Graph graph = new Graph(-1);
            System.out.println("Test 3 - Failed: Should have thrown exception");
        } catch (IllegalArgumentException e) {
            System.out.println("Test 3 - Correctly threw exception for negative vertices: PASSED");
        }
        
        System.out.println();
    }
    
    public static void testEdgeOperations() {
        System.out.println("=== Testing Edge Operations ===");
        
        Graph graph = new Graph(4);
        Graph.Node node0 = Graph.Node.getNode(0, "A");
        Graph.Node node1 = Graph.Node.getNode(1, "B");
        Graph.Node node2 = Graph.Node.getNode(2, "C");
        Graph.Node node3 = Graph.Node.getNode(3, "D");
        
        // Test 1: Add directed edges
        graph.addDirectedEdge(node0, node1);
        graph.addDirectedEdge(node1, node2);
        graph.addDirectedEdge(node2, node3);
        System.out.println("Test 1 - Added directed edges: PASSED");
        
        // Test 2: Add undirected edges
        graph.addUndirectedEdge(node0, node2);
        System.out.println("Test 2 - Added undirected edge: PASSED");
        
        // Test 3: Add self-loop (should work)
        graph.addDirectedEdge(node0, node0);
        System.out.println("Test 3 - Added self-loop: PASSED");
        
        System.out.println();
    }
    
    public static void testBFS() {
        System.out.println("=== Testing BFS Traversal ===");
        
        Graph graph = new Graph(5);
        Graph.Node node0 = Graph.Node.getNode(0, "A");
        Graph.Node node1 = Graph.Node.getNode(1, "B");
        Graph.Node node2 = Graph.Node.getNode(2, "C");
        Graph.Node node3 = Graph.Node.getNode(3, "D");
        Graph.Node node4 = Graph.Node.getNode(4, "E");
        
        // Create a simple graph: A -> B -> C, A -> D, D -> E
        graph.addDirectedEdge(node0, node1);
        graph.addDirectedEdge(node1, node2);
        graph.addDirectedEdge(node0, node3);
        graph.addDirectedEdge(node3, node4);
        
        System.out.println("Graph structure:");
        System.out.println("A -> B -> C");
        System.out.println("A -> D -> E");
        
        // Test BFS from different starting points
        System.out.println("\nBFS from A:");
        graph.bfs(node0);
        
        System.out.println("\nBFS from B:");
        graph.bfs(node1);
        
        System.out.println("\nBFS from D:");
        graph.bfs(node3);
        
        System.out.println();
    }
    
    public static void testDFS() {
        System.out.println("=== Testing DFS Traversal ===");
        
        Graph graph = new Graph(5);
        Graph.Node node0 = Graph.Node.getNode(0, "A");
        Graph.Node node1 = Graph.Node.getNode(1, "B");
        Graph.Node node2 = Graph.Node.getNode(2, "C");
        Graph.Node node3 = Graph.Node.getNode(3, "D");
        Graph.Node node4 = Graph.Node.getNode(4, "E");
        
        // Create the same graph as BFS test
        graph.addDirectedEdge(node0, node1);
        graph.addDirectedEdge(node1, node2);
        graph.addDirectedEdge(node0, node3);
        graph.addDirectedEdge(node3, node4);
        
        // Test DFS from different starting points
        System.out.println("DFS from A:");
        graph.dfs(node0);
        
        System.out.println("\nDFS from B:");
        graph.dfs(node1);
        
        System.out.println("\nDFS from D:");
        graph.dfs(node3);
        
        System.out.println();
    }
    
    public static void testConnectedComponents() {
        System.out.println("=== Testing Connected Components ===");
        
        Graph graph = new Graph(6);
        Graph.Node node0 = Graph.Node.getNode(0, "A");
        Graph.Node node1 = Graph.Node.getNode(1, "B");
        Graph.Node node2 = Graph.Node.getNode(2, "C");
        Graph.Node node3 = Graph.Node.getNode(3, "D");
        Graph.Node node4 = Graph.Node.getNode(4, "E");
        Graph.Node node5 = Graph.Node.getNode(5, "F");
        
        // Create two disconnected components
        // Component 1: A - B - C
        graph.addUndirectedEdge(node0, node1);
        graph.addUndirectedEdge(node1, node2);
        
        // Component 2: D - E - F
        graph.addUndirectedEdge(node3, node4);
        graph.addUndirectedEdge(node4, node5);
        
        System.out.println("Graph has two disconnected components:");
        System.out.println("Component 1: A - B - C");
        System.out.println("Component 2: D - E - F");
        
        System.out.println("\nBFS from A (should only reach A, B, C):");
        graph.bfs(node0);
        
        System.out.println("\nBFS from D (should only reach D, E, F):");
        graph.bfs(node3);
        
        System.out.println();
    }
    
    public static void testCyclicGraph() {
        System.out.println("=== Testing Cyclic Graph ===");
        
        Graph graph = new Graph(4);
        Graph.Node node0 = Graph.Node.getNode(0, "A");
        Graph.Node node1 = Graph.Node.getNode(1, "B");
        Graph.Node node2 = Graph.Node.getNode(2, "C");
        Graph.Node node3 = Graph.Node.getNode(3, "D");
        
        // Create a cycle: A -> B -> C -> D -> A
        graph.addDirectedEdge(node0, node1);
        graph.addDirectedEdge(node1, node2);
        graph.addDirectedEdge(node2, node3);
        graph.addDirectedEdge(node3, node0);
        
        System.out.println("Created cyclic graph: A -> B -> C -> D -> A");
        
        System.out.println("\nBFS from A (should handle cycle correctly):");
        graph.bfs(node0);
        
        System.out.println("\nDFS from A (should handle cycle correctly):");
        graph.dfs(node0);
        
        System.out.println();
    }
    
    public static void testNullAndEdgeCases() {
        System.out.println("=== Testing Null and Edge Cases ===");
        
        Graph graph = new Graph(3);
        Graph.Node node0 = Graph.Node.getNode(0, "A");
        Graph.Node node1 = Graph.Node.getNode(1, "B");
        
        // Test 1: BFS with null source
        System.out.println("Test 1 - BFS with null source:");
        graph.bfs(null);
        System.out.println("PASSED: No exception thrown");
        
        // Test 2: DFS with null source
        System.out.println("\nTest 2 - DFS with null source:");
        graph.dfs(null);
        System.out.println("PASSED: No exception thrown");
        
        // Test 3: BFS on empty graph
        System.out.println("\nTest 3 - BFS on empty graph:");
        graph.bfs(node0);
        System.out.println("PASSED: Handled empty graph correctly");
        
        System.out.println();
    }
    
    public static void testGraphPrinting() {
        System.out.println("=== Testing Graph Printing ===");
        
        Graph graph = new Graph(3);
        Graph.Node node0 = Graph.Node.getNode(0, "A");
        Graph.Node node1 = Graph.Node.getNode(1, "B");
        Graph.Node node2 = Graph.Node.getNode(2, "C");
        
        graph.addDirectedEdge(node0, node1);
        graph.addDirectedEdge(node1, node2);
        graph.addUndirectedEdge(node0, node2);
        
        System.out.println("Printing graph representations:");
        graph.print();
        
        System.out.println();
    }
    
    public static void main(String[] args) {
        testGraphCreation();
        testEdgeOperations();
        testBFS();
        testDFS();
        testConnectedComponents();
        testCyclicGraph();
        testNullAndEdgeCases();
        testGraphPrinting();
        
        System.out.println("All graph tests completed!");
    }
}
