package datasetCulturalPractice;

public interface Filter<T,S> {
	
	public Object MostFrequently(T choice);
	public Object FindUnique (T name);
	public Object conditionalFilter(T operator, int... numbers);
	public Object logicalFilter(S attribute, T operator, S value);
	
	
	
}
