package datasetCulturalPractice;

import java.util.Collection;

public interface Filter<T,E,S> {
	
	public Object MostFrequently(String choice);
	public Object Find(String value);
	public Object FindUnique (String name);
	public Collection<E> logicalFilter(T attribute,T operator,T value);
	public Collection<E> conditionalFilter(T attribute,T operator,S value);
	
	
	
}
