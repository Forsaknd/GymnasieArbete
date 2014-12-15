package GymnasieArbete.entities.mob;

import GymnasieArbete.Game;
import GymnasieArbete.entities.projectile.PistolProjectile;
import GymnasieArbete.entities.projectile.Projectile;
import GymnasieArbete.graphics.AnimatedSprite;
import GymnasieArbete.graphics.Screen;
import GymnasieArbete.graphics.Sprite;
import GymnasieArbete.graphics.SpriteSheet;
import GymnasieArbete.input.Keyboard;
import GymnasieArbete.input.Mouse;

public class Player extends Mob {

	private Keyboard input;
	private Sprite sprite;
	protected boolean walking = false;
	protected boolean canshoot = true;
	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.player_down, 32, 32, 3, 10);
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.player_up, 32, 32, 3, 10);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.player_left, 32, 32, 5, 10);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.player_right, 32, 32, 5, 10);

	private AnimatedSprite animSprite = down;

	private int fireRate = 0;

	private double speed = 1;

	public Player(Keyboard input) {
		this.input = input;
		sprite = Sprite.player;
		animSprite = down;
	}

	public Player(int x, int y, Keyboard input) {
		this.x = x;
		this.y = y;
		this.input = input;
		sprite = Sprite.player;
		fireRate = PistolProjectile.FIRE_RATE;
	}

	public void update() {
		if (walking)
			animSprite.update();
		else
			animSprite.setFrame(0);
		if (fireRate > 0) fireRate--;
		if (input.esc) Game.state = Game.STATE.PAUSED;

		double xa = 0, ya = 0;
		if (input.up) {
			animSprite = up;
			ya -= speed;
		} else if (input.down) {
			animSprite = down;
			ya += speed;
		}
		if (input.left) {
			animSprite = left;
			xa -= speed;
		} else if (input.right) {
			animSprite = right;
			xa += speed;
		}

		if (xa != 0 || ya != 0) {
			move(xa, ya);
			walking = true;
		} else {
			walking = false;
		}

		clear();
		updateShooting();
	}

	private void clear() {
		for (int i = 0; i < level.getProjectiles().size(); i++) {
			Projectile p = level.getProjectiles().get(i);
			if (p.isRemoved()) level.getProjectiles().remove(i);
		}
	}

	private void updateShooting() {
		if (Mouse.getB() == 1 && fireRate == 0 && canshoot == true) {
			double dx = Mouse.getX() - Game.getWindowWidth() / 2;
			double dy = Mouse.getY() - Game.getWindowHeight() / 2;
			double mdir = Math.atan2(dy, dx);

			shoot(x, y, mdir);
			fireRate = PistolProjectile.FIRE_RATE;

			if (!walking) {
				int ddir = (int) Math.toDegrees(mdir);
				if (ddir < -45 && ddir > -135) {
					animSprite = up;
				}
				if (ddir < 45 && ddir > -45) {
					animSprite = right;
				}
				if (ddir <= 120 && ddir >= 60) {
					animSprite = down;
				}
				if (ddir < -135 && ddir > -180 || ddir <= 180 && ddir > 135) {
					animSprite = left;
				}
			}
		}
	}

	public void render(Screen screen) {
		sprite = animSprite.getSprite();
		screen.renderMob((int) (x - 16), (int) (y - 16), sprite);
	}

}
