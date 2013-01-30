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

public class UsersClient {

	protected WebResource webResource;
	protected Client client;
	// See the Server Directory Client Example
	protected ServerDirectoryClient serverDirectoryClient;

	public UsersClient() {
		ClientConfig config = new DefaultClientConfig();
		client = Client.create(config);
		// Set up some timeouts so the application wont hang if the network
		// is down or if the other server is unreachable
		client.setConnectTimeout(5000);
		client.setReadTimeout(10000);
		serverDirectoryClient = new ServerDirectoryClient();
	}

	private WebResource getServerResource(Integer serverNumber)
			throws UniformInterfaceException {
		return client.resource(serverDirectoryClient.getServiceUriRoot(serverNumber));
	}

	public Player getUser(String userId, Integer serverNumber)
			throws JSONException, UniformInterfaceException {
		WebResource resource = getServerResource(serverNumber);

		String body = resource.path("users").queryParam("userID", userId).get(String.class);
		JSONObject json = new JSONObject(body);

		Player user = new Player(0,null,null,0);
		user.id=Integer.parseInt((json.getString("userID")));
		user.email=(json.getString("name"));
		user.money=(json.getInt("money"));

		return user;
	}

	public ArrayList<Player> getUsers(Integer serverNumber)
			throws JSONException, UniformInterfaceException {
		WebResource resource = getServerResource(serverNumber);

		String body = resource.path("users").get(String.class);
		JSONArray jsonArray = new JSONArray(body);

		ArrayList<Player> users = new ArrayList<Player>();
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			Player user = new Player(0,null,null,0);
			user.id=Integer.parseInt(jsonObject.getString("userID"));
			user.email=jsonObject.getString("name");
			user.money=(jsonObject.getInt("money"));
			users.add(user);
		}

		return users;
	}

	public void close() {
		client.destroy();
	}
}