package subway.domain;

public class Section {
	private final Station station;
	private final int order;

	public Section(Station station, int order) {
		this.station = station;
		this.order = order;
	}

	public static Section of(Station station, int order) {
		return new Section(station, order);
	}

	public boolean isStationOf(Station station) {
		return this.station.equals(station);
	}
}
