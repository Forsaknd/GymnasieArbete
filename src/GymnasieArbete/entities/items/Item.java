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
	protected int fireRate = 0, clipammo = 0, clipsize = 0, maxammo = 0;
	
	protected Player player;
	protected String name;
	protected String sound;
	
	public enum Type {
		WEAPON, CONSUMABLE, AMMO, EMPTY;
	}
	
	public Type type;

	public enum Ammotype {
		BULLET, SHELL, UNKOWN;
	}
	
	public Ammotype ammotype;
	
	public Item() {
		type = Type.EMPTY;
		ammotype = Ammotype.UNKOWN;
	}
	
	public int getFireRate() {
		return fireRate;
	}

	public void setPos(int x, int y) {
		this.x = x << 4;
		this.y = y << 4;
	}
	
	public void use() {
	}
	
	public void update() {
	}
	
	public void render(Screen screen) {
		screen.renderItem((int) x - 8, (int) y - 8, this);
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

	public void setClipAmmo(int clipammo) {
		this.clipammo = clipammo;
	}
	
	public int getClipAmmo() {
		return clipammo;
	}

	public int getMaxAmmo() {
		return maxammo;
	}
	
	public int getClipSize() {
		return clipsize;
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
