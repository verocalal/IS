package Factorias;

public abstract class Builder<T> 
{
	protected String type; // Tipo

	public Builder(String type) 
	{
		this.type = type;
	}

	public T createInstance(String[] info) 
	{
		// Compara type con el type de la info
		if (!info[0].equals(type)) 
		{
			return null;
		}
		// llama al creador de la instancia
		return createTheInstance(info); // abstracto que se implementa en cada builder

	}

	public abstract T createTheInstance(String[] info);
}
