package GymnasieArbete.entities.projectile;

import GymnasieArbete.graphics.Sprite;

public class SmgProjectile extends WeaponProjectile {

	public SmgProjectile(double x, double y, double dir) {
		super(x, y, dir);
		range = 175;
		speed = 8;
		damage = 10;
		sprite = Sprite.bullet;
		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);
	}
}
