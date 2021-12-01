package com.jbgames.game.managers;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.jbgames.game.controllers.InputController;
import com.jbgames.game.levels.Level;
import com.jbgames.game.screens.GameScreen;
import com.jbgames.game.world.World;

public class GameManager {

    private final Game game;
    private final World world;
    private final Renderer renderer;
    private Level currentLevel;

    public GameManager(Game game) {
        this.game = game;
        world = new World();
        renderer = new Renderer();
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
        renderer.render(delta);
    }


}
