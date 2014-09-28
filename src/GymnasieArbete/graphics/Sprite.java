package GymnasieArbete.graphics;

public class Sprite {

	public final int SIZE;
	private int x, y;
	public int[] pixels;
	private SpriteSheet sheet;

	public static Sprite voidSprite = new Sprite(16, 0x1B87E0);
	public static Sprite grass = new Sprite(16, 0, 0, SpriteSheet.tiles);
	public static Sprite rock = new Sprite(16, 1, 0, SpriteSheet.tiles);
	public static Sprite planks = new Sprite(16, 2, 0, SpriteSheet.tiles);
	public static Sprite flower = new Sprite(16, 3, 0, SpriteSheet.tiles);
	
	public static Sprite player_up = new Sprite(32, 0, 0, SpriteSheet.characters);
	public static Sprite player_right = new Sprite(32, 1, 0, SpriteSheet.characters);
	public static Sprite player_down = new Sprite(32, 2, 0, SpriteSheet.characters);
	public static Sprite player_left = new Sprite(32, 3, 0, SpriteSheet.characters);
	
	public static Sprite player_up_1 = new Sprite(32, 0, 1, SpriteSheet.characters);
	public static Sprite player_up_2 = new Sprite(32, 0, 2, SpriteSheet.characters);
	
	public static Sprite player_right_1 = new Sprite(32, 1, 1, SpriteSheet.characters);
	public static Sprite player_right_2 = new Sprite(32, 1, 2, SpriteSheet.characters);
	
	public static Sprite player_down_1 = new Sprite(32, 2, 1, SpriteSheet.characters);
	public static Sprite player_down_2 = new Sprite(32, 2, 2, SpriteSheet.characters);
	
	public static Sprite player_left_1 = new Sprite(32, 3, 1, SpriteSheet.characters);
	public static Sprite player_left_2 = new Sprite(32, 3, 2, SpriteSheet.characters);

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
