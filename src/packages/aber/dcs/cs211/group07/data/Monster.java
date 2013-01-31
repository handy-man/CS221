package aber.dcs.cs211.group07.data;

import java.sql.Time;
import java.util.Date;
import java.util.Random;

public class Monster {

	public int id;
	public int ownerID;
	
	/** The monsters name */
	public String name;
	
	/** The date the monster was born **/
	public Date birth_date;
	
	/** The date the monster will die **/
	public Date death_date;
	
	/** The rate at which the monster ages */
	public double age_rate;
	
	/** The monsters health */
	public double health = 1.0;
	
	/** The amount of health the monster has lost */
	public double health_lost = 0;
	
	/* The monsters attributes */
	
	public double strength;
	public double toughness;
	public double evasion;

	/* The cost to breed or buy the monster? */
	
	public int breed_offer = 0;
	public int sale_offer = 0;
	
	/**
	 * Constructor for a new random monster
	 * 
	 * @param owner The player that will own the monster
	 */
	public Monster(int ownerID){
		this.ownerID    = ownerID;
		this.name       = randomName();
		
		this.birth_date = new Date();
		this.death_date = calculateDeath();
		
		this.strength   = random();
		this.toughness  = random();
		this.evasion    = random();		
		this.age_rate   = randomAgeRate();
	}
	
	/**
	 * Constructor for a monster being retrieved from the database
	 */
	public Monster(int id, int ownerID, String name, Date birth_date, Date death_date,
			double age_rate, double health_lost, double strength, double toughness,
			int breed_offer, int sale_offer) {
		
		this.id          = id;
		this.ownerID     = ownerID;
		this.name        = name;
		
		this.birth_date  = birth_date;
		this.death_date  = death_date;
		this.age_rate    = age_rate;
		
		this.health_lost = health_lost;
		
		this.strength    = strength;
		this.toughness   = toughness;
		this.evasion     = 0;
		
		this.breed_offer = breed_offer;
		this.sale_offer  = sale_offer;
	}
	
	/**
	 * Constructor for a new monster bred from two parent monsters
	 * 
	 * @param owner The Player that will own the monster
	 * @param mother
	 * @param father
	 */
	public Monster(int ownerID, Monster mother, Monster father) {
		this.ownerID    = ownerID;
		this.name       = randomName();
		
		this.birth_date = new Date();
		this.death_date = calculateDeath();
		this.age_rate   = randomInheritance(mother.age_rate,  father.age_rate);
		this.strength   = randomInheritance(mother.strength,  father.strength);
		this.toughness  = randomInheritance(mother.toughness, father.toughness);
		this.evasion    = randomInheritance(mother.evasion,   father.evasion);
	}
	
	/* Functions that return random numbers to be used as attributes */
	
	/**
	 * @return A random number between 0.0 and 1.0
	 */
	private static double random() {
		return new Random().nextDouble();
	}
	
	/**
	 * Randomly inherits from one of the parent attributes,
	 * with a small chance of returning a completely random number
	 */
	private static double randomInheritance(double mother, double father) {
		if (random() > 0.05) {
			return random() > 0.5 ? mother : father;
		} else {
			return random();
		}
	}
	
	/**
	 * Gives a random double between 00.5 and 0.1
	 * 
	 * @return double A number suitable for use as a monsters age rate.
	 */
	public static double randomAgeRate() {
		Random r = new Random();
		double randomInt = r.nextInt(10-5)+5;
		double age_rate = randomInt/100;
		return age_rate;
	}
	
	private final static String CONSTANTS = "bcdfghjklmnpqrstvwxyz";
	private final static String VOWELS = "aeiou";
	
	/**
	 * Returns a random character from a string
	 */
	private static char randomChar(String chars) {
		Random r = new Random();
		return chars.charAt(r.nextInt(chars.length()));
	}
	
	/**
	 * Generates a random 5 letter name 
	 * 
	 * @return a 5 letter string 
	 */
	private static String randomName() {
		char[] name = new char[5];
		
		name[0] = randomChar(CONSTANTS);
		name[1] = randomChar(VOWELS);
		name[2] = randomChar(CONSTANTS);
		name[3] = randomChar(VOWELS);
		name[4] = randomChar(CONSTANTS);
		
		return new String(name);
	}
	
	/* Used to calculate attributes */
	
	private double expAge() {
		return Math.exp(this.getAge().getTime() * this.age_rate);
	}
	
	private double expValue(double value) {
		return value * (expAge()-1) * (2-expAge());
	}
	
	/* Getters for the calculated attributes */
	
	public double getHealth() {
		return 2 - expAge();
	}
	
	public double getStrength() {
		return expValue(this.strength);
	}
	
	public double getToughness() {
		return expValue(this.toughness);
	}
	
	public double getEvasion() {
		return expValue(this.evasion);
	}
	
	public double getCurrentHealth(){
		return this.getHealth() - this.health_lost;
	}
	
	/* Other functions */
	
	/**
	 * Increase the amount of health the monster has lost, 
	 * stopping at the maximum of 1.0.
	 *  
	 * @param amount The amount of health the monster will lose
	 */
	public void increaseHealthLost(double amount){
		this.health_lost = this.health_lost + amount;
		if(this.health_lost >= 1.0){
			this.health_lost = 1.0;
		}
	}
	
	/**
	 * Returns the age of the monster
	 */
	public Time getAge(){
		return new Time(new Date().getTime() - this.birth_date.getTime());
	}
	
	/**
	 * Calculates the death due to old age when a monster is born
	 * Currently calculating in days
	 * 
	 * @return a date which the monster will die
	 */
	public Date calculateDeath() {
		long timeTillDeath = (long) Math.round(Math.log(2) / age_rate) * 86400000;
		Date deathDate = new Date((birth_date.getTime()+ timeTillDeath));
		return deathDate;
	}
}
