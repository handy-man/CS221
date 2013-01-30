package aber.dcs.cs211.group07.api;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

/**
 * This simple HTTP client accesses the Server Directory to obtain the
 * service root URL's for servers.
 * @author James Bowcott
 */
public class ServerDirectoryClient {
	protected Client client;
	protected WebResource resource;
	private Servers server;

	public ServerDirectoryClient() {
		ClientConfig config = new DefaultClientConfig();
		client = Client.create(config);
		// Set up some timeouts so the application wont hang if the network
		// is down or if the server is unreachable
		client.setConnectTimeout(5000);
		client.setReadTimeout(10000);
		resource = client.resource("http://monstermash.digitdex.com/directory");
	}

	/**
	 * Returns an ArrayList of all the servers as instance
	 * of the Servers class
	 * 
	 * @return ArrayList of all servers
	 * @throws JSONException
	 */
	public ArrayList<Servers> getAllServers() throws JSONException {

		ArrayList<Servers> serverList = new ArrayList<Servers>();


		String body = resource.path("all").get(String.class);
		JSONArray jsonArray = new JSONArray(body);

		for (int i = 0; i < jsonArray.length(); i++) {

			JSONObject jsonObject;

			jsonObject = jsonArray.getJSONObject(i);
			int serverID = jsonObject.getInt("serverNumber");

			//removes our own server from the search, we can check this 
			//without a request
			if(serverID!=7) {

				String httpRoot = jsonObject.getString("httpRoot");
				String serviceRoot = jsonObject.getString("serviceRoot");
				Servers server = new Servers(serverID,httpRoot,serviceRoot);
				serverList.add(server);
			}

		}

		return serverList;

	}

	/**
	 * Returns the service url of a specified server
	 * 
	 * @param serverNumber - number of specified server
	 * @return service url
	 * @throws UniformInterfaceException
	 */
	public String getServiceUrlRoot(Integer serverNumber)
			throws UniformInterfaceException {
		// For example, this will be http://monstermash.digitdex.com/directory/3/service for server 3
		return resource.path(serverNumber.toString()).path("service").get(String.class);
	}

	//there are ways to get http and service root in plain text
	//also redirect to a servers main page, unsure if usefull

	/**
	 * Closes the client
	 * 
	 */
	public void close() {
		client.destroy();
	}

}
