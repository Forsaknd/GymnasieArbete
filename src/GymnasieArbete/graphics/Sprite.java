package GymnasieArbete.graphics;

public class Sprite {

	public final int SIZE;
	private int x, y;
	private int width, height;
	public int[] pixels;
	protected SpriteSheet sheet;

	// TILES
	// public static Sprite voidSprite = new Sprite(16, 0x1B87E0);
	public static Sprite voidSprite = new Sprite(16, 0, 0, SpriteSheet.tiles);
	public static Sprite grass = new Sprite(16, 0, 0, SpriteSheet.tiles);
	public static Sprite gravel = new Sprite(16, 1, 0, SpriteSheet.tiles);
	public static Sprite planksh = new Sprite(16, 2, 0, SpriteSheet.tiles);
	public static Sprite planksv = new Sprite(16, 3, 0, SpriteSheet.tiles);
	public static Sprite planksc = new Sprite(16, 4, 0, SpriteSheet.tiles);
	public static Sprite flower = new Sprite(16, 5, 0, SpriteSheet.tiles);
	public static Sprite stones = new Sprite(16, 6, 0, SpriteSheet.tiles);
	public static Sprite watershallow = new Sprite(16, 7, 0, SpriteSheet.tiles);

	// MENU BACKGROUND
	public static Sprite background = new Sprite(300, 0, 0, SpriteSheet.background);

	// ITEMS
	public static Sprite pistol = new Sprite(16, 0, 0, SpriteSheet.items);
	public static Sprite smg = new Sprite(16, 1, 0, SpriteSheet.items);
	public static Sprite shotgun = new Sprite(16, 2, 0, SpriteSheet.items);
	public static Sprite bandage = new Sprite(16, 3, 0, SpriteSheet.items);
	public static Sprite medkit = new Sprite(16, 4, 0, SpriteSheet.items);
	public static Sprite rifle = new Sprite(16, 5, 0, SpriteSheet.items);
	public static Sprite ammocrate = new Sprite(16, 6, 0, SpriteSheet.items);
	public static Sprite shellammocrate = new Sprite(16, 7, 0, SpriteSheet.items);
	
	// ACTIONBAR
	public static Sprite actionbar = new Sprite(162, 20, SpriteSheet.actionbar);
	public static Sprite hpbar = new Sprite(100, 14, SpriteSheet.hpbar);
	
	// PROJECTILES
	public static Sprite bullet = new Sprite(2,0xFFf1e7d9);
	public static Sprite muzzleflash_down = new Sprite(16, 0, 1, SpriteSheet.particles);
	public static Sprite muzzleflash_up = new Sprite(16, 1, 1, SpriteSheet.particles);
	public static Sprite muzzleflash_left = new Sprite(16, 2, 1, SpriteSheet.particles);
	public static Sprite muzzleflash_right = new Sprite(16, 3, 1, SpriteSheet.particles);

	// PARTICLES
	public static Sprite particle_normal = new Sprite(2, 0xFFb17847);
	public static Sprite particle_shell_left = new Sprite(16, 0, 0, SpriteSheet.particles);
	public static Sprite particle_shell_right = new Sprite(16, 1, 0, SpriteSheet.particles);
	public static Sprite particle_shotgunshell_left = new Sprite(16, 2, 0, SpriteSheet.particles);
	public static Sprite particle_shotgunshell_right = new Sprite(16, 3, 0, SpriteSheet.particles);
	public static Sprite particle_blood = new Sprite(2, 0xFFe71e1e);

	// MOBS
	public static Sprite zombie = new Sprite(32, 0, 0, SpriteSheet.mobs);

	// PLAYER
	public static Sprite player = new Sprite(32, 0, 0, SpriteSheet.player);

	protected Sprite(int width, int height, SpriteSheet sheet) {
		SIZE = (width == height) ? width : -1;
		this.width = width;
		this.height = height;
		this.sheet = sheet;
		pixels = new int[width * height];
		load();
	}

	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		SIZE = size;
		this.width = size;
		this.height = size;
		pixels = new int[SIZE * SIZE];
		this.x = x * size;
		this.y = y * size;
		this.sheet = sheet;
		load();
	}

	public Sprite(int width, int height, int color) {
		SIZE = -1;
		this.width = width;
		;
		this.height = height;
		pixels = new int[width * height];
		setColor(color);
	}

	public Sprite(int size, int color) {
		SIZE = size;
		this.width = size;
		this.height = size;
		pixels = new int[SIZE * SIZE];
		setColor(color);
	}

	public Sprite(int[] pixels, int width, int height) {
		SIZE = (width == height) ? width : -1;
		this.width = width;
		this.height = height;
		this.pixels = pixels;
	}

	private void setColor(int color) {
		for (int i = 0; i < width * height; i++) {
			pixels[i] = color;
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	private void load() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				pixels[x + y * width] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.WIDTH];
			}
		}
	}
}
