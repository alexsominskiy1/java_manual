package builder;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;


public class UniversalBuilder<T> {
	private T instance;
	private Class<T> clazz;
	
	public UniversalBuilder(Class<T> clazz) throws 
												InstantiationException, IllegalAccessException, 
												IllegalArgumentException, InvocationTargetException, 
												NoSuchMethodException, SecurityException {
		this.clazz = clazz;
		this.instance = clazz.getConstructor().newInstance();
	}
	
	public UniversalBuilder<T> with(String fieldName, Object value) throws 
												NoSuchFieldException, SecurityException, 
												IllegalArgumentException, IllegalAccessException {
		Field field = null;
		field = clazz.getDeclaredField(fieldName);
		field.setAccessible(true);
		field.set(instance, value);
		return this;
	}
	
	public T build() {
		return instance;
	}
}
