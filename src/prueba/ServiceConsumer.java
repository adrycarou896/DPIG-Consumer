package prueba;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.json.JSONObject;

public class ServiceConsumer {
	 
	public static void main(String[] args) {
	
			try {
				consumir();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
		
	}
	
	public static void consumir() throws IOException, ParseException
    {
		URL url = null;
	    url = new URL("http://localhost:8090/servicesREST/JR/validateUser");
	    HttpURLConnection urlConn = null;
	    urlConn = (HttpURLConnection) url.openConnection();
	    urlConn.setDoInput (true);
	    urlConn.setDoOutput (true);
	    urlConn.setRequestMethod("POST");
	    urlConn.setRequestProperty("Content-Type", "application/json");
	    urlConn.setRequestProperty("Accept", "application/json");
	    urlConn.connect();

	    DataOutputStream output = null;
	    DataInputStream input = null;
	    output = new DataOutputStream(urlConn.getOutputStream());
	    
	    Camera camera = new Camera("c1",new ArrayList<Camera>());
	    Person person = new Person("id1");
	    
	    Date date = new Date();
	    
	    /*
	    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
	    String day = format.format(fecha);
	    
	    format = new SimpleDateFormat("HH:mm:ss");
	    String hour = format.format(fecha);
	    */
	    
	    Match match = new Match(camera, person, date);
	    
	    JSONObject jsonObject = match.getJson();
	    
	    String content = jsonObject.toString();

	    /* Send the request data.*/
	    output.writeBytes(content);
	    output.flush();
	    output.close();

	    /* Get response data.*/
	    String response = null;
	    input = new DataInputStream (urlConn.getInputStream());
	    while (null != ((response = input.readLine()))) {
	        System.out.println(response);
	    }
	    input.close ();
    }
	
}
