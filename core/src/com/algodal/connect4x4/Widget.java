package com.algodal.connect4x4;

import static com.algodal.connect4x4.Constants.debugQuadBorder;
import static com.algodal.connect4x4.Constants.defaultColor;
import static com.algodal.connect4x4.Constants.defaultHeight;
import static com.algodal.connect4x4.Constants.defaultThick;
import static com.algodal.connect4x4.Constants.defaultWidth;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class Widget extends Entity implements InputProcessor {
	final Game g;
	ChangeListener change;
	final Vector2 v;
	Color color;
	float thick;
	
	public Widget(Game g) {
		this.g = g;
		g.im.addProcessor(this);
		v = new Vector2();
		w = defaultWidth;
		h = defaultHeight;
		color = defaultColor;
		thick = defaultThick;
	}
	
	void setListener(ChangeListener change) {
		this.change = change;
	}
	
	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		v.set(screenX, screenY);
		g.fvp.unproject(v);
		if(change!=null) {
			if(contains(v.x, v.y)) {
				change.touchDown(g, this, v, pointer, button);
			}
		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		v.set(screenX, screenY);
		g.fvp.unproject(v);
		if(change!=null) {
			if(contains(v.x, v.y)) {
				change.touchDown(g, this, v, pointer, button);
			}
		}
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public static class ChangeListener {
		void touchDown(Game g, Widget w, Vector2 v, int pointer, int button) {};
		void touchUp(Game g, Widget w, Vector2 v, int pointer, int button) {};
	}
	
	@Override
	void debug(Game g) {
		debugQuadBorder(g, this, color, thick);
	}
	
}















