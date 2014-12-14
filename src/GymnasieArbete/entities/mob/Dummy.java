package GymnasieArbete.entities.mob;

import GymnasieArbete.graphics.Screen;
import GymnasieArbete.graphics.Sprite;

public class Dummy extends Mob {

	public Dummy(int x, int y) {
		this.x = x << 4;
		this.y = y << 4;
		sprite = Sprite.dummy;
	}
	
	public void update() {
		
	}
	
	public void render(Screen screen) {
		screen.renderMob(x, y, sprite);
	}
	
}
