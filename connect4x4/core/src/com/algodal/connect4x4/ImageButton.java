package com.algodal.connect4x4;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ImageButton extends Button {
	TextureRegion normal, pressed;
	TextureRegion show;
	
	public ImageButton(Game g) {
		super(g);
	}
	
	@Override
	void draw(Game g) {
		if(super.down) {
			show = pressed;
		} else {
			show = normal;
		}
		
		if(show!=null) {
			g.sb.setProjectionMatrix(g.oc.combined);
			g.sb.begin();
			g.sb.draw(show, left(), bottom(), w, h);
			g.sb.end();
		}
	}
	
}



















