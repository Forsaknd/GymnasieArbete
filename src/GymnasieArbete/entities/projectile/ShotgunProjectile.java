package GymnasieArbete.entities.projectile;

import GymnasieArbete.graphics.Sprite;

public class ShotgunProjectile extends WeaponProjectile {

	public ShotgunProjectile(double x, double y, double dir) {
		super(x, y, dir);
		range = 100;
		speed = 8;
		damage = 10;
		sprite = Sprite.bullet;
		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);
	}
}
