package subway;

import static subway.view.InputView.inputOption;
import static subway.view.OutputView.printMainPage;

public class Application {
    public static void main(String[] args) {
        printMainPage();
        String option = inputOption();
    }
}
