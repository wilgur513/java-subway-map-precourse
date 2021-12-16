package subway.view;

public class OutputView {
	public static void printMainPage() {
		printHeader("화면 출력");
		printOptionMenu("1", "역 관리");
		printOptionMenu("2", "노선 관리");
		printOptionMenu("3", "구간 관리");
		printOptionMenu("Q", "종료");
		printBlankLine();
	}

	public static void printManageStationPage() {
		printHeader("역 관리 화면");
		printOptionMenu("1", "역 등록");
		printOptionMenu("2", "역 삭제");
		printOptionMenu("3", "역 조회");
		printOptionMenu("B", "돌아가기");
		printBlankLine();
	}

	public static void printManageLinePage() {
		printHeader("노선 관리 화면");
		printOptionMenu("1", "노선 등록");
		printOptionMenu("2", "노선 삭제");
		printOptionMenu("3", "노선 조회");
		printOptionMenu("B", "돌아가기");
		printBlankLine();
	}

	public static void printManageSectionPage() {
		printHeader("구간 관리 화면");
		printOptionMenu("1", "구간 등록");
		printOptionMenu("2", "구간 삭제");
		printOptionMenu("B", "돌아가기");
		printBlankLine();
	}

	public static void printErrorMessage(String message) {
		printError(message);
		printBlankLine();
	}

	public static void printInfoMessage(String message) {
		printInfo(message);
		printBlankLine();
	}

	private static void printHeader(String message) {
		System.out.println("## " + message);
	}

	private static void printOptionMenu(String option, String name) {
		System.out.println(option + ". " + name);
	}

	private static void printError(String message) {
		System.out.println("[ERROR] " + message);
	}

	private static void printInfo(String message) {
		System.out.println("[INFO] " + message);
	}

	private static void printBlankLine() {
		System.out.println();
	}
}
