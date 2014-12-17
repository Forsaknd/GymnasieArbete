package GymnasieArbete.entities.items;

import GymnasieArbete.graphics.AnimatedSprite;
import GymnasieArbete.graphics.Screen;
import GymnasieArbete.graphics.Sprite;
import GymnasieArbete.graphics.SpriteSheet;

public class Weapon extends Item {
	
	public Weapon(int x, int y, String name, int fireRate, Sprite sprite, String sound) {
		this.x = x << 4;
		this.y = y << 4;
		this.name = name;
		this.fireRate = fireRate;
		this.sprite = sprite;
		this.sound = sound;
		type = Type.WEAPON;
		
		if(name == "Pistol") {
			up = new AnimatedSprite(SpriteSheet.player_pistol_up, 32, 32, 3, 10);
			down = new AnimatedSprite(SpriteSheet.player_pistol_down, 32, 32, 3, 10);
			left = new AnimatedSprite(SpriteSheet.player_pistol_left, 32, 32, 5, 10);
			right = new AnimatedSprite(SpriteSheet.player_pistol_right, 32, 32, 5, 10);
		}
		
		if(name == "Smg") {
			up = new AnimatedSprite(SpriteSheet.player_smg_up, 32, 32, 3, 10);
			down = new AnimatedSprite(SpriteSheet.player_smg_down, 32, 32, 3, 10);
			left = new AnimatedSprite(SpriteSheet.player_smg_left, 32, 32, 5, 10);
			right = new AnimatedSprite(SpriteSheet.player_smg_right, 32, 32, 5, 10);
		}
		
		if(name == "Shotgun") {
			up = new AnimatedSprite(SpriteSheet.player_shotgun_up, 32, 32, 3, 10);
			down = new AnimatedSprite(SpriteSheet.player_shotgun_down, 32, 32, 3, 10);
			left = new AnimatedSprite(SpriteSheet.player_shotgun_left, 32, 32, 5, 10);
			right = new AnimatedSprite(SpriteSheet.player_shotgun_right, 32, 32, 5, 10);
		}
	}
	
	public void update() {
	}
	
	public void render(Screen screen) {
		screen.renderItem((int) x - 8, (int) y - 8, this);
	}

	public void renderInInventory(Screen screen, int x, int y) {
		if(player.getEquipped().equals(this)) {
			Sprite selected = new Sprite(14, 13, 0xFF00FF00);
			screen.renderSprite(x + 1, y + 2, selected, false);
		}
		screen.renderSprite(x, y, sprite, false);
	}
	
}
