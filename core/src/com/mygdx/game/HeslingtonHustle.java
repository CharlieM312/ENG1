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
import com.mygdx.game.Player.Direction;
import com.mygdx.game.Player.State;

public class HeslingtonHustle extends ApplicationAdapter {
	private Player player;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture house;
	private Texture study;
	private Texture lake;
	private Texture food;
	private Texture background;
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
		background = new Texture("background.jpg");
		font  = new BitmapFont();

		dayCounter = 0;
		foodCounter = 0;
		activityCounter = 0;
		dayCounter = 0;
		currentDay = days[dayCounter];
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		batch.setProjectionMatrix(camera.combined);
		camera.update();
		batch.begin();
		// Draws player
		batch.draw(player.GetCurrentTexture()
				, player.GetXPosition(), player.GetYPosition());
		// Draws background
		//batch.draw(background, 0, 0);

		// Draws buildings
		batch.draw(study, 400, 280);
		batch.draw(house, 100, 280);
		batch.draw(lake, 700, 470);
		batch.draw(food, 250, 470);

		// Draws text
		font.draw(batch, "Activities completetd: " + activityCounter, 800, 580);
		font.draw(batch, "Times Eaten: " + foodCounter, 800, 560);
		font.draw(batch, "Day: " + currentDay, 10, 580);

		// Checks for key presses and moves player in specified direction
		// otherwise set the texture to idle
		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			player.SetState(State.MOVING);

			float currentY = player.GetYPosition();
			player.SetYPosition(currentY += 180 * Gdx.graphics.getDeltaTime());
		}
		 else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			player.SetDirection(Direction.LEFT);
			player.SetState(State.MOVING);

			float currentX = player.GetXPosition();
			player.SetXPosition(currentX -= 180 * Gdx.graphics.getDeltaTime());
		}
		else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			player.SetDirection(Direction.RIGHT);
			player.SetState(State.MOVING);

			float currentX = player.GetXPosition();
			player.SetXPosition(currentX += 180 * Gdx.graphics.getDeltaTime());
		}
		else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			player.SetState(State.MOVING);

			float currentY = player.GetYPosition();
			player.SetYPosition( currentY -= 180 * Gdx.graphics.getDeltaTime());
		}
		else {
			player.SetState(State.IDLE);
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
		render();
	}
}
