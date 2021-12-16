package subway.domain;

public class Line {
    private String name;

    private Line(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Line of(String name) {
        return new Line(name);
    }
}
