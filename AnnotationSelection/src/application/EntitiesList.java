package application;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import annotations.Entity;

public class EntitiesList {
	
	private List<Class<?>> entitiesList = new ArrayList<>();
	
	public EntitiesList(String rootDirectory) {
		super();
		fillEntitiesList(Paths.get("src/"+rootDirectory));
	}
	
	public List<Class<?>> getEntitiesList() {return entitiesList;}

	private void fillEntitiesList(Path path) {
		try {
			Files.list(path).forEach(p -> {
				if (Files.isDirectory(p)) fillEntitiesList(p);
				else {
					String fileName = p.toString();
					if(fileName.endsWith(".java")) {
						Class<?> clazz = null;
						try {
							clazz = Class.forName(fileName.substring(4, fileName.length()-5).replaceAll("\\\\", "."));
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						}
						if (clazz.isAnnotationPresent(Entity.class))entitiesList.add(clazz);
					}
				}
			});
		} catch(IOException e) {e.printStackTrace();};
	}
}
