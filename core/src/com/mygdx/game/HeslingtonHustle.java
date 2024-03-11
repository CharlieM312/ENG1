package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class HeslingtonHustle extends ApplicationAdapter {
	SpriteBatch batch;
	private Texture house;
	private Texture study;
	private Texture lake;
	private Texture food;
	private int day_counter;
	private String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
	private String CurrentDay;
	private int actv_counter;
	private int food_counter;
	BitmapFont font;

	
	@Override
	public void create () {
		batch = new SpriteBatch();
		house = new Texture("house.jpg");
		study = new Texture("placetoeat.jpg");
		lake = new Texture("lake.jpg");
		food = new Texture("glasshouse.jpg");
		day_counter = 0;
		food_counter = 0;
		actv_counter = 0;
		CurrentDay = days[day_counter];
		font = new BitmapFont();
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		batch.draw(study, 120, 150);
		batch.draw(house, 12, 12);
		batch.draw(lake, 400, 300);
		batch.draw(food, 250, 250);
		font.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		font.draw(batch, "Day: " + CurrentDay, 25, 400);
		font.draw(batch, "Activities completetd: " + actv_counter, 450, 460);
		font.draw(batch, "Times Eaten: " + food_counter, 450, 440);
		batch.end();
	}
	
	
}
