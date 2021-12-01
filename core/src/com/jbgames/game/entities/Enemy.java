package com.jbgames.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.jbgames.game.helpers.AStar;
import com.jbgames.game.helpers.PathFinder;
import com.jbgames.game.helpers.Position;

import java.util.ArrayList;
import java.util.List;

public class Enemy extends GameObject {

    private final PathFinder pathFinder;
    public List<Position> path = new ArrayList<>();

    public Enemy(Position pos, PathFinder pathFinder) {
        super(pos, "enemy.png");
        this.pathFinder = pathFinder;
    }

    public void move(Player player, int[][] map) {
        /*
        path = pathFinder.getPath(this.pos, player.pos);
        pos = path.get(0);
         */
        AStar aStar = new AStar(map, pos.X(), pos.Y(), false);
        path = aStar.findPathTo(player.pos.X(), player.pos.Y());
        path.remove(0);
        pos = path.get(0);
    }
}
