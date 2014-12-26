package GymnasieArbete.entities.items;

import GymnasieArbete.graphics.Screen;

public class Ammo extends Item {

	protected int ammo = 0;

	public Ammo(int x, int y, String name) {
		this.x = x << 4;
		this.y = y << 4;
		this.name = name;
		type = Type.AMMO;
	}

	public void use() {
		player.setAmmo(player.getAmmo() + ammo);
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
