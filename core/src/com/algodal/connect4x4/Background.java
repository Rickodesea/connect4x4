package com.algodal.connect4x4;

import static com.algodal.connect4x4.Constants.debugQuadBorder;
import static com.algodal.connect4x4.Constants.screenHeight;
import static com.algodal.connect4x4.Constants.screenWidth;

import com.badlogic.gdx.graphics.Color;

public class Background extends Entity {
	final static float thick = 4;
	
	public Background() {
		w = screenWidth;
		h = screenHeight;
	}
	
	@Override
	void debug(Game g) {
		debugQuadBorder(g, this, Color.RED, thick);
	}
}
