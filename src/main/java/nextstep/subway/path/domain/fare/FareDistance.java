package nextstep.subway.path.domain.fare;

import java.util.Arrays;

import lombok.AllArgsConstructor;
import nextstep.subway.line.domain.Distance;
import nextstep.subway.path.domain.fare.distance.DefaultDistanceFare;
import nextstep.subway.path.domain.fare.distance.DistanceFare;
import nextstep.subway.path.domain.fare.distance.LongDistanceFare;
import nextstep.subway.path.domain.fare.distance.MiddleDistanceFare;

@AllArgsConstructor
public enum FareDistance {
	SHORT_DISTANCE(0, 10, new DefaultDistanceFare()),
	MIDDLE_DISTANCE(11, 50, new MiddleDistanceFare()),
	LONG_DISTANCE(51, Integer.MAX_VALUE, new LongDistanceFare());

	private final int minimumDistance;
	private final int maximumDistance;
	private final DistanceFare distanceFare;

	public static Money calculateDistance(final Distance distance, final FareUserAge ageFare) {
		return findDistanceFareType(distance.getDistance()).calculate(distance, ageFare);
	}

	private static FareDistance findDistanceFareType(final int distance) {
		return Arrays.stream(values())
			.filter(it -> distance >= it.minimumDistance && distance <= it.maximumDistance)
			.findFirst()
			.orElseThrow(IllegalArgumentException::new);
	}

	private Money calculate(Distance distance, FareUserAge ageFare) {
		return this.distanceFare.calculate(distance, ageFare);
	}
}
