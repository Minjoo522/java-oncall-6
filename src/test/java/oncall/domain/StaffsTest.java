package oncall.domain;

import static oncall.config.ErrorMessage.NAME_DUPLICATED;
import static oncall.config.ErrorMessage.STAFFS_SIZE_OUT_OF_BOUND;
import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StaffsTest {
    @DisplayName("[Exception] 중복된 직원 이름이 있으면 예외가 발생한다.")
    @Test
    void createStaffsByDuplicatedName() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Staffs(List.of("직원1", "직원1", "직원2", "직원3", "직원4")))
                .withMessage(NAME_DUPLICATED.getMessage());
    }

    @DisplayName("[Exception] 직원 이름 개수가 5개 미만이면 예외가 발생한다.")
    @Test
    void createStaffsByLowerSize() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Staffs(List.of("직원1", "직원2", "직원4")))
                .withMessage(STAFFS_SIZE_OUT_OF_BOUND.getMessage());
    }
}
