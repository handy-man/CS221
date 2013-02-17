/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author adriangawryszewski
 */
public class Player {

    public String email;
    public String password;
    public int money;
	public int id;

    public Player(String email, String pass) {
        this.email = email;
        this.password = pass;
        this.money = 500;
    }

    public Player(String email, String pass, int money) {
        this.email = email;
        this.password = pass;
        this.money = money;
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
    public void debit(int amount) {
    }

    public void credit(int amount) {
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

	public void getId() {
		this.id = id;
	}
}
