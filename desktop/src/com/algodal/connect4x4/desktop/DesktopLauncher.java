package com.algodal.connect4x4.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.algodal.connect4x4.Game;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 100 * 3;
		config.height = 177 * 3;
		new LwjglApplication(new Game(), config);
	}
}
