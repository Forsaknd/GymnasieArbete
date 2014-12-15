package GymnasieArbete.entities.spawner;

import GymnasieArbete.entities.particle.Particle;
import GymnasieArbete.graphics.Sprite;
import GymnasieArbete.level.Level;

public class ParticleSpawner extends Spawner {

	private int life;

	public ParticleSpawner(int x, int y, int life, int amount,Sprite sprite , Level level) {
		super(x, y, Type.PARTICLE, amount, level);
		this.life = life;
		for (int i = 0; i < amount; i++) {
			level.add(new Particle(x, y, life, sprite));
		}
	}
}
