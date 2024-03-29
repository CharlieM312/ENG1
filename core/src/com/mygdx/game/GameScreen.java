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

    Player player;
    House house;
	StudyBuilding piazza;
	Lake lake;
	Restaurant glasshouse;
	SportsVillage gym;
	Charles charles;
	Texture background;

	String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
	String currentDay;
	
	int hoursremain;
	int activityCounter;
	int foodCounter;
	int dayCounter;
	int studyCounter;
    OrthographicCamera camera;

    public GameScreen(final HeslingtonHustle game) {
        this.game = game;

        camera = new OrthographicCamera();
		camera.setToOrtho(false, game.screenWidth, game.screenHeight);

        player     = new Player(new Vector2(168, 20));
        house      = new House();
        piazza     = new StudyBuilding();
		lake       = new Lake();
		glasshouse = new Restaurant();
		gym = new SportsVillage();
		charles = new Charles();
		background = new Texture("background.jpg");

		dayCounter 		= 0;
		foodCounter 	= 0;
		activityCounter = 0;
		dayCounter = 0;
		UpdateCurrentDay();
    }

    @Override
    public void render(float delta) {
		// Starts next day if player energy is at 0
		if (!(player.HasEnergy())) 
			ProgressToNextDay();
		
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
		game.batch.draw(house.locationTexture, 100, 200);
		game.batch.draw(lake.locationTexture, 410, 80);
		game.batch.draw(glasshouse.locationTexture, 180, 470);
		game.batch.draw(gym.locationTexture, 600, 230);
		game.batch.draw(charles.locationTexture, 400, 460);

		// Draws text
        game.font.getData().setScale(1, 1);
		game.font.draw(game.batch, "Activities completed: " + activityCounter, 800, 580);
		game.font.draw(game.batch, "Times Eaten: " + foodCounter, 800, 560);
		game.font.draw(game.batch, "Day: " + currentDay, 10, 580);
		game.font.draw(game.batch, "Time remaining: " + hoursremain, 10, 560);
		game.font.draw(game.batch, "Energy: " + player.GetEnergy(), 800, 540);

		// If player is not interacting with a building and their state is not set to locked,
		// check for directional input keys
		if (player.GetCurrentPlayerState() != playerState.LOCKED){
			player.CheckForInput();
		}

		// If player is interacting with player allow the player to choose option yes [y] to perform the acctivity
		// and no [n] to exit back to main game screen. While a player has not selected either option they are in locked
		// state so the directional keys won't move the player until they have made an option and their state is set back to idle

		// Sets up menu to allow the player to interact with the Piazza building
		if (piazza.GetCurrentState() == locationState.INTERACTING_WITH_PLAYER) {
			Texture interactionMenu = piazza.getInteractionMenu();
			game.batch.draw(interactionMenu, (game.screenWidth / 2) - (interactionMenu.getWidth() / 2)
			, (game.screenHeight / 2) - (interactionMenu.getHeight() / 2));

			if (Gdx.input.isKeyPressed(Input.Keys.Y)) {
				player.ModifyEnergyLevel(piazza.GetEnergyModifier());
				player.SetState(playerState.IDLE);
				player.exitLocation();
				Modifytime(piazza.GethoursModifier());
				piazza.SetCurrentState(locationState.IDLE);
				IncrementStudyCount();
				
			}
			else if (Gdx.input.isKeyPressed(Input.Keys.N)) {
				player.SetState(playerState.IDLE);
				player.exitLocation();
				piazza.SetCurrentState(locationState.IDLE);
			}
		}

		// Sets up menu to allow the player to interact with the glasshouse
		if (glasshouse.GetCurrentState() == locationState.INTERACTING_WITH_PLAYER) {
			Texture interactionMenu = glasshouse.getInteractionMenu();
			game.batch.draw(interactionMenu, (game.screenWidth / 2) - (interactionMenu.getWidth() / 2)
			, (game.screenHeight / 2) - (interactionMenu.getHeight() / 2));

			if (Gdx.input.isKeyPressed(Input.Keys.Y)) {
				player.ModifyEnergyLevel(glasshouse.GetEnergyModifier());
				player.SetState(playerState.IDLE);
				player.exitLocation();
				Modifytime(glasshouse.GethoursModifier());
				glasshouse.SetCurrentState(locationState.IDLE);
				IncrementFoodCount();
			}
			else if (Gdx.input.isKeyPressed(Input.Keys.N)) {
				player.SetState(playerState.IDLE);
				player.exitLocation();
				glasshouse.SetCurrentState(locationState.IDLE);
			}
		}

		// Sets up menu to allow the player to interact with the lake
		if (lake.GetCurrentState() == locationState.INTERACTING_WITH_PLAYER) {
			Texture interactionMenu = lake.getInteractionMenu();
			game.batch.draw(interactionMenu, (game.screenWidth / 2) - (interactionMenu.getWidth() / 2)
			, (game.screenHeight / 2) - (interactionMenu.getHeight() / 2));

			if (Gdx.input.isKeyPressed(Input.Keys.Y)) {
				player.ModifyEnergyLevel(lake.GetEnergyModifier());
				Modifytime(lake.GethoursModifier());
				player.SetState(playerState.IDLE);
				player.exitLocation();
				lake.SetCurrentState(locationState.IDLE);
				IncrementActivityCount();
			}
			else if (Gdx.input.isKeyPressed(Input.Keys.N)) {
				player.SetState(playerState.IDLE);
				player.exitLocation();
				lake.SetCurrentState(locationState.IDLE);
			}
		}

		// Sets up menu to allow the player to interact with the house
		if (house.GetCurrentState() == locationState.INTERACTING_WITH_PLAYER) {
			Texture interactionMenu = house.getInteractionMenu();
			game.batch.draw(interactionMenu, (game.screenWidth / 2) - (interactionMenu.getWidth() / 2)
			, (game.screenHeight / 2) - (interactionMenu.getHeight() / 2));
			if (Gdx.input.isKeyPressed(Input.Keys.Y)) {
				player.ModifyEnergyLevel(house.GetEnergyModifier());
				ProgressToNextDay();
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

		// Sets up menu to allow the player to interact with the gym
		if (gym.GetCurrentState() == locationState.INTERACTING_WITH_PLAYER) {
			Texture interactionMenu = gym.getInteractionMenu();
			game.batch.draw(interactionMenu, (game.screenWidth / 2) - (interactionMenu.getWidth() / 2)
			, (game.screenHeight / 2) - (interactionMenu.getHeight() / 2));

			if (Gdx.input.isKeyPressed(Input.Keys.Y)) {
				player.ModifyEnergyLevel(gym.GetEnergyModifier());
				player.SetState(playerState.IDLE);
				player.exitLocation();
				Modifytime(gym.GethoursModifier());
				gym.SetCurrentState(locationState.IDLE);
				IncrementActivityCount();
			}
			else if (Gdx.input.isKeyPressed(Input.Keys.N)) {
				player.SetState(playerState.IDLE);
				player.exitLocation();
				gym.SetCurrentState(locationState.IDLE);
			}
		}

		// Sets up menu to allow the player to interact with the charles
		if (charles.GetCurrentState() == locationState.INTERACTING_WITH_PLAYER) {
			Texture interactionMenu = charles.getInteractionMenu();
			game.batch.draw(interactionMenu, (game.screenWidth / 2) - (interactionMenu.getWidth() / 2)
			, (game.screenHeight / 2) - (interactionMenu.getHeight() / 2));

			if (Gdx.input.isKeyPressed(Input.Keys.Y)) {
				player.ModifyEnergyLevel(charles.GetEnergyModifier());
				player.SetState(playerState.IDLE);
				player.exitLocation();
				Modifytime(charles.GethoursModifier());
				charles.SetCurrentState(locationState.IDLE);
				IncrementActivityCount();
			}
			else if (Gdx.input.isKeyPressed(Input.Keys.N)) {
				player.SetState(playerState.IDLE);
				player.exitLocation();
				charles.SetCurrentState(locationState.IDLE);
			}
		}
        game.batch.end();

		// Checks if player collides study location and locks player if they have
		if (player.bounds.overlaps(piazza.bounds)) {
			player.SetState(playerState.LOCKED);
			piazza.SetCurrentState(locationState.INTERACTING_WITH_PLAYER);
		}

		// Checks if player collides with food location and locks player if they have
		if (player.bounds.overlaps(glasshouse.bounds)) {
			player.SetState(playerState.LOCKED);
			glasshouse.SetCurrentState(locationState.INTERACTING_WITH_PLAYER);
		}
		
		// Checks if player collides with lake location and locks player if they have
		if (player.bounds.overlaps(lake.bounds)) {
			player.SetState(playerState.LOCKED);
			lake.SetCurrentState(locationState.INTERACTING_WITH_PLAYER);

		}

		// Checks if player collides with house location and locks player if they have
		if (player.bounds.overlaps(house.bounds)) {
			player.SetState(playerState.LOCKED);
			house.SetCurrentState(locationState.INTERACTING_WITH_PLAYER);
		}

		// Checks if player collides with gym location and locks player if they have
		if (player.bounds.overlaps(gym.bounds)) {
			player.SetState(playerState.LOCKED);
			gym.SetCurrentState(locationState.INTERACTING_WITH_PLAYER);
		}

		// Checks if player collides with charles location and locks player if they have
		if (player.bounds.overlaps(charles.bounds)) {
			player.SetState(playerState.LOCKED);
			charles.SetCurrentState(locationState.INTERACTING_WITH_PLAYER);
		}
		


		// Checks if player is within bounds of the map after moving
		if (player.GetXPosition() < 0)
			player.SetXPosition(0);
		if (player.GetXPosition() > game.screenWidth - 64)
			player.SetXPosition(game.screenWidth - 64);
		if (player.GetYPosition() < 0)
			player.SetYPosition(0);
		if (player.GetYPosition() > game.screenHeight - 64)
			player.SetYPosition(game.screenHeight - 64);
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

	public void ProgressToNextDay() {
		// If it is sunday then progress to end the game
		if (currentDay == "Sunday") {
			// Set screen to end game screen
			game.setScreen(new GameOverScreen(game));
			dispose();

		}
		else 
		{	
			//changes the current day
			IncrementDayCount();
			UpdateCurrentDay();
			// Resets player energy level to 100
			player.ModifyEnergyLevel(100);
			// Resets player position to default for new day
			player.setPosition(new Vector2(168, 20));
		}
		
		
		
	}

	public void UpdateCurrentDay() {
		currentDay = days[dayCounter];
		hoursremain = 16;
	}
	public void Modifytime(int hours)
	{
		hoursremain -=hours;
	}
	public void IncrementDayCount() {
		dayCounter++;
	}
	public void IncrementStudyCount(){
		studyCounter++;
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
}
