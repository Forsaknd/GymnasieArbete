package GymnasieArbete.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

	private boolean[] keys = new boolean[65536];
	public boolean esc, up, down, left, right, space;
	public boolean[] numbers = new boolean[10];

	public void update() {
		esc = keys[KeyEvent.VK_ESCAPE];
		up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
		space = keys[KeyEvent.VK_SPACE];
		numbers[0] = keys[KeyEvent.VK_1];
		numbers[1] = keys[KeyEvent.VK_2];
		numbers[2] = keys[KeyEvent.VK_3];
		numbers[3] = keys[KeyEvent.VK_4];
		numbers[4] = keys[KeyEvent.VK_5];
		numbers[5] = keys[KeyEvent.VK_6];
		numbers[6] = keys[KeyEvent.VK_7];
		numbers[7] = keys[KeyEvent.VK_8];
		numbers[8] = keys[KeyEvent.VK_9];
		numbers[9] = keys[KeyEvent.VK_0];

		for (int i = 0; i < keys.length; i++) {
			if (keys[i]) {
				System.out.println("KEY PRESSED: " + i);
			}
		}
	}

	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	public void keyTyped(KeyEvent e) {

	}

}
