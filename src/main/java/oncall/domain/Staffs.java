package oncall.domain;

import static oncall.config.Config.MAXIMUM_STAFFS_SIZE;
import static oncall.config.Config.MINIMUM_STAFFS_SIZE;
import static oncall.config.ErrorMessage.NAME_DUPLICATED;
import static oncall.config.ErrorMessage.STAFFS_SIZE_OUT_OF_BOUND;

import java.util.List;
import java.util.stream.Collectors;

public class Staffs {
    private final List<Staff> weekdaysStaffs;
    private List<Staff> weekendsStaffs;

    public Staffs(List<String> staffNames) {
        validate(staffNames);
        this.weekdaysStaffs = createStaffs(staffNames);
    }

    private void validate(List<String> staffNames) throws IllegalArgumentException {
        validateNotDuplicate(staffNames);
        validateNotOutOfBound(staffNames);
    }

    private void validateNotOutOfBound(List<String> staffNames) {
        if (staffNames.size() < MINIMUM_STAFFS_SIZE.getValue() || staffNames.size() > MAXIMUM_STAFFS_SIZE.getValue()) {
            throw new IllegalArgumentException(STAFFS_SIZE_OUT_OF_BOUND.getMessage());
        }
    }

    private void validateNotDuplicate(List<String> staffNames) throws IllegalArgumentException {
        long uniqueNames = staffNames.stream()
                .distinct()
                .count();
        if (uniqueNames != staffNames.size()) {
            throw new IllegalArgumentException(NAME_DUPLICATED.getMessage());
        }
    }

    private List<Staff> createStaffs(List<String> staffNames) {
        return staffNames.stream()
                .map(Staff::new)
                .collect(Collectors.toList());
    }
}
