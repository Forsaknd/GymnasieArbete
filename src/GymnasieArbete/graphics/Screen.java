package GymnasieArbete.graphics;

import java.util.Random;

import GymnasieArbete.entities.items.Item;
import GymnasieArbete.entities.mob.Mob;
import GymnasieArbete.entities.projectile.Projectile;
import GymnasieArbete.level.tile.Tile;

public class Screen {

	public int width;
	public int height;
	public int[] pixels;
	public final int MAP_SIZE = 64;
	public final int MAP_SIZE_MASKED = MAP_SIZE - 1;

	public int xOffset, yOffset;

	public int[] tiles = new int[MAP_SIZE * MAP_SIZE];

	private Random random = new Random();

	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];

		for (int i = 0; i < MAP_SIZE * MAP_SIZE; i++) {
			tiles[i] = random.nextInt(0xffffff);
			tiles[0] = 0;
		}

	}

	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}

	public void renderSheet(int xp, int yp, SpriteSheet sheet, boolean fixed) {
		if (fixed) {
			xp -= xOffset;
			yp -= yOffset;
		}
		for (int y = 0; y < sheet.HEIGHT; y++) {
			int ya = y + yp;
			for (int x = 0; x < sheet.WIDTH; x++) {
				int xa = x + xp;
				if (xa < 0 || xa >= width || ya < 0 || ya >= height) continue;
				int col = sheet.pixels[x + y * sheet.WIDTH];
				if (col != 0xffff00ff) pixels[xa + ya * width] = col;
			}
		}

	}

	public void renderSprite(int xp, int yp, Sprite sprite, boolean fixed) {
		if (fixed) {
			xp -= xOffset;
			yp -= yOffset;
		}
		for (int y = 0; y < sprite.getHeight(); y++) {
			int ya = y + yp;
			int ys = y;
			for (int x = 0; x < sprite.getWidth(); x++) {
				int xa = x + xp;
				int xs = x;
				if (xa < 0 || xa >= width || ya < 0 || ya >= height) continue;
				int col = sprite.pixels[xs + ys * sprite.getWidth()];
				if (col != 0xffff00ff) pixels[xa + ya * width] = col;
			}
		}

	}

	public void renderTile(int xp, int yp, Tile tile) {
		xp -= xOffset;
		yp -= yOffset;
		for (int y = 0; y < tile.getSpriteSize(); y++) {
			int ya = y + yp;
			for (int x = 0; x < tile.getSpriteSize(); x++) {
				int xa = x + xp;
				if (xa < -tile.getSpriteSize() || xa >= width || ya < 0 || ya >= height) break;
				if (xa < 0) xa = 0;
				pixels[xa + ya * width] = tile.sprite.pixels[x + y * tile.getSpriteSize()];
			}
		}
	}

	public void renderProjectile(int xp, int yp, Projectile p) {
		xp -= xOffset;
		yp -= yOffset;
		for (int y = 0; y < p.getSpriteSize(); y++) {
			int ya = y + yp;
			int ys = y;
			for (int x = 0; x < p.getSpriteSize(); x++) {
				int xa = x + xp;
				int xs = x;
				if (xa < -p.getSpriteSize() || xa >= width || ya < 0 || ya >= height) break;
				if (xa < 0) xa = 0;
				int col = p.getSprite().pixels[xs + ys * p.getSprite().SIZE];
				if (col != 0xffff00ff) pixels[xa + ya * width] = col;
			}
		}
	}

	public void renderMob(int xp, int yp, Sprite sprite) {
		xp -= xOffset;
		yp -= yOffset;
		for (int y = 0; y < sprite.SIZE; y++) {
			int ya = y + yp;
			int ys = y;
			for (int x = 0; x < sprite.SIZE; x++) {
				int xa = x + xp;
				int xs = x;
				if (xa < -sprite.SIZE || xa >= width || ya < 0 || ya >= height) break;
				if (xa < 0) xa = 0;
				int col = sprite.pixels[xs + ys * sprite.SIZE];
				if (col != 0xffff00ff) pixels[xa + ya * width] = col;
			}
		}
	}

	public void renderMob(int xp, int yp, Mob mob) {
		xp -= xOffset;
		yp -= yOffset;
		for (int y = 0; y < mob.getSprite().SIZE; y++) {
			int ya = y + yp;
			int ys = y;
			for (int x = 0; x < mob.getSprite().SIZE; x++) {
				int xa = x + xp;
				int xs = x;
				if (xa < -mob.getSprite().SIZE || xa >= width || ya < 0 || ya >= height) break;
				if (xa < 0) xa = 0;
				int col = mob.getSprite().pixels[xs + ys * mob.getSprite().SIZE];
				//if ((mob instanceof Zombie) && col == 0xffe3ccc7) col = 0xffBA0015;
				if (col != 0xffff00ff) pixels[xa + ya * width] = col;
			}
		}
	}

	public void renderItem(int xp, int yp, Item item) {
		xp -= xOffset;
		yp -= yOffset;
		for (int y = 0; y < item.getSprite().SIZE; y++) {
			int ya = y + yp;
			int ys = y;
			for (int x = 0; x < item.getSprite().SIZE; x++) {
				int xa = x + xp;
				int xs = x;
				if (xa < -item.getSprite().SIZE || xa >= width || ya < 0 || ya >= height) break;
				if (xa < 0) xa = 0;
				int col = item.getSprite().pixels[xs + ys * item.getSprite().SIZE];
				//if ((mob instanceof Zombie) && col == 0xffe3ccc7) col = 0xffBA0015;
				if (col != 0xffff00ff) pixels[xa + ya * width] = col;
			}
		}
	}

	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
}
