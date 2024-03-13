package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

public class HeslingtonHustle extends ApplicationAdapter {
	private Player player;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture house;
	private Texture study;
	private Texture lake;
	private Texture food;
	private String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
	private String currentDay;
	private int activityCounter;
	private int foodCounter;
	private int dayCounter;
	BitmapFont font;

	@Override
	public void create () {
		player = new Player(new Vector2(168, 20));
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1000, 600);
		batch = new SpriteBatch();
		house = new Texture("house.jpg");
		study = new Texture("placetoeat.jpg");
		lake  = new Texture("lake.jpg");
		food  = new Texture("glasshouse.jpg");
		font  = new BitmapFont();

		dayCounter = 0;
		foodCounter = 0;
		activityCounter = 0;
		dayCounter = 0;
		currentDay = days[dayCounter];
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 1, 1);
		batch.setProjectionMatrix(camera.combined);

		batch.begin();
		// Draws player
		batch.draw(player.GetCurrentTexture()
				, player.GetXPosition(), player.GetYPosition());

		// Draws buildings
		batch.draw(study, 340, 150);
		batch.draw(house, 24, 24);
		batch.draw(lake, 700, 450);
		batch.draw(food, 250, 250);

		// Draws text
		font.draw(batch, "Activities completetd: " + activityCounter, 800, 580);
		font.draw(batch, "Times Eaten: " + foodCounter, 800, 560);
		font.draw(batch, "Day: " + currentDay, 10, 580);

		// Checks for key presses and moves player in specified direction
		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			float currentY = player.GetYPosition();
			player.SetYPosition(currentY += 200 * Gdx.graphics.getDeltaTime());
		}
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			float currentX = player.GetXPosition();
			player.SetXPosition(currentX -= 200 * Gdx.graphics.getDeltaTime());
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			float currentX = player.GetXPosition();
			player.SetXPosition(currentX += 200 * Gdx.graphics.getDeltaTime());
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			float currentY = player.GetYPosition();
			player.SetYPosition( currentY -= 200 * Gdx.graphics.getDeltaTime());
		}
		
		// Checks if player is within bounds of the map after moving
		if (player.GetXPosition() < 0)
			player.SetXPosition(0);
		if (player.GetXPosition() > 1000 - 64)
			player.SetXPosition(1000 - 64);
		if (player.GetYPosition() < 0)
			player.SetYPosition(0);
		if (player.GetYPosition() > 600 - 64)
			player.SetYPosition(600 - 64);
		batch.end();
	}

	public int getDayCount()
	{
		return dayCounter;
	}

	public int getFoodCount() {
		return foodCounter;
	}

	public int getActivityCount() {
		return activityCounter;
	}

	public void IncrementDayCount() {
		dayCounter++;
	}

	public void IncrementFoodCount() {
		foodCounter++;
	}

	public void IncrementActivityCount() {
		activityCounter++;
	}

	public String getCurrentDayName() {
		return days[dayCounter];
	}

	public void UpdateCurrentDay(){
		IncrementDayCount();
		currentDay = days[dayCounter];
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		batch.draw(study, 340, 150);
		batch.draw(house, 24, 24);
		batch.draw(lake, 700, 450);
		batch.draw(food, 250, 250);
		font.draw(batch, "Activities completetd: " + activityCounter, 800, 580);
		font.draw(batch, "Times Eaten: " + foodCounter, 800, 560);
		font.draw(batch, "Day: " + currentDay, 10, 580);
		font.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		batch.end();
	}
}
