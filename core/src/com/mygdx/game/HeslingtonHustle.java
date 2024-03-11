package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class HeslingtonHustle extends ApplicationAdapter {
	SpriteBatch batch;
	private Texture house;
	private Texture study;
	private Texture lake;
	private Texture food;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		house = new Texture("house.jpg");
		study = new Texture("placetoeat.jpg");
		lake = new Texture("lake.jpg");
		food = new Texture("glasshouse.jpg");

	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		batch.draw(study, 120, 150);
		batch.draw(house, 12, 12);
		batch.draw(lake, 400, 300);
		batch.draw(food, 250, 250);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		house.dispose();
		study.dispose();
		lake.dispose();
		food.dispose();
	}
}
