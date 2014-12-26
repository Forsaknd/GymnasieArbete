package GymnasieArbete.util;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import GymnasieArbete.entities.items.Item;
import GymnasieArbete.entities.mob.Player;
import GymnasieArbete.graphics.Screen;

public class HUD {

	private Player parent;

	Message equipped = new Message(900, 650, Color.WHITE);
	private List<Message> messages = new ArrayList<Message>();

	public HUD(Player parent) {
		this.parent = parent;
		messages.add(equipped);
	}

	public void newMessage(String text, int lifespan) {
		Message temp = new Message(text, 550, 600, lifespan, Color.WHITE);
		messages.add(temp);
		int id = getIndex(temp);
		if (id > 1) messages.get(id - 1).setPos(id);
	}

	public int getIndex(Message msg) {
		for (int i = 0; i < messages.size(); i++) {
			if (messages.get(i).equals(msg)) {
				return i;
			}
		}

		return -1;
	}

	public void setEquipped(Item item) {
		equipped.setText(item.getName() + " " + parent.getEquipped().getAmmo() + " / " + parent.getAmmo());
	}

	public void render(Screen screen, Graphics g) {
		parent.getInventory().render(screen);
		parent.getHealth().render(screen);
		if (messages.size() > 0) {
			for (int i = 0; i < messages.size(); i++) {
				messages.get(i).render(g);
				if (messages.get(i).isRemoved()) {
					messages.remove(i);
				}
			}
		}
	}

}