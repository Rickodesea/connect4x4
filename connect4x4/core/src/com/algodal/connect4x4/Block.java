package com.algodal.connect4x4;

import static com.algodal.connect4x4.Constants.debugQuadBorder;
import static com.algodal.connect4x4.Constants.gridSize;
import static com.algodal.connect4x4.Constants.negative;
import static com.algodal.connect4x4.Constants.positive;
import static com.algodal.connect4x4.Constants.screenWidth;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;

public class Block extends Entity {
	final static float thick = 1;
	Color color;
	
	int r, c;
	
	/// movement ///
	boolean up, down, right, left;
	float tx, ty;
	final static float speed = screenWidth * 0.80f / 4;
	boolean overlap;
	
	public Block() {
		w = screenWidth * 0.10f;
		h = w;
		color = Color.YELLOW;
	}
	
	@Override
	void debug(Game g) {
		debugQuadBorder(g, this, color, thick);
		if(overlap) {
			if(up) {
				final float tempy = y;
				y = y - gridSize;
				debugQuadBorder(g, this, color, thick);
				y = tempy;
			}
			
			if(down) {
				final float tempy = y;
				y = y + gridSize;
				debugQuadBorder(g, this, color, thick);
				y = tempy;
			}
			
			if(right) {
				final float tempx = x;
				x = x - gridSize;
				debugQuadBorder(g, this, color, thick);
				x = tempx;
			}
			
			if(left) {
				final float tempx = x;
				x = x + gridSize;
				debugQuadBorder(g, this, color, thick);
				x = tempx;
			}
		}
	}
	
	@Override
	void update(Game g) {
		move(g);
	}
	
	void move(Game g) {
		if(up) {
			if(positive(ty - y, true)) {
				final float d = speed * g.delta;
				y += d;
			}
			
			if(negative(ty - y, true)) {
				y = ty;
				if(overlap) y = y - gridSize;
				clear();
				Gdx.app.log("block", "col " + c + ", row " + r);
			}
		}
		
		if(down) {
			if(negative(ty - y, true)) {
				final float d = speed * g.delta;
				y -= d;
			}
			
			if(positive(ty - y, true)) {
				y = ty;
				if(overlap) y = y + gridSize;
				clear();
				Gdx.app.log("block", "col " + c + ", row " + r);
			}
		}
		
		if(right) {
			if(positive(tx - x, true)) {
				final float d = speed * g.delta;
				x += d;
			}
			
			if(negative(tx - x, true)) {
				x = tx;
				if(overlap) x = x - gridSize;
				clear();
				Gdx.app.log("block", "col " + c + ", row " + r);
			}
		}
		
		if(left) {
			if(negative(tx - x, true)) {
				final float d = speed * g.delta;
				x -= d;
			}
			
			if(positive(tx - x, true)) {
				x = tx;
				if(overlap) x = x + gridSize;
				clear();
				Gdx.app.log("block", "col " + c + ", row " + r);
			}
		}
	}
	
	void clear() {
		up = down = left = right = false;
		overlap = false;
	}
	
	@Override
	void save(Game g, String name) {
		Preferences pref = Gdx.app.getPreferences(g.saveName() + name);
		pref.putFloat("x", x);
		pref.putFloat("y", y);
		pref.putFloat("tx", tx);
		pref.putFloat("ty", ty);
		pref.putInteger("r", r);
		pref.putInteger("c", c);
		pref.putBoolean("up", up);
		pref.putBoolean("down", down);
		pref.putBoolean("left", left);
		pref.putBoolean("right", right);
		pref.putBoolean("overlap", overlap);
		pref.putFloat("colorr", color.r);
		pref.putFloat("colorg", color.g);
		pref.putFloat("colorb", color.b);
		pref.putFloat("colora", color.a);
		pref.flush();
	}
	
	@Override
	void load(Game g, String name) {
		Preferences pref = Gdx.app.getPreferences(g.saveName() + name);
		color = new Color(
				pref.getFloat("colorr"), 
				pref.getFloat("colorg"), 
				pref.getFloat("colorb"), 
				pref.getFloat("colora")
				);
		overlap = pref.getBoolean("overlap");
		up = pref.getBoolean("overlap");
		down = pref.getBoolean("down");
		left = pref.getBoolean("left");
		right = pref.getBoolean("right");
		r = pref.getInteger("r");
		c = pref.getInteger("c");
		x = pref.getFloat("x");
		y = pref.getFloat("y");
		tx = pref.getFloat("tx");
		ty = pref.getFloat("ty");
	}
}




























