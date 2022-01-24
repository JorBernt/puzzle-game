package com.jbgames.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jbgames.game.helpers.Position;

public abstract class Renderable {
    protected Texture texture;
    protected Position pos;

    public Renderable() {}

    public Renderable(Position pos, String texturePath) {
        this.texture = new Texture(Gdx.files.internal(texturePath));
        this.pos = pos;
    }

    public void render(SpriteBatch batch, int x, int y) {
        batch.draw(texture, x, y);
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, pos.screenX(), pos.screenY());
    }


    public void setTexture(String texturePath) {
        texture = new Texture(Gdx.files.internal(texturePath));
    }

    public Texture getTexture() {
        return texture;
    }

    public Position getPos() {
        return pos;
    }

}
