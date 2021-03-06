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
	public static SpriteSheet mobs = new SpriteSheet("/textures/sheets/spritesheet_mobs.png", 256);
	public static SpriteSheet items = new SpriteSheet("/textures/sheets/spritesheet_items.png", 128);
	public static SpriteSheet particles = new SpriteSheet("/textures/sheets/spritesheet_particles.png", 64);
	
	//UI
	public static SpriteSheet actionbar = new SpriteSheet("/textures/sheets/spritesheet_actionbar.png", 162, 20);
	public static SpriteSheet hpbar = new SpriteSheet("/textures/sheets/spritesheet_hpbar.png", 100, 14);

	//Player
	public static SpriteSheet player = new SpriteSheet("/textures/sheets/spritesheet_player.png", 640);
	public static SpriteSheet playerbigguns = new SpriteSheet("/textures/sheets/spritesheet_playerbigguns.png", 640);
	public static SpriteSheet player_down = new SpriteSheet(player, 0, 0, 1, 3, 32);
	public static SpriteSheet player_up = new SpriteSheet(player, 1, 0, 1, 3, 32);
	public static SpriteSheet player_left = new SpriteSheet(player, 2, 0, 1, 5, 32);
	public static SpriteSheet player_right = new SpriteSheet(player, 3, 0, 1, 5, 32);
	
	public static SpriteSheet player_pistol_down = new SpriteSheet(player, 4, 0, 1, 3, 32);
	public static SpriteSheet player_pistol_up = new SpriteSheet(player, 5, 0, 1, 3, 32);
	public static SpriteSheet player_pistol_left = new SpriteSheet(player, 6, 0, 1, 5, 32);
	public static SpriteSheet player_pistol_right = new SpriteSheet(player, 7, 0, 1, 5, 32);
	
	public static SpriteSheet player_smg_down = new SpriteSheet(player, 8, 0, 1, 3, 32);
	public static SpriteSheet player_smg_up = new SpriteSheet(player, 9, 0, 1, 3, 32);
	public static SpriteSheet player_smg_left = new SpriteSheet(player, 10, 0, 1, 5, 32);
	public static SpriteSheet player_smg_right = new SpriteSheet(player, 11, 0, 1, 5, 32);
	
	public static SpriteSheet player_shotgun_down = new SpriteSheet(player, 12, 0, 1, 3, 32);
	public static SpriteSheet player_shotgun_up = new SpriteSheet(player, 13, 0, 1, 3, 32);
	public static SpriteSheet player_shotgun_left = new SpriteSheet(player, 14, 0, 1, 5, 32);
	public static SpriteSheet player_shotgun_right = new SpriteSheet(player, 15, 0, 1, 5, 32);
	
	public static SpriteSheet player_rifle_down = new SpriteSheet(playerbigguns, 0, 0, 1, 3, 48);
	public static SpriteSheet player_rifle_up = new SpriteSheet(playerbigguns, 1, 0, 1, 3, 48);
	public static SpriteSheet player_rifle_left = new SpriteSheet(playerbigguns, 2, 0, 1, 5, 48);
	public static SpriteSheet player_rifle_right = new SpriteSheet(playerbigguns, 3, 0, 1, 5, 48);
	
	//Zombie
	public static SpriteSheet zombie_down = new SpriteSheet(mobs, 0, 0, 1, 5, 32);
	public static SpriteSheet zombie_up = new SpriteSheet(mobs, 1, 0, 1, 5, 32);
	public static SpriteSheet zombie_left = new SpriteSheet(mobs, 2, 0, 1, 5, 32);
	public static SpriteSheet zombie_right = new SpriteSheet(mobs, 3, 0, 1, 5, 32);

	private Sprite[] sprites;

	public SpriteSheet(SpriteSheet sheet, int x, int y, int width, int height, int spriteSize) {
		int xx = x * spriteSize;
		int yy = y * spriteSize;
		int w = width * spriteSize;
		int h = height * spriteSize;
		if (width == height) SIZE = width;
		else SIZE = -1;
		WIDTH = w;
		HEIGHT = h;
		pixels = new int[w * h];
		for (int y0 = 0; y0 < h; y0++) {
			int yp = yy + y0;

			for (int x0 = 0; x0 < w; x0++) {
				int xp = xx + x0;
				pixels[x0 + y0 * w] = sheet.pixels[xp + yp * sheet.WIDTH];
			}
		}

		int frame = 0;
		sprites = new Sprite[width * height];
		for (int ya = 0; ya < height; ya++) {
			for (int xa = 0; xa < width; xa++) {
				int[] spritePixels = new int[spriteSize * spriteSize];
				for (int y0 = 0; y0 < spriteSize; y0++) {
					for (int x0 = 0; x0 < spriteSize; x0++) {
						spritePixels[x0 + y0 * spriteSize] = pixels[(x0 + xa * spriteSize) + (y0 + ya * spriteSize) * WIDTH];
					}
				}
				Sprite sprite = new Sprite(spritePixels, spriteSize, spriteSize);
				sprites[frame++] = sprite;
			}
		}

	}

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

	public Sprite[] getSprites() {
		return sprites;
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
