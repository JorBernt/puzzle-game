package com.jbgames.game.screens;

import com.badlogic.gdx.Screen;
import com.jbgames.game.managers.GameManager;

public class GameScreen implements Screen {

    private final GameManager gameManager;

    public GameScreen(GameManager gameManager) {
        this.gameManager = gameManager;
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        gameManager.update(delta);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
