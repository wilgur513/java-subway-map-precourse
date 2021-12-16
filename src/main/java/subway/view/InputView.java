package subway.view;

import java.util.Scanner;

public class InputView {
	private static final Scanner SCANNER = new Scanner(System.in);

	public static String inputOption() {
		System.out.println("## 원하는 기능을 선택하세요.");
		String option = SCANNER.next();
		System.out.println();
		return option;
	}
}
