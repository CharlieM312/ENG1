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
	private String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
	private String CurrentDay;
	private int ActivityCounter;
	private int FoodCounter;
	private int DayCounter;
	BitmapFont font;

	@Override
	public void create () {
		batch = new SpriteBatch();
		house = new Texture("house.jpg");
		study = new Texture("placetoeat.jpg");
		lake  = new Texture("lake.jpg");
		food  = new Texture("glasshouse.jpg");
		font  = new BitmapFont();
		batch = new SpriteBatch();
		house = new Texture("house.jpg");
		study = new Texture("placetoeat.jpg");
		lake  = new Texture("lake.jpg");
		food  = new Texture("glasshouse.jpg");
		font  = new BitmapFont();

		CurrentDay 		= days[DayCounter];
		DayCounter 		= 0;
		FoodCounter 	= 0;
		ActivityCounter = 0;
		DayCounter 		= 0;
		CurrentDay 		= days[DayCounter];
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		batch.draw(study, 120, 150);
		batch.draw(house, 12, 12);
		batch.draw(lake, 400, 300);
		batch.draw(food, 250, 250);

		font.draw(batch, "Day: " + CurrentDay, 25, 400);
		font.draw(batch, "Activities completetd: " + ActivityCounter, 450, 460);
		font.draw(batch, "Times Eaten: " + FoodCounter, 450, 440);
		font.draw(batch, "Day: " + CurrentDay, 10, 460);
		font.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		batch.end();
	}

	public String getCurrentDay() {
		return days[DayCounter];
	}

	public void IncrementDayCount(){
		DayCounter++;
	}
}
