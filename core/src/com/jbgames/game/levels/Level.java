package com.jbgames.game.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.jbgames.game.entities.Enemy;
import com.jbgames.game.entities.GameObject;
import com.jbgames.game.entities.Player;
import com.jbgames.game.helpers.AStar;
import com.jbgames.game.helpers.Directions;
import com.jbgames.game.helpers.PathFinder;
import com.jbgames.game.helpers.Position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.jbgames.game.entities.TileBuilder.Tiles.*;
import static com.jbgames.game.helpers.Directions.*;

public class Level {

    private final int[][] map;
    private final Player player;
    private final Enemy enemy;
    private final List<GameObject> gameObjects;
    private final PathFinder pathFinder;
    private final Position goal;

    public Level() {
        map = new int[11][11];
        for (int y = 0; y < map.length; y++)
            Arrays.fill(map[y], 0);
        map[0][5] = GREEN_TILE.id();
        for (int i = 3; i < 8; i++) {
            map[5][i] = WALL_TILE.id();
        }
        goal = new Position(5, 10);
        map[10][5] = BLUE_TILE.id();
        pathFinder = new PathFinder(map);
        player = new Player(new Position(5, 0, 0, 10));
        enemy = new Enemy(new Position(5, 8, 0, 10), pathFinder);
        gameObjects = new ArrayList<>();
        gameObjects.add(player);
        gameObjects.add(enemy);
    }

    public void update(float delta) {
        for (GameObject object : gameObjects) {
            object.update(delta);
        }
        if (player.hasMoved()) {
            enemy.move(player, map);
            player.setMoved(false);
        }
        if(player.getPos().equals(goal)) {
            System.out.println("winner");
        }
        if(player.getPos().equals(enemy.getPos())) {
            System.out.println("Loser");
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
            if (map[pos.Y()][pos.X()] != WALL_TILE.id())
                map[pos.Y()][pos.X()] = WALL_TILE.id();
            else map[pos.Y()][pos.X()] = GRAY_TILE.id();
            pathFinder.updateNodes(map);
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
        return y < 0 || y >= map.length || x < 0 || x >= map[y].length ||
                !getTileTypeById(map[y][x]).isPassable();
    }

    public int[][] getMap() {
        return map;
    }

    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    public List<Position> getPath() {
        return enemy.path;
    }

}
