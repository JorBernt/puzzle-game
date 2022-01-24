package com.jbgames.game.helpers;

import com.jbgames.game.entities.Player;
import com.jbgames.game.entities.Tile;
import com.jbgames.game.entities.TileBuilder;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import static com.jbgames.game.entities.TileBuilder.Tiles.getTileTypeById;

public class AStar {
    private final List<Node> open;
    private final List<Node> closed;
    private final List<Node> path;
    private final Tile[][] maze;
    private Node now;
    private final int xstart;
    private final int ystart;
    private int xend, yend;
    private final boolean diag;

    // Node class for convienience
    static class Node implements Comparable {
        public Node parent;
        public int x, y;
        public double g;
        public double h;
        Node(Node parent, int xpos, int ypos, double g, double h) {
            this.parent = parent;
            this.x = xpos;
            this.y = ypos;
            this.g = g;
            this.h = h;
        }
        // Compare by f value (g + h)
        @Override
        public int compareTo(Object o) {
            Node that = (Node) o;
            return (int)((this.g + this.h) - (that.g + that.h));
        }
    }

    public AStar(Tile[][] maze, Position pos, boolean diag) {
        this.open = new ArrayList<>();
        this.closed = new ArrayList<>();
        this.path = new ArrayList<>();
        this.maze = maze;
        this.now = new Node(null, pos.X(), pos.Y(), 0, 0);
        this.xstart = pos.X();
        this.ystart = pos.Y();
        this.diag = diag;
    }
    /*
     ** Finds path to xend/yend or returns null
     **
     ** @param (int) xend coordinates of the target position
     ** @param (int) yend
     ** @return (List<Node> | null) the path
     */
    public Position findPathTo(Position playerPosition) {
        this.xend = playerPosition.X();
        this.yend = playerPosition.Y();
        this.closed.add(this.now);
        addNeigborsToOpenList();
        while (this.now.x != this.xend || this.now.y != this.yend) {
            if (this.open.isEmpty()) { // Nothing to examine
                return null;
            }
            this.now = this.open.get(0); // get first node (lowest f score)
            this.open.remove(0); // remove it
            this.closed.add(this.now); // and add to the closed
            addNeigborsToOpenList();
        }
        this.path.add(0, this.now);
        while (this.now.x != this.xstart || this.now.y != this.ystart) {
            this.now = this.now.parent;
            this.path.add(0, this.now);
        }
        return path.size() > 0 ? new Position(path.get(1).x, path.get(1).y) : null;
    }
    /*
     ** Looks in a given List<> for a node
     **
     ** @return (bool) NeightborInListFound
     */
    private static boolean findNeighborInList(List<Node> array, Node node) {
        for(Node n :array) {
            if(n.x == node.x && n.y == node.y) return true;
        }
        return false;
    }
    /*
     ** Calulate distance between this.now and xend/yend
     **
     ** @return (int) distance
     */
    private double distance(int dx, int dy) {
        if (this.diag) { // if diagonal movement is alloweed
            return Math.hypot(this.now.x + dx - this.xend, this.now.y + dy - this.yend); // return hypothenuse
        } else {
            return Math.abs(this.now.x + dx - this.xend) + Math.abs(this.now.y + dy - this.yend); // else return "Manhattan distance"
        }
    }
    private void addNeigborsToOpenList() {
        Node node;
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                if (!this.diag && x != 0 && y != 0) {
                    continue; // skip if diagonal movement is not allowed
                }
                node = new Node(this.now, this.now.x + x, this.now.y + y, this.now.g, this.distance(x, y));
                if ((x != 0 || y != 0) // not this.now
                        && this.now.x + x >= 0 && this.now.x + x < this.maze[0].length // check maze boundaries
                        && this.now.y + y >= 0 && this.now.y + y < this.maze.length
                        && this.maze[this.now.y + y][this.now.x + x].isPassable() // check if square is walkable
                        && !findNeighborInList(this.open, node) && !findNeighborInList(this.closed, node)
                        && maze[this.now.y + y][this.now.x + x].isPassable()) { // if not already done

                    node.g = node.parent.g + 1.; // Horizontal/vertical cost = 1.0
                    node.g += 1; // add movement cost for this square

                    // diagonal cost = sqrt(hor_cost² + vert_cost²)
                    // in this example the cost would be 12.2 instead of 11
                        /*
                        if (diag && x != 0 && y != 0) {
                            node.g += .4;	// Diagonal movement cost = 1.4
                        }
                        */
                    this.open.add(node);
                }
            }
        }
        Collections.sort(this.open);
    }
}