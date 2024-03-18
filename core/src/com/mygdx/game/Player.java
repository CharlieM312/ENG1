package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;

public class Player {

    public enum State {
        IDLE,
        LOCKED,
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
            , walkingRightTexture
            , walkingLeftTexture;

    // Player position within the map
    Vector2 position;
    // Bounding box to detect player collision
    Rectangle bounds;

    State currentState = State.IDLE;
    Direction currentDirection = Direction.LEFT;

    public Player(Vector2 position) {
        idleTexture         = new Texture("player_idle.jpg");
        walkingRightTexture = new Texture("player_facing_right.jpg");
        walkingLeftTexture  = new Texture("player_facing_left.jpg");

        bounds = new Rectangle();
        bounds.setPosition(position);
        bounds.height = 64;
        bounds.width = 64;

        this.position = position;

        currentState = State.IDLE;
        currentDirection = Direction.LEFT;
    }

    public Texture GetCurrentTexture() {
        if (currentState == State.IDLE || currentState == State.LOCKED)
            return idleTexture;

        switch (currentDirection) {
            case RIGHT:
                return walkingRightTexture;
            case LEFT:
                return walkingLeftTexture;
            default:
                return idleTexture;   
        }
    }

    public void MoveUp() {
        SetYPosition(position.y += (180 * Gdx.graphics.getDeltaTime()));
    }

    public void MoveRight() {
        SetDirection(Direction.RIGHT);
        SetXPosition(position.x += 180 * Gdx.graphics.getDeltaTime());        
    }

    public void MoveDown() {
        SetYPosition(position.y -= 180 * Gdx.graphics.getDeltaTime());
    }

    public void MoveLeft() {
        SetDirection(Direction.LEFT);
        SetXPosition(position.x -= 180 * Gdx.graphics.getDeltaTime());
    }

    public void CheckForInput() {
		if (Gdx.input.isKeyPressed(Input.Keys.UP)      || Gdx.input.isKeyPressed(Input.Keys.RIGHT)
		    || Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.LEFT)
			|| Gdx.input.isKeyPressed(Input.Keys.W)    || Gdx.input.isKeyPressed(Input.Keys.D)
			|| Gdx.input.isKeyPressed(Input.Keys.S)    || Gdx.input.isKeyPressed(Input.Keys.A)) 
			SetState(State.MOVING);

        if ((Gdx.input.isKeyPressed(Input.Keys.RIGHT) && Gdx.input.isKeyPressed(Input.Keys.UP)) || 
            (Gdx.input.isKeyPressed(Input.Keys.D)     && Gdx.input.isKeyPressed(Input.Keys.W))) {
            SetDirection(Direction.RIGHT);
            MoveUp();
            MoveRight();
        }
        else if ((Gdx.input.isKeyPressed(Input.Keys.RIGHT) && Gdx.input.isKeyPressed(Input.Keys.DOWN)) ||
                 (Gdx.input.isKeyPressed(Input.Keys.D)     && Gdx.input.isKeyPressed(Input.Keys.S))) {
            SetDirection(Direction.RIGHT);
            MoveRight();
            MoveDown();
        }
        else if ((Gdx.input.isKeyPressed(Input.Keys.LEFT) && Gdx.input.isKeyPressed(Input.Keys.UP)) || 
                (Gdx.input.isKeyPressed(Input.Keys.A)     && Gdx.input.isKeyPressed(Input.Keys.W))) {
            SetDirection(Direction.LEFT);
            MoveLeft();
            MoveUp();
        }
        else if ((Gdx.input.isKeyPressed(Input.Keys.LEFT) && Gdx.input.isKeyPressed(Input.Keys.DOWN)) ||
                (Gdx.input.isKeyPressed(Input.Keys.A)     && Gdx.input.isKeyPressed(Input.Keys.S))) {
            SetDirection(Direction.LEFT);
            MoveLeft();
            MoveDown();
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) {
            MoveUp();
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
            MoveLeft();
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            MoveRight();
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {
            MoveDown();
        }
        else {
            SetState(State.IDLE);
        }
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

    public void SetState(State state) {
        currentState = state;
    }

    public Direction GetCurrentDirection() {
        return currentDirection;
    }

    public void SetDirection(Direction direction) {
        currentDirection = direction;
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
    
    public Vector2 GetPosition()
    {
        return position;
    }
}

