package com.BTKR8.perror;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
// import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
// import com.badlogic.gdx.ApplicationListener;
// import com.badlogic.gdx.Gdx;
// import com.badlogic.gdx.Input.Keys;
// import com.badlogic.gdx.InputProcessor;
// import com.badlogic.gdx.audio.Music;
// import com.badlogic.gdx.audio.Sound;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class BTKR8 extends ApplicationAdapter {
    private SpriteBatch batch;
    private FitViewport viewport;

    private Texture floor;
    private float floorHeight = 100.0f;

    private Texture charTexture;
    private float tempCharHeight;
    private int isCharJumping = 0;
    private Sprite charSprite;
    private int charWidth = 100, charHeight = 100;
    private float xSpeed = 3f, jumpSpeed = 4f, gSpeed = 4f, jumpHeight = 100.0f;

    @Override
    public void create() {
        batch = new SpriteBatch();
        viewport = new FitViewport(640, 480);
        floor = new Texture("floor.png");
        charTexture = new Texture("character.png");
        charSprite = new Sprite(charTexture, charWidth, charHeight);
        charSprite.setPosition(140, 100);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    private void input() {
        float worldWidth = viewport.getWorldWidth();

        if (charSprite.getX() < worldWidth-charWidth)
            if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                charSprite.translateX(xSpeed);
            }

        if (0 < charSprite.getX())
            if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                charSprite.translateX(-xSpeed);
            }

        if (charSprite.getY() == floorHeight) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                isCharJumping = 1;
                tempCharHeight = charSprite.getY();
            }
        }
        if ((isCharJumping == 1) && (charSprite.getY() - tempCharHeight < jumpHeight)) {
            charSprite.translateY(jumpSpeed);
            if (charSprite.getY() - tempCharHeight == jumpHeight) isCharJumping = 2;
        } else if ((isCharJumping == 2) && (charSprite.getY() > floorHeight)) {
            charSprite.translateY(-gSpeed);
            if (charSprite.getY() == floorHeight) isCharJumping = 0;
        }
    }
    
    private void draw() {
        viewport.apply();

        float worldWidth = viewport.getWorldWidth();
        batch.draw(floor, 0, 0, worldWidth, floorHeight);
        charSprite.draw(batch);
    }

    @Override
    public void render() {
        // ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        ScreenUtils.clear(1f, 1f, 1f, 0f);

        this.input();
        batch.begin();
        this.draw();
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        charTexture.dispose();
    }
}
