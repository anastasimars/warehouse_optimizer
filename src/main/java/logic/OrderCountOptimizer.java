package logic;

import logic.model.Order;
import logic.model.PickersData;

import java.time.Duration;
import java.time.LocalTime;
import java.util.*;

public class OrderCountOptimizer {

    public Map<String, List<Order>> optimizeOrderCount(PickersData pickersData, List<Order> orders) {
        orders.sort(Comparator.comparing(Order::getCompleteBy));


        LocalTime startTime = pickersData.getPickingStartTime();
        LocalTime endTime = pickersData.getPickingEndTime();
        Map<String, LocalTime> pickerWorkTimes = new HashMap<>();
        for (String picker : pickersData.getPickers()) {
            pickerWorkTimes.put(picker, startTime);
        }

        Map<String, List<Order>> assignedOrders = new HashMap<>();
        for (Order order : orders) {
            String assignedPicker = null;
            LocalTime earliestFinishTime = endTime;

            for (Map.Entry<String, LocalTime> entry : pickerWorkTimes.entrySet()) {
                LocalTime finishTime = entry.getValue().plus(order.getPickingTime());
                if (finishTime.isBefore(order.getCompleteBy()) && finishTime.isBefore(earliestFinishTime)) {
                    assignedPicker = entry.getKey();
                    earliestFinishTime = finishTime;
                }
            }

            if (assignedPicker != null) {
                if (!assignedOrders.containsKey(assignedPicker)) {
                    assignedOrders.put(assignedPicker, new ArrayList<>());
                }
                assignedOrders.get(assignedPicker).add(order);
                pickerWorkTimes.put(assignedPicker, earliestFinishTime);
            }
        }

        return assignedOrders;
    }
}
