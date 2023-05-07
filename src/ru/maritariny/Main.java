package ru.maritariny;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(reader.readLine());
        List<String> str = new ArrayList<>(t);
        for (int i = 0; i < t; i++) {
            str.add(reader.readLine());
        }
        int numberOfEdges = 0;
        Map<String, List<Edge>> graph = new HashMap<>();
        Set<String> allVertex = new HashSet<>();
        for (String cur : str) {
            for (int i = 0; i < cur.length() - 3; i++) {
                String substr = cur.substring(i, i + 3);
                String substr2 = cur.substring(i + 1, i + 4);
                allVertex.add(substr);
                allVertex.add(substr2);
                List<Edge> edges = graph.get(substr);
                if (edges == null) {
                    edges = new ArrayList<>();
                    Edge edge = new Edge(substr2, 1);
                    edges.add(edge);
                    numberOfEdges++;
                } else {
                    boolean found = false;
                    for (Edge ed : edges) {
                        if (ed.vertex.equals(substr2)) {
                            ed.weight++;
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        Edge ed = new Edge(substr2, 1);
                        edges.add(ed);
                        numberOfEdges++;
                    }
                }
                graph.put(substr, edges);
            }
        }
        System.out.println(allVertex.size());
        System.out.println(numberOfEdges);
        for (String key : graph.keySet()) {
            List<Edge> edges = graph.get(key);
            for (Edge ed : edges) {
                System.out.println(key + " " + ed.vertex + " " + ed.weight);
            }
        }
        reader.close();
    }
}

class Edge implements Comparable<Edge> {
    public String vertex;
    public Integer weight;

    public Edge(String vertex, Integer weight) {
        this.vertex = vertex;
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return vertex.equals(edge.vertex) && weight == edge.weight;
    }

    @Override
    public int hashCode() {
        return Objects.hash(vertex, weight);
    }

    @Override
    public int compareTo(Edge o) {
        return weight.compareTo(o.weight);
    }
}
