package com.jbgames.game.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jbgames.game.entities.GameObject;
import com.jbgames.game.entities.Tile;
import com.jbgames.game.helpers.Position;
import com.jbgames.game.levels.Level;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static com.jbgames.game.GameData.*;
import static com.jbgames.game.entities.TileBuilder.Tiles.*;

public class Renderer {

    private final SpriteBatch batch;
    private final OrthographicCamera cam;

    private Level level;
    private Map<Integer, Tile> tiles;

    public Renderer() {
        tiles = new HashMap<>();
        batch = new SpriteBatch();
        cam = new OrthographicCamera();
        cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.setProjectionMatrix(cam.combined);
    }


    public void setLevel(Level level) {
        this.level = level;
    }

    public void load() {
        Set<Integer> set = new HashSet<>();
        for (int[] row : level.getMap()) {
            for (int n : row) set.add(n);
        }
        for (int n : set) tiles.put(n, new Tile(getTileTypeById(n)));
        tiles.put(RED_TILE.id(), new Tile(RED_TILE));
    }

    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        int[][] map = level.getMap();
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                batch.draw(tiles.get(map[y][x]).getTexture(), x * TILE_WIDTH + OFFSET_X, y * TILE_HEIGHT + OFFSET_Y);
            }
        }
        for(Position pos : level.getPath()) {
            batch.draw(tiles.get(RED_TILE.id()).getTexture(), pos.screenX(), pos.screenY());
        }
        for (GameObject object : level.getGameObjects()) {
            Position pos = object.getPos();
            batch.draw(object.getTexture(), pos.screenX(), pos.screenY());
        }
        batch.end();
    }

}
