package GymnasieArbete.entities.mob;

import java.util.List;

import GymnasieArbete.entities.spawner.BackgroundParticleSpawner;
import GymnasieArbete.entities.spawner.ParticleSpawner;
import GymnasieArbete.graphics.AnimatedSprite;
import GymnasieArbete.graphics.Screen;
import GymnasieArbete.graphics.Sprite;
import GymnasieArbete.graphics.SpriteSheet;
import GymnasieArbete.level.Node;
import GymnasieArbete.util.Vector2i;

public class Zombie extends Mob {

	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.zombie_down, 32, 32, 5, 15);
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.zombie_up, 32, 32, 5, 15);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.zombie_left, 32, 32, 5, 15);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.zombie_right, 32, 32, 5, 15);

	private AnimatedSprite animSprite = down;

	private double xa = 0, ya = 0;
	private int time = 0;
	private List<Node> path = null;
	private int damage = 5;

	private double speed = 0.5;

	public Zombie(int x, int y) {
		this.x = x << 4;
		this.y = y << 4;
		sprite = Sprite.zombie;
		acqrange = 85;
	}

	public void move() {
		Player player = level.getPlayers(this, acqrange);
		if (player != null && !player.isDead()) {
			xa = 0;
			ya = 0;
			int px = (int) player.getX();
			int py = (int) player.getY();
			Vector2i start = new Vector2i((int) getX() >> 4, (int) getY() >> 4);
			Vector2i destination = new Vector2i(px >> 4, py >> 4);
			if (time % 3 == 0) path = level.findPath(start, destination);
			if (path != null) {
				if (path.size() > 0) {
					Vector2i vec = path.get(path.size() - 1).tile;
					if (x < vec.getX() << 4) xa += speed;
					if (x > vec.getX() << 4) xa -= speed;
					if (y < vec.getY() << 4) ya += speed;
					if (y > vec.getY() << 4) ya -= speed;
				}
			}
		} else {
			// if time % 60 == 0 / = 1 second
			if (time % (random.nextInt(50) + 60) == 0) {
				xa = (random.nextInt(3) - 1) * speed;
				ya = (random.nextInt(3) - 1) * speed;
				if (random.nextInt(4) == 0) {
					xa = 0;
					ya = 0;
				}
			}
		}

		if (xa != 0 || ya != 0) {
			move(xa, ya);
			walking = true;
		} else {
			walking = false;
		}

	}

	public void update() {
		time++;
		move();
		if (walking) animSprite.update();
		else animSprite.setFrame(0);
		if (time % 3 == 0) {
			if (ya < 0) {
				animSprite = up;
				dir = Direction.UP;
			} else if (ya > 0) {
				animSprite = down;
				dir = Direction.DOWN;
			}
			if (xa < 0) {
				animSprite = left;
				dir = Direction.LEFT;
			} else if (xa > 0) {
				animSprite = right;
				dir = Direction.RIGHT;
			}
		}

		if (time % 30 == 0) {
			Player current = level.getPlayer();
			// creates 19x19 "hitbox" if not dead
			if (!current.isDead()) {
				if (x < current.getX() + 20 && x > current.getX() - 20 && y < current.getY() + 20 && y > current.getY() - 20) {
					current.takeDamage(damage, 25);
				}
			}
		}
	}

	public void render(Screen screen) {
		sprite = animSprite.getSprite();
		screen.renderMob((int) x - 16, (int) y - 16, sprite);
	}

}
