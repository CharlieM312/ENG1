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
	private int DayCounter;
	private String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
	private String CurrentDay;
	BitmapFont font;

	
	@Override
	public void create () {
		batch = new SpriteBatch();
		house = new Texture("house.jpg");
		study = new Texture("placetoeat.jpg");
		lake  = new Texture("lake.jpg");
		food  = new Texture("glasshouse.jpg");
		DayCounter = 0;
		CurrentDay = days[DayCounter];
		font  	   = new BitmapFont();
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		batch.draw(study, 340, 150);
		batch.draw(house, 24, 24);
		batch.draw(lake, 700, 450);
		batch.draw(food, 250, 250);
		font.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		font.draw(batch, "Day: " + CurrentDay, 10, 580);
		batch.end();
	}

	public String getCurrentDay() {
		return days[DayCounter];
	}

	public void IncrementDayCount(){
		DayCounter++;
	}
}
