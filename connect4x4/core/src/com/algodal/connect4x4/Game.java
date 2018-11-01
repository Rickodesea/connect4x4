package com.algodal.connect4x4;

import static com.algodal.connect4x4.Constants.cc;
import static com.algodal.connect4x4.Constants.debugQuadFill;
import static com.algodal.connect4x4.Constants.screenHeight;
import static com.algodal.connect4x4.Constants.screenWidth;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class Game extends ApplicationAdapter {
	/// libgdx ///
	ShapeRenderer sr;
	OrthographicCamera oc;
	FitViewport fvp;
	float delta;
	InputMultiplexer im;
	SpriteBatch sb;
	
	/// game objects ///
	Background bg;
	Grid grid;
	Buttons buttons;
	
	/// ads ///
	Quad ads1, ads2;
	
	@Override
	public void create() {
		sr = new ShapeRenderer();
		oc = new OrthographicCamera();
		fvp = new FitViewport(screenWidth, screenHeight, oc);
		im = new InputMultiplexer();
		sb = new SpriteBatch();
		
		Gdx.input.setInputProcessor(im);
		
		bg = new Background();
		grid = new Grid(this);
		buttons = new Buttons(this);
		
		ads1 = new Quad().x(0).y(0.90f*screenHeight/2).w(0.80f*screenWidth).h(0.05f*screenHeight);
		ads2 = new Quad().x(0).y(-0.90f*screenHeight/2).w(0.80f*screenWidth).h(0.05f*screenHeight);
	}
	
	@Override
	public void dispose() {
		sr.dispose();
		sb.dispose();
	}
	
	@Override
	public void resize(int width, int height) {
		fvp.update(width, height);
	}
	
	@Override
	public void render() {
		Gdx.gl.glClearColor( cc.r, cc.g, cc.b, cc.a );
		Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );
		delta = Gdx.graphics.getDeltaTime();
		
		bg.debug(this);
		grid.debug(this);
		buttons.debug(this);
		
		debugQuadFill(this, ads1, Color.GREEN);
		debugQuadFill(this, ads2, Color.GREEN);
		
		grid.update(this);
	}
	
	String saveName() {
		return this.getClass().getCanonicalName();
	}
}






















































































