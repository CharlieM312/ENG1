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
            , walkingLeftTexture;
            
    static final float SPEED = 2f;
    static final float SIZE  = 64;

    // Player position within the map
    private Vector2 position;
    // Used to calculate the speed that the player moves
    private Vector2 velocity;
    // Bounding box to detect player collision
    private Rectangle bounds;

    private State currentState;
    private Direction currentDirection;

    public Player(Vector2 position) {
        idleTexture = new Texture("player_idle_texture.png");
        this.position = position;
        bounds.height = SIZE;
        bounds.width = SIZE;
        currentState = State.IDLE;
        currentDirection = Direction.LEFT;
    }
<<<<<<< Updated upstream
=======
    
    public Texture GetIdleTexture()
    {
        return idleTexture;
    }

    public Vector2 GetPosition()
    {
        return position;
    }

    public Rectangle GetBounds()
    {
        return bounds;
    }

    public State GetCurrentState()
    {
        return currentState;
    }

    public Direction GetCurrentDirection()
    {
        return currentDirection;
    }
>>>>>>> Stashed changes
}

