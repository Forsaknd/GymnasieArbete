package GymnasieArbete.entities.projectile;

import GymnasieArbete.graphics.Screen;
import GymnasieArbete.graphics.Sprite;

public class PistolProjectile extends Projectile {

	public PistolProjectile(int x, int y, double dir) {
		super(x, y, dir);
		range = 200;
		speed = 2;
		damage = 20;
		rateOfFire = 15;
		sprite = Sprite.pistolProjectile;
		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);
	}

	public void update() {
		move();
	}

	protected void move() {
		x += nx;
		y += ny;
		
		if (distance() > range) remove();
	}
	
	private double distance() {
		double dist = 0;
		dist = Math.sqrt(Math.abs((xOrigin - x) * (xOrigin - x) + (yOrigin - y) * (yOrigin - y)));
		return dist;
	}

	public void render(Screen screen) {
		screen.renderEntity((int) x, (int) y, sprite);
	}

}
