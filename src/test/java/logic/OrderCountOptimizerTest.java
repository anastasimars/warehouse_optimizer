package logic;


import logic.model.Order;
import logic.model.PickersData;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

class OrderCountOptimizerTest {
    private final OrderCountOptimizer orderCountOptimizer = new OrderCountOptimizer();


    @Test
    void optimizeOrderCount() {
        //sprawdzenie, czy algorytm wybierze zrealizowanie dwóch zamówień zamiast jednego

        //given
        List<Order> givenOrders = TestData.PREPARED_ORDERS_ADVANCED_OPTIMIZE_COUNT;
        PickersData givenPickersData = TestData.PREPARED_PICKERS_DATA_ADVANCED_OPTIMIZE_COUNT;

        //and
        String picker1 = "P1";
        String picker2 = "P2";
        Map<Order, String> expectedOptimizeOrders = Map.of(
                new Order("order-1", BigDecimal.valueOf(5.00), Duration.ofMinutes(15), LocalTime.of(9, 15)), picker2,
                new Order("order-5", BigDecimal.valueOf(5.00), Duration.ofMinutes(20), LocalTime.of(10, 0)), picker1,
                new Order("order-3", BigDecimal.valueOf(10.00), Duration.ofMinutes(45), LocalTime.of(10, 0)), picker2,
                new Order("order-2", BigDecimal.valueOf(5.00), Duration.ofMinutes(30), LocalTime.of(10, 0)), picker1);

        //when
        Map<Order, String> actualOptimizeOrders = orderCountOptimizer.optimizeOrderCount(givenPickersData, givenOrders);

        //then
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(expectedOptimizeOrders).isEqualTo(actualOptimizeOrders);
    }

    @Test
    void completeBy() {
        //sprawdzenie, czy zamówienia są kompletowane przed wymaganym czasem

        //given
        List<Order> givenOrders = TestData.PREPARED_ORDERS_COMPLETE_BY;
        PickersData givenPickersData = TestData.PREPARED_PICKERS_DATA_COMPLETE_BY;
        //when

        //then
    }

    @Test
    void endTime() {
        //sprawdzenie, czy zamówienia są kompletowane przed wymaganym czasem

        //given

        //when

        //then
    }

    void logicBomb() {
        //maksymalny rozmiar problemu, który algorytm powinien obsłużyć

        //given

        //when

        //then
    }

}