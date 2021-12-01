package com.jbgames.game.helpers;


import java.util.*;

import static com.jbgames.game.entities.TileBuilder.Tiles;
import static com.jbgames.game.entities.TileBuilder.Tiles.getTileTypeById;

public class PathFinder {

    private final List<Node> nodes;

    public PathFinder(int[][] map) {
        nodes = new ArrayList<>();
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                nodes.add(new Node(x, y, getTileTypeById(map[y][x])));
            }
        }
        for (Node n : nodes) {
            n.addAdjacent(nodes);
        }
        calculateDistance();
    }

    public void updateNodes(int[][] map) {
        for(Node n : nodes) {
            n.tile = getTileTypeById(map[n.y][n.x]);
        }
    }

    private void calculateDistance() {
        for (Node n : nodes) {
            int distance = 1;
            Queue<Node> queue = new LinkedList<>(n.adjacent);
            List<Node> visited = new ArrayList<>();
            visited.add(n);
            n.distToNodes.put(n, 0);
            while (!queue.isEmpty()) {
                Queue<Node> temp = new LinkedList<>();
                while (!queue.isEmpty()) {
                    Node a = queue.poll();
                    if(!a.tile.isPassable()) continue;
                    temp.add(a);
                    visited.add(a);
                    n.distToNodes.put(a, distance);
                }
                for (Node nn : temp) {
                    for (Node a : nn.adjacent) {
                        if (!visited.contains(a) && !queue.contains(a)) queue.add(a);
                    }
                }
                distance++;
            }
        }
    }



    public List<Position> getPath(Position startPos, Position goalPos) {
        for(Node n : nodes) n.score = Integer.MAX_VALUE;
        final Node start = getNodeByPosition(startPos);
        final Node goal = getNodeByPosition(goalPos);
        List<Node> path = new ArrayList<>();
        PriorityQueue<Node> queue = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return Integer.compare(o1.distToNodes.get(goal), o2.distToNodes.get(goal));
            }
        });
        queue.addAll(start.adjacent);
        int distance = 1;
        while (!queue.isEmpty()) {
            Node n = queue.poll();
            if(!n.tile.isPassable() || path.contains(n)) continue;
            n.score = Math.min(n.score, distance);
            distance++;
            path.add(n);
            queue.addAll(n.adjacent);
            if(n == goal) break;
        }
        queue = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return Integer.compare(o1.score, o2.score);
            }
        });
        queue.addAll(path);
        List<Position> pathPos = new ArrayList<>();
        Node node = start;
        while (node != goal) {
            node = queue.poll();
            pathPos.add(new Position(node.x, node.y));
        }
        return pathPos;
    }

    private Node getNodeByPosition(Position pos) {
        for (Node n : nodes) {
            if (n.x == pos.X() && n.y == pos.Y()) return n;
        }
        return null;
    }


    private static class Node {
        int x, y;
        List<Node> adjacent;
        Tiles tile;
        Map<Node, Integer> distToNodes;
        int score = Integer.MAX_VALUE;

        public Node(int x, int y, Tiles tile) {
            this.x = x;
            this.y = y;
            this.tile = tile;
            adjacent = new ArrayList<>();
            distToNodes = new HashMap<>();
        }

        private void addAdjacent(List<Node> nodes) {
            for (Node n : nodes) {
                if (n.x == x && n.y == y + 1) adjacent.add(n);
                if (n.x == x && n.y == y - 1) adjacent.add(n);
                if (n.x == x + 1 && n.y == y) adjacent.add(n);
                if (n.x == x - 1 && n.y == y) adjacent.add(n);
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return x == node.x && y == node.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

}
