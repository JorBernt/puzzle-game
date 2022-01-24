package com.jbgames.game.levels;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jbgames.game.entities.Tile;

import static com.jbgames.game.GameData.*;
import static com.jbgames.game.GameData.OFFSET_Y;

public class Map  {

    Tile[][] map;

    public Map(Tile[][] map) {
        this.map = map;
    }

    public void render(SpriteBatch batch) {
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                map[y][x].render(batch);
            }
        }
    }

    public Tile[][] getMapLevel() {
        return map;
    }

}
