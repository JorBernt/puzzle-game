package com.jbgames.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.jbgames.game.helpers.Directions;
import com.jbgames.game.helpers.Position;

public class Player extends GameObject {

    private boolean moved;

    public Player(Position pos) {
        super(pos, "player.png");
        moved = false;
    }

    @Override
    public void update(float delta) {

    }

    public void move(Directions direction) {
        switch (direction) {
            case LEFT:
                pos.moveX(-1);
                break;
            case RIGHT:
                pos.moveX(1);
                break;
            case UP:
                pos.moveY(1);
                break;
            case DOWN:
                pos.moveY(-1);
                break;
        }
        moved = true;
    }

    public boolean hasMoved() {
        return moved;
    }

    public void setMoved(boolean val) {
        moved = val;
    }


}
