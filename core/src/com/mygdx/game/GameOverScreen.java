
package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

// Implements Game Over Screen
public class GameOverScreen implements Screen {
    final HeslingtonHustle game;
    OrthographicCamera camera;
    Texture background;
    private float finalscore;
    public GameOverScreen(final HeslingtonHustle game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1000, 600);
        background = new Texture("background.jpg");
        finalscore = 0;

    }
    // Prints final score on the screen
    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(background, 0, 0);
        game.font.getData().setScale(3, 3);
        game.font.setColor(Color.WHITE);
        game.font.draw(game.batch, "Heslington Hustle", 300, 300);
        game.font.draw(game.batch, "Your final score was: " + finalscore, 300,250);
        game.font.draw(game.batch, "Press M to return to the main menu", 300, 200);
        game.batch.end();
        if (Gdx.input.isKeyJustPressed(Input.Keys.M)) {
            game.setScreen(new MainMenuScreen(game));
            dispose();
        }
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
