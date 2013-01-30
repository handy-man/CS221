package aber.dcs.cs211.group07.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import aber.dcs.cs211.group07.data.Player;

public class PlayerTest {
	Player player;
	
	@Test
	public void unsavedPlayerTest() {
		player = new Player("testplayer", "2345", 500);
		assertEquals(player.email, "testplayer");
		assertEquals(player.password,"2345");
		assertEquals(player.money, 500);
	}
	
	@Test
	public void localPlayerTest() {
		player = new Player(1, "testplayer", "2345", 500);
		assertEquals(player.id, 1);
	}
	
	@Test
	public void remotePlayerTest() {
		player = new Player(1, 1, "testplayer", "2345", 500);
		assertEquals(player.server, 1);
	}

}
