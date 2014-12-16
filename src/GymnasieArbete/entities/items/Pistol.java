package GymnasieArbete.entities.items;

import GymnasieArbete.graphics.Screen;
import GymnasieArbete.graphics.Sprite;

public class Pistol extends Item {

	public Pistol(int x, int y) {
		this.x = x << 4;
		this.y = y << 4;
		sprite = Sprite.pistol;
	}
	
	public void pickUp() {
	}

	public void update() {
		
	}
	
	public void render(Screen screen) {
		screen.renderItem((int) x - 8, (int) y - 8, this);
	}

	public void renderInInventory(Screen screen, int x, int y) {
		screen.renderSprite(x, y, sprite, false);
	}
	
}
