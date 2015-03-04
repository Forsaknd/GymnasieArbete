package GymnasieArbete.entities.items;

import GymnasieArbete.graphics.Sprite;

public class Rifle extends Weapon {
	
	public Rifle(int x, int y) {
		super(x, y, "Rifle");
		name = "Rifle";
		fireRate = 40;
		clipammo = 5;
		maxammo = 50;
		clipsize = 5;
		sprite = Sprite.rifle;
		sound = "weapons/rifle.wav";
	}
	
}