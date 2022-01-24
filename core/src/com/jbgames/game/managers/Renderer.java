package com.jbgames.game.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jbgames.game.entities.GameObject;
import com.jbgames.game.entities.Renderable;
import com.jbgames.game.levels.Level;

public class Renderer {

    private final SpriteBatch batch;
    private final OrthographicCamera cam;

    private Level level;

    public Renderer(SpriteBatch batch) {
        this.batch = batch;
        cam = new OrthographicCamera();
        cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.setProjectionMatrix(cam.combined);
    }


    public void setLevel(Level level) {
        this.level = level;
    }

    public void load() {
    }

    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        level.getMap().render(batch);
        for (GameObject o : level.getGameObjects()) {
            o.render(batch);
        }
        batch.end();
    }

}
