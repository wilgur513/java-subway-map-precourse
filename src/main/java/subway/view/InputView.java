package subway.view;

import java.util.Scanner;
import subway.domain.LineRepository;
import subway.domain.Station;
import subway.domain.StationRepository;

public class InputView {
	private static final Scanner SCANNER = new Scanner(System.in);

	public static String inputOption() {
		return inputWithMessage("원하는 기능을 선택하세요.");
	}

	public static String inputAddStation() {
		String value = inputWithMessage("등록할 역 이름을 입력하세요.");
		validateAddStationName(value);
		return value;
	}

	public static String inputRemoveStation() {
		String value = inputWithMessage("삭제할 역 이름을 입력하세요.");
		validateRemoveStationName(value);
		return value;
	}

	public static String inputAddLine() {
		String value = inputWithMessage("등록할 노선 이름을 입력하세요.");
		validateAddLineName(value);
		return value;
	}

	private static String inputWithMessage(String message) {
		System.out.println("## " + message);
		String value = SCANNER.next();
		System.out.println();
		return value;
	}

	private static void validateAddStationName(String value) {
		if (value.length() < 2) {
			throw new IllegalArgumentException("역 이름은 2글자 이상이여야 합니다.");
		}
		if (StationRepository.existsByName(value)) {
			throw new IllegalArgumentException("이미 등록된 역 이름입니다.");
		}
	}

	private static void validateRemoveStationName(String value) {
		if(!StationRepository.existsByName(value)) {
			throw new IllegalArgumentException("등록되지 않은 역 이름입니다.");
		}

		if(isIncludeInLine(value)) {
			throw new IllegalArgumentException("노선에 등록된 역입니다.");
		}
	}

	private static boolean isIncludeInLine(String value) {
		Station station = StationRepository.findByName(value).get();
		return LineRepository.lines().stream()
			.anyMatch(l -> l.hasStation(station));
	}

	private static void validateAddLineName(String value) {
		if(value.length() < 2) {
			throw new IllegalArgumentException("노선 이름은 2글자 이상이여야 합니다.");
		}

		if(LineRepository.existsByName(value)) {
			throw new IllegalArgumentException("이미 등록된 노선 이름입니다.");
		}
	}
}
