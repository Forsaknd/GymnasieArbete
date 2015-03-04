package GymnasieArbete.level;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import GymnasieArbete.entities.Entity;
import GymnasieArbete.entities.items.BulletAmmo;
import GymnasieArbete.entities.items.Item;
import GymnasieArbete.entities.items.ShellAmmo;
import GymnasieArbete.entities.mob.Player;
import GymnasieArbete.entities.mob.Zombie;
import GymnasieArbete.entities.particle.BackgroundParticle;
import GymnasieArbete.entities.particle.Particle;
import GymnasieArbete.entities.particle.SpriteParticle;
import GymnasieArbete.entities.projectile.Projectile;
import GymnasieArbete.graphics.Screen;
import GymnasieArbete.level.tile.Tile;
import GymnasieArbete.util.Vector2i;

public class Level {

	protected int width, height;
	protected int[] tilesInt;
	protected int[] tiles;
	protected int tile_size;
	protected int time = 0;
	protected Vector2i exitLoc;
	protected Vector2i spawnLoc;

	private List<Entity> entities = new ArrayList<Entity>();
	private List<Item> items = new ArrayList<Item>();
	private List<Projectile> projectiles = new ArrayList<Projectile>();
	private List<Particle> particles = new ArrayList<Particle>();
	private List<BackgroundParticle> backgroundParticles = new ArrayList<BackgroundParticle>();
	private List<SpriteParticle> spriteParticles = new ArrayList<SpriteParticle>();

	protected String name;
	protected String nextlevel;

	private Comparator<Node> nodeSorter = new Comparator<Node>() {
		@Override
		public int compare(Node n0, Node n1) {
			if (n1.fCost < n0.fCost) return +1;
			if (n1.fCost > n0.fCost) return -1;
			return 0;
		}
	};

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

	public void spawnZombies() {
		time++;
		if (time % 120 == 0) {
			if (entities.size() < 100) {
				Random r = new Random();
				add(new Zombie(r.nextInt(this.width), r.nextInt(this.height)));
				
				add(new ShellAmmo(r.nextInt(this.width), r.nextInt(this.height)));
				add(new BulletAmmo(r.nextInt(this.width), r.nextInt(this.height)));
				
				time = 0;
			}
		}
	}

