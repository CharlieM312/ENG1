package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Player.Direction;
import com.mygdx.game.Player.State;

public class GameScreen implements Screen {
    final HeslingtonHustle game;
    int upKey, rightKey, downKey, leftKey
    , wKey, dKey, sKey, aKey;
    Player player;
    House house1;
	StudyBuilding study1;
	Lake lake1;
	Restaurant food1;
	Texture background;
	String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
	String currentDay;
	int activityCounter;
	int foodCounter;
	int dayCounter;
    OrthographicCamera camera;

    public GameScreen(final HeslingtonHustle game) {
        this.game = game;

        upKey = Input.Keys.UP;
		rightKey = Input.Keys.RIGHT;
		downKey = Input.Keys.DOWN;
		leftKey = Input.Keys.LEFT;
		wKey = Input.Keys.W;
		dKey = Input.Keys.D;
		sKey = Input.Keys.S;
		aKey = Input.Keys.A;

        camera = new OrthographicCamera();
		camera.setToOrtho(false, 1000, 600);

        player = new Player(new Vector2(168, 20));
        house1 = new House();
        study1 = new StudyBuilding();
		lake1 = new Lake();
		food1 = new Restaurant();
		background = new Texture("background.jpg");
		dayCounter = 0;
		foodCounter = 0;
		activityCounter = 0;
		dayCounter = 0;
		currentDay = days[dayCounter];
    }

    @Override
    public void render(float felta) {
        ScreenUtils.clear(0, 0, 0, 0);
		camera.update();
		game.batch.setProjectionMatrix(camera.combined);
        
        game.batch.begin();
		// Draws background
		game.batch.draw(background, 0, 0);
		// Draws player
		game.batch.draw(player.GetCurrentTexture()
				, player.GetXPosition(), player.GetYPosition());

		// Draws buildings now using location class
		game.batch.draw(study1.locationTexture, 400, 280);
		game.batch.draw(house1.locationTexture, 100, 280);
		game.batch.draw(lake1.locationTexture, 700, 470);
		game.batch.draw(food1.locationTexture, 250, 470);

		// Draws text
        game.font.getData().setScale(1, 1);
		game.font.draw(game.batch, "Activities completed: " + activityCounter, 800, 580);
		game.font.draw(game.batch, "Times Eaten: " + foodCounter, 800, 560);
		game.font.draw(game.batch, "Day: " + currentDay, 10, 580);
        game.batch.end();

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
    }

    @Override
    public void pause() {}

    @Override
    public void show() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {}

    @Override
    public void resume() {}

    @Override
    public void resize(int x, int y) {}

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
	}
}
