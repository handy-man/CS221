package aber.dcs.cs211.group07.data;

import java.util.Random;

public class Fight {

	public Monster fight(Monster m1, Monster m2){
		Random r = new Random();
		Monster winner = null;
		
		double m_power1;
		m_power1 = m1.getCurrentHealth() + m1.getStrength() +
				+ m1.getToughness() + m1.getEvasion() + r.nextDouble();
		double m_power2;
		m_power2 = m2.getCurrentHealth() + m2.getStrength() +
				+ m2.getToughness() + m2.getEvasion() + r.nextDouble();
		
		if(m_power1 > m_power2)
			winner = m1;
		else if ( m_power1 < m_power1)
			winner = m2;
		else ; // code for draw
		
		// code for making the winner lose health based on the result from the fight 
		
		
		return winner;
	}
	
	
	
}
