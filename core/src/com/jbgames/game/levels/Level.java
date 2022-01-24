package com.jbgames.game.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.jbgames.game.entities.Enemy;
import com.jbgames.game.entities.GameObject;
import com.jbgames.game.entities.Player;
import com.jbgames.game.entities.Tile;
import com.jbgames.game.helpers.Directions;
import com.jbgames.game.helpers.Position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.jbgames.game.entities.TileBuilder.Tiles.*;
import static com.jbgames.game.helpers.Directions.*;

public class Level {

    private final Map map;
    private final Tile[][] mapLevel;
    private final Player player;
    private final List<Enemy> enemies;
    private final List<GameObject> gameObjects;
    private final Position goal;

    public Level() {
        mapLevel = new Tile[11][11];
        enemies = new ArrayList<>();
        mapLevel[0][5] = new Tile(0, 5, GREEN_TILE);
        for(int y = 0; y < mapLevel.length; y++) {
            for(int x = 0; x < mapLevel[y].length; x++) {
                mapLevel[y][x] = new Tile(x, y, Math.random()*100 < 30 ? WALL_TILE : GRAY_TILE);
            }
        }
        goal = new Position(5, 10);
        mapLevel[10][5] = new Tile(10, 5, BLUE_TILE);
        map = new Map(mapLevel);
        player = new Player(new Position(5, 0, 0, 10));
        enemies.add(new Enemy(new Position(8, 8, 0, 10)));
        enemies.add(new Enemy(new Position(3, 8, 0, 10)));
        gameObjects = new ArrayList<>();
        gameObjects.add(player);
        gameObjects.addAll(enemies);
    }

    public void update(float delta) {
        for (GameObject object : gameObjects) {
            object.update(delta);
        }
        if (player.hasMoved()) {
            for(Enemy enemy : enemies) enemy.move(player.getPos(), map);
            player.setMoved(false);
        }
        if(player.getPos().equals(goal)) {
            System.out.println("winner");
        }
        for(Enemy enemy : enemies) {
            if(player.getPos().equals(enemy.getPos())) {
                System.out.println("Loser");
            }
        }

    }

    public void keyDown(int keyCode) {
        if (keyCode == Input.Keys.LEFT) {
            if (!collision(player, LEFT))
                player.move(LEFT);
        } else if (keyCode == Input.Keys.RIGHT) {
            if (!collision(player, RIGHT))
                player.move(RIGHT);
        } else if (keyCode == Input.Keys.UP) {
            if (!collision(player, UP))
                player.move(UP);
        } else if (keyCode == Input.Keys.DOWN) {
            if (!collision(player, DOWN))
                player.move(DOWN);
        }
    }

    public void mouseClicked(int screenX, int screenY, int pointer, int button) {
        if (pointer == 0) {
            Position pos = Position.screenToGrid(Gdx.input.getX(), Gdx.input.getY());
            if (mapLevel[pos.Y()][pos.X()].getTileId() != WALL_TILE.id())
                mapLevel[pos.Y()][pos.X()].setTile(WALL_TILE);
            else mapLevel[pos.Y()][pos.X()].setTile(GRAY_TILE);
        }
    }

    public boolean collision(Player player, Directions direction) {
        int x = player.getPos().X();
        int y = player.getPos().Y();
        switch (direction) {
            case LEFT:
                x--;
                break;
            case RIGHT:
                x++;
                break;
            case UP:
                y++;
                break;
            case DOWN:
                y--;
                break;
        }
        return y < 0 || y >= mapLevel.length || x < 0 || x >= mapLevel[y].length ||
                !mapLevel[y][x].isPassable();
    }

    public Map getMap() {
        return map;
    }

    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    public List<Position> getPath() {
        List<Position> paths = new ArrayList<>();
        for(Enemy enemy : enemies) {
            paths.addAll(enemy.path);
        }
        return paths;
    }

}
