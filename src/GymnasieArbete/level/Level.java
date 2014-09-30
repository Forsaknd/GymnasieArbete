package GymnasieArbete.level;

import java.util.ArrayList;
import java.util.List;

import GymnasieArbete.entities.Entity;
import GymnasieArbete.graphics.Screen;
import GymnasieArbete.level.tile.Tile;

public class Level {

	protected int width, height;
	protected int[] tilesInt;
	protected int[] tiles;
	protected int tile_size;
	
	private List<Entity> entities = new ArrayList<Entity>();
	
    public static Level spawn = new SpawnLevel("/levels/SpawnLevel.png");

	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		tilesInt = new int[width * height];
		generateLevel();
	}

	public Level(String path) {
		loadLevel(path);
		generateLevel();
	}

	protected void generateLevel() {
		for (int y = 0; y < 64; y++) {
			for (int x = 0; x < 64; x++) {
				getTile(x, y);
			}
		}
		tile_size = 16;
	}

	protected void loadLevel(String path) {
	}

	public void update() {
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).update();
		}
	}

	private void time() {
	}

	public void render(int xScroll, int yScroll, Screen screen) {
		screen.setOffset(xScroll, yScroll);
		int x0 = xScroll >> 4;
		int x1 = (xScroll + screen.width + 16) >> 4;
		int y0 = yScroll >> 4;
		int y1 = (yScroll + screen.height + 16) >> 4;

		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				getTile(x, y).render(x, y, screen);
			}
		}
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).render(screen);
		}
	}

	public void add(Entity e) {
		entities.add(e);
	}
	
	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height) return Tile.voidTile;
		if (tiles[x + y * width] == Tile.col_grass) return Tile.grass;
		if (tiles[x + y * width] == Tile.col_stones) return Tile.stones;
		if (tiles[x + y * width] == Tile.col_flower) return Tile.flower;
		if (tiles[x + y * width] == Tile.col_gravel) return Tile.gravel;
		if (tiles[x + y * width] == Tile.col_planks) return Tile.planks;
		return Tile.voidTile;
	}

}
