package GymnasieArbete.entities.items;

import GymnasieArbete.graphics.Sprite;

public class BulletAmmo extends Ammo {

	public BulletAmmo(int x, int y) {
		super(x, y, "BulletAmmo");
		name = "BulletAmmo";
		ammo = 10;
		sprite = Sprite.ammocrate;
	}

}
