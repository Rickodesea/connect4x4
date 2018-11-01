package com.algodal.connect4x4;

import static com.algodal.connect4x4.Constants.debugQuadBorder;

public class Button extends Widget {
	boolean down;
	
	public Button(Game g) {
		super(g);
	}
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if(!down) return super.touchDown(screenX, screenY, pointer, button);
		down = true;
		return false;
	}
	
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if(down) return super.touchUp(screenX, screenY, pointer, button);
		down = false;
		return false;
	}
	
	@Override
	void debug(Game g) {
		debugQuadBorder(g, this, color, thick);
	}

}
