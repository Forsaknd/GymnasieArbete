package GymnasieArbete.level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import GymnasieArbete.entities.items.Bandage;
import GymnasieArbete.entities.items.Medkit;
import GymnasieArbete.entities.items.Pistol;
import GymnasieArbete.entities.items.Shotgun;
import GymnasieArbete.entities.items.Smg;
import GymnasieArbete.entities.items.Rifle;

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
			add(new Shotgun(22, 30));
			add(new Pistol(24, 30));
			add(new Smg(26, 30));
			add(new Bandage(20, 30));
			add(new Medkit(18, 30));
			add(new Rifle(16, 30));
			/*add(new Zombie(30, 30));
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
			add(new Zombie(30, 34));
			add(new Zombie(32, 34));
			add(new Zombie(34, 34));
			add(new Zombie(36, 34));
			add(new Zombie(38, 34));
			add(new Zombie(40, 34));
			add(new Zombie(30, 36));
			add(new Zombie(32, 36));
			add(new Zombie(34, 36));
			add(new Zombie(36, 36));
			add(new Zombie(38, 36));
			add(new Zombie(40, 36));*/
		}
	}

	protected void generateLevel() {
	}

}
