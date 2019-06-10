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

/**
 * Classe che ci permette di effettuare il download del nostro file a partire dall'url e parsando adeguatamente il documento json ottenuto
 * @author Giuseppe Costantini
 * @author Davide Vitaletti
 * @version 1.0
 */
public class Download {
	//Attributi
	private String url="";

	//Metodi
	/**
	 * Crea un nuovo oggetto di tipo Download
	 * @param url stringa che rappresenta l'Url da cui scaricare il file json da parsare
	 */
	public Download(String url) {
		super();
		this.url = url;
	}

	/**
	 * metodo che fa il download del file Json in cui e' contenuto il dataset a partire dall'url, lo parsa adeguatamente e lancia un metodo per effettuare il download
	 */
	public void getFile() {
		try {
			//apriamo la connessione
			URLConnection openConnection = new URL(url).openConnection();
			//consente di specificare chi è il client (nel nostro caso fittizio) che sta facendo la richiesta
			openConnection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
			InputStream in = openConnection.getInputStream();

			String data = "";
			String line = "";
			try {
				BufferedReader buf = new BufferedReader(new InputStreamReader( in ) );

				//mentre leggiamo riga per riga il file creiamo una stringa data con tutte le righe concatenate
				while ( ( line = buf.readLine() ) != null ) {
					data+= line;
				}
			} 
			catch(Exception e) 
			{
				e.printStackTrace();
			}
			finally 
			{
				//chiudiamo lo stream
				in.close();
			}
			//parsiamo la stringa ottenuta trasformandola in un oggetto json
			JSONObject obj = (JSONObject) JSONValue.parseWithException(data); 
			//da questo oggetto ci ricaviamo l'attributo result che è asua volta un oggetto json
			JSONObject objI = (JSONObject) (obj.get("result"));
			//e l'attributo resources che è invece un array e che si trova all'interno del secondo oggetto Json (objI)
			JSONArray objA = (JSONArray) (objI.get("resources"));

			//per ogni elemento dell'array objA
			for(Object o: objA){
				//se objA è di tipo jsonObject
				if ( o instanceof JSONObject ) {
					//viene castomizzato a json object
					JSONObject o1 = (JSONObject)o; 
					//e ci prendiamo gli attributi relativi al format e all'url
					String format = (String)o1.get("format");
					String urlD = (String)o1.get("url");
					//System.out.println(format + " | " + urlD);
					//se il formato è csv effettuiamo il download del file corrispondente al campo url
					if(format.equals("csv")) {
						download(urlD, Application.filename);
					}
				}
			}
			//System.out.println( "OK" );
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
/**
 * metodo che effettua il download del file a partire dall'url dato se non gia' presente un file con lo stesso filename
 * @param url url di cui effettuare il download
 * @param filename nome con cui salvare il file
 * @throws Exception in caso di errori generici relativi a problemi di Input Output etc
 */
	private void download(String url, String filename) throws Exception {
		File file=new File(filename);
		//se un file con quel nome già esiste non fa niente
		if (!file.exists())
		{
			//altrimenti copiamo l'inputstream ottenuto dall'url nel percorso specificato da filename
			try (InputStream in = URI.create(url).toURL().openStream()) 
			{
				Files.copy(in, Paths.get(filename));
			}
		}
	}
}

