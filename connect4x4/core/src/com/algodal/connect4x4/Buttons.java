package com.algodal.connect4x4;

import static com.algodal.connect4x4.Constants.gridSize;
import static com.algodal.connect4x4.Constants.screenWidth;

import com.algodal.connect4x4.Widget.ChangeListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class Buttons extends Entity {
	final ImageButton reset;
	final ImageButton undo;
	
	final Image image, counter;
	
	final ImageButton remove;
	
	public Buttons(Game g) {
		reset = new ImageButton(g);
		undo = new ImageButton(g);
		
		setSize(reset);
		setSize(undo);
		
		reset.left(Constants.left(0.80f));
		reset.bottom(Constants.bottom(0.65f));
		undo.right(Constants.right(0.80f));
		undo.bottom(Constants.bottom(0.65f));
		
		reset.setListener(new ChangeListener() {
			
			@Override
			public void touchDown(Game g, Widget w, Vector2 v, int pointer, int button) {
				Gdx.app.log("Button", "Reset Button Pressed");
				g.grid.blocks.load(g, "reset");
				g.grid.blocks.play.clear();
			}
		});
		
		undo.setListener(new ChangeListener() {
			
			@Override
			public void touchDown(Game g, Widget w, Vector2 v, int pointer, int button) {
				Gdx.app.log("Button", "Undo Button Pressed");
				if(!g.grid.blocks.isMoving()) g.grid.blocks.undo(g);
			}
		});
		
		image = new Image(g);
		counter = new Image(g);
		counter.top(Constants.top(0.79f));
		counter.w = 0.19f * screenWidth;
		counter.h = counter.w;
		
		remove = new ImageButton(g);
		remove.w = image.w;
		remove.h = image.h * 1.125f;
		remove.x = 0;
		remove.bottom(Constants.bottom(0.82f));
	}
	
	@Override
	void debug(Game g) {
		reset.debug(g);
		undo.debug(g);
		image.debug(g);
		counter.debug(g);
		remove.debug(g);
	}
	
	void setSize(Button b) {
		b.w = gridSize * 0.4f;
		b.h = b.w / 2;
	}
}























