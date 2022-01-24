package com.jbgames.game.managers;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jbgames.game.controllers.InputController;
import com.jbgames.game.levels.Level;
import com.jbgames.game.screens.GameScreen;
import com.jbgames.game.world.World;

public class GameManager {

    private final Game game;
    private final World world;
    private final Renderer renderer;
    private Level currentLevel;
    private SpriteBatch spriteBatch;

    public GameManager(Game game) {
        this.game = game;
        this.spriteBatch = new SpriteBatch();
        world = new World();
        renderer = new Renderer(spriteBatch);
        game.setScreen(new GameScreen(this));
        loadLevel();
    }


    private void loadLevel() {
        currentLevel = new Level();
        renderer.setLevel(currentLevel);
        Gdx.input.setInputProcessor(new InputController(currentLevel));
        renderer.load();
    }

    public void update(float delta) {
        currentLevel.update(delta);
        renderer.render();
    }


}
