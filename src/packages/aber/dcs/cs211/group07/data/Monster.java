package aber.dcs.cs211.group07.data;

import java.sql.Time;
import java.util.Date;
import java.util.Random;

public class Monster {

	public String name;
	
	public Date birth;
	public Date death_date;
	
	public double health = 1.0;
	
	public double age_rate;
	public double strength;
	public double toughness;
	public double evasion;
	
	public double health_lost;
	
	public int id;
	public int ownerID;
	
	public int breed_offer;
	public int sale_offer;

	// monster constructor from database
	public Monster(int id,int ownerID,String name,Date date,Date death_date,
			double age_rate,double health_lost,double base_health,double g_strength,
			double g_toughness,int breed_offer,int sale_offer) {
		
		this.id=id;
		this.ownerID=ownerID;
		this.name=name;
		this.birth=date;
		this.death_date=death_date;
		this.age_rate=age_rate;
		this.health_lost=health_lost;
		this.strength=g_strength;
		this.toughness=g_toughness;
		//this.evasion=g_evasion;
		this.breed_offer=breed_offer;
		this.sale_offer=breed_offer;
		
	}
	
	//constructor for random monster
	public Monster(int ownerID){
		this.ownerID = ownerID;
		this.name = generateName();
		this.birth = new Date();
		Random randomStrength = new Random();
		Random randomToughness = new Random();
		Random randomEvasion = new Random();
		this.strength = randomStrength.nextDouble();
		this.toughness = randomToughness.nextDouble();
		this.evasion = randomEvasion.nextDouble();		
		this.age_rate=generateAgeRate();
	}
	
	public Monster(String name, Date birth){
		this.name = name;
		this.birth = birth;;
	}
	
	//constructor for breeding monster
	public Monster(String name, Date birth, Monster mother, Monster father,int newOwnerID) {
		this(name, birth);
		this.strength = random(mother.strength, father.strength);
		this.toughness   = random(mother.toughness, father.toughness);
		this.evasion    = random(mother.evasion, father.evasion);
		this.age_rate = random(mother.age_rate,father.age_rate);
		
		this.ownerID=newOwnerID;
		
		this.name = generateName();

	}
	
	private double random(double mother, double father) {
		Random r = new Random();
		if (r.nextDouble() > 0.05) {
			return r.nextDouble() > 0.5 ? mother : father;
		} else {
			return r.nextDouble();
		}
	}

	private double expAge() { return Math.exp(this.getAge().getTime() * this.age_rate); }
	private double expValue(double value) { return value * (expAge()-1) * (2-expAge()); }
	
	public double getHealth()   { return 2 - expAge(); }
	public double getStrength() { return expValue(this.strength); }
	public double getToughness()   { return expValue(this.toughness); }
	public double getEvasion()    { return expValue(this.evasion); }
	
	public double getCurrentHealth(){
		return this.getHealth() - this.health_lost;
	}
	
	public void increaseHealthLost(double amount){
		this.health_lost = this.health_lost + amount;
		if(this.health_lost >= 1.0){
			this.health_lost = 1.0;
		}
	}
	
	public Time getAge(){
		return new Time(new Date().getTime() - this.birth.getTime());
	}
	
	/**
	 * Gives a random double between 00.5 and 0.1
	 * 
	 * @return double that becomes the age rate of the monster
	 */
	public double generateAgeRate() {
		double randomInt = randomInt(5,10);
		double age_rate = randomInt/100;
		return age_rate;
		
	}
	
	/**
	 * Calculates the death due to old age when a monster is born
	 * Currently calculating in days
	 * 
	 * @return a date which the monster will die
	 */
	public Date calculateDeath() {
		
		long timeTillDeath = (long) Math.round(Math.log(2) / age_rate) * 86400000;
		Date deathDate = new Date((birth.getTime()+ timeTillDeath));
		return deathDate;
	}
	
	/**
	 * Generates a random 5 letter name 
	 * 
	 * @return a 5 letter string 
	 */
	public String generateName() {
		
		String constants = "bcdfghjklmnpqrstvwxyz";
		String vowels = "aeiou";
		
		char one = constants.charAt(randomInt(0,20));
		char two = vowels.charAt(randomInt(0,4));
		char three = constants.charAt(randomInt(0,20));
		char four = vowels.charAt(randomInt(0,4));
		char five = constants.charAt(randomInt(0,20));		
		
		String monName = ""+one+two+three+four+five;
		
		return monName;
		
	}
	
	/**
	 * Randomises a number between the two given integers
	 * The given integers can also be included
	 * 
	 * @param startIndex - lowest number
	 * @param lastIndex - highest number
	 * @return
	 */
	public int randomInt(int startIndex,int lastIndex) {
		Random random = new Random();
		return random.nextInt(lastIndex-startIndex)+startIndex;
	}
	
}
