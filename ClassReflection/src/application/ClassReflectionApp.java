package application;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.function.Predicate;


import dto.Address;
import dto.Employee;
import dto.Person;
import dto.Speaking;

public class ClassReflectionApp {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, SecurityException {
		
		Person person = new Person(
				"John", 28, 84.2, true,
				new Address(223322, "Forkvill", "Mainstreet", 31, 8)
				);
		
		/* 1 */ Class<Person> personClass1 = Person.class;   // type name
		/* 2 */ Class<Person> personClass2 = (Class<Person>) person.getClass();   // instance	
		/* 3 */ Class<Person> personClass3 = (Class<Person>) Class.forName("dto.Person");	 // by name		
		
		System.out.println(personClass1 == personClass2);   // true
		System.out.println(personClass1 == personClass3);   // true
		
		try {
			instantiateOldStyle("Horse", "dto").say();    // I'm a horse
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Class<Person> clazz = Person.class;
		System.out.println(clazz.getCanonicalName());
		System.out.println(clazz.getName());
		System.out.println(clazz.getSimpleName());
		
		Class<Employee> employeeClass = Employee.class;
		for(Field f : employeeClass.getFields()) System.out.println(f);
		for(Field f : employeeClass.getDeclaredFields()) System.out.println(f);
		
	}

	public static Speaking instantiateOldStyle(String className, String pack) throws Exception {
		return (Speaking)(Class.forName(pack + "." + className).newInstance());
	}
	
	public static Speaking instantiateNewStyle(String className, String pack) throws Exception {
		
			return (Speaking)(Class.forName(pack + "." + className)     // Class instance
					               .getDeclaredConstructor()			// empty constructor
					               .newInstance());                     // speaking animal instance
			
	}
}
