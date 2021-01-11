package nextstep.subway.path.application;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import nextstep.subway.common.exception.NothingException;
import nextstep.subway.line.application.LineService;
import nextstep.subway.line.domain.Line;
import nextstep.subway.path.dto.PathRequest;
import nextstep.subway.station.domain.Station;

@DisplayName("First Outside In Test")
@ExtendWith(MockitoExtension.class)
class PathServiceTest {

	@Mock
	private LineService lineService;

	private PathService pathService;

	private Line line1;
	private Line line2;
	private Station 시청역;
	private Station 서초역;
	private Station 인천역;
	private Station 주안역;

	@BeforeEach
	void setUp() {
		pathService = new PathService(lineService);

		시청역 = new Station(1L, "시청역");
		서초역 = new Station(2L, "서초역");
		인천역 = new Station(3L, "인천역");
		주안역 = new Station(4L, "주안역");

		line1 = new Line(1L, "1호선", "blue", 인천역, 주안역, 50);
		line2 = new Line(2L, "2호선", "green", 시청역, 서초역, 100);
	}

	@DisplayName("경로찾기: [에러]동일한 역 경로검색")
	@Test
	void foundPathSameStationTest() {
		// given
		when(lineService.findStationById(1L)).thenReturn(시청역);
		PathRequest request = new PathRequest(시청역.getId(), 시청역.getId());

		// when // then
		assertThatExceptionOfType(RuntimeException.class)
			.isThrownBy(() -> pathService.findPath(request));
	}

	@DisplayName("경로찾기: [에러]존재하지 않는 역 경로검색")
	@Test
	void notFoundStationTest() {
		// given
		when(lineService.findStationById(1L)).thenReturn(시청역);
		when(lineService.findStationById(3L)).thenThrow(NothingException.class);
		PathRequest request = new PathRequest(시청역.getId(), 3L);

		// when // then
		assertThatExceptionOfType(NothingException.class)
			.isThrownBy(() -> pathService.findPath(request));
	}

	@DisplayName("경로찾기: [에러]같은 경로에 없는 역 경로검색")
	@Test
	void notFoundPathTest() {
		// given
		when(lineService.findStationById(1L)).thenReturn(시청역);
		when(lineService.findStationById(1L)).thenReturn(인천역);
		PathRequest request = new PathRequest(시청역.getId(), 인천역.getId());

		// when // then
		assertThatExceptionOfType(NothingException.class)
			.isThrownBy(() -> pathService.findPath(request));
	}
}
