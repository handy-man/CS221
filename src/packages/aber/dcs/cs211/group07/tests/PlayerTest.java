package aber.dcs.cs211.group07.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import aber.dcs.cs211.group07.data.Player;

public class PlayerTest {

	private Player player;
	
	@Before
	public void setup(){
		player = new Player(1,"svk2","2345",500);
	}
	
	@Test
	public void playerFromDatabaseTest() {
		assertEquals("Testing player's ID",player.getPlayerID(), 1);
		assertEquals("Testing player's email",player.getPlayerEmail(), "svk2");
		assertEquals("Testing palyer's password",player.getPlayerPassword(),"2345");
		assertEquals("Testing palyer's money",player.getPlayerMoney(),500);
	}

}
