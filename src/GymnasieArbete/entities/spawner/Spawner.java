package GymnasieArbete.entities.spawner;

import GymnasieArbete.entities.Entity;
import GymnasieArbete.level.Level;

public class Spawner extends Entity {

	public enum Type {
		MOB, PARTICLE, BACKGROUNDPARTICLE;
	}

	@SuppressWarnings("unused")
	private Type type;

	public Spawner(int x, int y, Type type, int amount, Level level) {
		init(level);
		this.x = x;
		this.y = y;
		this.type = type;
		remove();
	}

}
