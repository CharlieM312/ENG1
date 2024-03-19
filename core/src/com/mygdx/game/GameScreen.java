package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.GameLocation.locationState;
import com.mygdx.game.Player.playerState;
// Defines the GameScreen class
public class GameScreen implements Screen {
    final HeslingtonHustle game;

    int upKey, rightKey, downKey, leftKey
    , wKey, dKey, sKey, aKey;

    Player player;
    House house;
	StudyBuilding piazza;
	Lake lake;
	Restaurant glasshouse;
	Texture background;

	String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
	String currentDay;

	int activityCounter;
	int foodCounter;
	int dayCounter;

    OrthographicCamera camera;

    public GameScreen(final HeslingtonHustle game) {
        this.game = game;

        upKey    = Input.Keys.UP;
		rightKey = Input.Keys.RIGHT;
		downKey  = Input.Keys.DOWN;
		leftKey  = Input.Keys.LEFT;
		wKey     = Input.Keys.W;
		dKey     = Input.Keys.D;
		sKey     = Input.Keys.S;
		aKey     = Input.Keys.A;

        camera = new OrthographicCamera();
		camera.setToOrtho(false, 1000, 600);

        player     = new Player(new Vector2(168, 20));
        house      = new House();
        piazza     = new StudyBuilding();
		lake       = new Lake();
		glasshouse = new Restaurant();
		background = new Texture("background.jpg");

		dayCounter = 0;
		foodCounter = 0;
		activityCounter = 0;
		dayCounter = 0;
		currentDay = days[dayCounter];
    }

    @Override
    public void render(float delta) {
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
		game.batch.draw(piazza.locationTexture, 400, 280);
		game.batch.draw(house.locationTexture, 100, 280);
		game.batch.draw(lake.locationTexture, 700, 470);
		game.batch.draw(glasshouse.locationTexture, 250, 470);

		// Draws text
        game.font.getData().setScale(1, 1);
		game.font.draw(game.batch, "Activities completed: " + activityCounter, 800, 580);
		game.font.draw(game.batch, "Times Eaten: " + foodCounter, 800, 560);
		game.font.draw(game.batch, "Day: " + currentDay, 10, 580);

		// Players interaction with piazza building
		if (player.GetCurrentPlayerState() != playerState.LOCKED){
			player.CheckForInput();
		}
		if (piazza.GetCurrentState() == locationState.INTERACTING_WITH_PLAYER) {
			if (Gdx.input.isKeyPressed(Input.Keys.Y)) {
				player.SetState(playerState.IDLE);
				player.exitLocation();
				piazza.SetCurrentState(locationState.IDLE);
			}
			else if (Gdx.input.isKeyPressed(Input.Keys.N)) {
				player.SetState(playerState.IDLE);
				player.exitLocation();
				piazza.SetCurrentState(locationState.IDLE);
			}
		}
		// Players interaction with glasshouse building
		if (glasshouse.GetCurrentState() == locationState.INTERACTING_WITH_PLAYER) {
			if (Gdx.input.isKeyPressed(Input.Keys.Y)) {
				player.SetState(playerState.IDLE);
				player.exitLocation();
				glasshouse.SetCurrentState(locationState.IDLE);
			}
			else if (Gdx.input.isKeyPressed(Input.Keys.N)) {
				player.SetState(playerState.IDLE);
				player.exitLocation();
				glasshouse.SetCurrentState(locationState.IDLE);
			}
		}
		// Players interaction with lake
		if (lake.GetCurrentState() == locationState.INTERACTING_WITH_PLAYER) {
			if (Gdx.input.isKeyPressed(Input.Keys.Y)) {
				player.SetState(playerState.IDLE);
				player.exitLocation();
				lake.SetCurrentState(locationState.IDLE);
			}
			else if (Gdx.input.isKeyPressed(Input.Keys.N)) {
				player.SetState(playerState.IDLE);
				player.exitLocation();
				lake.SetCurrentState(locationState.IDLE);
			}
		}
		// Players interaction with house
		if (house.GetCurrentState() == locationState.INTERACTING_WITH_PLAYER) {
			if (Gdx.input.isKeyPressed(Input.Keys.Y)) {
				player.SetState(playerState.IDLE);
				player.exitLocation();
				house.SetCurrentState(locationState.IDLE);
			}
			else if (Gdx.input.isKeyPressed(Input.Keys.N)) {
				player.SetState(playerState.IDLE);
				player.exitLocation();
				house.SetCurrentState(locationState.IDLE);
			}
		}
        game.batch.end();

		// Checks if player collides study location
		if (player.bounds.overlaps(piazza.bounds)) {
			player.SetState(playerState.LOCKED);
			piazza.SetCurrentState(locationState.INTERACTING_WITH_PLAYER);
		}

		// Checks if player collides with food location
		if (player.bounds.overlaps(glasshouse.bounds)) {
			player.SetState(playerState.LOCKED);
			glasshouse.SetCurrentState(locationState.INTERACTING_WITH_PLAYER);
		}
		
		// Checks if player collides with lake location
		if (player.bounds.overlaps(lake.bounds)) {
			player.SetState(playerState.LOCKED);
			lake.SetCurrentState(locationState.INTERACTING_WITH_PLAYER);

		}

		// Checks if player collides with house location
		if (player.bounds.overlaps(house.bounds)) {
			player.SetState(playerState.LOCKED);
			house.SetCurrentState(locationState.INTERACTING_WITH_PLAYER);
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
