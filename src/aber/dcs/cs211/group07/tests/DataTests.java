package aber.dcs.cs211.group07.tests;

import static org.junit.Assert.*;

import java.sql.Time;

import org.junit.Test;

import aber.dcs.cs211.group07.data.Monster;
import aber.dcs.cs211.group07.data.Player;

public class DataTests {

	private static final double DELTA = 1e-10;
	
	@Test
	public void testMonsterHealth() {
		Monster monster = new Monster();
		Time age = monster.getAge();
		double calculatedHealth;
		double health = monster.getHealth();
		//Calculate health
		assertEquals(calculatedHealth, health, DELTA) ;
	}

	@Test
	public void testMonsterStrength() {
		Monster monster = new Monster();
		Time age = monster.getAge();
		double calculatedHealth;
		double health = monster.getHealth();
		//Calculate strength
		assertEquals(calculatedHealth, health, DELTA);
	}
	
	@Test
	public void testMonsterToughness() {
		Monster monster = new Monster();
		Time age = monster.getAge();
		double calculatedHealth;
		double health = monster.getHealth();
		//Calculate toughness
		assertEquals(calculatedHealth, health, DELTA);
	}
	
	@Test
	public void testMonsterEvasion() {
		Monster monster = new Monster();
		Time age = monster.getAge();
		double calculatedHealth;
		double health = monster.getHealth();
		//Calculate evasion
		assertEquals(calculatedHealth, health, DELTA);
	}
	
	@Test
	public void testDebit(){
		Player player = new Player();
		int money = player.getMoney();
		player.debit(500);
		assertEquals(money - 500, player.getMoney());
	}
	
	@Test
	public void testDebitFalse(){
		Player player = new Player();
		int money = player.getMoney();
		player.debit(400);
		assertFalse(money - 500 == player.getMoney());
	}
	
	@Test
	public void testCredit(){
		Player player = new Player();
		int money = player.getMoney();
		player.credit(500);
		assertEquals(money + 500, player.getMoney());
	}
	
	@Test
	public void testCreditFalse(){
		Player player = new Player();
		int money = player.getMoney();
		player.credit(400);
		assertFalse(money + 500 == player.getMoney());
	}
	
	
}
