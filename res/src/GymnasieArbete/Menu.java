package GymnasieArbete;

import GymnasieArbete.graphics.Screen;
import GymnasieArbete.graphics.Sprite;
import GymnasieArbete.input.Mouse;

public class Menu {
	
	private int xCenter = Game.getWindowWidth() / 2;

	public void render(Screen screen) {
		screen.renderSprite(0, 0, Sprite.background, false);	
		
		if (Mouse.getMenuB() == 1) {
			int mx = Mouse.getX();
			int my = Mouse.getY();
			if (mx >= xCenter - 110 && mx <= xCenter + 110) {
				if (my >= 215 && my <= 275) {
					Game.state = Game.STATE.GAME;
				}
			}
		}

		if (Mouse.getMenuB() == 1) {
			int mx = Mouse.getX();
			int my = Mouse.getY();
			if (mx >= xCenter - 110 && mx <= xCenter + 110) {
				if (my >= 310 && my <= 380) {
					Game.state = Game.STATE.GAME;
				}
			}
		}

		if (Mouse.getMenuB() == 1) {
			int mx = Mouse.getX();
			int my = Mouse.getY();
			if (mx >= xCenter - 105 && mx <= xCenter + 105) {
				if (my >= 410 && my <= 480) {
					Game.running = false;
				}
			}
		}
	}
}
