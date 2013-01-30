package aber.dcs.cs211.group07.data;

import java.util.Random;

public class Fight {

	public double calculateMonsterPower(Monster monster){
		Random r = new Random();
		
		double mStr,mDef;
		mStr = monster.getStrength();
		mDef = monster.getToughness();
		
		double mPower;
		mPower = mStr + mDef + r.nextDouble();
		
		return mPower;
	}
	
	public void fight(Monster m1, Monster m2){
		
		double mPower1,mPower2;
		mPower1 = this.calculateMonsterPower(m1);
		mPower2 = this.calculateMonsterPower(m2);
		
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
	}
	
	
	
}
