package GymnasieArbete.entities.projectile;

import GymnasieArbete.graphics.Screen;
import GymnasieArbete.graphics.Sprite;

public class PistolProjectile extends Projectile {
	
	public static final int FIRE_RATE = 20;

	public PistolProjectile(int x, int y, double dir) {
		super(x, y, dir);
		range = 125;
		speed = 4;
		damage = 20;
		sprite = Sprite.pistolProjectile;
		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);
	}

	public void update() {
		if(level.tileCollision(x, y, nx, ny, 2)) remove();
		move();
	}

	protected void move() {
			x += nx;
			y += ny;
		/*else if {level.mobCollision(x, y, nx, ny, sprite.SIZE)) {
			
		}*/
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
