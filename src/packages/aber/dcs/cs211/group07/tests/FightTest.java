package aber.dcs.cs211.group07.tests;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import aber.dcs.cs211.group07.data.Fight;
import aber.dcs.cs211.group07.data.Monster;

public class FightTest {

	private Monster monster1;
	private Monster monster2;
	private Monster monster3;
	private Fight fight;
	
	@Before
	public void setup(){
		final Date date = new Date();
		monster1 = new Monster(1);
		monster2 = new Monster(2);
		monster3 = new Monster(1,1,"John", date, date,
				0.1, 0.0, 1.0, 1.0, 1.0, 1, 1);
		fight = new Fight();
	}
	
	@Test
	public void monsterPowerCalculationTest(){
		assertTrue("Testing whether the monster power calculation is working correct",
				fight.calculateMonsterPower(monster3) >= 0.0 &&
				fight.calculateMonsterPower(monster3) <= 3.0 );
	}
	
	@Test
	public void fightTest() {
		assertTrue("Testing weather both monsters are on full health",
				monster1.getHealthLost() == 0.0 &&
				monster2.getHealthLost() == 0.0);
		fight.fight(monster1, monster2);
		assertTrue("Testing weather one of the monsters have lost health or died",
				monster1.getHealthLost() != 0.0 ||
				monster2.getHealthLost() != 0.0);
	}

}
