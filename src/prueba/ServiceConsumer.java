package prueba;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

public class ServiceConsumer {
	 
	public static void main(String[] args) {
	
			try {
				consumir();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
	}
	
	public static void consumir() throws IOException
    {
		URL url = null;
	    url = new URL("http://localhost:8090/servicesREST/JR/validateUser");
	    HttpURLConnection urlConn = null;
	    urlConn = (HttpURLConnection) url.openConnection();
	    urlConn.setDoInput (true);
	    urlConn.setDoOutput (true);
	    urlConn.setRequestMethod("POST");
	    urlConn.setRequestProperty("Content-Type", "application/json");
	    urlConn.connect();

	    DataOutputStream output = null;
	    DataInputStream input = null;
	    output = new DataOutputStream(urlConn.getOutputStream());

	                /*Construct the POST data.*/
	    JSONObject jsonObject = new JSONObject();
	    jsonObject.put("person", new Person("id1"));
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
	        input.close ();
	    }
    }
	
	/**
	 * Transforma el InputStream en un String
	 * @return StringBuilder
	 * */
	 private static StringBuilder inputStreamToString(InputStream is)
	 {  
		  String line = "";
		  StringBuilder stringBuilder = new StringBuilder();
		  BufferedReader rd = new BufferedReader( new InputStreamReader(is) );  
		  try
		  {
		   while( (line = rd.readLine()) != null )
		   {
		    stringBuilder.append(line);
		   }
		  }
		  catch( IOException e)
		  {
		   e.printStackTrace(); 
		  }
	
		  return stringBuilder;
	 }
}
