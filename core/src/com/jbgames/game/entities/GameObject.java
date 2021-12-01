package com.jbgames.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.jbgames.game.helpers.Position;

public abstract class GameObject {
    protected Position pos;
    protected Texture texture;

    public GameObject() {}

    public GameObject(Position pos, String texturePath) {
        this.pos = pos;
        this.texture = new Texture(Gdx.files.internal(texturePath));
    }

    public void update(float delta) { }

    public Position getPos() {
        return pos;
    }


    public Texture getTexture() {
        return texture;
    }

}
