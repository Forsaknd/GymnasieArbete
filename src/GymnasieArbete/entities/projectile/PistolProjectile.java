package GymnasieArbete.entities.projectile;

import GymnasieArbete.graphics.Screen;
import GymnasieArbete.graphics.Sprite;

public class PistolProjectile extends Projectile {
	
	public static final int FIRE_RATE = 30;

	public PistolProjectile(int x, int y, double dir) {
		super(x, y, dir);
		range = 125;
		speed = 3;
		damage = 20;
		sprite = Sprite.pistolProjectile;
		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);
	}

	public void update() {
		move();
	}

	protected void move() {
		if(!level.tileCollision(x, y, nx, ny, sprite.SIZE)) {
			x += nx;
			y += ny;
		}
		/*else if {level.mobCollision(x, y, nx, ny, sprite.SIZE)) {
			
		}*/
		else {
			remove();
		}
		
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
