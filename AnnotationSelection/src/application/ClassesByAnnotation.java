package application;

public class ClassesByAnnotation {
	
	public static void main(String[] args){		
		EntitiesList entitiesList = new EntitiesList("application");
		for(Class<?> cl : entitiesList.getEntitiesList())System.out.println(cl);
	}
}
