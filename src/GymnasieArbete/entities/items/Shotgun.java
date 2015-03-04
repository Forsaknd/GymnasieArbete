package GymnasieArbete.entities.items;

import GymnasieArbete.graphics.Sprite;

public class Shotgun extends Weapon {
	
	public Shotgun(int x, int y) {
		super(x, y, "Shotgun");
		name = "Shotgun";
		fireRate = 50;
		clipammo = 6;
		maxammo = 24;
		clipsize = 6;
		sprite = Sprite.shotgun;
		sound = "weapons/shotgun.wav";
		ammotype = Ammotype.SHELL;
	}
	
}