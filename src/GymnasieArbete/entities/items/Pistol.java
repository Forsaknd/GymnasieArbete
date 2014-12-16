package GymnasieArbete.entities.items;

import GymnasieArbete.graphics.Screen;
import GymnasieArbete.graphics.Sprite;

public class Pistol extends Item {

	protected Sprite sprite;

	public Pistol(int x, int y) {
		this.x = x;
		this.y = y;
		sprite = Sprite.pistol;
	}
	
	protected void pickUp() {
	}

	public void render(Screen screen) {
		screen.renderMob((int) (x - 16), (int) (y - 16), sprite);
	}
	
}
