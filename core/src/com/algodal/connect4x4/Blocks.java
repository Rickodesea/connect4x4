package com.algodal.connect4x4;

import java.util.Stack;

public class Blocks extends Entity {
	final static int undocap = 30;
	
	final Block[] blocks;
	final Grid grid;
	final Stack<Direction> play;
	
	public Blocks(int total, Grid grid) {
		blocks = new Block[total];
		this.grid = grid;
		play = new Stack<Direction>();
		
		for(int r = 0; r < Grid.rows; r++) {
			for(int c = 0; c < Grid.cols; c++) {
				final int i = (r*Grid.cols)+c;
				final Block block = new Block();
				blocks[i] = block;
				block.r = r;
				block.c = c;
				block.x = grid.quads[i].x;
				block.y = grid.quads[i].y;
				block.color = Grid.colors[r];
			}
		}
	}
	
	@Override
	void debug(Game g) {
		for(Block block : blocks) {
			block.debug(g);
		}
	}
	
	@Override
	void update(Game g) {
		for(Block block : blocks) {
			block.update(g);
		}
	}
	
	void setMoveUp(int c) {
		for(Block block : blocks) {
			if(block.c == c) {
				block.up = true;
				block.r ++;
				block.tx = grid.gridXY(block.c, block.r, true);
				block.ty = grid.gridXY(block.c, block.r, false);
				if(grid.gridCR(block.x, block.y, false) == Grid.rows - 1) {
					block.overlap = true;
					block.r = 0;
				}
			}
		}
	}
	
	void setMoveDown(int c) {
		for(Block block : blocks) {
			if(block.c == c) {
				block.down = true;
				block.r --;
				block.tx = grid.gridXY(block.c, block.r, true);
				block.ty = grid.gridXY(block.c, block.r, false);
				if(grid.gridCR(block.x, block.y, false) == 0) {
					block.overlap = true;
					block.r = 3;
				}
			}
		}
	}
	
	void setMoveLeft(int r) {
		for(Block block : blocks) {
			if(block.r == r) {
				block.left = true;
				block.c --;
				block.tx = grid.gridXY(block.c, block.r, true);
				block.ty = grid.gridXY(block.c, block.r, false);
				if(grid.gridCR(block.x, block.y, true) == 0) {
					block.overlap = true;
					block.c = 3;
				}
			}
		}
	}
	
	void setMoveRight(int r) {
		for(Block block : blocks) {
			if(block.r == r) {
				block.right = true;
				block.c ++;
				block.tx = grid.gridXY(block.c, block.r, true);
				block.ty = grid.gridXY(block.c, block.r, false);
				if(grid.gridCR(block.x, block.y, true) == Grid.cols - 1) {
					block.overlap = true;
					block.c = 0;
				}
			}
		}
	}
	
	@Override
	void save(Game g, String name) {
		for(int i = 0; i < blocks.length; i++) {
			blocks[i].save(g, name+"_"+i);
		}
	}
	
	@Override
	void load(Game g, String name) {
		for(int i = 0; i < blocks.length; i++) {
			blocks[i].load(g, name+"_"+i);
		}
	}
	
	void undo(Game g) {
		if(play.isEmpty()) return;
		
		Direction d = play.pop();
		
		switch(d.dir) {
			case Direction.UP: {
				setMoveDown(d.cr);
			}break;
			
			case Direction.DOWN: {
				setMoveUp(d.cr);
			}break;
			
			case Direction.RIGHT: {
				setMoveLeft(d.cr);
			}break;
			
			case Direction.LEFT: {
				setMoveRight(d.cr);
			}break;
			
			default : break;
		}
	}
	
	boolean isMoving() {
		for(Block block : blocks) {
			if(block.down || block.up || block.right || block.left) return true;
		}
		return false;
	}
	
	void push(Direction d) {
		play.push(d);
		if(play.size() > undocap) {
			play.remove(0);
		}
	}
}

































