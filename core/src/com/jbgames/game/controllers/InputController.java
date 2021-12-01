package com.jbgames.game.controllers;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.jbgames.game.levels.Level;

public class InputController implements InputProcessor {

    private final Level level;

    public InputController(Level level) {
        this.level = level;
    }

    @Override
    public boolean keyDown(int keycode) {
        level.keyDown(keycode);
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        level.mouseClicked(screenX, screenY, pointer, button);
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}


