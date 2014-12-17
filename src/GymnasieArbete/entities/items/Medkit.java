package GymnasieArbete.entities.items;

import GymnasieArbete.graphics.Screen;
import GymnasieArbete.graphics.Sprite;

public class Medkit extends Item {
	
	public Medkit(int x, int y) {
		this.x = x << 4;
		this.y = y << 4;
		sprite = Sprite.medkit;
		name = "Medkit";
		type = Type.CONSUMABLE;
		healing = 60;
	}
	
	public void use() {
		if (player.getHealth().getHealth() < player.getHealth().getMaxHealth()) {
			if(player.getHealth().getHealth() + healing < player.getHealth().getMaxHealth()) {
				player.getHealth().setHealth(player.getHealth().getHealth() + healing);
			} else {				
				player.getHealth().setHealth(player.getHealth().getMaxHealth());
			}
		}
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
