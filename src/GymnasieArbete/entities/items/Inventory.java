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
		int x = (screen.width - 162)/2;
		int y = (screen.height - 20);
		screen.renderSprite(x, y, sprite, false);
		for (int i = 0; i < items.size(); i++) {
			items.get(i).renderInInventory(screen, x + 1 + i * 16, y);
		}
	}

	public List<Item> getItems() {
		return items;
	}
	
	public Item getItem(int id) {
		return items.get(id);
	}
	
	public void addItem(Item item) {
		items.add(item);
	}

	public void removeItem(Item item) {
		items.remove(item);
	}

	public void expandInv() {
		//this.sprite = Sprite.inventory;
	}
}
