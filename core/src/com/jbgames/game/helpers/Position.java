package com.jbgames.game.helpers;

import com.badlogic.gdx.Gdx;
import com.jbgames.game.GameData;

import java.util.Objects;

import static com.jbgames.game.GameData.*;

public class Position {
    private int x, y;
    private int offX, offY;

    public Position(int x, int y, int offX, int offY) {
        this.x = x;
        this.y = y;
        this.offX = offX;
        this.offY = offY;
    }

    public Position(int x, int y) {
        this(x, y, 0, 0);
    }

    public Position(Position p) {
        this.x = p.X();
        this.y = p.Y();
    }

    public Position() {
        this(0, 0);
    }

    public void setPos(Position pos) {
        this.x = pos.X();
        this.y = pos.Y();
    }

    public int X() {
        return x;
    }

    public int Y() {
        return y;
    }

    public int screenX() {
        return x * TILE_WIDTH + OFFSET_X + offX;
    }

    public int screenY() {
        return y * TILE_HEIGHT + OFFSET_Y + offY;
    }

    public void moveX(int amount) {
       x += amount;
    }

    public void moveY(int amount) {
        y += amount;
    }

    public static Position screenToGrid(int x, int y) {
        y = Gdx.graphics.getHeight() - y;
        x -= OFFSET_X;
        y -= OFFSET_Y;
        x /= TILE_WIDTH;
        y /= TILE_HEIGHT;
        return new Position(x, y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
