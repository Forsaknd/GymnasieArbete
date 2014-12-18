package GymnasieArbete.entities.mob;

import GymnasieArbete.Game;
import GymnasieArbete.entities.items.Inventory;
import GymnasieArbete.entities.items.Item;
import GymnasieArbete.entities.projectile.Projectile;
import GymnasieArbete.entities.spawner.BackgroundParticleSpawner;
import GymnasieArbete.entities.spawner.ParticleSpawner;
import GymnasieArbete.graphics.AnimatedSprite;
import GymnasieArbete.graphics.Screen;
import GymnasieArbete.graphics.Sprite;
import GymnasieArbete.graphics.SpriteSheet;
import GymnasieArbete.input.Keyboard;
import GymnasieArbete.input.Mouse;

public class Player extends Mob {

	private Inventory inventory = new Inventory();
	private Item equipped = new Item();
	private Keyboard input;
	private Sprite sprite;
	private boolean dead = false;
	private boolean shooting = false;
	protected boolean walking = false;
	protected boolean canShoot = false;
	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.player_down, 32, 32, 3, 10);
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.player_up, 32, 32, 3, 10);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.player_left, 32, 32, 5, 10);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.player_right, 32, 32, 5, 10);

	private AnimatedSprite animSprite = down;

	private int fireRate = 20;

	private double speed = 1;

	public Player(Keyboard input) {
		this.input = input;
		sprite = Sprite.player;
		animSprite = down;
	}

	public Player(int x, int y, Keyboard input) {
		this.x = x;
		this.y = y;
		this.input = input;
		sprite = Sprite.player;
		dead = false;
	}

	public void update() {
		if (!dead) {
			if (walking) animSprite.update();
			else animSprite.setFrame(0);
			if (fireRate > 0) fireRate--;
			if (input.esc) Game.state = Game.STATE.PAUSED;

			double xa = 0, ya = 0;
			if (input.up) {
				if (!shooting) {
					animSprite = up;
				}
				ya -= speed;
			} else if (input.down) {
				if (!shooting) {
					animSprite = down;
				}
				ya += speed;
			}
			if (input.left) {
				if (!shooting) {
					updateShooting();
					animSprite = left;
				}
				xa -= speed;
			} else if (input.right) {
				if (!shooting) {
					updateShooting();
					animSprite = right;
				}
				xa += speed;
			}
			if (input.space) {
				Item item = level.getItemCol(this, 16);
				if (item != null) {
					inventory.addItem(item);
					item.setOwner(this);
					item.remove();
				}
			}
			if (input.g) {
				if (equipped != null && equipped.type != Item.Type.EMPTY) {
					inventory.removeItem(equipped);
					equipped.setPos((int) x, (int) y);
					level.add(equipped);
					equipped = new Item();
					canShoot = false;
					up = new AnimatedSprite(SpriteSheet.player_up, 32, 32, 3, 10);
					down = new AnimatedSprite(SpriteSheet.player_down, 32, 32, 3, 10);
					left = new AnimatedSprite(SpriteSheet.player_left, 32, 32, 5, 10);
					right = new AnimatedSprite(SpriteSheet.player_right, 32, 32, 5, 10);
					if (dir == Direction.UP) {
						animSprite = up;
					}
					if (dir == Direction.DOWN) {
						animSprite = down;
					}
					if (dir == Direction.LEFT) {
						animSprite = left;
					}
					if (dir == Direction.RIGHT) {
						animSprite = right;
					}
				}
			}
			for (int i = 0; i < 10; i++) {
				if (input.numbers[i]) {
					if (inventory.getItems().size() > i) {
						Item current = inventory.getItem(i);
						if (current.type == Item.Type.WEAPON) {

							equipped = current;
							canShoot = true;
							fireRate = equipped.getFireRate();
							up = equipped.getAnimatedSpriteUp();
							down = equipped.getAnimatedSpriteDown();
							left = equipped.getAnimatedSpriteLeft();
							right = equipped.getAnimatedSpriteRight();

							if (dir == Direction.UP) {
								animSprite = up;
							}
							if (dir == Direction.DOWN) {
								animSprite = down;
							}
							if (dir == Direction.LEFT) {
								animSprite = left;
							}
							if (dir == Direction.RIGHT) {
								animSprite = right;
							}
						} else if (current.type == Item.Type.CONSUMABLE && hp.getHealth() < hp.getMaxHealth()) {
							current.use();
							inventory.removeItem(current);
						}
					}
				}
			}

			if (xa != 0 || ya != 0) {
				move(xa, ya);
				walking = true;
			} else {
				walking = false;
			}

			clear();
			updateShooting();
		}
	}

	private void clear() {
		for (int i = 0; i < level.getProjectiles().size(); i++) {
			Projectile p = level.getProjectiles().get(i);
			if (p.isRemoved()) level.getProjectiles().remove(i);
		}
	}

	private void updateShooting() {
		if (Mouse.getB() == 1) shooting = true;
		if (Mouse.getB() == -1) shooting = false;
		if (Mouse.getB() == 1 && fireRate == 0 && canShoot == true) {
			shooting = true;
			double dx = Mouse.getX() - Game.getWindowWidth() / 2;
			double dy = Mouse.getY() - Game.getWindowHeight() / 2;
			double mdir = Math.atan2(dy, dx);

			shoot(equipped, x, y, mdir);
			fireRate = equipped.getFireRate();

			int ddir = (int) Math.toDegrees(mdir);
			if (ddir < -45 && ddir > -135) {
				animSprite = up;
			}
			if (ddir < 45 && ddir > -45) {
				animSprite = right;
			}
			if (ddir <= 135 && ddir >= 45) {
				animSprite = down;
			}
			if (ddir < -135 && ddir > -180 || ddir <= 180 && ddir > 135) {
				animSprite = left;
			}
		}
	}

	public void takeDamage(int damage, int particleamount) {
		level.add(new ParticleSpawner((int) x, (int) y, 20, particleamount, Sprite.particle_blood, level));
		hp.setHealth(hp.getHealth() - damage);
		if (hp.getHealth() <= 0) {
			level.add(new ParticleSpawner((int) x, (int) y, 40, particleamount * 4, Sprite.particle_blood, level));
			level.add(new BackgroundParticleSpawner((int) x + 4, (int) y - 4, 1000, 3, particleamount * 8, Sprite.particle_blood, level));
			level.add(new BackgroundParticleSpawner((int) x - 4, (int) y + 4, 1000, 3, particleamount * 8, Sprite.particle_blood, level));
			dead = true;
		} else dead = false;
	}

	public void setCanShoot(boolean canShoot) {
		this.canShoot = canShoot;
	}

	public void render(Screen screen) {
		if (!dead) {
			sprite = animSprite.getSprite();
			screen.renderMob((int) (x - 16), (int) (y - 16), sprite);
		}
	}

	protected boolean collision(double xa, double ya) {
		boolean solid = false;
		for (int c = 0; c < 4; c++) {
			double xt = ((x + xa) - c % 2 * 5 - 5) / 16;
			double yt = ((y + ya) - c / 2 * 15 + 15) / 16;
			int ix = (int) Math.ceil(xt);
			int iy = (int) Math.ceil(yt);
			if (c % 2 == 0) ix = (int) Math.floor(xt);
			if (c / 2 == 0) iy = (int) Math.floor(yt);
			if (level.getTile(ix, iy).solid()) solid = true;
		}
		return solid;
	}

	public void dropItem(Item item) {

	}

	public void setEquipped(Item item) {
		equipped = item;
	}

	public Item getEquipped() {
		return equipped;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public Health getHealth() {
		return hp;
	}

	public boolean isDead() {
		return dead;
	}

}
