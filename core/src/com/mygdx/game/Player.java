package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.Texture;

public class Player {
    public enum State {
        IDLE,
        MOVING,
        DOING_RECREATIONAL_ACTIVITY,
        STUDYING,
        SLEEPING
    }  

    public enum Direction {
        UP,
        RIGHT,
        DOWN,
        LEFT
    }

    private Texture idleTexture
            , walkingUpTexture
            , walkingRightTexture
            , walkingDownTexture
            , walkingLeftTexture;
            


    // Player position within the map
    private Vector2 position;
    // Bounding box to detect player collision
    private Rectangle bounds;

    private State currentState;
    private Direction currentDirection;

    public Player(Vector2 position) {
        idleTexture = new Texture("player_idle_texture.png");
        bounds = new Rectangle();
        bounds.setPosition(position);
        bounds.height = 64;
        bounds.width = 64;
        this.position = position;
        currentState = State.IDLE;
        currentDirection = Direction.LEFT;
    }
    
    public Texture GetCurrentTexture() {
        if (currentState == State.IDLE)
            return idleTexture;

        switch (currentDirection) {
            case UP:
                return walkingUpTexture;
            case RIGHT:
                return walkingRightTexture;
            case DOWN:
                return walkingDownTexture;
            case LEFT:
                return walkingLeftTexture;
            default:
                return idleTexture;   
        }
    }

    public Vector2 GetPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        bounds.setPosition(position);
        this.position = position;
    }

    public Rectangle GetBounds() {
        return bounds;
    }

    public State GetCurrentState() {
        return currentState;
    }

    public Direction GetCurrentDirection() {
        return currentDirection;
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

