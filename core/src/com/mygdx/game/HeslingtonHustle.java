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
	private int upKey, rightKey, downKey, leftKey
				, wKey, dKey, sKey, aKey;
	private Player player;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	//private Texture house;
	//private Texture study;
	//private Texture lake;
	//private Texture food;
	private Location house1;
	private Location study1;
	private Location lake1;
	private Location food1;
	private Texture background;
	private String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
	private String currentDay;
	private int activityCounter;
	private int foodCounter;
	private int dayCounter;
	BitmapFont font;

	@Override
	public void create () {
		upKey = Input.Keys.UP;
		rightKey = Input.Keys.RIGHT;
		downKey = Input.Keys.DOWN;
		leftKey = Input.Keys.LEFT;
		wKey = Input.Keys.W;
		dKey = Input.Keys.D;
		sKey = Input.Keys.S;
		aKey = Input.Keys.A;

		player = new Player(new Vector2(168, 20));
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1000, 600);
		batch = new SpriteBatch();
		//now uses location class to allow for collisions with the player
		house1 = new Location(new Vector2(100, 280),64,64,new Texture("house.jpg") );
		//house = new Texture("house.jpg");
		study1 = new Location(new Vector2(400, 280),64,64,new Texture("placetoeat.jpg") );
		//study = new Texture("placetoeat.jpg");
		lake1 = new Location(new Vector2(700, 470),64,64,new Texture("lake.jpg") );
		//lake  = new Texture("lake.jpg");
		food1 = new Location(new Vector2(250, 470),64,64,new Texture("glasshouse.jpg") );
		//food  = new Texture("glasshouse.jpg");
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
		ScreenUtils.clear(0, 0, 0, 0);
		batch.setProjectionMatrix(camera.combined);
		camera.update();

		batch.begin();
		// Draws background
		batch.draw(background, 0, 0);
		// Draws player
		batch.draw(player.GetCurrentTexture()
				, player.GetXPosition(), player.GetYPosition());

		// Draws buildings now using location class
		batch.draw(study1.locationTexture, 400, 280);
		batch.draw(house1.locationTexture, 100, 280);
		batch.draw(lake1.locationTexture, 700, 470);
		batch.draw(food1.locationTexture, 250, 470);

		// Draws text
		font.draw(batch, "Activities completed: " + activityCounter, 800, 580);
		font.draw(batch, "Times Eaten: " + foodCounter, 800, 560);
		font.draw(batch, "Day: " + currentDay, 10, 580);

		float currentX = player.GetXPosition();
		float currentY = player.GetYPosition();

		// If any directional key is pressed, set player state to moving
		if (Gdx.input.isKeyPressed(upKey)  || Gdx.input.isKeyPressed(rightKey)
                || Gdx.input.isKeyPressed(downKey) || Gdx.input.isKeyPressed(leftKey)
                || Gdx.input.isKeyPressed(wKey)    || Gdx.input.isKeyPressed(dKey)
                || Gdx.input.isKeyPressed(sKey)    || Gdx.input.isKeyPressed(aKey)) 
		    player.SetState(State.MOVING);

		// Checks for key presses and moves player in specified direction,
		// otherwise set the texture to idle
		if ((Gdx.input.isKeyPressed(rightKey) && Gdx.input.isKeyPressed(upKey)) || 
			(Gdx.input.isKeyPressed(dKey) && Gdx.input.isKeyPressed(wKey))) {

			player.SetDirection(Direction.RIGHT);
			player.setPosition(new Vector2(currentX += 180 * Gdx.graphics.getDeltaTime()
							, currentY += 180 * Gdx.graphics.getDeltaTime()));
		}
		else if ((Gdx.input.isKeyPressed(rightKey) && Gdx.input.isKeyPressed(downKey)) ||
		         (Gdx.input.isKeyPressed(dKey) && Gdx.input.isKeyPressed(sKey))) {

			player.SetDirection(Direction.RIGHT);
			player.setPosition(new Vector2(currentX += 180 * Gdx.graphics.getDeltaTime()
							, currentY -= 180 * Gdx.graphics.getDeltaTime()));
		}
		else if ((Gdx.input.isKeyPressed(leftKey) && Gdx.input.isKeyPressed(upKey)) || 
		         (Gdx.input.isKeyPressed(aKey) && Gdx.input.isKeyPressed(wKey))) {

			player.SetDirection(Direction.LEFT);
			player.setPosition(new Vector2(currentX -= 180 * Gdx.graphics.getDeltaTime()
							, currentY += 180 * Gdx.graphics.getDeltaTime()));
		}
		else if ((Gdx.input.isKeyPressed(leftKey) && Gdx.input.isKeyPressed(downKey)) ||
		         (Gdx.input.isKeyPressed(aKey) && Gdx.input.isKeyPressed(sKey))) {

			player.SetDirection(Direction.LEFT);
			player.setPosition(new Vector2(currentX -= 180 * Gdx.graphics.getDeltaTime()
							, currentY -= 180 * Gdx.graphics.getDeltaTime()));
		}
		else if (Gdx.input.isKeyPressed(upKey) || Gdx.input.isKeyPressed(wKey)) {
			player.SetYPosition(currentY += (180 * Gdx.graphics.getDeltaTime()));
		}
		 else if (Gdx.input.isKeyPressed(leftKey) || Gdx.input.isKeyPressed(aKey)) {
			player.SetDirection(Direction.LEFT);
			player.SetXPosition(currentX -= 180 * Gdx.graphics.getDeltaTime());
		}
		else if (Gdx.input.isKeyPressed(rightKey) || Gdx.input.isKeyPressed(dKey)) {
			player.SetDirection(Direction.RIGHT);
			player.SetXPosition(currentX += 180 * Gdx.graphics.getDeltaTime());
		}
		else if (Gdx.input.isKeyPressed(downKey) || Gdx.input.isKeyPressed(sKey)) {
			player.SetYPosition(currentY -= 180 * Gdx.graphics.getDeltaTime());
		}
		else {
			player.SetState(State.IDLE);
		}
		
		// Checks if player collides study location
		if (player.bounds.overlaps(study1.bounds)) {
			if (Gdx.input.isKeyPressed(upKey) || Gdx.input.isKeyPressed(wKey))
				player.SetYPosition(currentY - 60) ;
			else if (Gdx.input.isKeyPressed(leftKey) || Gdx.input.isKeyPressed(aKey))
				player.SetXPosition(currentX + 60);
			else if (Gdx.input.isKeyPressed(downKey) || Gdx.input.isKeyPressed(wKey)) 
				player.SetYPosition(currentY + 60);
			else if (Gdx.input.isKeyPressed(rightKey) || Gdx.input.isKeyPressed(wKey))
				player.SetXPosition(currentX - 60);	
		}

		// Checks if player collides with food location
		if (player.bounds.overlaps(food1.bounds)) {
			if (Gdx.input.isKeyPressed(upKey) || Gdx.input.isKeyPressed(wKey))
				player.SetYPosition(currentY - 60) ;
			else if (Gdx.input.isKeyPressed(leftKey) || Gdx.input.isKeyPressed(aKey))
				player.SetXPosition(currentX + 60);
			else if (Gdx.input.isKeyPressed(downKey) || Gdx.input.isKeyPressed(wKey)) 
				player.SetYPosition(currentY + 60);
			else if (Gdx.input.isKeyPressed(rightKey) || Gdx.input.isKeyPressed(wKey))
				player.SetXPosition(currentX - 60);
		}
		
		// Checks if player collides with lake location
		if (player.bounds.overlaps(lake1.bounds)) {
			if (Gdx.input.isKeyPressed(upKey) || Gdx.input.isKeyPressed(wKey))
				player.SetYPosition(currentY - 60) ;
			else if (Gdx.input.isKeyPressed(leftKey) || Gdx.input.isKeyPressed(aKey))
				player.SetXPosition(currentX + 60);
			else if (Gdx.input.isKeyPressed(downKey) || Gdx.input.isKeyPressed(wKey)) 
				player.SetYPosition(currentY + 60);
			else if (Gdx.input.isKeyPressed(rightKey) || Gdx.input.isKeyPressed(wKey))
				player.SetXPosition(currentX - 60);
		}

		// Checks if player collides with house location
		if (player.bounds.overlaps(house1.bounds)) {
			if (Gdx.input.isKeyPressed(upKey) || Gdx.input.isKeyPressed(wKey))
				player.SetYPosition(currentY - 60) ;
			else if (Gdx.input.isKeyPressed(leftKey) || Gdx.input.isKeyPressed(aKey))
				player.SetXPosition(currentX + 60);
			else if (Gdx.input.isKeyPressed(downKey) || Gdx.input.isKeyPressed(wKey)) 
				player.SetYPosition(currentY + 60);
			else if (Gdx.input.isKeyPressed(rightKey) || Gdx.input.isKeyPressed(wKey))
				player.SetXPosition(currentX - 60);
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
