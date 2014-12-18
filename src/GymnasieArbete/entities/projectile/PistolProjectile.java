package GymnasieArbete.entities.projectile;

import GymnasieArbete.graphics.Sprite;

public class PistolProjectile extends WeaponProjectile {

	public PistolProjectile(double x, double y, double dir) {
		super(x, y, dir);
		range = 150;
		speed = 8;
		damage = 20;
		sprite = Sprite.bullet;
		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);
	}
	
}
