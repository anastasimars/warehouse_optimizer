package logic;

import logic.model.Order;
import logic.model.PickersData;

import java.time.Duration;
import java.time.LocalTime;
import java.util.*;

public class OrderCountOptimizer {

    public Map<Order, String> optimizeOrderCount(PickersData pickersData, List<Order> orders) {
        Comparator<Order> ordersByUrgency = Comparator.comparing(Order::getCompleteBy).reversed();
        Comparator<Order> ordersByExecution = ordersByUrgency.thenComparing(Order::getPickingTime).reversed();
        orders.sort(ordersByExecution);


        LocalTime pickingStartTime = pickersData.getPickingStartTime();
        LocalTime pickingEndTime = pickersData.getPickingEndTime();
        Map<String, LocalTime> pickerWorkTimes = new HashMap<>();
        for (String picker : pickersData.getPickers()) {
            pickerWorkTimes.put(picker, pickingStartTime);
        }

        Map<Order, String> assignedOrders = new HashMap<>();
        for (Order order : orders) {

            String assignedPicker = null;
            LocalTime earliestFinishTime = pickingEndTime;

            for (Map.Entry<String, LocalTime> pickerEntry : pickerWorkTimes.entrySet()) {
                LocalTime finishTimeToPickOneOrder = pickerEntry.getValue().plus(order.getPickingTime());
                if (finishTimeToPickOneOrder.isBefore(order.getCompleteBy().plusMinutes(1))
                        && finishTimeToPickOneOrder.isBefore(earliestFinishTime.plusMinutes(1))) {
                    assignedPicker = pickerEntry.getKey();
                    earliestFinishTime = finishTimeToPickOneOrder;
                }
            }

            if (assignedPicker != null) {
                assignedOrders.put(order, assignedPicker);
                pickerWorkTimes.put(assignedPicker, earliestFinishTime);

            }
        }

        return assignedOrders;
    }
}
