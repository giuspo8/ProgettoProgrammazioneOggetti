package datasetCulturalPractice;

/**
 * Interfaccia che rappresenta le operazioni di filtraggio richiedibili dall'utente
 * @author Giuseppe Costantini
 * @author Davide Vitaletti
 * @version 1.0
 */
public interface Filter<T,S> {
	/**
	 * metodo che ci consente di trovare il valore che compare piu' di frequente in un insieme di elementi tra di loro coerenti
	 * @param choice attributo scelto
	 * @return l'occorenza che compare piu' di frequente nell'attributo scelto oppure un messaggio di errore
	 * @throws WrongAttributeException nel caso in cui l'attributo immesso non sia presente
	 */
	public Object MostFrequently(T choice) throws WrongAttributeException;
	/**
	 * ci consente di effettuare il conteggio elementi unici
	 * @param name attributo sul quale effettuare l'operazione
	 * @return una mappa chiave valore con tutti gli elementi mappati in base alle loro occorrenze o un messaggio di errore
	 * @throws WrongAttributeException nel caso in cui l'attributo immesso non sia presente
	 */
	public Object FindUnique (T name) throws WrongAttributeException;
	/**
	 * applica un filtro condizionale al dataset
	 * @param operator operatore di filtraggio scelto
	 * @param numbers numeri su cui effettuare il filtraggio
	 * @return il dataset opportunatamente filtrato per numero di pratica oppure un messaggio di errore se l'operatore non e' giusto
	 */
	public Object conditionalFilter(T operator, int... numbers);
	/**
	 * applica un filtro logico al dataset
	 * @param attribute attributo/i su cui effettuare il filtraggio
	 * @param operator operatore di filtraggio scelto
	 * @param value valori scelti per il filtraggio
	 * @return il dataset opportunatamente filtrato oppure un messaggio di errore se non viene rispettata qualche regola di applicazione degli operatori
	 * @throws WrongAttributeException nel caso in cui l'attributo immesso non sia presente
	 */
	public Object logicalFilter(S attribute, T operator, S value) throws WrongAttributeException;
	
}
