
package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

// Implements Game Over Screen
public class GameOverScreen implements Screen {
    final HeslingtonHustle game;
    OrthographicCamera camera;
    Texture background;
    Vector2 titlePosition;
    private float finalscore;
    public GameOverScreen(final HeslingtonHustle game) {
        titlePosition = new Vector2((game.screenWidth / 2) - (150), (game.screenHeight / 2));
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1000, 600);
        background = new Texture("background.jpg");
        finalscore = score;

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
        game.font.draw(game.batch, "Heslington Hustle", titlePosition.x, titlePosition.y);
        game.font.getData().setScale(2, 2);
        game.font.draw(game.batch, "Final score: " + finalscore, titlePosition.x + 60, 250);
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
