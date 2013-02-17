/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Monster_classes;

/**
 *
 * @author adriangawryszewski
 */

public class Player {

	public int id;
	public int serverID;
	public String email;
	public String password;
	public int money;

    public Player(int id, String email, String pass) {
        this.id=id;
        this.serverID=0;
        this.email=email;
        this.password=pass;
        this.money=500;
    }
	
/*	public List<Player> getFriends(){
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
	*/
	public void debit(int amount){
		
	}
	
	public void credit(int amount){
		
	}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getServerID() {
        return serverID;
    }

    public void setServerID(int serverID) {
        this.serverID = serverID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
	
}
