package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class HeslingtonHustle extends ApplicationAdapter {
	SpriteBatch batch;
	private Texture house;
	private Texture food;
	private Texture img;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		house = new Texture("house.jpg");
		//food = new Texture("food.jpg");

	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		//batch.draw(food, 100, 12);
		batch.draw(house, 12, 12);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		house.dispose();
	}
}
