package GymnasieArbete.entities.items;

import GymnasieArbete.graphics.Sprite;

public class Pistol extends Weapon {
	
	public Pistol(int x, int y) {
		super(x, y, "Pistol");
		name = "Pistol";
		fireRate = 25;
		clipammo = 12;
		maxammo = 124;
		clipsize = 12;
		sprite = Sprite.pistol;
		sound = "weapons/pistol.wav";
	}
	
}