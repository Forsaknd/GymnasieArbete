package GymnasieArbete.entities.mob;

import GymnasieArbete.graphics.Screen;
import GymnasieArbete.graphics.Sprite;

public class Guard extends Mob {

	private int health;
	private Sprite sprite;
	
	public Guard(int x, int y) {
		this.x = x;
		this.y = y;
		sprite = Sprite.enemy;
	}
	
	public void damageHealth(int hp) {
		health =- hp;
	}
	
	public void update() {
		if (health <= 0) {
			remove();
		}
	}
	
	public void render(Screen screen) {
		screen.renderEntity(x - 16, y - 16, sprite);
	}
	

	public boolean solid(){
		return true;
	}
}
