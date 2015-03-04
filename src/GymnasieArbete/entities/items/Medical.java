package GymnasieArbete.entities.items;

import GymnasieArbete.entities.items.Item.Ammotype;
import GymnasieArbete.graphics.Screen;

public class Medical extends Item {

	protected int healing = 0;

	public Medical(int x, int y, String name) {
		this.x = x << 4;
		this.y = y << 4;
		this.name = name;
		type = Type.CONSUMABLE;
		ammotype = Ammotype.UNKOWN;
	}

	public void use() {
		if (player.getHealth().getHealth() + healing < player.getHealth().getMaxHealth()) {
			player.getHealth().setHealth(player.getHealth().getHealth() + healing);
		} else if (player.getHealth().getHealth() + healing > player.getHealth().getMaxHealth()) {
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