	public void update() {
		spawnZombies();
		for (int i = 0; i < items.size(); i++) {
			items.get(i).update();
		}

		for (int i = 0; i < backgroundParticles.size(); i++) {
			backgroundParticles.get(i).update();
		}

		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).update();
		}

		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).update();
		}

		for (int i = 0; i < particles.size(); i++) {
			particles.get(i).update();
		}

		for (int i = 0; i < spriteParticles.size(); i++) {
			spriteParticles.get(i).update();
		}

		remove();
	}

	private void remove() {

		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).isRemoved()) items.remove(i);
		}

		for (int i = 0; i < backgroundParticles.size(); i++) {
			if (backgroundParticles.get(i).isRemoved()) backgroundParticles.remove(i);
		}

		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i).isRemoved()) entities.remove(i);
		}

		for (int i = 0; i < projectiles.size(); i++) {
			if (projectiles.get(i).isRemoved()) projectiles.remove(i);
		}

		for (int i = 0; i < particles.size(); i++) {
			if (particles.get(i).isRemoved()) particles.remove(i);
		}

		for (int i = 0; i < spriteParticles.size(); i++) {
			if (spriteParticles.get(i).isRemoved()) spriteParticles.remove(i);
		}
	}

	public void removeAll() {

		for (int i = 0; i < items.size(); i++) {
			items.remove(i);
		}

		for (int i = 0; i < backgroundParticles.size(); i++) {
			backgroundParticles.remove(i);
		}

		for (int i = 0; i < entities.size(); i++) {
			entities.remove(i);
		}

		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.remove(i);
		}

		for (int i = 0; i < particles.size(); i++) {
			particles.remove(i);
		}

		for (int i = 0; i < spriteParticles.size(); i++) {
			spriteParticles.remove(i);
		}
	}

	// private void time() {
	// }

	// collision entity > tile
	public boolean tileCollision(int x, int y, int size, int xOffset, int yOffset) {
		boolean solid = false;
		for (int c = 0; c < 4; c++) {
			int xt = (x - c % 2 * size + xOffset) >> 4;
			int yt = (y - c / 2 * size + yOffset) >> 4;
			if (getTile(xt, yt).solid()) solid = true;
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

		for (int i = 0; i < backgroundParticles.size(); i++) {
			backgroundParticles.get(i).render(screen);
		}

		for (int i = 0; i < items.size(); i++) {
			items.get(i).render(screen);
		}

		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i) instanceof Player) continue;
			entities.get(i).render(screen);
		}

		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).render(screen);
		}

		for (int i = 0; i < particles.size(); i++) {
			particles.get(i).render(screen);
		}

		getPlayer().render(screen);

		for (int i = 0; i < spriteParticles.size(); i++) {
			spriteParticles.get(i).render(screen);
		}
	}

	public void add(Entity e) {
		e.init(this);
		if (e instanceof Item) {
			items.add((Item) e);
		} else if (e instanceof Particle) {
			particles.add((Particle) e);
		} else if (e instanceof BackgroundParticle) {
			backgroundParticles.add((BackgroundParticle) e);
		} else if (e instanceof Projectile) {
			projectiles.add((Projectile) e);
		} else if (e instanceof SpriteParticle) {
			spriteParticles.add((SpriteParticle) e);
		} else {
			entities.add(e);
		}
	}

	public List<Projectile> getProjectiles() {
		return projectiles;
	}

	public List<Node> findPath(Vector2i start, Vector2i goal) {
		List<Node> openList = new ArrayList<Node>();
		List<Node> closedList = new ArrayList<Node>();
		Node current = new Node(start, null, 0, getDistance(start, goal));
		openList.add(current);
		while (openList.size() > 0) {
			Collections.sort(openList, nodeSorter);
			current = openList.get(0);
			if (current.tile.equals(goal)) {
				List<Node> path = new ArrayList<Node>();
				while (current.parent != null) {
					path.add(current);
					current = current.parent;
				}
				openList.clear();
				closedList.clear();
				return path;
			}
			openList.remove(current);
			closedList.add(current);
			for (int i = 0; i < 9; i++) {
				if (i == 4) continue;
				int x = current.tile.getX();
				int y = current.tile.getY();
				int xi = (i % 3) - 1;
				int yi = (i / 3) - 1;
				Tile at = getTile(x + xi, y + yi);
				if (at == null) continue;
				if (at.solid()) continue;
				Vector2i a = new Vector2i(x + xi, y + yi);
				double gCost = current.gCost + (getDistance(current.tile, a) == 1 ? 1 : 0.95);
				double hCost = getDistance(a, goal);
				Node node = new Node(a, current, gCost, hCost);
				if (vecInList(closedList, a) && gCost >= node.gCost) continue;
				if (!vecInList(openList, a) || gCost < node.gCost) openList.add(node);
			}
		}
		closedList.clear();
		return null;
	}

	private boolean vecInList(List<Node> list, Vector2i vector) {
		for (Node n : list) {
			if (n.tile.equals(vector)) return true;
		}
		return false;
	}

	private double getDistance(Vector2i tile, Vector2i goal) {
		double dx = tile.getX() - goal.getX();
		double dy = tile.getY() - goal.getY();
		return Math.sqrt((dx * dx) + (dy * dy));
	}

	public List<Entity> getEntities(Entity e, int radius) {
		List<Entity> result = new ArrayList<Entity>();
		int ex = (int) e.getX();
		int ey = (int) e.getY();
		for (int i = 0; i < entities.size(); i++) {
			Entity entity = entities.get(i);
			int x = (int) entity.getX();
			int y = (int) entity.getY();
			int dx = Math.abs(x - ex);
			int dy = Math.abs(y - ey);
			double distance = Math.sqrt((dx * dx) + (dy * dy));
			if (distance <= radius) result.add(entity);
		}
		return result;
	}

	public List<Zombie> getEnemy(Entity e, int radius) {
		List<Entity> entities = getEntities(e, radius);
		List<Zombie> result = new ArrayList<Zombie>();
		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i) instanceof Zombie) {
				result.add((Zombie) entities.get(i));
			}
		}
		return result;
	}

	public Player getPlayer() {
		Player result = null;
		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i) instanceof Player) {
				result = (Player) entities.get(i);
			}
		}
		return result;
	}

	public Player getPlayerInRange(Entity e, int radius) {
		List<Entity> entities = getEntities(e, radius);
		Player result = null;
		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i) instanceof Player) {
				result = (Player) entities.get(i);
			}
		}
		return result;
	}

	public Item getItemCol(Entity e, int radius) {
		Item result = null;
		int ex = (int) e.getX();
		int ey = (int) e.getY();
		for (int i = 0; i < items.size(); i++) {
			Item entity = items.get(i);
			int x = (int) entity.getX();
			int y = (int) entity.getY();
			int dx = Math.abs(x - ex);
			int dy = Math.abs(y - ey);
			double distance = Math.sqrt((dx * dx) + (dy * dy));
			if (distance <= radius) result = entity;
			else continue;
		}
		return result;
	}

	public Entity getEntitiesCol(Entity e, int radius) {
		Entity result = null;
		int ex = (int) e.getX();
		int ey = (int) e.getY();
		for (int i = 0; i < entities.size(); i++) {
			Entity entity = entities.get(i);
			int x = (int) entity.getX();
			int y = (int) entity.getY();
			int dx = Math.abs(x - ex);
			int dy = Math.abs(y - ey);
			double distance = Math.sqrt((dx * dx) + (dy * dy));
			if (distance <= radius) result = entity;
		}
		return result;
	}

	public boolean getEntityCol(Entity e, int radius) {
		boolean result = false;
		Entity entity = getEntitiesCol(e, radius);
		if (!entity.isRemoved()) {
			if (entity.equals(e)) result = false;
			else result = true;
		}
		return result;
	}

	public List<Entity> getallEntities() {
		return entities;
	}

	public Vector2i getExitLoc() {
		return exitLoc;
	}

	public Vector2i getSpawnLoc() {
		return spawnLoc;
	}

	public String getName() {
		return name;
	}

	public String getNextLevel() {
		return nextlevel;
	}

	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height) return Tile.voidTile;
		if (tiles[x + y * width] == Tile.col_grass) return Tile.grass;
		if (tiles[x + y * width] == Tile.col_gravel) return Tile.gravel;
		if (tiles[x + y * width] == Tile.col_planksh) return Tile.planksh;
		if (tiles[x + y * width] == Tile.col_planksv) return Tile.planksv;
		if (tiles[x + y * width] == Tile.col_planksc) return Tile.planksc;
		if (tiles[x + y * width] == Tile.col_stones) return Tile.stones;
		if (tiles[x + y * width] == Tile.col_flower) return Tile.flower;
		if (tiles[x + y * width] == Tile.col_watershallow) return Tile.watershallow;
		return Tile.voidTile;
	}
}
