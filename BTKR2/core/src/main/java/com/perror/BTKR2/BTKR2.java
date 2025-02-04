package com.perror.BTKR2;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class BTKR2 extends ApplicationAdapter {
    private SpriteBatch batch;
    private FitViewport viewport;

    private Vector2 gravity = new Vector2(0, -9.8f);
    private World world = new World(gravity, true);
    private Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer();
    private OrthographicCamera camera;
    
    private BodyDef groundBodyDef;
    private Body groundBody;
    private PolygonShape groundBox;
    // private Texture image;
    
    @Override
    public void create() {
        batch = new SpriteBatch();
        viewport = new FitViewport(640, 480);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.update();
        
        // image = new Texture("libgdx.png");
        
        groundBox = new PolygonShape();
        groundBodyDef = new BodyDef();  
        groundBodyDef.position.set(new Vector2(0, 10));
        groundBody = world.createBody(groundBodyDef);
        groundBox.setAsBox(viewport.getWorldWidth(), 10.0f);
        groundBody.createFixture(groundBox, 0.0f);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    private void draw() {
        viewport.apply();
        // batch.draw(image, 140, 210);
    }

    private void drawFloor() {

    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        camera.update();
        
        batch.begin();
        draw();
        drawFloor();
        batch.end();
        debugRenderer.render(world, camera.combined);

        world.step(1/60f, 6, 2);
    }

    @Override
    public void dispose() {
        batch.dispose();
        // image.dispose();
        groundBox.dispose();
    }
}
