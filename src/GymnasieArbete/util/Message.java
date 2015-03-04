package GymnasieArbete.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Message {

	private int timer = 0;
	private int lifespan = 0;
	private boolean removed = false;

	private Vector2i position;
	private String text;
	private Color color;

	public Message(int x, int y, Color color) {
		position = new Vector2i(x, y);
		this.color = color;
	}

	public Message(String text, int x, int y, int lifespan, Color color) {
		this.text = text;
		this.lifespan = lifespan;
		position = new Vector2i(x, y);
		this.color = color;
	}

	public void update() {
		if (lifespan != 0) {
			timer++;
			if (timer % lifespan == 0) {
				removed = true;
			}
		}
	}

	public void moveUp(int id) {
		position.setY(position.getY()-(id*12));
	}
	
	public void setText(String text) {
		this.text = text;
	}

	public void render(Graphics g) {
		update();
		
		g.setColor(color);
		
		if (text == "Pistol" || text == "SMG" || text == "Shotgun"){
			g.setFont(new Font(g.getFont().getFontName(), Font.BOLD, 20));
		}
		else g.setFont(new Font(g.getFont().getFontName(), Font.BOLD, 14));
		
		if (text != null) {
			g.drawString(text, (int) position.getX(), (int) position.getY());
		}
	}

	public boolean isRemoved() {
		return removed;
	}

}