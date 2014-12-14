package GymnasieArbete.entities.mob;

import GymnasieArbete.graphics.AnimatedSprite;
import GymnasieArbete.graphics.Screen;
import GymnasieArbete.graphics.Sprite;
import GymnasieArbete.graphics.SpriteSheet;

public class Zombie extends Mob {
	
	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.zombie_down, 32, 32, 5, 15);
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.zombie_up, 32, 32, 5, 15);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.zombie_left, 32, 32, 5, 15);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.zombie_right, 32, 32, 5, 15);

	private AnimatedSprite animSprite = down;
	
	private int xa = 0;
	private int ya = 0;
	
	public Zombie(int x, int y) {
		this.x = x << 4;
		this.y = y << 4;
		sprite = Sprite.zombie;
	}
	
	public void update() {
		if (walking) animSprite.update();
		else animSprite.setFrame(0);
		
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

		if (xa != 0 || ya != 0) {
			move(xa, ya);
			walking = true;
		} else {
			walking = false;
		}
	}
	
	public void render(Screen screen) {
		sprite = animSprite.getSprite();
		screen.renderMob(x, y, this);
	}
	
}
