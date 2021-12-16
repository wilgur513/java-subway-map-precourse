package subway.controller;

import static subway.view.InputView.inputOption;
import static subway.view.OutputView.*;
import java.util.function.Supplier;
import subway.domain.Station;
import subway.domain.StationRepository;
import subway.view.InputView;
import subway.view.OutputView;

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
			String stationName = retryInput(InputView::inputAddStation);
			Station station = Station.of(stationName);
			StationRepository.addStation(station);
			OutputView.printInfoMessage("지하철 역이 등록되었습니다.");
		} else if(option.equals("2")) {
			String stationName = retryInput(InputView::inputRemoveStation);
			StationRepository.deleteStation(stationName);
			OutputView.printInfoMessage("지하철 역이 삭제되었습니다.");
		} else if(option.equals("3")) {
			OutputView.printStations(StationRepository.stations());
		}
	}

	private String retryInput(Supplier<String> supplier) {
		try {
			return supplier.get();
		} catch (IllegalArgumentException e) {
			printErrorMessage(e.getMessage());
			return retryInput(supplier);
		}
	}
}
