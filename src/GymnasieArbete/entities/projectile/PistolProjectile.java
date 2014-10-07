package GymnasieArbete.entities.projectile;

import GymnasieArbete.entities.spawner.ParticleSpawner;
import GymnasieArbete.graphics.Screen;
import GymnasieArbete.graphics.Sprite;

public class PistolProjectile extends Projectile {
	
	public static final int FIRE_RATE = 5;

	public PistolProjectile(int x, int y, double dir) {
		super(x, y, dir);
		range = 150;
		speed = 2;
		damage = 20;
		sprite = Sprite.pistolProjectile;
		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);
	}

	public void update() {
		if(level.tileCollision((int) (x + nx), (int) (y + ny), 2, 7, 7)) {
			level.add(new ParticleSpawner((int) x + 5, (int) y + 6, 40, 20, level));
			remove();
		} else
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
		screen.renderProjectile((int) x, (int) y, this);
	}

}
