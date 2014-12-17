package GymnasieArbete.entities.items;

import GymnasieArbete.entities.Entity;
import GymnasieArbete.entities.mob.Player;
import GymnasieArbete.graphics.AnimatedSprite;
import GymnasieArbete.graphics.Screen;

public class Item extends Entity {

	protected AnimatedSprite up;
	protected AnimatedSprite down;
	protected AnimatedSprite left;
	protected AnimatedSprite right;
	protected int fireRate = 0;
	
	protected Player player;
	protected String name;
	protected String sound;
	
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

	public String getSound() {
		return sound;
	}
	
	public String getName() {
		return name;
	}
	
	public AnimatedSprite getAnimatedSpriteUp() {
		return up;
	}

	public AnimatedSprite getAnimatedSpriteDown() {
		return down;
	}
	
	public AnimatedSprite getAnimatedSpriteLeft() {
		return left;
	}
	
	public AnimatedSprite getAnimatedSpriteRight() {
		return right;
	}
	
	public void renderInInventory(Screen screen, int x, int y) {
	}
}
