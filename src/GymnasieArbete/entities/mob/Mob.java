package GymnasieArbete.entities.mob;

import GymnasieArbete.entities.Entity;
import GymnasieArbete.entities.projectile.PistolProjectile;
import GymnasieArbete.entities.projectile.Projectile;
import GymnasieArbete.graphics.Screen;
import GymnasieArbete.graphics.Sprite;

public abstract class Mob extends Entity {

	protected boolean walking = false;
	protected boolean canshoot = false;

	protected enum Direction {
		UP, DOWN, LEFT, RIGHT
	}
	
	protected Direction dir;
	
	public void move(int xa, int ya) {
		if (xa != 0 && ya != 0) {
			move(xa, 0);
			move(0, ya);
			return;
		}
		
		if (xa > 0) dir = Direction.RIGHT;
		if (xa < 0) dir = Direction.LEFT;
		if (ya > 0) dir = Direction.DOWN;
		if (ya < 0) dir = Direction.UP;

		if (!collision(xa, ya)) {
			x += xa;
			y += ya;
		} 
	}

	public void update() {
	}
	
	protected void shoot(int x, int y, double dir) {
		Projectile p = new PistolProjectile(x-8, y-8, dir);
		level.add(p);
	}

	public void render(Screen screen) {
	}

	private boolean collision(int xa, int ya) {
		boolean solid = false;
		for (int c = 0; c < 4; c++) {
			int xt = ((x + xa) + c % 2 * 12 - 6) / 16;
			int yt = ((y + ya) + c / 2 * 12 + 3) / 16;
			if (level.getTile(xt, yt).solid()) solid = true;
		}
		return solid;
	}
}
