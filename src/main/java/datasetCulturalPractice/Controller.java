package datasetCulturalPractice;
import java.util.HashSet;
import java.util.Set;

import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Classe Controller con cui mappiamo le richieste REST
 * @author Giuseppe Costantini
 * @author Davide Vitaletti
 * @version 1.0
 */
@RestController
public class Controller {

	/**
	 * metodo che viene lanciato in caso di eccezione {@link NumberFormatException}
	 * @return una stringa con la descrizione dell'errore
	 */
	@ExceptionHandler (NumberFormatException.class)
	public Object conflictNotANumber(){
		return "i dati inseriti non sono nel formato corretto!";
	}
	
	/**
	 * metodo che viene lanciato in caso di eccezione {@link WrongAttributeException}
	 * @return una stringa con la descrizione dell'errore
	 */
	@ExceptionHandler (WrongAttributeException.class)
	public Object conflictWrongAttribute(){
		return "attributo non corretto!";
	}
	
	/**
	 * metodo che viene lanciato in caso di eccezione {@link ArrayIndexOutOfBoundsException}
	 * @return una stringa con la descrizione dell'errore
	 */
	@ExceptionHandler (ArrayIndexOutOfBoundsException.class)
	public Object conflictOutOfBounds() {
		return "il numero di attributi inseriti non è coerente con il numero dei valori";
	}
	
	/**
	 * metodo che viene lanciato in caso di eccezione {@link MissingServletRequestParameterException} cioe' quando la richiesta HTTP non e' scritta in modo corretto
	 * @return una stringa con la descrizione dell'errore
	 */
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public Object conflictMissingParameters() {
		return "Bad request. ci sono dei parametri che non sono stati inseriti";
	}
	
	/**
	 * mappa la richiesta per ottenere tutti le {@link CulturalPractice}
	 * @return un oggetto contenente tutti le {@link CulturalPractice}
	 */
	@RequestMapping("/data")
	public Object data(){
		return DatasetCulturalPractice.getPractices();
	}

	/**
	 * mappa la richiesta per ottenere le {@link CulturalPractice} filtrate secondo un filtro condizionale
	 * @return un oggetto contenente le {@link CulturalPractice} filtrate oppure un messaggio di errore
	 * @throws NumberFormatException quando il formato di numbers non e' di tipo int (ad esempio e' una lettera)
	 */
	@RequestMapping(value = "/conditionalFilter", method = RequestMethod.GET)
	public Object filterConditional(@RequestParam("numbers") int[] numbers,@RequestParam("operator")String operator)
	{
			DatasetCulturalPractice c = new DatasetCulturalPractice ();
			return c.conditionalFilter(operator,numbers);
	}
	
	/**
	 * mappa la richiesta per ottenere le {@link CulturalPractice} filtrate secondo un filtro logico
	 * @return un oggetto contenente le {@link CulturalPractice} filtrate oppure un messaggio di errore
	 * @throws ArrayIndexOutOfBoundsException se vengono inseriti due attributi e un solo valore
	 */
	@RequestMapping(value = "/logicalFilter", method = RequestMethod.GET)
	public Object filterLogical(@RequestParam("attribute") String[] attribute,@RequestParam("operator")String operator,
			@RequestParam("value")String[] value) throws WrongAttributeException
	{
		DatasetCulturalPractice c = new DatasetCulturalPractice ();
		return c.logicalFilter(attribute,operator,value);
	}


	/**
	 * mappa la richiesta per ottenere il conteggio degli elementi unici
	 * @return un oggetto contenente le occorrenze chiave valore
	 */
	@RequestMapping(value = "/countUnique", method = RequestMethod.GET)
	public Object FindUniqueElement(@RequestParam("attribute") String name) throws WrongAttributeException
	{
		DatasetCulturalPractice c = new DatasetCulturalPractice();
		return c.FindUnique(name);
	}

	/**
	 * mappa la richiesta per ottenere l'occorrenza piu' frequente di un certo attributo
	 * @return l'elemento piu' frequente
	 */
	@RequestMapping(value = "/mostFrequently", method = RequestMethod.GET)
	public Object MostFrequently(@RequestParam("attribute") String name) throws WrongAttributeException
	{
		DatasetCulturalPractice c = new DatasetCulturalPractice();
		return c.MostFrequently(name);
	}

	/**
	 * mappa la richiesta per ottenere la lista degli {@link Error}
	 * @return un oggetto contenente la lista degli {@link Error}
	 */
	@RequestMapping(value = "/data/errors", method = RequestMethod.GET)
	public Object ShowErrors()
	{
		return DatasetCulturalPractice.getErrors();
	}

	/**
	 * mappa la richiesta per ottenere il numero degli elementi parsati correttamente
	 * @return il numero degli elementi parsati correttamente
	 */
	@RequestMapping(value = "/count", method = RequestMethod.GET)
	public Object Count()
	{
		return "il numero di elementi con formattazione valida nel DataSet è pari a "+DatasetCulturalPractice.getPractices().size();
	}

	/**
	 * mappa la richiesta per ottenere tutti i {@link Partner}
	 * @return un oggetto contenente tutti i {@link Partner}
	 */
	@RequestMapping(value = "/partners", method = RequestMethod.GET)
	public Object partners()
	{
		Set<Institution> partners=new HashSet<Institution>();
		DatasetCulturalPractice c = new DatasetCulturalPractice();
		return c.getPartners(partners);
	}
	
	/**
	 * mappa la richiesta per ottenere tutti le {@link Town}
	 * @return un oggetto contenente tutti le {@link Town}
	 */
	@RequestMapping(value = "/towns", method = RequestMethod.GET)
	public Object towns()
	{
		Set<Town> towns=new HashSet<Town>();
		DatasetCulturalPractice c = new DatasetCulturalPractice();
		return c.getTowns(towns);
	}

	/**
	 * mappa la richiesta per ottenere tutti i {@link Proponent}
	 * @return un oggetto contenente tutti i {@link Proponent}
	 */
	@RequestMapping(value = "/proponents", method = RequestMethod.GET)
	public Object proponents()
	{
		Set<Institution> proponents=new HashSet<Institution>();
		DatasetCulturalPractice c = new DatasetCulturalPractice();
		return c.getProponents(proponents);
	}

	/**
	 * mappa la richiesta per ottenere tutti i {@link Metadata}
	 * @return un oggetto contenente tutti i {@link Metadata}
	 */
	@RequestMapping(value = "/metadata", method = RequestMethod.GET)
	public Object metadata()
	{
		return DatasetCulturalPractice.getMetadata();
	}
}
