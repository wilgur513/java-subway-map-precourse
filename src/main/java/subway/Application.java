package subway;

import static subway.view.InputView.inputOption;
import static subway.view.OutputView.*;

public class Application {
	public static void main(String[] args) {
		printMainPage();
		String option = inputOption();

		if (option.equals("1")) {
            printManageStationPage();
		} else if(option.equals("2")) {
			printManageLinePage();
		}
	}
}
