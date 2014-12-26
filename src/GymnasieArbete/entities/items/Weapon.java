package GymnasieArbete.entities.items;

import GymnasieArbete.graphics.AnimatedSprite;
import GymnasieArbete.graphics.Screen;
import GymnasieArbete.graphics.Sprite;
import GymnasieArbete.graphics.SpriteSheet;

public class Weapon extends Item {

	public Weapon(int x, int y, String name) {
		this.x = x << 4;
		this.y = y << 4;
		type = Type.WEAPON;

		if (name == "Pistol") {
			up = new AnimatedSprite(SpriteSheet.player_pistol_up, 32, 32, 3, 10);
			down = new AnimatedSprite(SpriteSheet.player_pistol_down, 32, 32, 3, 10);
			left = new AnimatedSprite(SpriteSheet.player_pistol_left, 32, 32, 5, 10);
			right = new AnimatedSprite(SpriteSheet.player_pistol_right, 32, 32, 5, 10);
		} else if (name == "SMG") {
			up = new AnimatedSprite(SpriteSheet.player_smg_up, 32, 32, 3, 10);
			down = new AnimatedSprite(SpriteSheet.player_smg_down, 32, 32, 3, 10);
			left = new AnimatedSprite(SpriteSheet.player_smg_left, 32, 32, 5, 10);
			right = new AnimatedSprite(SpriteSheet.player_smg_right, 32, 32, 5, 10);
		} else if (name == "Shotgun") {
			up = new AnimatedSprite(SpriteSheet.player_shotgun_up, 32, 32, 3, 10);
			down = new AnimatedSprite(SpriteSheet.player_shotgun_down, 32, 32, 3, 10);
			left = new AnimatedSprite(SpriteSheet.player_shotgun_left, 32, 32, 5, 10);
			right = new AnimatedSprite(SpriteSheet.player_shotgun_right, 32, 32, 5, 10);
		} else if (name == "Rifle") {
			up = new AnimatedSprite(SpriteSheet.player_rifle_up, 48, 48, 3, 10);
			down = new AnimatedSprite(SpriteSheet.player_rifle_down, 48, 48, 3, 10);
			left = new AnimatedSprite(SpriteSheet.player_rifle_left, 48, 48, 5, 10);
			right = new AnimatedSprite(SpriteSheet.player_rifle_right, 48, 48, 5, 10);
		}
	}

	public void renderInInventory(Screen screen, int x, int y) {
		if (player.getEquipped().equals(this)) {
			Sprite selected = new Sprite(14, 13, 0xFF00FF00);
			screen.renderSprite(x + 1, y + 2, selected, false);
		}
		screen.renderSprite(x, y, sprite, false);
	}

}
