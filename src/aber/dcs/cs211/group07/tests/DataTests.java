package aber.dcs.cs211.group07.tests;

import java.sql.Time;

import org.junit.Test;

import aber.dcs.cs211.group07.data.Monster;
import aber.dcs.cs211.group07.data.Player;

public class DataTests {

	@Test
	public void testMonsterHealth() {
		Monster monster = new MonsterReference().getMonster();
		Time age = monster.getAge();
		int calculatedHealth;
		int health = monster.getHealth();
		//Calculate health
		assertEquals(calculatedHealth, health);
	}

	public void testMonsterStrength() {
		Monster monster = new MonsterReference().getMonster();
		Time age = monster.getAge();
		int calculatedHealth;
		int health = monster.getHealth();
		//Calculate strength
		assertEquals(calculatedHealth, health);
	}
	
	public void testMonsterToughness() {
		Monster monster = new MonsterReference().getMonster();
		Time age = monster.getAge();
		int calculatedHealth;
		int health = monster.getHealth();
		//Calculate toughness
		assertEquals(calculatedHealth, health);
	}
	
	public void testMonsterEvasion() {
		Monster monster = new MonsterReference().getMonster();
		Time age = monster.getAge();
		int calculatedHealth;
		int health = monster.getHealth();
		//Calculate evasion
		assertEquals(calculatedHealth, health);
	}
	
	public void testDebit(){
		Player player = new Player();
		int money = player.getMoney();
		player.debit(500);
		assertEquals(money + 500, player.getMoney());
	}
	
}
