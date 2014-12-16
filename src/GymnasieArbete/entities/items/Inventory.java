package GymnasieArbete.entities.items;

import java.util.ArrayList;
import java.util.List;

import GymnasieArbete.graphics.Screen;
import GymnasieArbete.graphics.Sprite;

public class Inventory {

	private List<Item> items;
	private Sprite sprite;
	
	public Inventory() {
		items = new ArrayList<Item>();
		this.sprite = Sprite.actionbar;
	}
	
	public void update() {
	}
	
	public void render(Screen screen) {
		screen.renderSprite(69, 140, sprite, false);
		for (int i = 0; i < items.size(); i++) {
			items.get(i).renderInInventory(screen, 70 + (i * 16), 140);
		}
	}
	
	public void addItem(Item item) {
		items.add(item);
	}

	public void expandInv() {
		//this.sprite = Sprite.inventory;
	}
}
