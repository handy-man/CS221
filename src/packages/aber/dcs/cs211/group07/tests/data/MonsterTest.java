package aber.dcs.cs211.group07.tests.data;


import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import aber.dcs.cs211.group07.data.Monster;
import aber.dcs.cs211.group07.data.Player;

public class MonsterTest {
	private Player owner;
	private Monster monster;

	@Before
	public void setupClass(){
		final Date date = new Date();
		
		owner = new Player(1, 0, "player@example.com", "password", 0);
		monster = new Monster(1, owner.id, "John", date, 
				0.1, 0.0, 0.3, 0.5, 1, 1);
	}
	
	@Test
	public void testMonsterStrength() {
		double calculatedStrength = monster.strength *
				( Math.exp(monster.getAge().getTime() * monster.age_rate) -1) *
				( 2 - Math.exp(monster.getAge().getTime() * monster.age_rate));
		
		assertTrue("Monster has correct strength", calculatedStrength == monster.getStrength());
	}
	
	@Test
	public void testMonsterToughness() {
		double calculatedToughness = monster.toughness *
				( Math.exp(monster.getAge().getTime() * monster.age_rate) -1) *
				( 2 - Math.exp(monster.getAge().getTime() * monster.age_rate));
		
		assertTrue("Monster has correct toughness", calculatedToughness == monster.getToughness());
	}
	
	@Test
	public void testMonsterEvasion() {
		double calculatedEvasion = monster.evasion *
				( Math.exp(monster.getAge().getTime() * monster.age_rate) -1) *
				( 2 - Math.exp(monster.getAge().getTime() * monster.age_rate));
		
		assertTrue("Monster has correct evasion", calculatedEvasion == monster.getEvasion());
	}
	
	@Test
	public void getCurrentHealthAndIncreaseHealthLostTest(){
		monster.increaseHealthLost(0.5);
		assertTrue("Monster has lost health", monster.health_lost == 0.5);
		
		monster.increaseHealthLost(1.0);
		assertTrue("Monster has lost all health and stopped at 1.0", monster.health_lost == 1.0);
	}
	
	/**
	 * Test that the monster has been constructed with the given values
	 */
	@Test
	public void monsterDatabaseTest() {
		assertTrue(monster.id == 1);
		assertTrue(monster.ownerID == 1);
		assertTrue(monster.name.equals("John"));
		assertTrue(monster.health_lost == 0.0);
		assertTrue(monster.strength == 0.3);
		assertTrue(monster.toughness == 0.5);
	}
	
	/**
	 * Test that randomly generating a monster works correctly
	 */
	@Test
	public void monsterRandomTest(){
		Monster randomMonster = new Monster(owner.id);
		
		assertTrue("Monster has correct owner", randomMonster.ownerID == 1);
		assertTrue("Monster has a randomly generated name", randomMonster.name.length() == 5);
		assertTrue("Monster will die in the future", randomMonster.death_date.before(randomMonster.birth_date));
		assertTrue("Monster has an age rate", 0.0 <= randomMonster.age_rate && randomMonster.age_rate <= 1.0);
		assertTrue("Monster has a strength attribute", 0.0 <= randomMonster.strength && randomMonster.strength <= 1.0);
		assertTrue("Monster has a toughness attribute", 0.0 <= randomMonster.toughness && randomMonster.toughness <= 1.0);
		assertTrue("Monster has a evasion attribute", 0.0 <= randomMonster.evasion && randomMonster.evasion <= 1.0);
	}
	
	/**
	 * Test that breeding works correctly
	 */
	@Test
	public void monsterBreedingTest(){
		Date birth_date = new Date();
		
		Monster monsterMother = new Monster(1, owner.id, "Kate", birth_date, 0.1, 0.0, 0.5, 0.5, 1, 1);
		Monster monsterFather = new Monster(1, owner.id, "John", birth_date, 0.1, 0.0, 1.0, 1.0, 1, 1);
		Monster child = new Monster(owner.id, monsterMother, monsterFather);
		
		assertTrue("Child has correct owner", child.ownerID == 1);
		assertTrue("Child has a randomly generated name", child.name.length() == 5);
		assertTrue("Child will die in the future", child.death_date.before(child.birth_date));
		assertTrue("Child has an age rate", 0.0 <= child.age_rate && child.age_rate <= 1.0);
		assertTrue("Child has a strength attribute", 0.0 <= child.strength && child.strength <= 1.0);
		assertTrue("Child has a toughness attribute", 0.0 <= child.toughness && child.toughness <= 1.0);
		assertTrue("Child has a evasion attribute", 0.0 <= child.evasion && child.evasion <= 1.0);
	}	
}
