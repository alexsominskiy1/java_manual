package application;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;

import annotations.ClassAnnotation;
import annotations.FieldAnnotation;
import annotations.MethodAnnotation;
import annotations.ParameterAnnotation;
import dto.Example;
import dto.ExampleChild;

public class AnnotationUsageApp {

	public static void main(String[] args) throws NoSuchMethodException, SecurityException, NoSuchFieldException{		
		// class annotations		
		System.out.println("********Parent*******");
		Annotation[] aClass = Example.class.getDeclaredAnnotations();
		System.out.println(Arrays.toString(aClass));
		
		System.out.println("********Child*******");
		Annotation[] childDeclared = ExampleChild.class.getDeclaredAnnotations();  // excluding inherited
		System.out.println(Arrays.toString(childDeclared));
		Annotation[] childInherited = ExampleChild.class.getAnnotations();		   // including inherited
		System.out.println(Arrays.toString(childInherited));
		
		// annotation class retrieving		
		System.out.println("\n"+ClassAnnotation.class);
		System.out.println(aClass[0].getClass());
		System.out.println(aClass[0].annotationType()+"\n");
		
		// annotations parameter values retrieving		
		ClassAnnotation classAnnotation = Example.class.getAnnotation(ClassAnnotation.class);
		if(classAnnotation != null) System.out.println("id = " + classAnnotation.id());
		if(classAnnotation != null) System.out.println("names = " + Arrays.toString(classAnnotation.names()));
		
		FieldAnnotation fieldAnnotation = Example.class.getDeclaredField("word").getAnnotation(FieldAnnotation.class);
		if(fieldAnnotation != null)System.out.println("length = " + fieldAnnotation.length());
		
		Method method =  Example.class.getDeclaredMethod("foo", String.class, int.class);
		MethodAnnotation methodAnnotation  = method.getAnnotation(MethodAnnotation.class);
		if(methodAnnotation != null)System.out.println("access = " + methodAnnotation.access());
		
		// method parameters		
		Parameter[] methodParameters = method.getParameters();
		System.out.println("*****parameters*****");
		for(Parameter p : methodParameters)System.out.println(p);
		
		ParameterAnnotation parameterAnnotation = methodParameters[1].getAnnotation(ParameterAnnotation.class);
		if(parameterAnnotation != null)System.out.println("negativesAllowed = " + parameterAnnotation.negativesAllowed());
	}
}
