package subway.controller;

import static subway.view.InputView.inputOption;
import static subway.view.OutputView.*;
import java.util.function.Supplier;
import subway.application.LineService;
import subway.domain.LineRepository;
import subway.domain.Station;
import subway.domain.StationRepository;
import subway.view.InputView;
import subway.view.OutputView;

public class SubwayController {
	public void service() {
		while (true) {
			printMainPage();
			String option = retryInput(() -> inputOption("1", "2", "3", "4", "Q"));

			if(option.equals("Q")) {
				break;
			}

			handleMainPage(option);
		}
	}

	private void handleMainPage(String option) {
		if (option.equals("1")) {
			manageStationPage();
		} else if (option.equals("2")) {
			manageLinePage();
		} else if (option.equals("3")) {
			manageSectionPage();
		} else if(option.equals("4")) {
			printSubwayMapPage();
		}
	}

	private void manageStationPage() {
		printManageStationPage();
		String option = retryInput(() -> inputOption("1", "2", "3", "B"));
		handleManageStationPage(option);
	}

	private void handleManageStationPage(String option) {
		if (option.equals("1")) {
			String stationName = retryInput(InputView::inputAddStation);
			StationRepository.addStation(Station.of(stationName));
			OutputView.printInfoMessage("지하철 역이 등록되었습니다.");
		} else if (option.equals("2")) {
			String stationName = retryInput(InputView::inputRemoveStation);
			StationRepository.deleteStation(stationName);
			OutputView.printInfoMessage("지하철 역이 삭제되었습니다.");
		} else if (option.equals("3")) {
			OutputView.printStations(StationRepository.stations());
		}
	}

	private void manageLinePage() {
		printManageLinePage();
		String option = retryInput(() -> inputOption("1", "2", "3", "B"));
		handleManageLinePage(option);
	}

	private void handleManageLinePage(String option) {
		if (option.equals("1")) {
			addLine();
			OutputView.printInfoMessage("지하철 노선이 등록되었습니다.");
		} else if (option.equals("2")) {
			String lineName = retryInput(InputView::inputRemoveLine);
			LineRepository.deleteLineByName(lineName);
			OutputView.printInfoMessage("지하철 노선이 삭제되었습니다.");
		} else if (option.equals("3")) {
			OutputView.printLines(LineRepository.lines());
		}
	}

	private void addLine() {
		try {
			String lineName = retryInput(InputView::inputAddLine);
			String startName = retryInput(() -> InputView.inputLastStation("상행"));
			String lastName = retryInput(() -> InputView.inputLastStation("하행"));
			LineService.addLine(lineName, startName, lastName);
		} catch (IllegalArgumentException e) {
			printErrorMessage(e.getMessage());
			addLine();
		}
	}

	private void manageSectionPage() {
		printManageSectionPage();
		String option = retryInput(() -> inputOption("1", "2", "B"));
		handleManageSectionPage(option);
	}

	private void handleManageSectionPage(String option) {
		if (option.equals("1")) {
			addSection();
		} else if(option.equals("2")) {
			removeSection();
		}
	}

	private void addSection() {
		try {
			String lineName = retryInput(InputView::inputExistLine);
			String stationName = retryInput(InputView::inputExistStation);
			String order = retryInput(InputView::inputStationOrder);
			LineService.addSection(lineName, stationName, Integer.valueOf(order));
			OutputView.printInfoMessage("구간이 등록되었습니다.");
		} catch (IllegalArgumentException e) {
			printErrorMessage(e.getMessage());
			addSection();
		}
	}

	private void removeSection() {
		try {
			String lineName = retryInput(InputView::inputExistLine);
			String stationName = retryInput(InputView::inputExistStation);
			LineService.deleteSection(lineName, stationName);
			OutputView.printInfoMessage("구간이 삭제되었습니다.");
		} catch (IllegalArgumentException e) {
			printErrorMessage(e.getMessage());
		}
	}

	private void printSubwayMapPage() {
		OutputView.printSubwayMap(LineRepository.lines());
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
