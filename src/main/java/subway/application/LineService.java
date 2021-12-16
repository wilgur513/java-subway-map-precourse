package subway.application;

import subway.domain.Line;
import subway.domain.LineRepository;
import subway.domain.Station;
import subway.domain.StationRepository;

public class LineService {
	public static void addLine(String lineName, String startName, String lastName) {
		if (startName.equals(lastName)) {
			throw new IllegalArgumentException("상/하행 종점역이 동일합니다.");
		}

		Line line = Line.of(lineName, findStationByName(startName), findStationByName(lastName));
		LineRepository.addLine(line);
	}

	public static void addSection(String lineName, String stationName, Integer order) {
		Line line = findLineByName(lineName);
		Station station = findStationByName(stationName);
		line.addStation(station, order - 1);
	}

	private static Station findStationByName(String name) {
		return StationRepository.findByName(name).get();
	}

	private static Line findLineByName(String name) {
		return LineRepository.findByName(name).get();
	}
}
