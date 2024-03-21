package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
// Defines the main menu screen
public class MainMenuScreen implements Screen {
    final HeslingtonHustle game;
    OrthographicCamera camera;
    Texture background;
    private Vector2 titlePosition;
    private Vector2 newGameButtonPosition;
    private Vector2 optionsButtonPosition;
    private Vector2 instructionsButtonPosition;
    private Vector2 exitButtonPosition;

    public MainMenuScreen(final HeslingtonHustle game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, game.screenWidth, game.screenHeight);
        background = new Texture("background.jpg");

        titlePosition = new Vector2((game.screenWidth / 2) - (150), (game.screenHeight / 2));
        newGameButtonPosition = new Vector2(titlePosition.x + 80, titlePosition.y - 50);
        optionsButtonPosition = new Vector2(titlePosition.x + 80, newGameButtonPosition.y - 40);
        instructionsButtonPosition = new Vector2(titlePosition.x + 80, optionsButtonPosition.y - 40);
        exitButtonPosition = new Vector2(titlePosition.x + 80, instructionsButtonPosition.y - 40);
    }
    // Renders the main menu screen
    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(background, 0, 0);
        game.font.getData().setScale(3, 3);
        game.font.setColor(Color.WHITE);
        game.font.draw(game.batch, "Heslington Hustle", titlePosition.x, titlePosition.y);
        // Checks for user input to start the game, view the instructions, or exit the game
        if (Gdx.input.isKeyJustPressed(Input.Keys.I)) {
            game.setScreen(new InstructionScreen(game));
            dispose();
        }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
            dispose();
        }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.N)) {
            game.setScreen(new GameScreen(game));
            dispose();
        }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.O)) {
            game.setScreen(new OptionsScreen(game));
            dispose();
        }
        // Changes the font depending on whether the cursor is hovering over an option
        game.font.getData().setScale(2, 2);        
        if (isCursorOnNewGame())
        {
            game.font.setColor(Color.GRAY);
            game.font.draw(game.batch, "New game(N)", newGameButtonPosition.x, newGameButtonPosition.y);
            game.font.setColor(Color.WHITE);
            game.font.draw(game.batch, "Options(O)", optionsButtonPosition.x, optionsButtonPosition.y);
            game.font.draw(game.batch, "Instructions(I)", instructionsButtonPosition.x, instructionsButtonPosition.y);
            game.font.draw(game.batch, "Exit(Esc)", exitButtonPosition.x, exitButtonPosition.y);
        } 
        else if (isCursorOnOptions()) 
        {
            game.font.draw(game.batch, "New game(N)", newGameButtonPosition.x, newGameButtonPosition.y);
            game.font.setColor(Color.GRAY);
            game.font.draw(game.batch, "Options(O)", optionsButtonPosition.x, optionsButtonPosition.y);
            game.font.setColor(Color.WHITE);
            game.font.draw(game.batch, "Instructions(I)", instructionsButtonPosition.x, instructionsButtonPosition.y);
            game.font.draw(game.batch, "Exit(Esc)", exitButtonPosition.x, exitButtonPosition.y);
        }
        else if (isCursorOnInstructions()) 
        {
            game.font.draw(game.batch, "New game(N)", newGameButtonPosition.x, newGameButtonPosition.y);
            game.font.draw(game.batch, "Options(O)", optionsButtonPosition.x, optionsButtonPosition.y);
            game.font.setColor(Color.GRAY);
            game.font.draw(game.batch, "Instructions(I)", instructionsButtonPosition.x, instructionsButtonPosition.y);
            game.font.setColor(Color.WHITE);
            game.font.draw(game.batch, "Exit(Esc)", exitButtonPosition.x, exitButtonPosition.y);
        }
        else if (isCursorOnExit()) 
        {
            game.font.draw(game.batch, "New game(N)", newGameButtonPosition.x, newGameButtonPosition.y);
            game.font.draw(game.batch, "Options(O)", optionsButtonPosition.x, optionsButtonPosition.y);
            game.font.draw(game.batch, "Instructions(I)", instructionsButtonPosition.x, instructionsButtonPosition.y);
            game.font.setColor(Color.GRAY);
            game.font.draw(game.batch, "Exit(Esc)", exitButtonPosition.x, exitButtonPosition.y);
        }
        else {
            game.font.setColor(Color.WHITE);
            game.font.draw(game.batch, "New game(N)", newGameButtonPosition.x, newGameButtonPosition.y);
            game.font.draw(game.batch, "Options(O)", optionsButtonPosition.x, optionsButtonPosition.y);
            game.font.draw(game.batch, "Instructions(I)", instructionsButtonPosition.x, instructionsButtonPosition.y);
            game.font.draw(game.batch, "Exit(Esc)", exitButtonPosition.x, exitButtonPosition.y);
        }
        game.batch.end();
        // Checks if the user has clicked on an option
        if (Gdx.input.isTouched()) {
            if (isCursorOnNewGame())
                game.setScreen(new GameScreen(game));
            else if (isCursorOnExit())
                Gdx.app.exit();
            else if (isCursorOnInstructions())
                game.setScreen(new InstructionScreen(game));
        }
    }

    public boolean isCursorOnNewGame() {
        if (600 - Gdx.input.getY() < newGameButtonPosition.y + 10 && 600 - Gdx.input.getY() > newGameButtonPosition.y - 30
            && Gdx.input.getX() > newGameButtonPosition.x && Gdx.input.getX() < newGameButtonPosition.x + 138)
            return true;
        return false;
    }

    public boolean isCursorOnOptions() {
        if (600 - Gdx.input.getY() < optionsButtonPosition.y + 10 && 600 - Gdx.input.getY() > optionsButtonPosition.y - 30
            && Gdx.input.getX() > optionsButtonPosition.x && Gdx.input.getX() < optionsButtonPosition.x + 105)
            return true;
        return false;
    }

    public boolean isCursorOnInstructions() {
        if (600 - Gdx.input.getY() < instructionsButtonPosition.y + 10 && 600 - Gdx.input.getY() > instructionsButtonPosition.y - 30
            && Gdx.input.getX() > instructionsButtonPosition.x && Gdx.input.getX() < instructionsButtonPosition.x + 145)
            return true;
        return false;
    }

    public boolean isCursorOnExit() {
        if (600 - Gdx.input.getY() < exitButtonPosition.y + 10 && 600 - Gdx.input.getY() > exitButtonPosition.y - 30
            && Gdx.input.getX() > exitButtonPosition.x && Gdx.input.getX() < exitButtonPosition.x + 48)
            return true;
        return false;
    }

    @Override
    public void pause() {

    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void resize(int x, int y) {

    }
}