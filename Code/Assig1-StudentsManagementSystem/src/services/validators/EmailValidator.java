package services.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator implements Validator {

	@Override
	public void validate(Object email) {
		
		Pattern pattern = Pattern.compile("^[A-Za-z0-9-]+(\\-[A-Za-z0-9])*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9])");
		Matcher matcher = pattern.matcher((String)email);

		if (!matcher.matches()) {
			throw new IllegalArgumentException("Invalid email format!");
		}
	}
}
