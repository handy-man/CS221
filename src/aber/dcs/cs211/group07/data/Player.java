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
	
	public Player(int id, int serverID, String email, String password, int money) {
		//constructor for a player retrieved from database
		this.id = id;
		this.serverID = serverID;
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
	
	public void debit(int amount){
		money = money - amount;
	}
	
	public void credit(int amount){
		money = money + amount;
	}

	public int getMoney() {
		return money;
	}
	
}
