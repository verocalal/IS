package Factorias;

public class BuilderBasedFactory<T> implements Factoria<T>
{
	private Builder<T> _builder;

	public BuilderBasedFactory(Builder<T> builder) 
	{
		this._builder = builder;
	}

	@Override
	public T createInstance(String[] info) 
	{
		
		T instance = _builder.createInstance(info);
		if (instance != null) 
		{
			return instance;
		}
		
		return null;
	}

}
