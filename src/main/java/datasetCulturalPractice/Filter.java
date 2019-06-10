package datasetCulturalPractice;

public interface Filter<T,S> {
	
	public Object MostFrequently(T choice) throws WrongAttributeException;
	public Object FindUnique (T name) throws WrongAttributeException;
	public Object conditionalFilter(T operator, int... numbers);
	public Object logicalFilter(S attribute, T operator, S value) throws WrongAttributeException;
	
}
