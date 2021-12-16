package subway.view;

import static subway.view.Validator.*;
import java.util.Scanner;

public class InputView {
	private static final Scanner SCANNER = new Scanner(System.in);

	public static String inputOption(String... validValues) {
		String value = inputWithMessage("원하는 기능을 선택하세요.");
		validateIsIncludeIn(value, "선택할 수 없는 기능입니다.", validValues);
		return value;
	}

	public static String inputAddStation() {
		String value = inputWithMessage("등록할 역 이름을 입력하세요.");
		validateLength(value, 2, "역 이름은 2글자 이상이여야 합니다.");
		validateIsExistStation(value, "이미 등록된 역 이름입니다.");
		return value;
	}

	public static String inputRemoveStation() {
		String value = inputWithMessage("삭제할 역 이름을 입력하세요.");
		validateIsNotExistStation(value, "등록되지 않은 역 이름입니다.");
		validateIsStationIncludeInLine(value, "노선에 등록된 역입니다.");
		return value;
	}

	public static String inputAddLine() {
		String value = inputWithMessage("등록할 노선 이름을 입력하세요.");
		validateLength(value, 2, "노선 이름은 2글자 이상이여야 합니다.");
		validateIsExistLine(value, "이미 등록된 노선입니다.");
		return value;
	}

	public static String inputLastStation(String type) {
		String value = inputWithMessage("등록할 노선의 " + type + " 종점역 이름을 입력하세요.");
		validateIsNotExistStation(value, "등록되지 않은 역 이름입니다.");
		return value;
	}

	public static String inputRemoveLine() {
		String value = inputWithMessage("삭제할 노선 이름을 입력하세요.");
		validateIsNotExistLine(value, "등록되지 않은 노선 이름입니다.");
		return value;
	}

	public static String inputExistLine() {
		String value = inputWithMessage("노선을 입력하세요.");
		validateIsNotExistLine(value, "동록되지 않은 노선 이름입니다.");
		return value;
	}

	public static String inputExistStation() {
		String value = inputWithMessage("역이름을 입력하세요.");
		validateIsNotExistStation(value, "등록되지 않은 역 이름입니다.");
		return value;
	}

	public static String inputStationOrder() {
		String value = inputWithMessage("순서를 입력하세요.");
		validateIsNumber(value, "순서는 숫자여야 합니다.");
		return value;
	}

	private static String inputWithMessage(String message) {
		System.out.println("## " + message);
		String value = SCANNER.next();
		System.out.println();
		return value;
	}
}
