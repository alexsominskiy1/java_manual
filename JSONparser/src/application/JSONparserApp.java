package application;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import dto.Person;
import parser.JSONparsers;
import parser.JSONparsersException;

public class JSONparserApp {
	
	public static void main(String[] args) throws FileNotFoundException, JSONparsersException {
		List<Object> result = JSONparsers.parseData(new FileReader("src/data/person.txt"), Person.class);
		for(Object obj : result) System.out.println(obj);
	}


}
