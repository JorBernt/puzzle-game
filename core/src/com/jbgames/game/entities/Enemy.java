package com.jbgames.game.entities;

import com.jbgames.game.helpers.AStar;
import com.jbgames.game.helpers.Position;
import com.jbgames.game.levels.Map;

import java.util.ArrayList;
import java.util.List;

public class Enemy extends GameObject {

    public List<Position> path = new ArrayList<>();

    public Enemy(Position pos) {
        super(pos, "enemy.png");
    }

    public void move(Position playerPosition, Map map) {
        AStar aStar = new AStar(map.getMapLevel(), pos, false);
        Position p = aStar.findPathTo(playerPosition);
        if (p != null)
            pos.setPos(p);
    }

}
