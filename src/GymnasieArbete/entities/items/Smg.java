package GymnasieArbete.entities.items;

import GymnasieArbete.graphics.Screen;
import GymnasieArbete.graphics.Sprite;

public class Smg extends Item {
	
	public Smg(int x, int y) {
		this.x = x << 4;
		this.y = y << 4;
		sprite = Sprite.smg;
		name = "SMG";
		type = Type.WEAPON;
		fireRate = 15;
	}
	
	public void update() {
	}
	
	public void render(Screen screen) {
		screen.renderItem((int) x - 8, (int) y - 8, this);
	}

	public void renderInInventory(Screen screen, int x, int y) {
		if(player.getEquipped().equals(this)) {
			Sprite selected = new Sprite(14, 13, 0xFF00FF00);
			screen.renderSprite(x + 1, y + 2, selected, false);
		}
		screen.renderSprite(x, y, sprite, false);
	}
	
}
