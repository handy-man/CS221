package aber.dcs.cs211.group07.data;

import java.util.Random;

public class Fight {

	public void fight(Monster m1, Monster m2){
		Random r = new Random();
		//Monster winner = null;
		
		double m1Str,m1Def;
		m1Str = m1.getStrength();
		m1Def = m1.getToughness();
		
		double m2Str,m2Def;
		m2Str = m2.getStrength();
		m2Def = m2.getToughness();
		
		double mPower1,mPower2;
		mPower1 = m1Str + m1Def + r.nextDouble();
		mPower2 = m2Str + m2Def + r.nextDouble();
		
		if(mPower1 > mPower2){
			double test = 1.0;
			double diffPower = mPower1 - mPower2;
			test = test - diffPower;
			if ( test <= 0.0 )
				m1.increaseHealthLost(0.0);
			else 
				m1.increaseHealthLost(test);
			m2.increaseHealthLost(1.0);
		}else if ( mPower1 < mPower2){
			double test = 1.0;
			double diffPower = mPower1 - mPower2;
			test = test - diffPower;
			if ( test <= 0.0 )
				m2.increaseHealthLost(0.0);
			else 
				m2.increaseHealthLost(test);			
			m1.increaseHealthLost(1.0);
		}else{
			m1.increaseHealthLost(1.0);
			m2.increaseHealthLost(1.0);
		}
		
		
		//return winner;
	}
	
	
	
}
