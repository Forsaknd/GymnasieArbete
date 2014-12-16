package GymnasieArbete.entities.mob;

import GymnasieArbete.entities.Entity;
import GymnasieArbete.entities.projectile.PistolProjectile;
import GymnasieArbete.entities.projectile.Projectile;
import GymnasieArbete.entities.spawner.BackgroundParticleSpawner;
import GymnasieArbete.entities.spawner.ParticleSpawner;
import GymnasieArbete.graphics.Screen;
import GymnasieArbete.graphics.Sprite;

public abstract class Mob extends Entity {

	protected boolean walking = false;
	protected boolean canshoot = false;
	private int hp = 100;

	protected enum Direction {
		UP, DOWN, LEFT, RIGHT
	}

	protected Direction dir;

	public void move(double xa, double ya) {
		if (xa != 0 && ya != 0) {
			move(xa, 0);
			move(0, ya);
			return;
		}

		if (xa > 0) dir = Direction.RIGHT;
		if (xa < 0) dir = Direction.LEFT;
		if (ya > 0) dir = Direction.DOWN;
		if (ya < 0) dir = Direction.UP;

		while (xa != 0) {
			if (Math.abs(xa) > 1) {
				if (!collision(abs(xa), ya)) {
					this.x += abs(xa);
				}
				xa -= abs(xa);
			} else {
				if (!collision(abs(xa), ya)) {
					this.x += xa;
				}
				xa = 0;
			}
		}

		while (ya != 0) {
			if (Math.abs(ya) > 1) {
				if (!collision(xa, abs(ya))) {
					this.y += abs(ya);
				}
				ya -= abs(ya);
			} else {
				if (!collision(xa, abs(ya))) {
					this.y += ya;
				}
				ya = 0;
			}
		}
	}

	private int abs(double value) {
		if (value < 0) return -1;
		return 1;
	}

	public void update() {
	}
	
	public void takeDamage(int damage, int particleamount) {
		level.add(new ParticleSpawner((int) x, (int) y, 20, particleamount, Sprite.particle_blood, level));
		hp -= damage;
		if(hp<=0) {
			level.add(new ParticleSpawner((int) x, (int) y, 40, particleamount*4, Sprite.particle_blood, level));
			level.add(new BackgroundParticleSpawner((int) x + 4, (int) y - 4, 300, particleamount*7, Sprite.particle_blood, level));
			level.add(new BackgroundParticleSpawner((int) x - 4, (int) y + 4, 300, particleamount*7, Sprite.particle_blood, level));
			remove();
		}
	}
	
	protected void shoot(double x, double y, double dir) {
		Projectile p = new PistolProjectile(x - 8, y - 8, dir);
		level.add(p);
	}

	public void render(Screen screen) {
	}

	private boolean collision(double xa, double ya) {
		boolean solid = false;
		for (int c = 0; c < 4; c++) {
			double xt = ((x + xa) - c % 2 * 15 ) / 16;
			double yt = ((y + ya) - c / 2 * 15 ) / 16;
			int ix = (int) Math.ceil(xt);
			int iy = (int) Math.ceil(yt);
			if (c % 2 == 0) ix = (int) Math.floor(xt);
			if (c / 2 == 0) iy = (int) Math.floor(yt);
			if (level.getTile(ix, iy).solid()) solid = true;
		}
		if (level.getEntityCol(this, 17)) solid = true;
		return solid;
	}
}
