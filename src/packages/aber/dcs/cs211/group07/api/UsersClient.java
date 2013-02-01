package aber.dcs.cs211.group07.api;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import aber.dcs.cs211.group07.data.Player;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UsersClient extends GeneralClient {

	public UsersClient() {
		super();
	}

	/**
	 * Returns the service url of a specified server
	 * 
	 * @param serverNumber - the number of the server
	 * @return service url
	 * @throws UniformInterfaceException
	 */
	private WebResource getServerResource(Integer serverNumber)
			throws UniformInterfaceException {
		return client.resource(serverDirectoryClient.getServiceUrlRoot(serverNumber));
	}

	/**
	 * Gets a user from another server using their unique id and server number
	 * 
	 * @param userId - id unique to the user on their server
	 * @param serverNumber - the server number
	 * @return a instance of player that represent the user
	 * @throws JSONException
	 * @throws UniformInterfaceException
	 */
	public Player getUser(String userID, Integer serverNumber)
			throws JSONException, UniformInterfaceException {
		WebResource resource = getServerResource(serverNumber);

		String body = resource.path("users").queryParam("userID", userID).get(String.class);
		JSONObject jsonObject = new JSONObject(body);
		
		Player user = new Player(0,serverNumber,
				jsonObject.getString("userID"),
				null,
				jsonObject.getInt("money"));

		return user;
	}

	/**
	 * Gets a list of all users on a specified server
	 * 
	 * @param serverNumber - the server's number
	 * @return an ArrayList of players
	 * @throws JSONException
	 * @throws UniformInterfaceException
	 */
	public ArrayList<Player> getUsers(Integer serverNumber)
			throws JSONException, UniformInterfaceException {
		WebResource resource = getServerResource(serverNumber);

		String body = resource.path("users").get(String.class);
		JSONArray jsonArray = new JSONArray(body);

		ArrayList<Player> users = new ArrayList<Player>();
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			
			Player user = new Player(0,serverNumber,
					jsonObject.getString("userID"),
					null,
					jsonObject.getInt("money"));
			users.add(user);
		}

		return users;
	}

	/**
	 * Returns the friend that matches the exact search
	 * 
	 * @param friendName - exact name of the friend
	 * @return an instance of player that represents the friend
	 * @throws JSONException
	 */
	public Player getFriend(String friendName) throws JSONException {
		
		ArrayList<Servers> serverList = serverDirectoryClient.getAllServers();
		
		for(int i=0;i<serverList.size();i++) {
			ArrayList<Player> userList = getUsers(serverList.get(i).serverNumber);
			for(int j=0;j<userList.size();j++) {
				if(friendName.equals(userList.get(j).email)) {
					return userList.get(j);
				}
			}
		}
		
		return null;
	}
	
	public void close() {
		client.destroy();
	}
}