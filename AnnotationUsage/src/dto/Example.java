package dto;

import annotations.ClassAnnotation;
import annotations.FieldAnnotation;
import annotations.MethodAnnotation;
import annotations.ParameterAnnotation;

@ClassAnnotation(id=123456)
public class Example {
	
	@FieldAnnotation(length = 5)
	private String word = "Some word";

	
	@MethodAnnotation(access = true)
	void foo(String bar, @ParameterAnnotation(negativesAllowed=false) int val) {}

}
