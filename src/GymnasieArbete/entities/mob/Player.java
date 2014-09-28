package GymnasieArbete.entities.mob;

import GymnasieArbete.graphics.Screen;
import GymnasieArbete.graphics.Sprite;
import GymnasieArbete.input.Keyboard;

public class Player extends Mob {
	
	private Keyboard input;
	private Sprite sprite;
	private int anim = 0;
	private boolean walking = false;
	
	public Player(Keyboard input) {
		this.input = input;
		sprite = Sprite.player_up;
	}
	
	public Player(int x, int y, Keyboard input) {
		this.x = x;
		this.y = y;
		this.input = input;
		sprite = Sprite.player_up;
	}
	
	public void update() {
		int xa = 0, ya = 0;
		if (anim < 7500) anim++; 
		else anim = 0;
		if (input.up) ya--;
		if (input.down) ya++;
		if (input.left) xa--;
		if (input.right) xa++;	
			
		if (xa != 0 || ya != 0) { 
			move(xa, ya);
			walking = true;
		}
		else {
			walking = false;
		}
	}
	
	public void render(Screen screen) {
		if(dir == 0) {
			sprite = Sprite.player_up;
			if (walking) {
				if (anim % 30 > 15) {
					sprite = Sprite.player_up_1;
				}
				else {
					sprite = Sprite.player_up_2;
				}
			}
		}
		if(dir == 1) {
			sprite = Sprite.player_right;
			if (walking) {
				if (anim % 40 > 30) {
					sprite = Sprite.player_right_1;
				}
				else if (anim % 40 > 20) {
					sprite = Sprite.player_right_2;
				}
				else if (anim % 40 > 10) {
					sprite = Sprite.player_right_3;
				}
				else {
					sprite = Sprite.player_right_4;
				}
			}
		}
		if(dir == 2) {
			sprite = Sprite.player_down;
			if (walking) {
				if (anim % 30 > 15) {
					sprite = Sprite.player_down_1;
				}
				else {
					sprite = Sprite.player_down_2;
				}
			}
		}
		if(dir == 3) {
			sprite = Sprite.player_left;
			if (walking) {
				if (anim % 40 > 30) {
					sprite = Sprite.player_left_1;
				}
				else if (anim % 40 > 20) {
					sprite = Sprite.player_left_2;
				}
				else if (anim % 40 > 10) {
					sprite = Sprite.player_left_3;
				}
				else {
					sprite = Sprite.player_left_4;
				}
			}
		}
		screen.renderPlayer(x - 16, y - 16, sprite);
	}
	
}
