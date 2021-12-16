package subway.domain;

import java.util.ArrayList;
import java.util.List;

public class Line {
    private String name;
    private final List<Station> stations;

    private Line(String name) {
        this.name = name;
        stations = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public static Line of(String name, Station start, Station end) {
        Line line = new Line(name);
        line.addFirst(start);
        line.addLast(end);
        return line;
    }

    public void addFirst(Station station) {
        stations.add(0, station);
    }

    public void addLast(Station station) {
        stations.add(station);
    }

    public void addStation(Station station, int index) {
        stations.add(index, station);
    }

    public boolean hasStation(Station station) {
        return stations.stream().anyMatch(s -> s.equals(station));
    }

    @Override
    public String toString() {
        return "Line{" +
            "name='" + name + '\'' +
            ", stations=" + stations +
            '}';
    }

    public boolean isValidOrder(Integer order) {
        return order >= 0 && order <= stations.size();
    }

    public void deleteStation(Station station) {
        stations.remove(station);
    }

    public boolean isRemovableStation() {
        return stations.size() > 2;
    }
}
