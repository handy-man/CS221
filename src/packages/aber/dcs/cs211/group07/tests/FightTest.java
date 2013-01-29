package aber.dcs.cs211.group07.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import aber.dcs.cs211.group07.data.Monster;

public class FightTest {

	private Monster monster1;
	private Monster monster2;
	
	@Before
	public void setup(){
		monster1 = new Monster(1);
		monster2 = new Monster(2);
	}
	
	@Test
	public void fightTest() {
		assertFalse("Shout be flase",monster1.getHealthLost() != 0.0 ||
				monster2.getHealthLost() != 0.0);
	}

}
