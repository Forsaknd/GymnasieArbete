package GymnasieArbete.level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import GymnasieArbete.entities.items.Pistol;
import GymnasieArbete.util.Vector2i;

public class SpawnLevel extends Level {

	public SpawnLevel(String path) {
		super(path);
		spawnLoc = new Vector2i(26 << 4, 30 << 4);
		//exitloc unavailable
		exitLoc = new Vector2i(400, 400);
		name = "spawn";
		nextlevel = "house";
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
	}

	protected void generateLevel() {
		add(new Pistol(30, 30));
	}

}
