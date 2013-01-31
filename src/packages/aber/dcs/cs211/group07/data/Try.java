package aber.dcs.cs211.group07.data;

import aber.dcs.cs211.group07.db.MonsterTableConnector;

public class Try {

	public static void main(String[] args) {

		Monster m = new Monster(1);
		MonsterTableConnector mtc = new MonsterTableConnector();
		
		m = mtc.getMonster(3);

		
		System.out.println(m.getStrength());
		

	}

}
