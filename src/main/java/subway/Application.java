package subway;

import static subway.view.InputView.inputOption;
import static subway.view.OutputView.printMainPage;
import static subway.view.OutputView.printManageStationPage;

public class Application {
	public static void main(String[] args) {
		printMainPage();
		String option = inputOption();

		if (option.equals("1")) {
            printManageStationPage();
		}
	}
}
