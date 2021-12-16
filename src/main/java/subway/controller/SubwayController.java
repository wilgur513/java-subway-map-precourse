package subway.controller;

import subway.domain.Station;
import subway.domain.StationRepository;
import static subway.view.InputView.*;
import static subway.view.OutputView.*;

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


}
