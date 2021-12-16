package subway;

import java.util.Arrays;
import java.util.stream.IntStream;
import subway.controller.SubwayController;
import subway.domain.Line;
import subway.domain.LineRepository;
import subway.domain.Station;
import subway.domain.StationRepository;

public class Application {
	public static void main(String[] args) {
		saveInitialData();
		SubwayController controller = new SubwayController();
		controller.service();
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
		String[][] lastStationNames = {{"교대역", "역삼역"}, {"교대역", "매봉역"}, {"강남역", "양재시민의숲역"}};
		IntStream.range(0, lineNames.length)
			.mapToObj(i -> Line.of(lineNames[i],
				findStationByName(lastStationNames[i][0]),
				findStationByName(lastStationNames[i][1])))
			.forEach(LineRepository::addLine);
	}

	private static void saveSections() {
		saveSectionToLine("2호선", "강남역");
		saveSectionToLine("3호선", "남부터미널역", "양재역");
		saveSectionToLine("신분당선", "양재역");
	}

	private static void saveSectionToLine(String lineName, String... stationNames) {
		Line line = LineRepository.findByName(lineName).get();
		IntStream.range(0, stationNames.length)
			.forEach(i -> line.addStation(findStationByName(stationNames[i]), i + 1));
	}

	private static Station findStationByName(String name) {
		return StationRepository.findByName(name).get();
	}
}
