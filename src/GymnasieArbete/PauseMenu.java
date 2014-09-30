package GymnasieArbete;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import GymnasieArbete.input.Mouse;

public class PauseMenu {

	private int xCenter = Game.getWindowWidth() / 2;
	public Rectangle resumeButton = new Rectangle(xCenter - 90, 150, 160, 50);
	public Rectangle helpButton = new Rectangle(xCenter - 60, 250, 100, 50);
	public Rectangle quitButton = new Rectangle(xCenter - 60, 350, 100, 50);
	public static Mouse mouse = new Mouse();

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		Font ftitle = new Font("arial", Font.BOLD, 50);
		g.setFont(ftitle);
		g.setColor(Color.white);
		g.drawString("PAUSED", xCenter - 115, 100);

		Font fbutton = new Font("arial", Font.BOLD, 30);
		g.setFont(fbutton);
		g.drawString("Resume", resumeButton.x + 20, resumeButton.y + 35);
		g2d.draw(resumeButton);
		g.drawString("Help", helpButton.x + 20, helpButton.y + 35);
		g2d.draw(helpButton);
		g.drawString("Quit", quitButton.x + 20, quitButton.y + 35);
		g2d.draw(quitButton);

		if (Mouse.getB() == 1) {
			if (Mouse.getX() >= xCenter - 90 && Mouse.getX() <= xCenter + 70) {
				if (Mouse.getY() >= 150 && Mouse.getY() <= 200) {
					Game.state = Game.STATE.GAME;
				}
			}
		}

		if (Mouse.getB() == 1) {
			int mx = Mouse.getX();
			int my = Mouse.getY();
			if (mx >= xCenter - 60 && mx <= xCenter + 40) {
				if (my >= 250 && my <= 300) {
					Game.state = Game.STATE.GAME;
				}
			}
		}

		if (Mouse.getB() == 1) {
			int mx = Mouse.getX();
			int my = Mouse.getY();
			if (mx >= xCenter - 60 && mx <= xCenter + 40) {
				if (my >= 350 && my <= 400) {
					Game.running = false;
				}
			}
		}
	}
}
