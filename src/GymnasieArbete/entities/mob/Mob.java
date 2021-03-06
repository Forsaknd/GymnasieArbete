package GymnasieArbete.entities.mob;

import GymnasieArbete.Game;
import GymnasieArbete.entities.Entity;
import GymnasieArbete.entities.items.Item;
import GymnasieArbete.entities.items.Pistol;
import GymnasieArbete.entities.items.Rifle;
import GymnasieArbete.entities.items.Shotgun;
import GymnasieArbete.entities.items.Smg;
import GymnasieArbete.entities.projectile.PistolProjectile;
import GymnasieArbete.entities.projectile.Projectile;
import GymnasieArbete.entities.projectile.RifleProjectile;
import GymnasieArbete.entities.projectile.ShotgunProjectile;
import GymnasieArbete.entities.projectile.SmgProjectile;
import GymnasieArbete.entities.spawner.BackgroundParticleSpawner;
import GymnasieArbete.entities.spawner.ParticleSpawner;
import GymnasieArbete.entities.spawner.SpriteParticleSpawner;
import GymnasieArbete.graphics.Screen;
import GymnasieArbete.graphics.Sprite;

public abstract class Mob extends Entity {

	protected int acqrange;
	protected boolean walking = false;
	protected boolean canshoot = false;
	protected Health hp = new Health(100);

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

	public void takeDamage(int damage, int particleamount, String target) {
		hp.setHealth(hp.getHealth() - damage);
		if (hp.getHealth() <= 0) {
			playSound(target + "die");
			Game.playSound("splat.wav", false);
			level.add(new ParticleSpawner((int) x, (int) y, 40, particleamount * 4, Sprite.particle_blood, level));
			level.add(new BackgroundParticleSpawner((int) x + 4, (int) y - 4, 1000, 3, particleamount * 8, Sprite.particle_blood, level));
			level.add(new BackgroundParticleSpawner((int) x - 4, (int) y + 4, 1000, 3, particleamount * 8, Sprite.particle_blood, level));
			remove();
			if(target == "zombie") {
				level.getPlayer().setExperience(level.getPlayer().getExperience() + 10);
			}
		} else {
			playSound(target + "hit");
			level.add(new ParticleSpawner((int) x, (int) y, 20, particleamount, Sprite.particle_blood, level));
			acqrange = 200;
		}
	}

	public void playSound(String type) {
		if (type.equals("zombieaggro")) {
			Game.playSound("zombie/growl_" + random.nextInt(9) + ".wav", false);
		}
		if (type.equals("zombiehit")) {
			Game.playSound("zombie/hit_" + random.nextInt(8) + ".wav", false);
		}
		if (type.equals("zombieplayerhit")) {
			Game.playSound("zombie/strike_" + random.nextInt(3) + ".wav", false);
		}
		if (type.equals("zombiedie")) {
			Game.playSound("zombie/hit_" + random.nextInt(8) + ".wav", false);
		}
		if (type.equals("playerdie")) {
			// Game.playSound("zombie/hit_" + random.nextInt(8) + ".wav",
			// false);
		}
	}

	protected void shoot(Item equipped, double x, double y, double direction) {
		Game.playSound(equipped.getSound(), false);
		Projectile p = null;
		int r = random.nextInt(2);
		if (equipped instanceof Pistol) {
			if (r == 1) new BackgroundParticleSpawner((int) x - 3, (int) y + 10, 100, 1, 1, Sprite.particle_shell_left, level);
			else new BackgroundParticleSpawner((int) x - 3, (int) y + 10, 100, 1, 1, Sprite.particle_shell_right, level);
			p = new PistolProjectile(x, y + 1, direction);
		} else if (equipped instanceof Smg) {
			if (r == 1) new BackgroundParticleSpawner((int) x - 3, (int) y + 10, 100, 1, 1, Sprite.particle_shell_left, level);
			else new BackgroundParticleSpawner((int) x - 3, (int) y + 10, 100, 1, 1, Sprite.particle_shell_right, level);
			p = new SmgProjectile(x, y + 1, direction);
		} else if (equipped instanceof Rifle) {
			if (r == 1) new BackgroundParticleSpawner((int) x - 3, (int) y + 10, 100, 1, 1, Sprite.particle_shell_left, level);
			else new BackgroundParticleSpawner((int) x - 3, (int) y + 10, 100, 1, 1, Sprite.particle_shell_right, level);
			p = new RifleProjectile(x, y + 1, direction);
		} else if (equipped instanceof Shotgun) {
			if (r == 1) new BackgroundParticleSpawner((int) x - 3, (int) y + 10, 100, 1, 1, Sprite.particle_shotgunshell_left, level);
			else new BackgroundParticleSpawner((int) x - 3, (int) y + 10, 100, 1, 1, Sprite.particle_shotgunshell_right, level);
			p = new ShotgunProjectile(x, y + 4, direction);
			for (int i = -3; i < 3; i++) {
				Projectile a = new ShotgunProjectile(x, y - 4, direction + (i * 0.15));
				level.add(a);
			}
		}
		int ddir = (int) Math.toDegrees(direction);
		if (ddir < -45 && ddir > -135) {
			new SpriteParticleSpawner((int) x - 1, (int) y - 20, 3, 1, Sprite.muzzleflash_down, level);
		}
		if (ddir < 45 && ddir > -45) {
			new SpriteParticleSpawner((int) x + 14, (int) y - 7, 3, 1, Sprite.muzzleflash_right, level);
		}
		if (ddir <= 135 && ddir >= 45) {
			new SpriteParticleSpawner((int) x - 10, (int) y + 4, 3, 1, Sprite.muzzleflash_up, level);
		}
		if (ddir < -135 && ddir > -180 || ddir <= 180 && ddir > 135) {
			new SpriteParticleSpawner((int) x - 28, (int) y - 7, 3, 1, Sprite.muzzleflash_left, level);
		}
		level.add(p);
	}

	public void render(Screen screen) {
	}

	protected boolean collision(double xa, double ya) {
		boolean solid = false;
		for (int c = 0; c < 4; c++) {
			double xt = ((x + xa) - c % 2 * 15) / 16;
			double yt = ((y + ya) - c / 2 * 15) / 16;
			int ix = (int) Math.ceil(xt);
			int iy = (int) Math.ceil(yt);
			if (c % 2 == 0) ix = (int) Math.floor(xt);
			if (c / 2 == 0) iy = (int) Math.floor(yt);
			if (level.getTile(ix, iy).solid()) solid = true;
			else if (level.getEntityCol(this, 20)) solid = true;
		}
		return solid;
	}

	protected boolean entityCollision(double xa, double ya) {
		boolean solid = false;
		if (level.getEntityCol(this, 20)) solid = true;
		return solid;
	}
}
