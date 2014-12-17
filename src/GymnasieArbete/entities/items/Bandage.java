package GymnasieArbete.entities.items;

import GymnasieArbete.graphics.Screen;
import GymnasieArbete.graphics.Sprite;

public class Bandage extends Item {
	
	public Bandage(int x, int y) {
		this.x = x << 4;
		this.y = y << 4;
		sprite = Sprite.bandage;
		name = "Bandage";
		type = Type.CONSUMABLE;
		healing = 20;
	}
	
	public void use() {
		if(player.getHealth().getHealth() + healing < player.getHealth().getMaxHealth()) {
			player.getHealth().setHealth(player.getHealth().getHealth() + healing);
		}
		else if (player.getHealth().getHealth() < player.getHealth().getMaxHealth()) {
			player.getHealth().setHealth(player.getHealth().getMaxHealth());
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
