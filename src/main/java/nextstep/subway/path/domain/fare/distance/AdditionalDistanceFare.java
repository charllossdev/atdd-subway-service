package nextstep.subway.path.domain.fare.distance;

import lombok.AllArgsConstructor;
import nextstep.subway.line.domain.Distance;
import nextstep.subway.path.domain.fare.FareUserAge;
import nextstep.subway.path.domain.fare.Money;

@AllArgsConstructor
public class AdditionalDistanceFare implements DistanceFare {

	private static final int INCREASE_DISTANCE_FARE = 100;

	private final int minimumDistance;
	private final int increaseDistance;
	private final DistanceFare additionalDistanceFare;

	// 10 ~ 50, 50~ 00
	@Override
	public Money calculate(Distance distance, FareUserAge ageFare) {
		Money fare = ageFare.discount(calculateDistance(distance.minus(minimumDistance), increaseDistance));
		return fare.plus(additionalDistanceFare.calculate(Distance.of(minimumDistance), ageFare));
	}

	private Money calculateDistance(int distance, int rangeDistance) {
		int range = (distance - 1) / rangeDistance;
		return Money.of((int)((Math.ceil(range) + 1) * INCREASE_DISTANCE_FARE));
	}
}
