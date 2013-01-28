package aber.dcs.cs211.group07.tests;

import static org.junit.Assert.*;

import java.sql.Time;

import org.junit.Test;

import aber.dcs.cs211.group07.data.Monster;

public class DataTests {

	private static final double DELTA = 1e-10;
	
	@Test
	public void testMonsterHealth() {
		Monster monster = new Monster();
		Time age = monster.getAge();
		double calculatedHealth = 0;
		double health = monster.getHealth();
		//Calculate health
		assertEquals(calculatedHealth, health, DELTA) ;
	}

	@Test
	public void testMonsterStrength() {
		Monster monster = new Monster();
		Time age = monster.getAge();
		double calculatedStrength = 0;
		double health = monster.getHealth();
		//Calculate strength
		assertEquals(calculatedStrength, health, DELTA);
	}
	
	@Test
	public void testMonsterToughness() {
		Monster monster = new Monster();
		Time age = monster.getAge();
		double calculatedHealth = 0;
		double health = monster.getHealth();
		//Calculate toughness
		assertEquals(calculatedHealth, health, DELTA);
	}
	
	@Test
	public void testMonsterEvasion() {
		Monster monster = new Monster();
		Time age = monster.getAge();
		double calculatedEvasion = 0;
		double health = monster.getHealth();
		assertEquals(calculatedEvasion, health, DELTA);
	}
	
}
