package datasetCulturalPractice;

public interface Filter<T,S> {
	
	public Object MostFrequently(T choice) throws wrongAttributeException;
	public Object FindUnique (T name) throws wrongAttributeException;
	public Object conditionalFilter(T operator, int... numbers);
	public Object logicalFilter(S attribute, T operator, S value) throws wrongAttributeException;
	
	
	
}
