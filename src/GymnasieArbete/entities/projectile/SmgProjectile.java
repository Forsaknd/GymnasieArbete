package GymnasieArbete.entities.projectile;

import java.util.List;

import GymnasieArbete.entities.Entity;
import GymnasieArbete.entities.mob.Zombie;
import GymnasieArbete.entities.spawner.ParticleSpawner;
import GymnasieArbete.graphics.Screen;
import GymnasieArbete.graphics.Sprite;

public class SmgProjectile extends Projectile {

	public SmgProjectile(double x, double y, double dir) {
		super(x, y, dir);
		range = 175;
		speed = 8;
		damage = 10;
		sprite = Sprite.pistolProjectile;
		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);
	}

	public void update() {
		List<Entity> entities = level.getallEntities();
		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i) instanceof Zombie) {
				Zombie current = (Zombie) entities.get(i);
				//creates 16x16 "hitbox"
				if (x < current.getX() + 17 && x > current.getX() - 17 && y < current.getY() + 17 && y > current.getY() - 17) {
					current.takeDamage(damage, 10);
					remove();
				}
			}
		}
		if (level.tileCollision((int) (x + nx), (int) (y + ny), 2, 7, 7)) {
			level.add(new ParticleSpawner((int) x + 5, (int) y + 6, 40, 20, Sprite.particle_normal, level));
			remove();
		} else {
			move();
		}
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
