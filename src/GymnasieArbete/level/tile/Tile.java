package GymnasieArbete.level.tile;

import GymnasieArbete.graphics.Screen;
import GymnasieArbete.graphics.Sprite;

public class Tile {

	public int x, y;
	public Sprite sprite;	

	public static Tile voidTile = new VoidTile(Sprite.voidSprite);
	public static Tile grass = new GrassTile(Sprite.grass);
	public static Tile gravel = new GravelTile(Sprite.gravel);
	public static Tile planksh = new PlanksTile(Sprite.planksh);
	public static Tile planksv = new PlanksTile(Sprite.planksv);
	public static Tile planksc = new PlanksTile(Sprite.planksc);
	public static Tile flower = new GrassTile(Sprite.flower);
	public static Tile stones = new GrassTile(Sprite.stones);
	public static Tile watershallow = new GrassTile(Sprite.watershallow);

	public static final  int col_grass = 0xFF00FF00;
	public static final  int col_gravel = 0xFF7F7F00;
	public static final  int col_planksh = 0xFF9d815d;
	public static final  int col_planksv = 0xFF6a5943;
	public static final  int col_planksc = 0xFFab7c3e;
	public static final  int col_flower = 0xFFFFFF00;
	public static final  int col_stones = 0xFF8E8E8E;
	public static final  int col_watershallow = 0xFF3758d3;
	
	
	public Tile (Sprite sprite) {
		this.sprite = sprite;
	}
	
	public void render(int x, int y, Screen screen) {
	}
	
	public boolean solid(){
		return false;
	}
	
	public Sprite getSprite() {
		return sprite;
	}
	
	public int getSpriteSize() {
		return sprite.SIZE;
	}
	
}
