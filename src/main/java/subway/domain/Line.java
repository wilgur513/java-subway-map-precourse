package subway.domain;

import java.util.ArrayList;
import java.util.List;

public class Line {
    private String name;
    private final List<Section> sections;

    private Line(String name) {
        this.name = name;
        sections = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public static Line of(String name) {
        return new Line(name);
    }

    public void addSection(Section section) {
        sections.add(section);
    }

    public boolean hasStation(Station station) {
        return sections.stream().anyMatch(s -> s.isStationOf(station));
    }
}
