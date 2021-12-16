package subway.view;

import subway.domain.LineRepository;
import subway.domain.Station;
import subway.domain.StationRepository;

public class Validator {
	public static void validateLength(String value, int length, String errorMessage) {
		if (value.length() < length) {
			throw new IllegalArgumentException(errorMessage);
		}
	}

	public static void validateIsExistStation(String value, String errorMessage) {
		if (StationRepository.existsByName(value)) {
			throw new IllegalArgumentException(errorMessage);
		}
	}

	public static void validateIsNotExistStation(String value, String errorMessage) {
		if (!StationRepository.existsByName(value)) {
			throw new IllegalArgumentException(errorMessage);
		}
	}

	public static void validateIsStationIncludeInLine(String value, String errorMessage) {
		if (isIncludeInLine(value)) {
			throw new IllegalArgumentException(errorMessage);
		}
	}

	private static boolean isIncludeInLine(String value) {
		Station station = StationRepository.findByName(value).get();
		return LineRepository.lines().stream()
			.anyMatch(l -> l.hasStation(station));
	}

	public static void validateExistLine(String value, String errorMessage) {
		if (LineRepository.existsByName(value)) {
			throw new IllegalArgumentException(errorMessage);
		}
	}

	public static void validateNotExistLine(String value, String errorMessage) {
		if (!StationRepository.existsByName(value)) {
			throw new IllegalArgumentException(errorMessage);
		}
	}
}