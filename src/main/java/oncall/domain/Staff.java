package oncall.domain;

import static oncall.config.Config.MAXIMUM_NAME_LENGTH;
import static oncall.config.ErrorMessage.NOT_EMPTY_NAME;
import static oncall.config.ErrorMessage.NAME_LENGTH_OUT_OF_BOUND;

public class Staff {
    private final String name;

    public Staff(String name) {
        validate(name);
        this.name = name.trim();
    }

    private void validate(String name) throws IllegalArgumentException {
        validateNotNull(name);
        validateLength(name);
    }

    private void validateNotNull(String name) throws IllegalArgumentException {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(NOT_EMPTY_NAME.getMessage());
        }
    }

    private void validateLength(String name) throws IllegalArgumentException {
        if (name.trim().length() > MAXIMUM_NAME_LENGTH) {
            throw new IllegalArgumentException(NAME_LENGTH_OUT_OF_BOUND.getMessage());
        }
    }

    public String getName() {
        return this.name;
    }
}
