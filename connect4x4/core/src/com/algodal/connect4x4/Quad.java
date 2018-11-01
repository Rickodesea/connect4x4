package com.algodal.connect4x4;

public class Quad {
	float x, y, w, h;
	
	Quad x(float x) { this.x = x; return this; }
	Quad y(float y) { this.y = y; return this; }
	Quad w(float w) { this.w = w; return this; }
	Quad h(float h) { this.h = h; return this; }
	
	Quad left(float left) { x = left + hw(); return this; }
	Quad right(float right) { x = right - hw(); return this; }
	Quad bottom(float bottom) { y = bottom + hh(); return this; }
	Quad top(float top) { y = top - hh(); return this; }
	
	float hw() { return w / 2; }
	float hh() { return h / 2; }
	
	float left() { return x - hw(); }
	float right() { return x + hw(); }
	float top() { return y + hh(); }
	float bottom() { return y - hh(); }
	
	boolean contains(float x, float y) {
		return
				left() < x && x < right() &&
				bottom() < y && y < top();
	}
	
	boolean contains(Quad q) { return contains(q.x, q.y); }
	
	float wide(Quad q) { return q.x - x; }
	float high(Quad q) { return q.y - y; }
	
	boolean collides(Quad q) {
		return
				Math.abs(wide(q)) <= (hw() + q.hw()) &&
				Math.abs(high(q)) <= (hh() + q.hh());
	}
}
