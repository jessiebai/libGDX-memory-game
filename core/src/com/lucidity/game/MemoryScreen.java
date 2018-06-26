package com.lucidity.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import sun.rmi.runtime.Log;

import static com.lucidity.game.Constants.BACKGROUND_COLOR;

public class MemoryScreen extends InputAdapter implements Screen {
    public static final String TAG = MemoryScreen.class.getName();
    MemoryGame game;
    Constants.Difficulty difficulty;

    ExtendViewport memoryViewport;
    ScreenViewport hudViewport;

    ShapeRenderer renderer;

    int screenWidth;
    int screenHeight;

    int score;

    int flag1;
    int flag2;
    int flag3;
    int flag4;


    public MemoryScreen(MemoryGame game, Constants.Difficulty difficulty) {
        this.game = game;
        this.difficulty = difficulty;

        this.flag1=0;
        this.flag2=0;
        this.flag3=0;
        this.flag4=0;

        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
    }

    @Override
    public void show() {
        memoryViewport = new ExtendViewport(Constants.WORLD_SIZE, Constants.WORLD_SIZE);
        hudViewport = new ScreenViewport();

        renderer = new ShapeRenderer();

        Gdx.input.setInputProcessor(this);

        score = 0;
        flag1=1;
        flag2=1;


        Timer.schedule(new Timer.Task(){
                           @Override
                           public void run() {
                               flag1=0;
                               flag2=0;
                           }
                       },
                0,30/30.0f);


    }


    @Override
    public void resize(int width, int height) {
        memoryViewport.update(width, height, true);
        hudViewport.update(width, height, true);
        System.out.print("resize");

    }

    @Override
    public void dispose() {

    }

    @Override
    public void render(float delta) {

        memoryViewport.apply(true);
        Gdx.gl.glClearColor(BACKGROUND_COLOR.r, BACKGROUND_COLOR.g, BACKGROUND_COLOR.b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.begin(ShapeRenderer.ShapeType.Filled);

        if(flag1 == 1) {
            renderer.setColor(Color.valueOf("#FFC04C"));
        } else {
            renderer.setColor(Color.valueOf("#FFD2E8"));

        }

        renderer.rect(screenWidth / 2 - screenHeight / 4, screenHeight / 4, screenHeight / 4, screenHeight / 4);

        if(flag2 == 1) {
            renderer.setColor(Color.valueOf("#FFC04C"));

        } else {
            renderer.setColor(Color.valueOf("#FFD2E8"));
        }

        renderer.rect(screenWidth / 2, screenHeight / 4, screenHeight / 4, screenHeight / 4);

        if(flag3 == 1) {
            renderer.setColor(Color.valueOf("#FFC04C"));

        } else {
            renderer.setColor(Color.valueOf("#FFD2E8"));
        }

        renderer.rect(screenWidth / 2, screenHeight / 2, screenHeight / 4, screenHeight / 4);

        if(flag4 == 1) {
            renderer.setColor(Color.valueOf("#FFC04C"));

        } else {
            renderer.setColor(Color.valueOf("#FFD2E8"));
        }

        renderer.rect(screenWidth / 2 - screenHeight / 4, screenHeight / 2, screenHeight / 4, screenHeight / 4);
        renderer.end();


        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.setColor(Color.valueOf("#FF3232"));
        renderer.rect(screenWidth/2-screenHeight/4, screenHeight/4, screenHeight/4, screenHeight/4);
        renderer.rect(screenWidth/2-screenHeight/4, screenHeight/2, screenHeight/4, screenHeight/4);
        renderer.rect(screenWidth/2, screenHeight/2, screenHeight/4, screenHeight/4);
        renderer.rect(screenWidth/2, screenHeight/4, screenHeight/4, screenHeight/4);
        renderer.end();

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {


    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if(screenX > screenWidth/2-screenHeight/4 && screenX < screenWidth/2
                && screenY > screenHeight/2 && screenY < 3*screenHeight/4) {
           flag1=1-flag1;
        }
        else if(screenX>screenWidth/2 && screenX < screenWidth+screenHeight/4
                && screenY >screenHeight/2 && screenY < 3*screenHeight/4) {
            flag2=1-flag2;
        }
        else if(screenX>screenWidth/2 && screenX < screenWidth+screenHeight/4
                && screenY > screenHeight/4 && screenY < screenHeight/2) {
            flag3=1-flag3;
        }
        else if(screenX > screenWidth/2-screenHeight/4 && screenX < screenWidth/2
                && screenY > screenHeight/4 && screenY < screenHeight/2) {
            flag4=1-flag4;
        }

        return true;
    }

}
