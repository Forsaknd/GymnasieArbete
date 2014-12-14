package GymnasieArbete.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {

	private String path;
	public final int SIZE;
	public final int WIDTH, HEIGHT;
	public int[] pixels;

	public static SpriteSheet background = new SpriteSheet("/textures/sheets/spritesheet_background.png", 300);
	public static SpriteSheet tiles = new SpriteSheet("/textures/sheets/spritesheet_tiles.png", 256);
	public static SpriteSheet characters = new SpriteSheet("/textures/sheets/spritesheet_characters.png", 256);
	public static SpriteSheet player = new SpriteSheet("/textures/sheets/spritesheet_player.png", 160);
	public static SpriteSheet items = new SpriteSheet("/textures/sheets/spritesheet_items.png", 128);
	public static SpriteSheet projectiles = new SpriteSheet("/textures/sheets/spritesheet_projectiles.png", 64);
	
	public SpriteSheet(String path, int size) {
		this.path = path;
		SIZE = size;
		WIDTH = size;
		HEIGHT = size;
		pixels = new int[SIZE * SIZE];
		load();
	}
	
	public SpriteSheet(String path, int width, int height) {
		this.path = path;
		SIZE = -1;
		WIDTH = width;
		HEIGHT = height;
		pixels = new int[WIDTH * HEIGHT];
		load();
	}
	
	private void load() {
		try {
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			int w = image.getWidth();
			int h = image.getHeight();
			image.getRGB(0, 0, w, h, pixels, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
