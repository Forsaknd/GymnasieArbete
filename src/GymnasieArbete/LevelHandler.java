package GymnasieArbete;

import java.util.HashMap;

import GymnasieArbete.entities.mob.Player;
import GymnasieArbete.level.Level;
import GymnasieArbete.util.Vector2i;

public class LevelHandler {
	private HashMap<String, Level> levelMap = new HashMap<String, Level>();
	private Level currentLevel;

	public void addLevel(Level level) {
		levelMap.put(level.getName(), level);
		if (currentLevel == null) currentLevel = level;
	}

	public Level getLevel(String name) {
		return levelMap.get(name);
	}

	public Level getCurrentLevel() {
		return currentLevel;
	}

	public void nextLevel(Player player, Level level) {
		String nextLevel = level.getNextLevel();
		gotoLevel(player, nextLevel);
	}

	public void gotoLevel(Player player, String levelName) {
		Level level = getLevel(levelName);
		currentLevel = level;
		Vector2i playerSpawn = level.getSpawnLoc();
		player.setPos(playerSpawn.getX(), playerSpawn.getY());
		if (level.getPlayer() == null) level.add(player);
		player.init(level);

		// do other initialization stuff to the level here
	}
}
