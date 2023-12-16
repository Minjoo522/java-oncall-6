package oncall.domain;

import static oncall.config.ErrorMessage.NAME_LENGTH_OUT_OF_BOUND;
import static oncall.config.ErrorMessage.NOT_EMPTY_NAME;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

public class StaffTest {
    @DisplayName("[Exception] 직원의 이름이 공백이면 예외가 발생한다")
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "\t", "\n"})
    void createStaffByNull(String input) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Staff(input))
                .withMessage(NOT_EMPTY_NAME.getMessage());
    }

    @DisplayName("[Exception] 직원의 이름이 5자를 초과하면 예외가 발생한다")
    @Test
    void createStaffByOutOfBoundNameLength() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Staff("다섯자넘는이름"))
                .withMessage(NAME_LENGTH_OUT_OF_BOUND.getMessage());
    }
}
