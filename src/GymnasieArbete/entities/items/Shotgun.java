package GymnasieArbete.entities.items;

import GymnasieArbete.graphics.Sprite;

public class Shotgun extends Weapon {
	
	public Shotgun(int x, int y) {
		super(x, y, "Shotgun");
		name = "Shotgun";
		fireRate = 50;
		maxammo = 6;
		ammo = 6;
		sprite = Sprite.shotgun;
		sound = "weapons/shotgun.wav";
	}
	
}