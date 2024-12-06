package org.flab.api.domain;

import org.flab.api.domain.event.domain.event.Event;
import org.flab.api.domain.event.domain.seat.DiscountPolicy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.ZonedDateTime;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class DiscountPolicyTest {

    @ParameterizedTest
    @MethodSource("provideDiscountTestData")
    @DisplayName("할인된 가격 계산")
    public void discountPriceTest(int percent, long basePrice, long expectedPrice) {
        // given
        long id = 1L;
        String name = percent + " % 할인";
        Event mockEvent = mock(Event.class);

        // when
        DiscountPolicy target = new DiscountPolicy(id, name, percent, mockEvent, ZonedDateTime.now(), null);
        long result = target.getDiscountPrice(basePrice);

        // then
        assertThat(result).isEqualTo(expectedPrice);
    }

    private static Stream<Arguments> provideDiscountTestData() {
        return Stream.of(
                Arguments.of(10, 10000, 1000),  // 10% 할인
                Arguments.of(20, 15000, 3000), // 20% 할인
                Arguments.of(50, 20000, 10000),// 50% 할인
                Arguments.of(0, 10000, 0),     // 0% 할인
                Arguments.of(100, 5000, 5000)  // 100% 할인
        );
    }
}
