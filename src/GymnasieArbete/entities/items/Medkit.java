package GymnasieArbete.entities.items;

import GymnasieArbete.graphics.Sprite;

public class Medkit extends Medical {
	
	public Medkit(int x, int y) {
		super(x, y, "Medkit");
		name = "Medkit";
		healing = 50;
		sprite = Sprite.medkit;
	}
	
}
