package aber.dcs.cs211.group07.tests;

import static org.junit.Assert.*;

import java.sql.Time;

import org.junit.Test;

import aber.dcs.cs211.group07.data.Monster;

public class MonsterTests {

	private static final double DELTA = 1e-10;
	
	@Test
	public void testMonsterHealth() {
		Monster monster = new Monster(0);
		Time age = monster.getAge();
		double health = monster.getHealth();
		double calculatedHealth = 0;
		calculatedHealth = 2 - Math.exp( (age.getTime() * Monster.AGE_RATE) );
		assertEquals(calculatedHealth, health, DELTA) ;
	}

	@Test
	public void testMonsterStrength() {
		Monster monster = new Monster(0);
		Time age = monster.getAge();
		double calculatedStrength = 0;
		double geneticStrength = monster.strength;
		double strength = monster.getStrength();
		double ageRate = Monster.AGE_RATE;
		calculatedStrength = geneticStrength * ( Math.exp(age.getTime() * ageRate) ) * ( 2 - Math.exp(age.getTime() * ageRate));
		assertEquals(calculatedStrength, strength, DELTA);
	}
	
	@Test
	public void testMonsterToughness() {
		Monster monster = new Monster(0);
		Time age = monster.getAge();
		double calculatedToughness = 0;
		double geneticToughness = monster.strength;
		double toughness = monster.getStrength();
		double ageRate = Monster.AGE_RATE;
		calculatedToughness = geneticToughness * ( Math.exp(age.getTime() * ageRate) ) * ( 2 - Math.exp(age.getTime() * ageRate));
		assertEquals(calculatedToughness, toughness, DELTA);
	}
	
	/*@Test
	public void testMonsterEvasion() {
		Monster monster = new Monster(0);
		Time age = monster.getAge();
		double calculatedEvasion = 0;
		double health = monster.getHealth();
		assertEquals(calculatedEvasion, health, DELTA);
	}
	*/
}
