package GymnasieArbete.entities.items;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import GymnasieArbete.entities.items.Item.Ammotype;
import GymnasieArbete.entities.mob.Player;
import GymnasieArbete.graphics.Screen;
import GymnasieArbete.graphics.Sprite;

public class Inventory {

	private Player parent;
	private List<Item> items;
	private Sprite sprite;
	private int bammo = 0, sammo = 0;
	
	public Inventory(Player parent) {
		this.parent = parent;
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
		for (int i = 0; i < items.size(); i++) {			
			if(items.get(i).type == Item.Type.EMPTY) {
				Item empty = items.get(i);
				int id = getIndex(empty);
				Collections.swap(items, id, items.size() - 1);
				items.remove(items.size() - 1);
				break;
			}
		}
	}
	
	public void removeItem(Item item) {
		int id = getIndex(item);
		for (int i = 0; i < items.size(); i++) {			
			if(items.get(i).equals(item)) {
				Item empty = new Item();
				items.add(empty);
				Collections.swap(items, id, items.size() - 1);
				items.remove(items.size() - 1);
			}
		}
	}
	
	public int getIndex(Item item)
	{
	    for (int i = 0; i < items.size(); i++)
	    {
	        if (items.get(i).equals(item))
	        {
	            return i;
	        }
	    } 

	    return -1;
	}
	
	public void expandInv() {
		//this.sprite = Sprite.inventory;
	}
	
	public int getBAmmo() {
		return bammo;
	}
	
	public void setBAmmo(int bammo) {
		this.bammo = bammo;
	}
	
	public int getSAmmo() {
		return sammo;
	}
	
	public void setSAmmo(int sammo) {
		this.sammo = sammo;
	}
	
	public int getRelAmmo() {
		if(parent.getEquipped().ammotype == Ammotype.BULLET) {
			return bammo;
		} else if (parent.getEquipped().ammotype == Ammotype.SHELL) {
			return sammo;
		}
		return 0;
	}
	
	public void setRelAmmo(int ammo) {
		if(parent.getEquipped().ammotype == Ammotype.BULLET) {
			bammo = ammo;
		} else if (parent.getEquipped().ammotype == Ammotype.SHELL) {
			sammo = ammo;
		}
	}
}
