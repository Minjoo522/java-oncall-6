package oncall.view;

public class OutputView {
    private OutputView() {
        // 인스턴스 생성 방지용
    }

    public static void printErrorMessage(IllegalArgumentException exception) {
        System.out.println(exception.getMessage());
    }
}
