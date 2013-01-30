package aber.dcs.cs211.group07.tests;

import static org.junit.Assert.*;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import aber.dcs.cs211.group07.data.Monster;

public class MonsterTest {

	private Monster monsterDatabase;
	private Monster monsterRandom;
	private Monster monsterMother;
	private Monster monsterFather;
	private Monster child;
	
	@Before
	public void setup(){
		final Date date = new Date();
		monsterDatabase = new Monster(1, 1, "John", date, date,
				0.1, 0.0, 0.3, 0.5, 0.7, 1, 1);
		monsterRandom = new Monster(1);
		monsterMother = new Monster(1, 1, "Kate", date, date,
				0.1, 0.0, 0.5, 0.5, 0.5, 1, 1);
		monsterFather = new Monster(1,1,"John", date, date,
				0.1, 0.0, 1.0, 1.0, 1.0, 1, 1);
	}
	
	@Test
	public void testMonsterStrength() {
		double calculatedStrength = monsterDatabase.getGStrength() *
				( Math.exp(monsterDatabase.getAge().getTime() * monsterDatabase.getAgeRate()) -1) *
				( 2 - Math.exp(monsterDatabase.getAge().getTime() * monsterDatabase.getAgeRate()));
		assertTrue("Testing calculations for monster current strenght",
				calculatedStrength == monsterDatabase.getStrength());
	}
	
	@Test
	public void testMonsterToughness() {
		double calculatedToughness = monsterDatabase.getGToughness() *
				( Math.exp(monsterDatabase.getAge().getTime() * monsterDatabase.getAgeRate()) -1) *
				( 2 - Math.exp(monsterDatabase.getAge().getTime() * monsterDatabase.getAgeRate()));
		assertTrue("Testing calculations for monster current toughness",
				calculatedToughness == monsterDatabase.getToughness());
	}
	
	@Test
	public void testMonsterEvasion() {
		double calculatedEvasion = monsterDatabase.getGEvasion() *
				( Math.exp(monsterDatabase.getAge().getTime() * monsterDatabase.getAgeRate()) -1) *
				( 2 - Math.exp(monsterDatabase.getAge().getTime() * monsterDatabase.getAgeRate()));
		assertTrue("Testing calculations for monster current evasion",
				calculatedEvasion == monsterDatabase.getEvasion());
	}
	
	@Test
	public void monsterDatabaseTest() {
		assertEquals("Testing monsterDatabase's ID",monsterDatabase.getID(), 1);
		assertEquals("Testing monsterDatabase's owner ID",monsterDatabase.getOwnerID(), 1);
		assertEquals("Testing monsterDatabase's name",monsterDatabase.getName(),"John");
		assertTrue("Testing monsterDatabase's health lost",monsterDatabase.getHealthLost() == 0.0);
		assertTrue("Testing monsterDatabase's GStrength",monsterDatabase.getGStrength() == 0.3);
		assertTrue("Testing monsterDatabase's GToughness",monsterDatabase.getGToughness() == 0.5);
		assertTrue("Testing monsterDatabase's GEvasion",monsterDatabase.getGEvasion() == 0.7);
	}
	
	@Test
	public void monsterRandomTest(){
		assertEquals("Testing monsterRandom's owner ID",monsterRandom.getOwnerID(), 1);
		assertEquals("Testing the random name generator used in the random monster constructor",
				monsterRandom.getName().length(), 5);
		assertTrue("Testing monsterRandom's health lost",monsterRandom.getHealthLost() == 0.0);
		assertTrue("Testing monsterRandom's GStrength",monsterRandom.getGStrength() >= 0.0 && 
				monsterRandom.getGStrength() <= 1.0);
		assertTrue("Testing monsterRandom's GToughness",monsterRandom.getGToughness() >= 0.0 && 
				monsterRandom.getGToughness() <= 1.0);
		assertTrue("Testing monsterRandom's GEvasion",monsterRandom.getGEvasion() >= 0.0 && 
				monsterRandom.getGEvasion() <= 1.0);
	}
	
	@Test
	public void monsterBreedingTest(){
		child = new Monster(monsterMother, monsterFather, 1);
		assertEquals("Testing monsterBreeding's owner ID",child.getOwnerID(), 1);
		assertTrue("Testing monsterBreeding's GStrength",child.getGStrength() >= monsterMother.getGStrength() && 
				monsterRandom.getGStrength() <= monsterFather.getGStrength());
		assertTrue("Testing monsterBreeding's GToughness",child.getGToughness() >= monsterMother.getGToughness() && 
				monsterRandom.getGToughness() <= monsterFather.getGToughness());
		assertTrue("Testing monsterBreeding's GEvasion",child.getGEvasion() >= monsterMother.getGEvasion() && 
				monsterRandom.getGEvasion() <= monsterFather.getGEvasion());
	}

	@Test
	public void getCurrentHealthAndIncreaseHealthLostTest(){
		monsterRandom.increaseHealthLost(0.5);
		assertTrue("Testing current health of monsters calculation",monsterRandom.getHealthLost() == 0.5);
	}
	
}
