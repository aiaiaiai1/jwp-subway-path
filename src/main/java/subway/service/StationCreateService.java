package subway.service;

import org.springframework.stereotype.Service;
import subway.dao.DbEdgeDao;
import subway.dao.DbLine2Dao;
import subway.dao.StationDao;
import subway.domain.Line;
import subway.domain.Station;
import subway.domain.SubwayGraphs;
import subway.dto.StationAddRequest;
import subway.dto.StationResponse;

import java.util.List;

@Service
public class StationCreateService {
//이거 사용

    private final SubwayGraphs subwayGraphs;
    private final DbLine2Dao dbLine2Dao;
    private final StationDao stationDao;
    private final DbEdgeDao edgeDao;

    public StationCreateService(final SubwayGraphs subwayGraphs, final DbLine2Dao dbLine2Dao, final StationDao stationDao, final DbEdgeDao edgeDao) {
        this.subwayGraphs = subwayGraphs;
        this.dbLine2Dao = dbLine2Dao;
        this.stationDao = stationDao;
        this.edgeDao = edgeDao;
    }

    public StationResponse createStation(final StationAddRequest stationRequest) {
        final Line line = dbLine2Dao.findByName(stationRequest.getLine());

        final Station upLineStation = new Station(stationRequest.getUpLineStation());
        final Station downLineStation = new Station(stationRequest.getDownLineStation());
        final int distance = stationRequest.getDistance();

        final Station createdStation = subwayGraphs.createStation(line, upLineStation, downLineStation, distance);

        final Station savedStation = stationDao.saveStation(createdStation);

        final List<Station> allStationsInOrder = subwayGraphs.findAllStationsInOrderOf(line);

        edgeDao.deleteEdgesIn(line);

        for (final Station station : allStationsInOrder) {
            final long stationOrder = allStationsInOrder.indexOf(station);
            edgeDao.save(line, station, stationOrder);
        }

        return StationResponse.of(savedStation);
    }
}