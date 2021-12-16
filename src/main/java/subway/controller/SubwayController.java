package subway.controller;

import static subway.view.InputView.inputOption;
import static subway.view.OutputView.*;
import java.util.function.Supplier;
import subway.application.LineService;
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
				manageLine();
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
			StationRepository.addStation(Station.of(stationName));
		} else if (option.equals("2")) {
			String stationName = retryInput(InputView::inputRemoveStation);
			StationRepository.deleteStation(stationName);
			OutputView.printInfoMessage("지하철 역이 삭제되었습니다.");
		} else if (option.equals("3")) {
			OutputView.printStations(StationRepository.stations());
		}
	}

	private void manageLine() {
		printManageLinePage();
		String option = inputOption();

		if (option.equals("1")) {
			addLine();
		}
	}

	private void addLine() {
		try {
			String lineName = retryInput(InputView::inputAddLine);
			String startName = retryInput(() -> InputView.inputLastStation("상행"));
			String lastName = retryInput(() -> InputView.inputLastStation("하행"));
			LineService.addLine(lineName, startName, lastName);
			OutputView.printInfoMessage("지하철 노선이 등록되었습니다.");
		} catch (IllegalArgumentException e) {
			printErrorMessage(e.getMessage());
			addLine();
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
