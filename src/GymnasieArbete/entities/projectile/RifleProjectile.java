package GymnasieArbete.entities.projectile;

import GymnasieArbete.graphics.Sprite;

public class RifleProjectile extends WeaponProjectile {

	public RifleProjectile(double x, double y, double dir) {
		super(x, y, dir);
		range = 200;
		speed = 10;
		damage = 100;
		sprite = Sprite.bullet;
		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);
	}
	
}
