package aber.dcs.cs211.group07.actions;

import java.util.Random;

import aber.dcs.cs211.group07.data.Monster;

public class Fight {
	/**
	 * Return a 'power' value for the monster
	 */
	public static double monsterPower(Monster monster){
		Random r = new Random();
		return monster.getStrength() + monster.getToughness() + r.nextDouble();
	}
	
	/**
	 * Make two monsters fight
	 * 
	 * @return The winning monster
	 */
	public static Monster fight(Monster monster1, Monster monster2) {
		double monsterPower1 = Fight.monsterPower(monster1);
		double monsterPower2 = Fight.monsterPower(monster2);
		
		Monster winner, loser;
		double winnerPower, loserPower;
		
		/* Ensure there is a clear loser and winner, not a draw */
		if (monsterPower1 > monsterPower2) {
			winner = monster1;
			loser = monster2;
			winnerPower = monsterPower1;
			loserPower = monsterPower2;
		} else {
			winner = monster2;
			loser = monster1;
			winnerPower = monsterPower2;
			loserPower = monsterPower1;
		}
		
		double powerDiffrence = 1.0 - (winnerPower - loserPower);
		
		/* Remove powerDiffrence from the winners health unless it is below 0 */
		winner.increaseHealthLost(powerDiffrence < 0.0 ? 0.0 : powerDiffrence);
		
		/* Remove 1.0 from the losers health */
		loser.increaseHealthLost(1.0);
		
		return winner;
	}
	
	
	
}
