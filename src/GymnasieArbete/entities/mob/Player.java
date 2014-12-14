package GymnasieArbete.entities.mob;

import GymnasieArbete.Game;
import GymnasieArbete.entities.projectile.PistolProjectile;
import GymnasieArbete.entities.projectile.Projectile;
import GymnasieArbete.graphics.Screen;
import GymnasieArbete.graphics.Sprite;
import GymnasieArbete.input.Keyboard;
import GymnasieArbete.input.Mouse;

public class Player extends Mob {

	private Keyboard input;
	private Sprite sprite;
	private int anim = 0;
	protected boolean walking = false;
	protected boolean canshoot = true;

	private int fireRate = 0;

	public Player(Keyboard input) {
		this.input = input;
		sprite = Sprite.player_up;
	}

	public Player(int x, int y, Keyboard input) {
		this.x = x;
		this.y = y;
		this.input = input;
		sprite = Sprite.player_up;
		fireRate = PistolProjectile.FIRE_RATE;
	}

	public void update() {
		if (fireRate > 0) fireRate--;
		if (input.esc) Game.state = Game.STATE.PAUSED;

		if (anim < 7500) anim++;
		else anim = 0;

		int xa = 0, ya = 0;
		if (input.up) ya--;
		if (input.down) ya++;
		if (input.left) xa--;
		if (input.right) xa++;

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
			if (p.isRemoved())
				level.getProjectiles().remove(i);
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
					dir = 0;
				}
				if (ddir < 45 && ddir > -45) {
					dir = 1;
				}
				if (ddir <= 120 && ddir >= 60) {
					dir = 2;
				}
				if (ddir < -135 && ddir > -180 || ddir <= 180 && ddir > 135) {
					dir = 3;
				}
			}
		}
	}
	
	public void render(Screen screen) {
		if (dir == 0) {
			sprite = Sprite.player_up;
			if (walking) {
				if (anim % 30 > 15) {
					sprite = Sprite.player_up_1;
				} else {
					sprite = Sprite.player_up_2;
				}
			}
		}
		if (dir == 1) {
			sprite = Sprite.player_right;
			if (walking) {
				if (anim % 40 > 30) {
					sprite = Sprite.player_right_1;
				} else if (anim % 40 > 20) {
					sprite = Sprite.player_right_2;
				} else if (anim % 40 > 10) {
					sprite = Sprite.player_right_3;
				} else {
					sprite = Sprite.player_right_4;
				}
			}
		}
		if (dir == 2) {
			sprite = Sprite.player_down;
			if (walking) {
				if (anim % 30 > 15) {
					sprite = Sprite.player_down_1;
				} else {
					sprite = Sprite.player_down_2;
				}
			}
		}
		if (dir == 3) {
			sprite = Sprite.player_left;
			if (walking) {
				if (anim % 40 > 30) {
					sprite = Sprite.player_left_1;
				} else if (anim % 40 > 20) {
					sprite = Sprite.player_left_2;
				} else if (anim % 40 > 10) {
					sprite = Sprite.player_left_3;
				} else {
					sprite = Sprite.player_left_4;
				}
			}
		}
		screen.renderMob(x - 16, y - 16, sprite);
	}

}
