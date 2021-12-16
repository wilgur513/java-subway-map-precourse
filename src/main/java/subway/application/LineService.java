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

	private static Station findStationByName(String name) {
		return StationRepository.findByName(name).get();
	}
}
