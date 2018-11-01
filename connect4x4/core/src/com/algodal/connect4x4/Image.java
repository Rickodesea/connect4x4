package com.algodal.connect4x4;

import static com.algodal.connect4x4.Constants.gridSize;
import static com.algodal.connect4x4.Constants.screenHeight;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Image extends Widget {
	
	TextureRegion img;

	public Image(Game g) {
		super(g);
		w = 0.50f * gridSize;
		h = 0.13f * gridSize;
		x = 0;
		y = 0.55f * screenHeight / 2;
	}

}
