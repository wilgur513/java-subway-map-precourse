package subway.controller;

import static subway.view.InputView.inputOption;
import static subway.view.OutputView.*;
import static subway.view.OutputView.printManageSectionPage;

public class SubwayController {
	public void service() {
		while(true) {
			printMainPage();
			String option = inputOption();

			if (option.equals("1")) {
				printManageStationPage();
			} else if(option.equals("2")) {
				printManageLinePage();
			} else if(option.equals("3")) {
				printManageSectionPage();
			} else if(option.equals("Q")) {
				break;
			}
		}
	}
}
