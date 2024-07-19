package com.awaion.demo031.data_structure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphDemo {
    private Map<Integer, List<Integer>> adjacencyList;

    public GraphDemo() {
        adjacencyList = new HashMap<>();
    }

    // 添加节点,如果节点已存在则不执行任何操作
    public void addVertex(int vertex) {
        adjacencyList.putIfAbsent(vertex, new ArrayList<>());
    }

    // 添加边,如果节点不存在则先添加节点
    public void addEdge(int source, int destination) {
        addVertex(source);
        addVertex(destination);
        adjacencyList.get(source).add(destination);
        adjacencyList.get(destination).add(source); // 对于无向图,两边都要添加
    }

    // 打印图的邻接列表表示
    public void printGraph() {
        for (Map.Entry<Integer, List<Integer>> entry : adjacencyList.entrySet()) {
            int vertex = entry.getKey();
            List<Integer> neighbors = entry.getValue();
            System.out.println("Vertex " + vertex + " is connected to " + neighbors);
        }
    }

    public static void main(String[] args) {
        GraphDemo graphDemo = new GraphDemo();

        // 添加节点
        graphDemo.addVertex(1);
        graphDemo.addVertex(2);
        graphDemo.addVertex(3);
        graphDemo.addVertex(4);

        // 添加边
        graphDemo.addEdge(1, 2);
        graphDemo.addEdge(1, 3);
        graphDemo.addEdge(2, 3);
        graphDemo.addEdge(3, 4);

        // 打印图
        graphDemo.printGraph();
    }
}
