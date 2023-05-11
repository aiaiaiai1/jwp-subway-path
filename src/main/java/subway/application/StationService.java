package subway.application;

import org.springframework.stereotype.Service;
import subway.dao.StationDao2;
import subway.domain.Station3;
import subway.dto.StationCreateRequest;
import subway.dto.StationResponse;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StationService {
    private final StationDao2 stationDao;

    public StationService(StationDao2 stationDao) {
        this.stationDao = stationDao;
    }

    public StationResponse saveStation(StationCreateRequest stationRequest) {
//        Station station = stationDao.insert(new Station(stationRequest.getName()));
//        return StationResponse.of(station);
        return null;
    }

    public StationResponse findStationResponseById(Long id) {
        return StationResponse.of(stationDao.findById(id));
    }

    public List<StationResponse> findAllStationResponses() {
        List<Station3> stations = stationDao.findAll();

        return stations.stream()
                .map(StationResponse::of)
                .collect(Collectors.toList());
    }

    public void updateStation(Long id, StationCreateRequest stationRequest) {
//        stationDao.update(new Station(id, stationRequest.getName()));
    }

    public void deleteStationById(Long id) {
        stationDao.deleteById(id);
    }
}
