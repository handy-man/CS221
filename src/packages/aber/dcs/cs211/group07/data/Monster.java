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

	/**
	 * Constructor for a monster coming out of the database
	 * 
	 * @param id
	 * @param ownerID
	 * @param name
	 * @param date
	 * @param death_date
	 * @param age_rate
	 * @param health_lost
	 * @param base_health
	 * @param g_strength
	 * @param g_toughness
	 * @param breed_offer
	 * @param sale_offer
	 */
	
	public Monster(int id,int ownerID,String name,Date date,Date death_date,
			double age_rate,double health_lost,double g_strength,double g_toughness,
			double g_evasion, int breed_offer,int sale_offer) {
		
		this.id=id;
		this.ownerID=ownerID;
		this.name=name;
		this.birth=date;
		this.death_date=death_date;
		this.age_rate=age_rate;
		this.health_lost=health_lost;
		this.strength=g_strength;
		this.toughness=g_toughness;
		this.evasion=g_evasion;
		this.breed_offer=breed_offer;
		this.sale_offer=sale_offer;
		
	}
	
	/**
	 * Constructor for random monster, defining only a owner ID
	 * 
	 * @param ownerID
	 */
	public Monster(int ownerID){
		this.ownerID = ownerID;
		this.name = generateName();
		this.birth = new Date();
		Random randomStrength = new Random();
		Random randomToughness = new Random();
		//Random randomEvasion = new Random();
		this.strength = randomStrength.nextDouble();
		this.toughness = randomToughness.nextDouble();
		//this.evasion = randomEvasion.nextDouble();		
		this.age_rate=generateAgeRate();
		this.breed_offer=0;
		this.sale_offer=0;
		this.health_lost=0;
		this.death_date = calculateDeath();
	}
	
	/**
	 * Constructor for a monster received from breeding
	 * Insert into the database and then pull it out to get unique ID.
	 * 
	 * @param mother
	 * @param father
	 * @param newOwnerID
	 */
	public Monster(Monster mother, Monster father,int newOwnerID) {
		this.name = generateName();
		this.birth = new Date();
		this.strength = random(mother.strength, father.strength);
		this.toughness   = random(mother.toughness, father.toughness);
		this.evasion    = random(mother.evasion, father.evasion);
		this.age_rate = random(mother.age_rate,father.age_rate);
		
		this.ownerID=newOwnerID;
		this.death_date=calculateDeath();
		this.name = generateName();

	}
	
	/**
	 * Constructor for a monster received from trading
	 * Insert into the database and then pull it out to get unique ID.
	 * 
	 * @param ownerID
	 * @param date
	 * @param death_date
	 * @param health_lost
	 * @param base_health
	 * @param g_strength
	 * @param g_toughness
	 * @param breed_offer
	 * @param sale_offer
	 */
	public Monster(int monID,int ownerID,Date date,Date deatdate,
			double currentHealth,double g_strength,
			double g_toughness,int breed_offer,int sale_offer) {
		
		this.id=monID;
		this.ownerID=ownerID;
		this.name=generateName();
		this.birth=date;
		this.death_date=calculateDeath();
		this.age_rate=generateAgeRate();
		this.health_lost=health-currentHealth;
		this.strength=g_strength;
		this.toughness=g_toughness;
		//this.evasion=g_evasion;
		this.breed_offer=breed_offer;
		this.sale_offer=sale_offer;
		
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
	
	
	// DONT NOT NEED METHODS BELOW. Variables above are public and hence can be accessed
	// like this. int monsterID=mon.id
	
	/** 
	 * Used in JUnit testing
	 * 
	 * @return
	 */
	public int getID(){
		return id;
	}
	
	/** 
	 * Used in JUnit testing
	 * 
	 * @return
	 */
	public int getOwnerID(){
		return ownerID;
	}
	
	/** 
	 * Used in JUnit testing
	 * 
	 * @return
	 */
	public String getName(){
		return name;
	}
	
	/** 
	 * Used in JUnit testing
	 * 
	 * @return
	 */
	public  double getHealthLost(){
		return health_lost;
	}
	
	/** 
	 * Used in JUnit testing
	 * 
	 * @return
	 */
	public double getGStrength(){
		return strength;
	}
	
	/** 
	 * Used in JUnit testing
	 * 
	 * @return
	 */
	public double getGToughness(){
		return toughness;
	}
	
	/** 
	 * Used in JUnit testing
	 * 
	 * @return
	 */
	public double getGEvasion(){
		return evasion;
	}
	
	/**
	 * Used in JUnit testing
	 * 
	 * @return
	 */
	public double getAgeRate(){
		return age_rate;
	}
	
}
