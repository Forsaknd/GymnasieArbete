package GymnasieArbete.entities.particle;

import GymnasieArbete.entities.Entity;
import GymnasieArbete.graphics.Screen;
import GymnasieArbete.graphics.Sprite;

public class SpriteParticle extends Entity {

	protected Sprite sprite = Sprite.particle_normal;

	private int life;
	private int time = 0;
	
	public SpriteParticle(int x, int y, int life, Sprite sprite) {
		this.x = x;
		this.y = y;
		this.life = life;
		this.sprite = sprite;
	}

	public void update() {
		time++;
		if (time % life == 0) remove();
	}

	public void render(Screen screen) {
		screen.renderSprite((int) x - 1, (int) y, sprite, true);
	}

}
