package com.algodal.connect4x4;

import static com.algodal.connect4x4.Constants.debugQuadBorder;
import static com.algodal.connect4x4.Constants.gridSize;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class Grid extends Entity {
	final static float thick = 4;
	final static int rows = 4;
	final static int cols = 4;
	final static int total = rows * cols;
	
	final Quad[] quads;
	final Blocks blocks;
	final static Color[] colors = {Color.GREEN, Color.BROWN, Color.DARK_GRAY, Color.MAGENTA};
	
	/// touch input ///
	final Vector2 v;
	boolean left, right, down, up;
	
	public Grid(Game g) {
		quads = new Quad[total];
		w = gridSize;
		h = w;
		
		for(int r = 0; r < rows; r++) {
			for(int c = 0; c < cols; c++) {
				final Quad q = new Quad();
				q.w = w / cols;
				q.h = h / rows;
				final float cx = left() + (q.w / 2);
				final float cy = bottom() + (q.h / 2);
				q.x = cx + (c * q.w);
				q.y = cy + (r * q.h);
				quads[(r * cols) + c] = q;
			}
		}
		
		v = new Vector2();
		((InputMultiplexer)Gdx.input.getInputProcessor()).addProcessor(new GestureDetector(createListener()));
		
		blocks = new Blocks(total, this);
		blocks.save(g, "reset");
	}
	
	@Override
	void debug(Game g) {
		debugQuadBorder(g, this, Color.BLUE, thick);
		blocks.debug(g);
	}
	
	@Override
	void update(Game g) {
		if(!blocks.isMoving()) {
			if(left) {
				g.fvp.unproject(v);
				if(contains(v.x, v.y)) {
					Gdx.app.log("touched " + v.x + ", " + v.y, "left");
					final int cr = gridCR(v.x, v.y, false);
					blocks.setMoveLeft(cr);
					blocks.play.push(new Direction(Direction.LEFT, cr));
				}
			}
			
			if(right) {
				g.fvp.unproject(v);
				if(contains(v.x, v.y)) {
					Gdx.app.log("touched " + v.x + ", " + v.y, "right");
					final int cr = gridCR(v.x, v.y, false);
					blocks.setMoveRight(cr);
					blocks.play.push(new Direction(Direction.RIGHT, cr));
				}
			}
			
			if(down) {
				g.fvp.unproject(v);
				if(contains(v.x, v.y)) {
					Gdx.app.log("touched " + v.x + ", " + v.y, "down");
					final int cr = gridCR(v.x, v.y, true);
					blocks.setMoveDown(cr);
					blocks.play.push(new Direction(Direction.DOWN, cr));
				}
			}
			
			if(up) {
				g.fvp.unproject(v);
				if(contains(v.x, v.y)) {
					Gdx.app.log("touched " + v.x + ", " + v.y, "up");
					final int cr = gridCR(v.x, v.y, true);
					blocks.setMoveUp(cr);
					blocks.play.push(new Direction(Direction.UP, cr));
				}
			}
		}
		
		clear();
		
		blocks.update(g);
	}
	
	void clear() {
		up = down = left = right = false;
	}
	
	/** returns either the row or col. b == true then returns col **/
	int gridCR(float x, float y, boolean b) {
		for(int r = 0; r < rows; r++) {
			for(int c = 0; c < cols; c++) {
				if(quads[(r*cols)+c].contains(x, y)) {
					if(b) return c; else return r;
				}
			}
		}
		
		throw new GdxRuntimeException("Grid does not contain " + x + ", " + y);
	}
	
	float gridXY(int c, int r, boolean b) {
		if(b) {
			if(c==cols) {
				return quads[(c-1)].x + quads[0].w;
			} else if(c==-1) {
				return quads[0].x - quads[0].w;
			} else {
				return quads[c].x;
			}
		} else {
			if(r==rows) {
				return quads[(r-1)*cols].y + quads[0].h;
			} else if(r==-1) {
				return quads[0].y - quads[0].h;
			} else {
				return quads[r*cols].y;
			}
		}
	
	}
	
	GestureListener createListener() {
		return new GestureListener() {
			
			@Override
			public boolean zoom(float initialDistance, float distance) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean touchDown(float x, float y, int pointer, int button) {
				v.set(x, y);
				return false;
			}
			
			@Override
			public boolean tap(float x, float y, int count, int button) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public void pinchStop() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean panStop(float x, float y, int pointer, int button) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean pan(float x, float y, float deltaX, float deltaY) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean longPress(float x, float y) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean fling(float velocityX, float velocityY, int button) {
		        if(Math.abs(velocityX)>Math.abs(velocityY)){
		            if(velocityX>0){
		                    right = true;
		            }else{
		                    left = true;
		            }
		        }else{
		            if(velocityY>0){
		                    down = true;
		            }else{                                  
		                    up = true;
		            }
		        }
				return false;
			}
		};
	}
}











































