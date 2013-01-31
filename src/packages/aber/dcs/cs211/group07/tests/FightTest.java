package aber.dcs.cs211.group07.tests;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import aber.dcs.cs211.group07.actions.Fight;
import aber.dcs.cs211.group07.data.Monster;
import aber.dcs.cs211.group07.data.Player;

public class FightTest {
	final Player player = new Player(1, 0, "TestPlayer", "testpassword", 0);
	
	@Test
	public void monsterPowerCalculationTest(){
		Monster m = new Monster(1, player.id, "John", new Date(), new Date(), 0.1, 0.0, 1.0, 1.0, 0, 0);
		double monsterPower = Fight.monsterPower(m);
		assertTrue("The monster power is in within bounds", monsterPower >= 0.0 && monsterPower <= 3.0 );
	}
	
	@Test
	public void fightTest() {
		Monster monster1 = new Monster(player.id);
		Monster monster2 = new Monster(player.id);
		
		assertTrue("Both monsters are on full health", monster1.health_lost == 0.0 && monster2.health_lost == 0.0);
		
		Fight.fight(monster1, monster2);
		
		assertTrue("One of the monsters is dead", monster1.health_lost == 1.0 || monster2.health_lost == 1.0);
	}

}
