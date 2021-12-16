package subway.domain;

import java.util.Objects;

public class Station {
    private String name;

    private Station(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Station of(String name) {
        return new Station(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Station station = (Station) o;
        return Objects.equals(name, station.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
