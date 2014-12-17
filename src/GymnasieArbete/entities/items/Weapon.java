package GymnasieArbete.entities.items;

import GymnasieArbete.graphics.Screen;
import GymnasieArbete.graphics.Sprite;

public class Weapon extends Item {
	
	public Weapon(int x, int y, String name, int fireRate,Sprite sprite) {
		this.x = x << 4;
		this.y = y << 4;
		this.name = name;
		this.fireRate = fireRate;
		this.sprite = sprite;
		type = Type.WEAPON;
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
