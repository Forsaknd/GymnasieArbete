package GymnasieArbete.entities.items;

import GymnasieArbete.graphics.Sprite;

public class ShellAmmo extends Ammo {

	public ShellAmmo(int x, int y) {
		super(x, y, "ShellAmmo");
		name = "ShellAmmo";
		ammo = 5;
		sprite = Sprite.ammocrate;
	}

}
