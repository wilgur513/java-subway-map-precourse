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

		if (line.hasStation(station)) {
			throw new IllegalArgumentException("이미 등록된 역이 노선에 있습니다.");
		}

		if (!line.isValidOrder(order - 1)) {
			throw new IllegalArgumentException("순서가 잘못되었습니다.");
		}

		line.addStation(station, order - 1);
	}

	public static void deleteSection(String lineName, String stationName) {
		Line line = findLineByName(lineName);
		Station station = findStationByName(stationName);

		if (!line.hasStation(station)) {
			throw new IllegalArgumentException("노선에 등록된 역이 없습니다.");
		}

		line.deleteStation(station);
	}

	private static Station findStationByName(String name) {
		return StationRepository.findByName(name).get();
	}

	private static Line findLineByName(String name) {
		return LineRepository.findByName(name).get();
	}
}
