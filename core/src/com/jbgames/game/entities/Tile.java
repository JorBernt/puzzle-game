package com.jbgames.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.jbgames.game.helpers.Position;
import com.jbgames.game.entities.TileBuilder.Tiles;

public class Tile extends Renderable{

    private Tiles type;
    private boolean passable;

    public Tile(Position position, Tiles type) {
        super(position, type.texturePath());
        this.type = type;
        passable = type.isPassable();
    }

    public Tile(int x, int y, Tiles type) {
        this(new Position(x, y), type);
    }

    public int getTileId() {
        return type.id();
    }

    public void setTile(Tiles type) {
        this.type = type;
        setTexture(type.texturePath());
    }

    public boolean isPassable() {
        return passable;
    }
}
