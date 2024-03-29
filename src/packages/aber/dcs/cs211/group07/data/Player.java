package aber.dcs.cs211.group07.data;

import java.util.List;

import aber.dcs.cs211.group07.data.offers.Offer;
import aber.dcs.cs211.group07.data.requests.Request;

public class Player {

	/**
	 * The database ID for the player
	 */
	public int id;
	
	/**
	 * The server the Player is from
	 * 0 means the local server
	 */
	public int server;
	
	public String email;
	public String password;
	public int money;
	
	/**
	 * The basic constructor for a Player
	 */
	public Player(String email, String password, int money) {
		this.email = email;
		this.password = password;
		this.money = money;
	}
	
	/**
	 * Full constructor for a player
	 */
	public Player(int id, int server, String email, String password, int money) {
		this(email, password, money);
		this.id = id;
		this.server = server;
	}
	
	/**
	 * Constructor for a local player
	 */
	public Player(int id, String email, String password, int money) {
		this.email = email;
		this.password = password;
		this.money = money;
		this.id = id;
		this.server = 0;
	}

	public List<Player> getFriends(){
		return null;
	}
	
	public List<Offer> getOffers(){
		return null;
	}
	
	public List<Request> getRequests(){
		return null;
	}
	
	public List<Monster> getMonsters(){
		return null;
	}
}
