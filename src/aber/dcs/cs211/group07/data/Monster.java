package aber.dcs.cs211.group07.data;

import java.sql.Time;
import java.util.Date;
import java.util.Random;

public class Monster {

	public String name;
	
	public final Date birth;
	static final double AGE_RATE = 0.1;
	
	public double health;
	public double strength;
	public double toughness;
	public double evasion;
	
	public double health_lost = 0;
	
	public int id;
	public int ownerID;

	// monster constructor from database
	public Monster(int id,int ownerID,String name,Date date,
			double health_lost,double base_health,double g_strength,
			double g_toughness,double g_evasion) {
		
		this.id=id;
		this.ownerID=ownerID;
		this.name=name;
		this.birth=date;
		this.health_lost=health_lost;
		this.health=base_health;
		this.strength=g_strength;
		this.toughness=g_toughness;
		this.evasion=g_evasion;
		
	}
	
	//constructor for random monster
	public Monster(int ownerID){
		//get name of monster from somewhere
		this.birth = new Date();
		Random randomHealth = new Random();
		Random randomStrength = new Random();
		Random randomToughness = new Random();
		Random randomEvasion = new Random();
		// is health random or calculated from toughness ?
		this.health = randomHealth.nextDouble();
		this.strength = randomStrength.nextDouble();
		this.toughness = randomToughness.nextDouble();
		this.evasion = randomEvasion.nextDouble();
		
		this.ownerID=ownerID;
	}
	
	public Monster(String name, Date birth){
		this.name = name;
		this.birth = birth == null ? birth : new Date();
	}
	
	//constructor for breeding monster
	public Monster(String name, Date birth, Monster mother, Monster father,int newOwnerID) {
		this(name, birth);		
		this.health   = random(mother.health, father.health);
		this.strength = random(mother.strength, father.strength);
		this.toughness   = random(mother.toughness, father.toughness);
		this.evasion    = random(mother.evasion, father.evasion);
		
		this.ownerID=newOwnerID;
	}
	
	private double random(double mother, double father) {
		Random r = new Random();
		if (r.nextDouble() > 0.05) {
			return r.nextDouble() > 0.5 ? mother : father;
		} else {
			return r.nextDouble();
		}
	}

	private double expAge() { return Math.exp(this.getAge().getTime() * Monster.AGE_RATE); }
	private double expValue(double value) { return value * (expAge()-1) * (2-expAge()); }
	
	public double getHealth()   { return 2 - expAge(); }
	public double getStrength() { return expValue(this.strength); }
	public double getToughness()   { return expValue(this.toughness); }
	public double getEvasion()    { return expValue(this.evasion); }
	
	public double getCurrentHealth(){
		return this.getHealth() - this.health_lost;
	}
	
	public Time getAge(){
		return new Time(new Date().getTime() - this.birth.getTime());
	}

}
