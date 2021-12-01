package com.jbgames.game.entities;

import java.util.HashMap;
import java.util.Map;

public class TileBuilder {
    private static Map<Integer, Tiles> map = new HashMap<>();

    public enum Tiles {
        GRAY_TILE("gray_tile.png", 0, true),
        BLUE_TILE("blue_tile.png", 1, true),
        RED_TILE("red_tile.png", 2, true),
        GREEN_TILE("green_tile.png", 3, true),
        WALL_TILE("wall_tile.png", 4, false);
        String path;
        int id;
        boolean passable;

        Tiles(String path, int id, boolean passable) {
            this.path = path;
            this.id = id;
            this.passable = passable;
            map.put(id, this);
        }

        public static Tiles getTileTypeById(int id) {
            return map.get(id);
        }

        public int id() {
            return id;
        }

        public String texturePath() {
            return path;
        }

        public boolean isPassable() {
            return passable;
        }

    }
}
