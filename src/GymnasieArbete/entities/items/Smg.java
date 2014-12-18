package GymnasieArbete.entities.items;

import GymnasieArbete.graphics.Sprite;

public class Smg extends Weapon {

	public Smg(int x, int y) {
		super(x, y, "SMG");
		name = "SMG";
		fireRate = 10;
		maxammo = 20;
		ammo = 20;
		sprite = Sprite.smg;
		sound = "weapons/smg.wav";
	}
}
