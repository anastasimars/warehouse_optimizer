package logic;

import logic.model.Order;
import logic.model.PickerData;
import logic.model.PickersData;

import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class OrderCountOptimizerAlternative {
    public Map<String, List<Order>> optimizeOrderCount(PickersData pickersData, List<Order> orders) {
        Map<String, List<Order>> optimizedShift = new HashMap<>();
        Comparator<Order> comparator = Comparator.comparing(Order::getCompleteBy).thenComparing(Order::getPickingTime).reversed();

        Comparator<Order> comparator1 = Comparator.comparing(Order::getCompleteBy);
        Comparator<Order> comparator2 = Comparator.comparing(Order::getPickingTime).reversed();
        orders.sort(comparator1.thenComparing(comparator2));

        List<PickerData> pickerData = parseToPickerData(pickersData);
        Set<Order> availableOrders = new HashSet<>(orders);

        for (Order order : orders) {
            for (PickerData picker : pickerData) {
                List<Order> orderList;

                if (isPossibleToTakeOrder(picker, order) && availableOrders.contains(order)) {
                    if (optimizedShift.containsKey(picker.getPickerName())) {
                        orderList = optimizedShift.get(picker.getPickerName());
                    } else {
                        orderList = new ArrayList<>();
                    }

                    orderList.add(order);
                    availableOrders.remove(order);
                    optimizedShift.put(picker.getPickerName(), orderList);
                    picker.setPickingStartTime(picker.getPickingStartTime().plus(order.getPickingTime()));
                }
            }

        }

        return optimizedShift;
    }

    private List<PickerData> parseToPickerData(PickersData pickersData) {
        List<PickerData> pickerData = new ArrayList<>();
        for (String picker : pickersData.getPickers()) {
            pickerData.add(new PickerData(picker, pickersData.getPickingStartTime(), pickersData.getPickingEndTime()));
        }

        return pickerData;
    }

    private boolean isPossibleToTakeOrder(PickerData picker, Order order) {
        LocalTime startTime = picker.getPickingStartTime();
        LocalTime endTime = picker.getPickingEndTime();
        Duration orderDuration = order.getPickingTime();

        LocalTime actualTime = startTime.plus(orderDuration);
        LocalTime completeBy = order.getCompleteBy();

        boolean isPossibleToComplete = ChronoUnit.MINUTES.between(actualTime, completeBy) >= 0;
        boolean hasTime = Duration.between(startTime, endTime).minus(orderDuration).getSeconds() >= 0;

        return isPossibleToComplete && hasTime;
    }

}
