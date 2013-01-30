package aber.dcs.cs211.group07.data;

import java.util.List;

import aber.dcs.cs211.group07.data.offers.Offer;
import aber.dcs.cs211.group07.data.requests.Request;

public class Player {

	public int id;
	public int serverID;
	public String email;
	public String password;
	public int money;
	
	/**
	 * Constructor for a player retrieved from database
	 * 
	 * @param id
	 * @param email
	 * @param password
	 * @param money
	 */
	public Player(int id, String email, String password, int money) {
		this.id = id;
	//	this.serverID = serverID;
		this.email = email;
		this.password = password;
		this.money = money;
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
	
	/**
	 * Used in JUnit testing
	 * 
	 * @return
	 */
	public int getPlayerID(){
		return id;
	}
	
	/**
	 * Used in JUnit testing
	 * 
	 * @return
	 */
	public String getPlayerEmail(){
		return email;
	}
	
	/**
	 * Used in JUnit testing
	 * 
	 * @return
	 */
	public String getPlayerPassword(){
		return password;
	}
	
	/**
	 * Used in JUnit testing
	 * 
	 * @return
	 */
	public int getPlayerMoney(){
		return money;
	}
	
}
