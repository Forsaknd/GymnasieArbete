package GymnasieArbete.entities.items;

import GymnasieArbete.entities.Entity;
import GymnasieArbete.entities.mob.Player;
import GymnasieArbete.graphics.Screen;

public class Item extends Entity {

	protected int fireRate = 0;
	
	protected Player player;
	protected String name;

	public enum Type {
		WEAPON, CONSUMABLE, EMPTY;
	}
	
	public Type type;
	
	public int getFireRate() {
		return fireRate;
	}

	public void use() {
	}
	
	public void update() {
	}

	public void render(Screen screen) {
	}
	
	public void setOwner(Player player) {
		this.player = player;
	}
	
	public String getName() {
		return name;
	}
	
	public void renderInInventory(Screen screen, int x, int y) {
	}
}
