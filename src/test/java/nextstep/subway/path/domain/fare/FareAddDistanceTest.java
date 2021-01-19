package nextstep.subway.path.domain.fare;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import nextstep.subway.line.domain.Distance;

class FareAddDistanceTest {

	@DisplayName("인스턴스 기반 거리요금 구하기")
	@Test
	void distanceAddFareTest() {
		// given
		Distance distance = Distance.of(66);
		FareUserAge fareUserAge = FareUserAge.ADULT;

		// when
		Money result = FareAddDistance.calculateDistance(distance, fareUserAge);

		// then
		assertThat(result).isNotNull();
		assertThat(result.getMoney()).isEqualTo(2250);
	}
}
