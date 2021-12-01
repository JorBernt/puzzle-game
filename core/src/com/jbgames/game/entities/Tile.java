package com.jbgames.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Tile {
    private final Texture texture;
    private final int id;

    public Tile(TileBuilder.Tiles type) {
        texture = new Texture(Gdx.files.internal(type.texturePath()));
        id = type.id;
    }

    public Texture getTexture() {
        return texture;
    }

    public int getId() {
        return id;
    }
}
