package GymnasieArbete.level;

import java.util.ArrayList;
import java.util.List;

import GymnasieArbete.entities.Entity;
import GymnasieArbete.entities.mob.Player;
import GymnasieArbete.entities.particle.Particle;
import GymnasieArbete.entities.projectile.Projectile;
import GymnasieArbete.graphics.Screen;
import GymnasieArbete.level.tile.Tile;

public class Level {

	protected int width, height;
	protected int[] tilesInt;
	protected int[] tiles;
	protected int tile_size;

	private List<Entity> entities = new ArrayList<Entity>();
	private List<Projectile> projectiles = new ArrayList<Projectile>();
	private List<Particle> particles = new ArrayList<Particle>();

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

		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).update();
		}

		for (int i = 0; i < particles.size(); i++) {
			particles.get(i).update();
		}
		remove();
	}

	private void remove() {
		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i).isRemoved())
				entities.remove(i);
		}

		for (int i = 0; i < projectiles.size(); i++) {
			if (projectiles.get(i).isRemoved())
				projectiles.remove(i);
		}

		for (int i = 0; i < particles.size(); i++) {
			if (particles.get(i).isRemoved())
				particles.remove(i);
		}
	}

	private void time() {
	}

	// collision entity > tile
	public boolean tileCollision(int x, int y, int size, int xOffset, int yOffset) {
		boolean solid = false;
		for (int c = 0; c < 4; c++) {
			int xt = (x - c % 2 * size + xOffset) >> 4;
			int yt = (y - c / 2 * size + yOffset) >> 4;
			if (getTile(xt, yt).solid())
				solid = true;
		}
		return solid;
	}

	/*
	 * public boolean mobCollision(double x, double y, double xa, double ya, int
	 * size) { boolean solid = false; for (int c = 0; c < 4; c++) { int xt =
	 * (((int) x + (int) xa) + c % 2 * size / 8 + 6) / 16; int yt = (((int) y +
	 * (int) ya) + c / 2 * size / 8 + 6) / 16; if (getMob(xt, yt).collision())
	 * solid = true; } return solid; }
	 */

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

		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).render(screen);
		}

		for (int i = 0; i < particles.size(); i++) {
			particles.get(i).render(screen);
		}
	}

	public void add(Entity e) {
		e.init(this);
		if (e instanceof Particle) {
			particles.add((Particle) e);
		} else if (e instanceof Projectile) {
			projectiles.add((Projectile) e);
		} else {
			entities.add(e);
		}
	}

	public List<Projectile> getProjectiles() {
		return projectiles;
	}

	public Player getPlayer() {
		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i) instanceof Player) {
				return (Player) entities.get(i);
			}
		}
		return null;
	}

	public List<Entity> getEntities(Entity e, int radius) {
		List<Entity> result = new ArrayList<Entity>();
		int ex = e.getX();
		int ey = e.getY();
		for (int i = 0; i < entities.size(); i++) {
			Entity entity = entities.get(i);
			int x = entity.getX();
			int y = entity.getY();
			int dx = Math.abs(x - ex);
			int dy = Math.abs(y - ey);
			double distance = Math.sqrt((dx * dx) + (dy * dy));
			if (distance <= radius)
				result.add(entity);
		}
		return result;
	}
	
	public List<Player> getPlayers(Entity e, int radius) {
		List<Entity> entities = getEntities(e, radius);
		List<Player> result = new ArrayList<Player>();
		for (int i = 0; i < entities.size(); i++) {
			if(entities.get(i) instanceof Player) {
				result.add((Player) entities.get(i));
			}
		}
		return result;
	}

	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height)
			return Tile.voidTile;
		if (tiles[x + y * width] == Tile.col_grass)
			return Tile.grass;
		if (tiles[x + y * width] == Tile.col_gravel)
			return Tile.gravel;
		if (tiles[x + y * width] == Tile.col_planksh)
			return Tile.planksh;
		if (tiles[x + y * width] == Tile.col_planksv)
			return Tile.planksv;
		if (tiles[x + y * width] == Tile.col_planksc)
			return Tile.planksc;
		if (tiles[x + y * width] == Tile.col_stones)
			return Tile.stones;
		if (tiles[x + y * width] == Tile.col_flower)
			return Tile.flower;
		if (tiles[x + y * width] == Tile.col_watershallow)
			return Tile.watershallow;
		return Tile.voidTile;
	}

	/*
	 * public Mob getMob(int x, int y) { if (x < 0 || y < 0 || x >= width || y
	 * >= height) return null; if (tiles[x + y * width] == .col_grass) return
	 * Tile.grass; return Tile.voidTile; }
	 */
}
