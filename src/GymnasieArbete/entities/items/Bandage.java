package GymnasieArbete.entities.items;

import GymnasieArbete.graphics.Sprite;

public class Bandage extends Medical {
	
	public Bandage(int x, int y) {
		super(x, y, "Bandage");
		name = "Bandage";
		healing = 20;
		sprite = Sprite.bandage;
	}
	
}
