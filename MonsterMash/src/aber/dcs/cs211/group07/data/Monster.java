package aber.dcs.cs211.group07.data;

import java.sql.Time;
import java.util.Date;

public class Monster {

	public Player player;
	public String name;
	public final Date birth;
	public final boolean gender;
	public int health_lost;
	public Time age_rate;
	
	public Monster(Date d,boolean g){
		this.birth = d;
		this.gender = g;
	}
	
	public int getHealth(){
		return 0;
	}
	
	public int getStrength(){
		return 0;
	}
	
	public int getToughness(){
		return 0;
	}
	
	public int getEvasion(){
		return 0;
	}
	
	public Time getAge(){
		return null;
	}
	
}
