package subway.view;

import java.util.Scanner;

public class InputView {
	private static final Scanner SCANNER = new Scanner(System.in);

	public static String inputOption() {
		return inputWithMessage("원하는 기능을 선택하세요.");
	}

	public static String inputStation() {
		return inputWithMessage("등록할 역 이름을 입력하세요.");
	}

	private static String inputWithMessage(String message) {
		System.out.println("## " + message);
		String value = SCANNER.next();
		System.out.println();
		return value;
	}
}
