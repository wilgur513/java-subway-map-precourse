package subway;

import java.util.Arrays;
import java.util.stream.IntStream;
import subway.domain.Line;
import subway.domain.LineRepository;
import subway.domain.Section;
import subway.domain.Station;
import subway.domain.StationRepository;
import static subway.view.InputView.inputOption;
import static subway.view.OutputView.*;

public class Application {
	public static void main(String[] args) {
		saveInitialData();

		printMainPage();
		String option = inputOption();

		if (option.equals("1")) {
            printManageStationPage();
		} else if(option.equals("2")) {
			printManageLinePage();
		} else if(option.equals("3")) {
			printManageSectionPage();
		}
	}

	private static void saveInitialData() {
		saveStations();
		saveLines();
		saveSections();
	}

	private static void saveStations() {
		String[] stationNames = {"교대역", "강남역", "역삼역", "남부터미널역", "양재역", "양재시민의숲역", "매봉역"};
		Arrays.stream(stationNames).map(Station::of).forEach(StationRepository::addStation);
	}

	private static void saveLines() {
		String[] lineNames = {"2호선", "3호선", "신분당선"};
		Arrays.stream(lineNames).map(Line::of).forEach(LineRepository::addLine);
	}

	private static void saveSections() {
		saveSectionToLine("2호선", "교대역", "강남역", "역삼역");
		saveSectionToLine("3호선", "교대역", "남부터미널역", "양재역", "매봉역");
		saveSectionToLine("신분당선", "강남역", "양재역", "양재시민의숲역");
	}

	private static void saveSectionToLine(String lineName, String... stationNames) {
		Line line = LineRepository.findByName(lineName).get();
		IntStream.range(0, stationNames.length)
				.forEach(i -> line.addSection(Section.of(findStationByName(stationNames[i]), i)));
	}

	private static Station findStationByName(String name) {
		return StationRepository.findByName(name).get();
	}
}
