package GymnasieArbete.util;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import GymnasieArbete.entities.items.Item;
import GymnasieArbete.entities.items.Item.Type;
import GymnasieArbete.entities.mob.Player;
import GymnasieArbete.graphics.Screen;

public class HUD {

	private Player parent;

	Message info = new Message(900, 650, Color.WHITE);
	private List<Message> ui = new ArrayList<Message>();
	private List<Message> messages = new ArrayList<Message>();

	public HUD(Player parent) {
		this.parent = parent;
		info.setText("Level: " + parent.getLevel() + " - " + parent.getExperience() + "/" + parent.getLevel() * 20);
		ui.add(info);
	}

	public void newMessage(String text, int lifespan) {
		Message temp = new Message(text, 550, 600, lifespan, Color.WHITE);
		messages.add(temp);
		int id = messages.indexOf(temp);
		if (id > 0) messages.get(id - 1).moveUp(id);
	}
	
	public void updateInfo(Item item) {
		if (parent.getEquipped().type == Type.EMPTY) {	
			info.setText("Level: " + parent.getLevel() + " - " + parent.getExperience() + "/" + parent.getLevel() * 20);
		} else {
			info.setText("Level: " + parent.getLevel() + " - " + parent.getExperience() + "/" + parent.getLevel() * 20 + " " + item.getName() + " " + parent.getEquipped().getClipAmmo() + " / " + parent.getInventory().getRelAmmo());			
		}
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
		if (ui.size() > 0) {
			for (int i = 0; i < ui.size(); i++) {
				ui.get(i).render(g);
				if (ui.get(i).isRemoved()) {
					ui.remove(i);
				}
			}
		}
	}

}