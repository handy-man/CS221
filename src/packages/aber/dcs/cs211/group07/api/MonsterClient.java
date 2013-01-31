package aber.dcs.cs211.group07.api;

import java.util.ArrayList;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import aber.dcs.cs211.group07.data.Monster;
import aber.dcs.cs211.group07.data.Player;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class MonsterClient extends GeneralClient {

	public MonsterClient() {
		super();
	}
	
	/**
	 * Gets the monsters from a friend. Only grabs relevant information
	 * for the play on the monsters, i.e battle attributes + IDs
	 * 
	 * @param userID
	 * @param serverNumber
	 * @return
	 * @throws JSONException
	 * @throws UniformInterfaceException
	 */
	public ArrayList<Monster> getAllMonsters(String userID, int serverNumber)
			throws JSONException, UniformInterfaceException {
		WebResource resource = getServerResource(serverNumber);
		
		ArrayList<Monster> monsterList = new ArrayList<Monster>();

		String body = resource.path("monsters").queryParam("userID", userID).get(String.class);
		JSONArray jsonArray = new JSONArray(body);

		for(int i=0;i<jsonArray.length();i++) {
			JSONObject jsonObject = new JSONObject(jsonArray.get(i));
			
			int monID = Integer.parseInt(jsonObject.getString("monsterID"));
			int ownerID = Integer.parseInt(jsonObject.getString("userID"));
			Date birth = new Date(jsonObject.getInt("birthDate"));
			//Date death = new Date(jsonObject.getInt("lifespan"));
			double currentStrength = jsonObject.getDouble("currentStrength");
			double currentDefence = jsonObject.getDouble("currentDefence");
			double currentHealth = jsonObject.getDouble("currentDefence");

			Monster mon = new Monster(monID,ownerID,null,null,currentHealth,
					currentStrength,currentDefence,0,0);
			monsterList.add(mon);
		}

		return monsterList;
	}
	
	/**
	 * Gets a single monster using its ID
	 * 
	 * @param monID
	 * @param serverNumber
	 * @return
	 * @throws JSONException
	 */
	public Monster getMonster(String monID,int serverNumber) throws JSONException {
		
		WebResource resource = getServerResource(serverNumber);

		String body = resource.path("monsters").queryParam("monsterID", monID).get(String.class);
		JSONObject jsonObject = new JSONObject(body);

		int monsterID = Integer.parseInt(jsonObject.getString("monsterID"));
		int ownerID = Integer.parseInt(jsonObject.getString("userID"));	
		double currentStrength = jsonObject.getDouble("currentStrength");
		double currentDefence = jsonObject.getDouble("currentDefence");
		double currentHealth = jsonObject.getDouble("currentDefence");

		Monster mon = new Monster(monsterID,ownerID,null,null,currentHealth,
				currentStrength,currentDefence,0,0);
		
		return mon;
		
	}

	
}