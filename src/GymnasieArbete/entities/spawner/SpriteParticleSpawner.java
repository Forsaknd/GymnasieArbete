package GymnasieArbete.entities.spawner;

import GymnasieArbete.entities.particle.SpriteParticle;
import GymnasieArbete.graphics.Sprite;
import GymnasieArbete.level.Level;

public class SpriteParticleSpawner extends Spawner {

	@SuppressWarnings("unused")
	private int life;

	public SpriteParticleSpawner(int x, int y, int life, int amount,Sprite sprite , Level level) {
		super(x, y, Type.SPRITEPARTICLE, amount, level);
		this.life = life;
		for (int i = 0; i < amount; i++) {
			level.add(new SpriteParticle(x, y, life, sprite));
		}
		remove();
	}
}
