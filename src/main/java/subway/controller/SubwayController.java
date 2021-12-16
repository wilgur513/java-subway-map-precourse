package subway.controller;

import static subway.view.InputView.inputOption;
import static subway.view.OutputView.*;
import subway.domain.Station;
import subway.domain.StationRepository;
import subway.view.InputView;

public class SubwayController {
	public void service() {
		while (true) {
			printMainPage();
			String option = inputOption();

			if (option.equals("1")) {
				manageStation();
			} else if (option.equals("2")) {
				printManageLinePage();
			} else if (option.equals("3")) {
				printManageSectionPage();
			} else if (option.equals("Q")) {
				break;
			}
		}
	}

	private void manageStation() {
		printManageStationPage();
		String option = inputOption();

		if (option.equals("1")) {
			String stationName = inputStation();
			Station station = Station.of(stationName);
			StationRepository.addStation(station);
		}
	}

	private String inputStation() {
		try {
			return InputView.inputStation();
		} catch (IllegalArgumentException e) {
			printErrorMessage(e.getMessage());
			return inputStation();
		}
	}
}
