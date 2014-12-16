package GymnasieArbete.level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import GymnasieArbete.entities.items.Pistol;
import GymnasieArbete.entities.mob.Zombie;

public class SpawnLevel extends Level {

	public SpawnLevel(String path) {
		super(path);
	}

	protected void loadLevel(String path) {
		try {
			BufferedImage image = ImageIO.read(SpawnLevel.class.getResource(path));
			int w = width = image.getWidth();
			int h = height = image.getHeight();
			tiles = new int[w * h];
			image.getRGB(0, 0, w, h, tiles, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Exception! Could not load level file!");
		}
		for (int i = 0; i < 1; i++) {
			add(new Pistol(30, 30));
			add(new Zombie(30, 30));
			add(new Zombie(32, 30));
			add(new Zombie(34, 30));
			add(new Zombie(36, 30));
			add(new Zombie(38, 30));
			add(new Zombie(40, 30));
			add(new Zombie(30, 32));
			add(new Zombie(32, 32));
			add(new Zombie(34, 32));
			add(new Zombie(36, 32));
			add(new Zombie(38, 32));
			add(new Zombie(40, 32));
		}
	}

	protected void generateLevel() {
	}

}
