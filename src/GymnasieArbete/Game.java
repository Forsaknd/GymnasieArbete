package GymnasieArbete;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;

import GymnasieArbete.entities.mob.Player;
import GymnasieArbete.graphics.Screen;
import GymnasieArbete.input.Keyboard;
import GymnasieArbete.input.Mouse;
import GymnasieArbete.level.Level;
import GymnasieArbete.level.TileCoordinate;
import GymnasieArbete.util.Vector2i;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;

	private static int width = 400;
	private static int height = width / 16 * 9;
	public static int scale = 3;
	public static String title = "Game";
	private static Clip hit;
	private static Clip step;

	private Thread thread;
	private JFrame frame;
	private Keyboard key;
	private Level level;
	private Player player;
	private Menu menu;
	private PauseMenu paused;
	private Graphics g;
	public static boolean running = false;

	private Screen screen;

	private BufferStrategy bs;
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

	public static enum STATE {
		MENU, PAUSED, GAME, GAMEOVER
	};

	public static STATE state = STATE.MENU;

	public Game() {
		Dimension size = new Dimension(Game.width * Game.scale, Game.height * Game.scale);
		setPreferredSize(size);

		screen = new Screen(width, height);
		frame = new JFrame();
		key = new Keyboard();
		level = Level.spawn;
		Vector2i playerSpawn = level.getSpawnLoc();
		player = new Player(playerSpawn.getX(), playerSpawn.getY(), key);
		level.add(player);
		menu = new Menu();
		paused = new PauseMenu();

		addKeyListener(key);

		Mouse mouse = new Mouse();
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
	}

	public static int getWindowWidth() {
		return width * scale;
	}

	public static int getWindowHeight() {
		return height * scale;
	}

	public synchronized void start() {
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}

	public synchronized void stop() {
		running = false;
		System.exit(ABORT);
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		playSound("ambience.wav", true);
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		int frames = 0;
		int updates = 0;
		requestFocus();
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1) {
				update();
				updates++;
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frame.setTitle(title + " | " + updates + " UPS " + frames + " FPS");
				updates = 0;
				frames = 0;
			}
		}
		stop();
	}

	public void update() {
		if (state == STATE.GAME) {
			key.update();
			level.update();
			if (level.getExitLoc().equals(new Vector2i((int) player.getX() >> 4, (int) player.getY() >> 4))) {
				level.removeAll();
				level = Level.house;
				Vector2i playerSpawn = level.getSpawnLoc();
				player = new Player(playerSpawn.getX(), playerSpawn.getY(), key);
				level.add(player);
			}
		}
	}

	public void render() {
		bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);

		if (state == STATE.GAME) {
			screen.clear();
			double xScroll = player.getX() - screen.width / 2;
			double yScroll = player.getY() - screen.height / 2;
			level.render((int) xScroll, (int) yScroll, screen);
			if (player.getHUD() != null) {
				player.getHUD().render(screen, g);
			}
			// screen.renderSheet(40, 40, SpriteSheet.player, false);
			// Sprite sprite = new Sprite(width - 40, 20, 0x0);
			// screen.renderSprite(20, height - 20, sprite, false);
			// screen.renderSprite(20, height - 20, Sprite.pistol, false);
			for (int i = 0; i < pixels.length; i++) {
				pixels[i] = screen.pixels[i];
			}
			g.setColor(Color.WHITE);
			// g.fillRect(Mouse.getX() - 32, Mouse.getY() - 32, 64, 64);
		} else if (state == STATE.MENU) {
			menu.render(screen);
			for (int i = 0; i < pixels.length; i++) {
				pixels[i] = screen.pixels[i];
			}
		}

		else if (state == STATE.PAUSED) {
			paused.render(g);
		}

		g.dispose();
		bs.show();
	}

	public static synchronized void playSound(final String url, boolean loop) {
		try {
			if (url.contains("hit")) {
				if (hit != null) hit.stop();
				hit = AudioSystem.getClip();
				String path = "/sounds/" + url;
				AudioInputStream inputStream = AudioSystem.getAudioInputStream(Game.class.getResource(path));
				hit.open(inputStream);
				hit.start();
			} else if (url.contains("step")) {
				if (step != null) step.stop();
				step = AudioSystem.getClip();
				String path = "/sounds/" + url;
				AudioInputStream inputStream = AudioSystem.getAudioInputStream(Game.class.getResource(path));
				step.open(inputStream);
				step.start();
			} else {
				Clip clip = AudioSystem.getClip();
				String path = "/sounds/" + url;
				AudioInputStream inputStream = AudioSystem.getAudioInputStream(Game.class.getResource(path));
				clip.open(inputStream);
				if (!loop) {
					clip.start();
				}
				if (loop) {
					clip.loop(Clip.LOOP_CONTINUOUSLY);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.frame.setLayout(new FlowLayout());
		game.frame.setResizable(true);
		game.frame.setTitle(title);
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);
		game.start();
	}
}
