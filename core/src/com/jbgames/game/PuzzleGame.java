package com.jbgames.game;

import com.badlogic.gdx.Game;
import com.jbgames.game.managers.GameManager;

public class PuzzleGame extends Game {

	private GameManager gameManager;

	@Override
	public void create() {
		gameManager = new GameManager(this);
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void dispose() {
		super.dispose();
	}


}
