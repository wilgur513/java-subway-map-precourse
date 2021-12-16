package subway.view;

import java.util.List;
import subway.domain.Line;
import subway.domain.Station;

public class OutputView {
	public static void printMainPage() {
		printHeader("화면 출력");
		printOptionMenu("1", "역 관리");
		printOptionMenu("2", "노선 관리");
		printOptionMenu("3", "구간 관리");
		printOptionMenu("4", "지하철 노선도 출력");
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

	public static void printStations(List<Station> stations) {
		printHeader("역 목록");
		stations.stream().map(Station::getName).forEach(OutputView::printInfo);
		printBlankLine();
	}

	public static void printLines(List<Line> lines) {
		printHeader("노선 목록");
		lines.stream().map(Line::getName).forEach(OutputView::printInfo);
		printBlankLine();
	}

	public static void printSubwayMap(List<Line> lines) {
		printHeader("지하철 노선도");
		lines.stream().forEach(OutputView::printSubwayMapEachLine);
	}

	private static void printSubwayMapEachLine(Line l) {
		printInfo(l.getName());
		printInfo("---");
		l.getStations().stream().map(Station::getName).forEach(OutputView::printInfo);
		printBlankLine();
	}

	static void printHeader(String message) {
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

	static void printBlankLine() {
		System.out.println();
	}
}
