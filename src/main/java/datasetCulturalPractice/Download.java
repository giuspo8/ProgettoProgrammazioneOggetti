package datasetCulturalPractice;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

public class Download {

	private String url="";

	public Download(String url) {
		super();
		this.url = url;
	}

	public void getFile() {
		try {

			URLConnection openConnection = new URL(url).openConnection();
			openConnection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
			InputStream in = openConnection.getInputStream();

			String data = "";
			String line = "";
			try {
				BufferedReader buf = new BufferedReader(new InputStreamReader( in ) );

				while ( ( line = buf.readLine() ) != null ) {
					data+= line;
					System.out.println( line );
				}
			} 
			catch(Exception e) 
			{
				e.printStackTrace();
			}
			finally 
			{
				in.close();
			}
			JSONObject obj = (JSONObject) JSONValue.parseWithException(data); 
			JSONObject objI = (JSONObject) (obj.get("result"));
			JSONArray objA = (JSONArray) (objI.get("resources"));

			for(Object o: objA){
				if ( o instanceof JSONObject ) {
					JSONObject o1 = (JSONObject)o; 
					String format = (String)o1.get("format");
					String urlD = (String)o1.get("url");
					System.out.println(format + " | " + urlD);
					if(format.equals("csv")) {
						download(urlD, Application.filename);
					}
				}
			}
			System.out.println( "OK" );
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void download(String url, String filename) throws Exception {
		File file=new File(filename);
		if (!file.exists())
		{
			try (InputStream in = URI.create(url).toURL().openStream()) 
			{
				Files.copy(in, Paths.get(filename));
			}
		}
	}
}
