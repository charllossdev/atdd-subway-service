package nextstep.subway.station.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import nextstep.subway.station.domain.Station;

@AllArgsConstructor
@Getter
public class StationRequest {
	private String name;
	
	public Station toStation() {
		return new Station(name);
	}
}
