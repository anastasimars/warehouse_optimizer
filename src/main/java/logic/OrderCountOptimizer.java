package logic;

import logic.model.Order;
import logic.model.PickersData;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderCountOptimizer {
    public Map<Order, String> optimizeOrderCount(PickersData pickersData, List<Order> orders) {
        // 1. Firstly need to sort order by completeBy
        orders.sort(Comparator.comparing(Order::getCompleteBy));
        //2. Next create a map with a picker and picker's work time Map<String, LocalTime> pickerWorkTime
        Map<String, LocalTime> pickerWorkTime = new HashMap<>();
        for (String picker : pickersData.getPickers()) {
            pickerWorkTime.put(picker, pickersData.getPickingStartTime());
        }
        // 3. Next create a Map<Order, String> assignedOrders where will assign a picker to order and after update a pickerWorkTime
        Map<Order, String> assignedOrders = new HashMap<>();
        for (int i = 0; i < orders.size(); i++) {
            String assignedPicker = null;
            Order actualOrder = orders.get(i);
            LocalTime earliestFinishTimeToPickOrder = pickersData.getPickingEndTime();

            for (Map.Entry<String, LocalTime> pickerEntry : pickerWorkTime.entrySet()) {
                LocalTime finishTimeToPickOneOrder = pickerEntry.getValue().plus(orders.get(i).getPickingTime());
                if (finishTimeToPickOneOrder.isBefore(orders.get(i).getCompleteBy().plusMinutes(1))
                        && finishTimeToPickOneOrder.isBefore(earliestFinishTimeToPickOrder.plusMinutes(1))) {
                    //Here we need a check for the picker, so that if p1 is already assigned, it will not be changed to p2
                    if (assignedPicker == null) {
                        assignedPicker = pickerEntry.getKey();

                    }
                    earliestFinishTimeToPickOrder = finishTimeToPickOneOrder;
                }
            }
            if (assignedOrders.isEmpty() && checkTotalTime(orders, pickersData, i, actualOrder)) {
                assignedOrders.put(actualOrder, assignedPicker);
            }
            if (assignedPicker != null && !assignedOrders.isEmpty() && !checkTotalTime(orders, pickersData, i, actualOrder)) {
                assignedOrders.put(actualOrder, assignedPicker);
                pickerWorkTime.put(assignedPicker, earliestFinishTimeToPickOrder);
            }

        }
        return assignedOrders;
    }

    private boolean checkTotalTime(List<Order> orders, PickersData pickersData, int i) {
        long totalPickingTime = orders.get(i).getPickingTime().toMinutes();
        for (int j = i + pickersData.getPickers().size(); j < orders.size(); j++) {
            totalPickingTime += orders.get(j).getPickingTime().toMinutes();
        }
        long pickingTimeLeft = Duration.between(pickersData.getPickingStartTime(), pickersData.getPickingEndTime()).plusMinutes(1).toMinutes();
        return totalPickingTime > pickingTimeLeft;
    }

}


