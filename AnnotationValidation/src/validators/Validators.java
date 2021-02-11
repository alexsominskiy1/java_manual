package validators;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import validators.builtin_annotations.DoubleRange;
import validators.builtin_annotations.LocalDateRange;
import validators.builtin_annotations.PatternString;

public class Validators {
	
	@SuppressWarnings("unused")
	private static String check(PatternString ann, String str) {
		if (str == null) return ann.message();
		return Pattern.matches(ann.pattern(), (String)str) ? null : ann.message() + ann.pattern();
	}
	
	@SuppressWarnings("unused")
	private static String check(DoubleRange ann, double val) {
		return ann.min()<= val && ann.max() > val ? null : ann.message()+"["+ann.min()+","+ann.max()+")";
	}
			 
	@SuppressWarnings("unused")
	private static String check(LocalDateRange ann, LocalDate localDate) {
		if (localDate == null) return ann.message();
		String dateStr = ((LocalDate)localDate).format(DateTimeFormatter.ISO_LOCAL_DATE);
		return ann.from().compareTo(dateStr) <= 0 && ann.to().compareTo(dateStr) > 0 ? null : 
			 ann.message() + "[" + ann.from() + "," + ann.to()+")";
	}
 
	public static void validateField(Class<?> clazz, String name, Object value){
		try {
			Field field = clazz.getDeclaredField(name);
			if (!field.isAnnotationPresent(Validate.class)) return;
			
			final List<String> violations = new ArrayList<>();
			Arrays.stream(field.getDeclaredAnnotations())
					.filter(ann -> ann.annotationType().isAnnotationPresent(ValidatorAnnotation.class))			
					.forEach(ann -> {
						Class<? extends Annotation> annType = ann.annotationType();
						try {
							ValidatorAnnotation valAnn = annType.getAnnotation(ValidatorAnnotation.class);
							String result = (String) Class.forName(valAnn.className())
								           .getDeclaredMethod(valAnn.method(), annType, valAnn.valueType())
								           .invoke(null, ann, value);		
							if (result != null) violations.add("\n  Wrong data: field=" + 
								                              field.getName() + "; value=" + value + ". " + result);
						}catch (Exception e){
							e.printStackTrace();
						}			
					});
			
			if (violations.size() > 0) {
				String viols = violations.toString();
				throw new ValidationException(viols.substring(1, viols.length()-1));
			}
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}				
	}

}
