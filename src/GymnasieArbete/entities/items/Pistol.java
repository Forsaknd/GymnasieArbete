package GymnasieArbete.entities.items;

import GymnasieArbete.graphics.Sprite;

public class Pistol extends Weapon {
	
	public Pistol(int x, int y) {
		super(x, y, "Pistol");
		name = "Pistol";
		fireRate = 25;
		maxammo = 12;
		ammo = 12;
		sprite = Sprite.pistol;
		sound = "weapons/pistol.wav";
	}
	
}