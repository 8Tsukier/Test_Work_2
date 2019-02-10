package ua.telesens.io;

import java.util.*;

public class TransportationNetwork<T> {
    // Флаг, используемый в методе toString().
    private static boolean debug = false;

    public static boolean isDebug() {
        return debug;
    }

    public static void setDebug(boolean debug) {
        TransportationNetwork.debug = debug;
    }

    private List<Route<T>> routes = new ArrayList<>();

    public void addRoute(Route<T> route) {
        routes.add(route);
    }

    public final int routesCount(T node) {
        int count = 0;
        for (Route<T> route : routes) {
            if (route.getFrom().equals(node) || route.getTo().equals(node)) {
                count++;
            }
        }
        return count;
    }

    public final List<T> getNodes() {
        List<T> nodes = new ArrayList<>();
        for (Route<T> route : routes) {
            T node = route.getFrom();
            if (Collections.frequency(nodes, node) == 0) {
                nodes.add(node);
            }
            node = route.getTo();
            if (Collections.frequency(nodes, node) == 0) {
                nodes.add(node);
            }
        }
        return nodes;
    }

    public Set<T> getNeighbors(T node) {
        Set<T> result = new HashSet<>();
        for (Route<T> route : routes) {
            if (route.getFrom().equals(node)) {
                result.add(route.getTo());
            }
            if (route.getTo().equals(node)) {
                result.add(route.getFrom());
            }
        }
        return result;
    }

    public Set<Route<T>> getAdjacentRoutes(T node) {
        Set<Route<T>> result = new HashSet<>();
        for (Route<T> route : routes) {
            if (route.getFrom().equals(node)) {
                result.add(route);
            }
            if (route.getTo().equals(node)) {
                result.add(route);
            }
        }
        return result;
    }

    public T getOppositeEnd(Route<T> route, T node) {
        if (route.getFrom().equals(node)) {
            return route.getTo();
        }
        if (route.getTo().equals(node)) {
            return route.getFrom();
        }
        return null;
    }

    public final List<T> sortedNodes() {
        List<T> nodes = getNodes();
        nodes.sort(new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                return Integer.compare(routesCount(o1), routesCount(o2));
            }
        });
        return nodes;
    }

    public Route<T> TheMostHighSpeed() {
        return Collections.max(routes, new Comparator<Route<T>>() {
            @Override
            public int compare(Route<T> r1, Route<T> r2) {
                return Double.compare(r1.speed(), r2.speed());
            }
        });
    }

    public String calcDijkstra(T start, T end) {
        Dijkstra dijkstra = new Dijkstra(start, end);
        dijkstra.fillMap(start);
        dijkstra.calcDijkstra();
        return dijkstra.getPath();
    }

    private class Dijkstra {
        private class NodeData {
            int pathLength;
            boolean processed;
            T previous;

            NodeData(int pathLength) {
                this.pathLength = pathLength;
                this.processed = false;
                previous = null;
            }
        }

        private T start = null;
        private T end = null;
        private int length = 0;
        private String path = "";
        private Map<T, NodeData> nodes = new HashMap<>();

        Dijkstra(T start, T end) {
            this.start = start;
            this.end = end;
            List<T> listOfNodes = getNodes();
            for (T node : listOfNodes) {
                if (!start.equals(node)) {
                    nodes.put(node, new NodeData(Integer.MAX_VALUE));
                }
            }
            nodes.put(start, new NodeData(0));
        }

        int getLength() {
            return length;
        }

        String getPath() {
            return path;
        }

        void calcDijkstra() {
            fillMap(start);
            path = end.toString();
            T node = end;
            while (node != start) {
                NodeData nodeData = nodes.get(node);
                node = nodeData.previous;
                path = node + " -> " + path;
            }
        }

        private void fillMap(T currentNode) {
            NodeData nodeData = nodes.get(currentNode);
            Set<Route<T>> adjacent = getAdjacentRoutes(currentNode);
            PriorityQueue<T> neighbors = new PriorityQueue<>(
                    Comparator.comparingDouble(n -> nodes.get(n).pathLength));
            for (Route<T> route : adjacent) {
                T neighbor = getOppositeEnd(route, currentNode);
                NodeData neighborNodeData = nodes.get(neighbor);
                if (neighborNodeData.processed) {
                    continue;
                }
                neighbors.add(neighbor);
                int newTime = nodeData.pathLength + route.getTime();
                if (neighborNodeData.pathLength > newTime) {
                    neighborNodeData.pathLength = newTime;
                    neighborNodeData.previous = currentNode;
                }
            }
            if (isDebug()) {
                print();
            }
            nodeData.processed = true;
            T node;
            while ((node = neighbors.poll()) != null) {
                fillMap(node);
            }
        }

        void print() {
            for (Map.Entry<T, NodeData> entry : nodes.entrySet()) {
                System.out.print(entry.getKey() + " " + entry.getValue().pathLength + "  ");
            }
            System.out.println();
        }
    }
}