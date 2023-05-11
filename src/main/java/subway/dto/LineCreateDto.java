package subway.dto;

public class LineCreateDto {

    private final String upLineStationName;
    private final String downLineStationName;
    private final String lineName;
    private final double distance;

    public LineCreateDto(final String upLineStationName, final String downLineStationName, final String lineName, final double distance) {
        this.upLineStationName = upLineStationName;
        this.downLineStationName = downLineStationName;
        this.lineName = lineName;
        this.distance = distance;
    }

    public static LineCreateDto from(final LineCreateRequest lineRequest) {
        return new LineCreateDto(
                lineRequest.getUpLineStation(),
                lineRequest.getDownLineStation(),
                lineRequest.getLine(),
                lineRequest.getDistance());
    }

    public String getUpLineStationName() {
        return upLineStationName;
    }

    public String getDownLineStationName() {
        return downLineStationName;
    }

    public String getLineName() {
        return lineName;
    }

    public double getDistance() {
        return distance;
    }
}
