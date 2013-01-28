package aber.dcs.cs211.group07.data;

import java.sql.Time;
import java.util.Date;
import java.util.Random;

public class Monster {

	public Player player;
	public String name;
	
	public final Date birth;
	static final double AGE_RATE = 0.1;
	
	private double health;
	private double strength;
	private double speed;
	private double accuracy;
	private double armour;
	private double dodge;
	
	public double health_lost = 0;

	public Monster(String name, Date birth){
		this.name = name;
		this.birth = birth == null ? birth : new Date();
	}
	
	public Monster(String name, Date birth, Monster mother, Monster father) {
		this(name, birth);		
		this.health   = random(mother.health, father.health);
		this.strength = random(mother.strength, father.strength);
		this.speed    = random(mother.speed, father.speed);
		this.accuracy = random(mother.accuracy, father.accuracy);
		this.armour   = random(mother.armour, father.armour);
		this.dodge    = random(mother.dodge, father.dodge);
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
	public double getSpeed()    { return expValue(this.speed); }
	public double getAccuracy() { return expValue(this.accuracy); }
	public double getArmour()   { return expValue(this.armour); }
	public double getDodge()    { return expValue(this.dodge); }
	
	public double getCurrentHealth(){
		return this.getHealth() - this.health_lost;
	}
	
	public Time getAge(){
		return new Time(new Date().getTime() - this.birth.getTime());
	}

}
