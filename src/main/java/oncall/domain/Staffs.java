package oncall.domain;

import static oncall.config.Config.MAXIMUM_STAFFS_SIZE;
import static oncall.config.Config.MINIMUM_STAFFS_SIZE;
import static oncall.config.ErrorMessage.INVALID_INPUT;
import static oncall.config.ErrorMessage.NAME_DUPLICATED;
import static oncall.config.ErrorMessage.STAFFS_SIZE_OUT_OF_BOUND;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Staffs {
    private final List<Staff> weekdaysStaffs;
    private List<Staff> weekendsStaffs;

    public Staffs(List<String> staffNames) {
        validate(staffNames);
        this.weekdaysStaffs = createStaffs(staffNames);
    }

    public void createWeekendsStaffs(List<String> staffNames) {
        validate(staffNames);
        this.weekendsStaffs = findStaffs(staffNames);
    }

    private void validate(List<String> staffNames) throws IllegalArgumentException {
        validateNotDuplicate(staffNames);
        validateNotOutOfBound(staffNames);
    }

    private void validateNotOutOfBound(List<String> staffNames) {
        if (staffNames.size() < MINIMUM_STAFFS_SIZE || staffNames.size() > MAXIMUM_STAFFS_SIZE) {
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

    private List<Staff> findStaffs(List<String> staffNames) throws IllegalArgumentException {
        List<Staff> weekendsStaffs = new ArrayList<>();
        for (String name : staffNames) {
            weekdaysStaffs.stream()
                    .filter(staff -> staff.getName().equals(name))
                    .findFirst()
                    .ifPresent(weekendsStaffs::add);
        }
        if (weekdaysStaffs.size() != weekendsStaffs.size()) {
            throw new IllegalArgumentException(INVALID_INPUT.getMessage());
        }
        return weekendsStaffs;
    }

    public Staff getStaff(String category, int date) {
        if (Objects.equals(category, "평일")) {
            return weekdaysStaffs.get(date % weekendsStaffs.size());
        }
        if (Objects.equals(category, "휴일")) {
            return weekendsStaffs.get(date % weekendsStaffs.size());
        }
        return null;
    }

    public Staff getNextStaff(String category, int date) {
        if (Objects.equals(category, "평일")) {
            return weekdaysStaffs.get(date % weekendsStaffs.size() + 1);
        }
        if (Objects.equals(category, "휴일")) {
            return weekendsStaffs.get(date % weekendsStaffs.size() + 1);
        }
        return null;
    }
}
