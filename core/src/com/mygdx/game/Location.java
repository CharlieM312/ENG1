package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.Texture;

public class Location {
    public enum State {
        IDLE,
        INTERACTING_WITH_PLAYER
    }

    protected Texture locationTexture;

    protected Vector2 position;
    // Bounding box to detect player collision
    protected Rectangle bounds;
    protected State currentState;

    public Location(Vector2 position, float width, float height, Texture image) {
        bounds = new Rectangle();
        bounds.setPosition(position);
        locationTexture = image;
        this.position = position;
        currentState = State.IDLE;
        bounds.width = width;
        bounds.height = height;
    }

    public Rectangle GetBounds() {
        return bounds;
    }

    public State GetCurrentState() {
        return currentState;
    }

    public float GetXPosition() {
        return bounds.x;
    }

    public void SetXPosition(float x) {
        bounds.x = x;
        position.x = x;
    }

    public float GetYPosition()
    {
        return bounds.y;
    }

    public void SetYPosition(float y)
    {
        bounds.y = y;
        position.y = y;
    }
}
