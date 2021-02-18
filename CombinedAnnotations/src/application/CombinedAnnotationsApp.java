package application;

import java.lang.annotation.Annotation;

import annotations.Batch;
import dto.Foo;

public class CombinedAnnotationsApp {

	public static void main(String[] args) throws SecurityException, ClassNotFoundException {
		
		System.out.println("****** Foo annotations **********************");
		
		Annotation[] fooAnnotations = Foo.class.getAnnotations();    // all annotation of Foo
		for (Annotation a : fooAnnotations)System.out.println(a);
		
		System.out.println("******* Batch annotations ***************");
		
		Annotation batch = Foo.class.getAnnotation(Batch.class);                     // extract the @Batch
		Annotation[] batchAnnotations = batch.annotationType().getAnnotations();     // all annotations of @Batch
		for (Annotation a : batchAnnotations)System.out.println(a);
	}
}
