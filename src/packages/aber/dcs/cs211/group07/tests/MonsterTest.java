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
		monsterDatabase = new Monster(1,1,"John", date,
				0.0, 0.3, 0.5, 0.7);
		monsterRandom = new Monster(1);
		monsterMother = new Monster(1,1,"Kate", date,
				0.0, 0.5, 0.5, 0.5);
		monsterFather = new Monster(1,1,"John", date,
				0.0, 1.0, 1.0, 1.0);
	}
	
	@Test
	public void monsterDatabaseTest() {
		assertTrue("Shout be true",monsterDatabase.getID() == 1);
		assertTrue("Shout be true",monsterDatabase.getOwnerID() == 1);
		assertTrue("Should be equal",monsterDatabase.getName().equals("John"));
		assertTrue("Shout be true",monsterDatabase.getHealthLost() == 0.0);
		assertTrue("Shout be true",monsterDatabase.getGStrength() == 0.3);
		assertTrue("Shout be true",monsterDatabase.getGToughness() == 0.5);
		assertTrue("Shout be true",monsterDatabase.getGEvasion() == 0.7);
	}
	
	@Test
	public void monsterRandomTest(){
		assertTrue("Shout be true",monsterRandom.getOwnerID() == 1);
		assertTrue("Shout be true",monsterRandom.getName().length() == 5);
		assertTrue("Shout be true",monsterRandom.getHealthLost() == 0.0);
		assertTrue("Shout be true",monsterRandom.getGStrength() >= 0.0 && 
				monsterRandom.getGStrength() <= 1.0);
		assertTrue("Shout be true",monsterRandom.getGToughness() >= 0.0 && 
				monsterRandom.getGToughness() <= 1.0);
		assertTrue("Shout be true",monsterRandom.getGEvasion() >= 0.0 && 
				monsterRandom.getGEvasion() <= 1.0);
	}
	
	@Test
	public void monsterBreedingTest(){
		child = new Monster(monsterMother, monsterFather, 1);
		assertTrue("Shout be true",child.getOwnerID() == 1);
		assertTrue("Shout be true",child.getGStrength() >= 0.5 && 
				monsterRandom.getGStrength() <= 1.0);
		assertTrue("Shout be true",child.getGToughness() >= 0.5 && 
				monsterRandom.getGToughness() <= 1.0);
		assertTrue("Shout be true",child.getGEvasion() >= 0.5 && 
				monsterRandom.getGEvasion() <= 1.0);
	}

	@Test
	public void getCurrentHealthAndIncreaseHealthLostTest(){
		monsterRandom.increaseHealthLost(0.5);
		assertTrue("Shout be true",monsterRandom.getHealthLost() == 0.5);
	}
	
}
