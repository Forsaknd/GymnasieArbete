package GymnasieArbete.entities.spawner;

import GymnasieArbete.entities.particle.BackgroundParticle;
import GymnasieArbete.graphics.Sprite;
import GymnasieArbete.level.Level;

public class BackgroundParticleSpawner extends Spawner {

	@SuppressWarnings("unused")
	private int life;

	public BackgroundParticleSpawner(int x, int y, int life, int amount,Sprite sprite , Level level) {
		super(x, y, Type.BACKGROUNDPARTICLE, amount, level);
		this.life = life;
		for (int i = 0; i < amount; i++) {
			level.add(new BackgroundParticle(x, y, life, sprite));
		}
		remove();
	}
}
