package custom_validators;

import java.time.LocalDate;

import custom_validators.custom_annotations.MonthForbidden;

public class CustomValidators {
	
	public static String monthForbidden(MonthForbidden mForbidden, LocalDate date) {
				if (date == null) return "birthDate is null";
				return ((LocalDate)date).getMonth() != mForbidden.month() ? null : mForbidden.message() + mForbidden.month();
	};
}
