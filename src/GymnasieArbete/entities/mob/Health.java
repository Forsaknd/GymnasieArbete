package GymnasieArbete.entities.mob;

import GymnasieArbete.graphics.Screen;
import GymnasieArbete.graphics.Sprite;

public class Health {

	private int hp, maxhp;
	private Sprite curhp;
	private Sprite border;

	public Health(int hp) {
		this.maxhp = hp;
		this.hp = hp;
		curhp = new Sprite(hp, 13, 0xFF00FF00);
		border = Sprite.hpbar;
	}

	public void setHealth(int hp) {
		this.hp = hp;
	}
	
	public int getHealth() {
		return hp;
	}
	
	public int getMaxHealth() {
		return maxhp;
	}

	public void render(Screen screen) {
		if (hp > 0) {
			int x = (screen.width - 382)/2;
			int y = (screen.height - 19);
			curhp = new Sprite(hp, 13, 0xFF00FF00);
			screen.renderSprite(x, y + 1, curhp, false);
			screen.renderSprite(x, y + 1, border, false);
		}
	}

}
