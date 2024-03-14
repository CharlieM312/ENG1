package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Rectangle;

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

    static final float SPEED = 2f;
    static final float SIZE  = 0.5f;

    // Player position within the map
    Vector2 position = new Vector2();
    // Used to calculate the speed that the player moves
    Vector2 velocity = new Vector2();
    // Bounding box to detect player collision
    Rectangle bounds = new Rectangle();

    State currentState = State.IDLE;
    Direction currentDirection = Direction.LEFT;

    public Player(Vector2 position) {
        this.position = position;
        this.bounds.height = SIZE;
        this.bounds.width = SIZE;
    }
    
    public Vector2 GetPosition()
    {
        return position;
    }
}

