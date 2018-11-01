package com.algodal.connect4x4;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Constants {
	final static Color cc = Color.BLACK;
	
	final static float screenWidth = 100;
	final static float screenHeight = (screenWidth * 16) / 9;
	final static float gridSize = screenWidth * 0.80f;
	final static float defaultWidth = screenWidth * 0.20f;
	final static float defaultHeight = defaultWidth / 3;
	final static Color defaultColor = Color.CHARTREUSE;
	final static float defaultThick = 1;
	
	static void debugQuadBorder(Game g, Quad q, Color c, float t) {
		/// side top-horizontal : 1 ///
		final float s1w = q.w;
		final float s1h = t;
		final float s1left = q.left();
		final float s1bottom = q.top() - t;
		
		/// side bottom-horizontal : 2 ///
		final float s2w = q.w;
		final float s2h = t;
		final float s2left = q.left();
		final float s2bottom = q.bottom();
		
		/// side left-vertical : 3 ///
		final float s3w = t;
		final float s3h = q.h - t - t;
		final float s3left = q.left();
		final float s3bottom = q.bottom() + t;
		
		/// side right-vertical : 4 ///
		final float s4w = t;
		final float s4h = q.h - t - t;
		final float s4left = q.right() - t;
		final float s4bottom = q.bottom() + t;
		
		g.sr.setColor(c);
		g.sr.setProjectionMatrix(g.oc.combined);
		g.sr.begin(ShapeType.Filled);
		g.sr.rect(s1left, s1bottom, s1w, s1h);
		g.sr.rect(s2left, s2bottom, s2w, s2h);
		g.sr.rect(s3left, s3bottom, s3w, s3h);
		g.sr.rect(s4left, s4bottom, s4w, s4h);
		g.sr.end();
	}
	
	static void debugQuadFill(Game g, Quad q, Color c) {
		g.sr.setColor(c);
		g.sr.setProjectionMatrix(g.oc.combined);
		g.sr.begin(ShapeType.Filled);
		g.sr.rect(q.left(), q.bottom(), q.w, q.h);
		g.sr.end();
	}
	
	/** b == true, include 0 **/
	static boolean positive(float f, boolean b) {
		if(b) return f >= 0;
		return f > 0;
	}
	
	/** b == true, include 0 **/
	static boolean negative(float f, boolean b) {
		if(b) return f <= 0;
		return f < 0;
	}
	
	static float left(float percent) {
		return -((screenWidth / 2) * percent);
	}
	
	static float right(float percent) {
		return +((screenWidth / 2) * percent);
	}
	
	static float bottom(float percent) {
		return -((screenHeight / 2) * percent);
	}
	
	static float top(float percent) {
		return +((screenHeight / 2) * percent);
	}
}





















