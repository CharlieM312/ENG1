package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;

public class Player {
    // Defines all the different player states
    public enum playerState {
        IDLE,
        LOCKED,
        INTERACTING_WITH_LOCATION,
        MOVING,
        DOING_RECREATIONAL_ACTIVITY,
        STUDYING,
        SLEEPING
    }  
    // Defines all the different directions the player can travel in
    public enum Direction {
        UP,
        RIGHT,
        DOWN,
        LEFT
    }
    // Defines the different textures the player can have
    private Texture idleTexture
            , walkingRightTexture
            , walkingLeftTexture;

    // Player position within the map
    Vector2 position;
    // Bounding box to detect player collision
    Rectangle bounds;

    playerState currentplayerState = playerState.IDLE;
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

        currentplayerState = playerState.IDLE;
        currentDirection = Direction.LEFT;
    }
    // Gets the current texture of the player depending on what direction the player is moving in
    public Texture GetCurrentTexture() {
        if (currentplayerState == playerState.IDLE || currentplayerState == playerState.LOCKED)
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
    // Checks for user input and if so sets the player state to moving, if not sets the player state to idle
    public void CheckForInput() {
		if (Gdx.input.isKeyPressed(Input.Keys.UP)      || Gdx.input.isKeyPressed(Input.Keys.RIGHT)
		    || Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.LEFT)
			|| Gdx.input.isKeyPressed(Input.Keys.W)    || Gdx.input.isKeyPressed(Input.Keys.D)
			|| Gdx.input.isKeyPressed(Input.Keys.S)    || Gdx.input.isKeyPressed(Input.Keys.A)) 
			SetState(playerState.MOVING);

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
            SetDirection(Direction.UP);
            MoveUp();
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
            SetDirection(Direction.LEFT);
            MoveLeft();
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            SetDirection(Direction.RIGHT);
            MoveRight();
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {
            SetDirection(Direction.DOWN);
            MoveDown();
        }
        else {
            SetState(playerState.IDLE);
        }
    }
    // Moves the player 10 pixels back from the location they are currently in when the user wishes to exit
    public void exitLocation() {
        switch (currentDirection) {
            case UP:
                this.SetYPosition(this.GetYPosition() - 10);
                break;
            case RIGHT:
                this.SetXPosition(this.GetXPosition() - 10);
                break;
            case DOWN:
                this.SetYPosition(this.GetYPosition() + 10);
                break;
            case LEFT:
                this.SetXPosition(this.GetXPosition() + 10);
            default:
                break;
        }
    }

    public void setPosition(Vector2 position) {
        bounds.setPosition(position);
        this.position = position;
    }

    public Rectangle GetBounds() {
        return bounds;
    }

    public playerState GetCurrentPlayerState() {
        return currentplayerState;
    }
    
    public void SetState(playerState playerState) {
        currentplayerState = playerState;
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

