package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

public class MainMenuScreen implements Screen {
    final HeslingtonHustle game;
    OrthographicCamera camera;
    Texture background;
    private Vector2 newGameButtonPosition;
    private Vector2 optionsButtonPosition;
    private Vector2 instructionsButtonPosition;
    private Vector2 exitButtonPosition;

    public MainMenuScreen(final HeslingtonHustle game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1000, 600);
        background = new Texture("background.jpg");

        newGameButtonPosition = new Vector2(440, 230);
        optionsButtonPosition = new Vector2(440, 190);
        instructionsButtonPosition = new Vector2(440, 150);
        exitButtonPosition = new Vector2(440, 110);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(background, 0, 0);
        game.font.getData().setScale(3, 3);
        game.font.setColor(Color.WHITE);
        game.font.draw(game.batch, "Heslington Hustle", 340, 300);

        game.font.getData().setScale(2, 2);        
        if (isCursorOnNewGame())
        {
            game.font.setColor(Color.GRAY);
            game.font.draw(game.batch, "New game", newGameButtonPosition.x, newGameButtonPosition.y);
            game.font.setColor(Color.WHITE);
            game.font.draw(game.batch, "Options", optionsButtonPosition.x, optionsButtonPosition.y);
            game.font.draw(game.batch, "Instructions", instructionsButtonPosition.x, instructionsButtonPosition.y);
            game.font.draw(game.batch, "Exit", exitButtonPosition.x, exitButtonPosition.y);
        } 
        else if (isCursorOnOptions()) 
        {
            game.font.draw(game.batch, "New game", newGameButtonPosition.x, newGameButtonPosition.y);
            game.font.setColor(Color.GRAY);
            game.font.draw(game.batch, "Options", optionsButtonPosition.x, optionsButtonPosition.y);
            game.font.setColor(Color.WHITE);
            game.font.draw(game.batch, "Instructions", instructionsButtonPosition.x, instructionsButtonPosition.y);
            game.font.draw(game.batch, "Exit", exitButtonPosition.x, exitButtonPosition.y);
        }
        else if (isCursorOnInstructions()) 
        {
            game.font.draw(game.batch, "New game", newGameButtonPosition.x, newGameButtonPosition.y);
            game.font.draw(game.batch, "Options", optionsButtonPosition.x, optionsButtonPosition.y);
            game.font.setColor(Color.GRAY);
            game.font.draw(game.batch, "Instructions", instructionsButtonPosition.x, instructionsButtonPosition.y);
            game.font.setColor(Color.WHITE);
            game.font.draw(game.batch, "Exit", exitButtonPosition.x, exitButtonPosition.y);
        }
        else if (isCursorOnExit()) 
        {
            game.font.draw(game.batch, "New game", newGameButtonPosition.x, newGameButtonPosition.y);
            game.font.draw(game.batch, "Options", optionsButtonPosition.x, optionsButtonPosition.y);
            game.font.draw(game.batch, "Instructions", instructionsButtonPosition.x, instructionsButtonPosition.y);
            game.font.setColor(Color.GRAY);
            game.font.draw(game.batch, "Exit", exitButtonPosition.x, exitButtonPosition.y);
        }
        else {
            game.font.setColor(Color.WHITE);
            game.font.draw(game.batch, "New game", newGameButtonPosition.x, newGameButtonPosition.y);
            game.font.draw(game.batch, "Options", optionsButtonPosition.x, optionsButtonPosition.y);
            game.font.draw(game.batch, "Instructions", instructionsButtonPosition.x, instructionsButtonPosition.y);
            game.font.draw(game.batch, "Exit", exitButtonPosition.x, exitButtonPosition.y);
        }
        game.batch.end();

        if (Gdx.input.isTouched()) {
            if (isCursorOnNewGame())
                game.setScreen(new GameScreen(game));
            else if (isCursorOnExit())
                Gdx.app.exit();
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