package GymnasieArbete.graphics;

public class AnimatedSprite extends Sprite {

	private int frame = 0;
	private Sprite sprite;
	private int rate = 7;
	private int time = 0;
	private int length = -1;

	public AnimatedSprite(SpriteSheet sheet, int width, int height, int length, int rate) {
		super(width, height, sheet);
		this.length = length;
		this.rate = rate;
		sprite = sheet.getSprites()[0];
		if (length > sheet.getSprites().length) System.err.println("Error!: length of animation is too long!");
	}

	public void update() {
		time++;
		if (time % rate == 0) {
			if (frame >= length - 1) frame = 0;
			else frame++;
			sprite = sheet.getSprites()[frame];
		}
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void setFrame(int index) {
		if (index > sheet.getSprites().length - 1) {
			System.err.println("Index out of bounds in " + this);
			return;
		}
		sprite = sheet.getSprites()[index];
	}

	public void setFrameRate(int frames) {
		rate = frames;
	}

}
