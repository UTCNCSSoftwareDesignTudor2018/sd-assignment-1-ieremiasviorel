package services.validators;

public class PositiveQuantityValidator implements Validator {

	@Override
	public void validate(Object quantity) {
		if ((Float)quantity < 0) {
			throw new IllegalArgumentException("Grade cannot be negative number");
		}
	}
}
