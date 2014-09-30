package GymnasieArbete.graphics;

public class Sprite {

	public final int SIZE;
	private int x, y;
	public int[] pixels;
	private SpriteSheet sheet;

	public static Sprite voidSprite = new Sprite(16, 0x1B87E0);
	public static Sprite grass = new Sprite(16, 0, 0, SpriteSheet.tiles);
	public static Sprite flower = new Sprite(16, 3, 0, SpriteSheet.tiles);
	public static Sprite stones = new Sprite(16, 4, 0, SpriteSheet.tiles);
	public static Sprite gravel = new Sprite(16, 1, 0, SpriteSheet.tiles);
	public static Sprite planks = new Sprite(16, 2, 0, SpriteSheet.tiles);
	
	//PROJECTILES
	public static Sprite pistolProjectile = new Sprite(16, 0, 0, SpriteSheet.projectiles);
	
	//MOBS
	public static Sprite enemy = new Sprite(32, 0, 0, SpriteSheet.characters);
	
	//PLAYER
	public static Sprite player_up = new Sprite(32, 0, 0, SpriteSheet.player);
	public static Sprite player_right = new Sprite(32, 1, 0, SpriteSheet.player);
	public static Sprite player_down = new Sprite(32, 2, 0, SpriteSheet.player);
	public static Sprite player_left = new Sprite(32, 3, 0, SpriteSheet.player);
	
	public static Sprite player_up_1 = new Sprite(32, 0, 1, SpriteSheet.player);
	public static Sprite player_up_2 = new Sprite(32, 0, 2, SpriteSheet.player);
	
	public static Sprite player_right_1 = new Sprite(32, 1, 1, SpriteSheet.player);
	public static Sprite player_right_2 = new Sprite(32, 1, 2, SpriteSheet.player);
	public static Sprite player_right_3 = new Sprite(32, 1, 3, SpriteSheet.player);
	public static Sprite player_right_4 = new Sprite(32, 1, 4, SpriteSheet.player);
	
	public static Sprite player_down_1 = new Sprite(32, 2, 1, SpriteSheet.player);
	public static Sprite player_down_2 = new Sprite(32, 2, 2, SpriteSheet.player);
	
	public static Sprite player_left_1 = new Sprite(32, 3, 1, SpriteSheet.player);
	public static Sprite player_left_2 = new Sprite(32, 3, 2, SpriteSheet.player);
	public static Sprite player_left_3 = new Sprite(32, 3, 3, SpriteSheet.player);
	public static Sprite player_left_4 = new Sprite(32, 3, 4, SpriteSheet.player);
	
	//PLAYER SHOOTING
	public static Sprite player_shoot_left_1 = new Sprite(32, 4, 0, SpriteSheet.player);
	public static Sprite player_shoot_left_2 = new Sprite(32, 4, 1, SpriteSheet.player);
	
	public static Sprite player_shoot_right_1 = new Sprite(32, 4, 2, SpriteSheet.player);
	public static Sprite player_shoot_right_2 = new Sprite(32, 4, 3, SpriteSheet.player);

	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		SIZE = size;
		pixels = new int[SIZE * SIZE];
		this.x = x * size;
		this.y = y * size;
		this.sheet = sheet;
		load();
	}

	public Sprite(int size, int color) {
		SIZE = size;
		pixels = new int[SIZE * SIZE];
		setColor(color);
	}	
	
	private void setColor(int color) {
		for (int i = 0; i < SIZE*SIZE; i++) {
			pixels[i] = color;
		}		
	}

	private void load() {
		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				pixels[x + y * SIZE] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SIZE];
			}
		}
	}

}
