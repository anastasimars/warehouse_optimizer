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

        orders.sort(Comparator.comparing(Order::getCompleteBy));

        Map<String, LocalTime> pickerWorkTime = new HashMap<>();
        for (String picker : pickersData.getPickers()) {
            pickerWorkTime.put(picker, pickersData.getPickingStartTime());
        }

        Map<Order, String> assignedOrders = new HashMap<>();
        for (int i = 0; i < orders.size(); i++) {
            String assignedPicker = null;
            Order actualOrder = orders.get(i);
            LocalTime earliestFinishTimeToPickOrder = pickersData.getPickingEndTime();

            for (Map.Entry<String, LocalTime> pickerEntry : pickerWorkTime.entrySet()) {
                LocalTime finishTimeToPickOneOrder = pickerEntry.getValue().plus(actualOrder.getPickingTime());
                if (finishTimeToPickOneOrder.compareTo(orders.get(i).getCompleteBy().plusMinutes(1)) < 0
                        && finishTimeToPickOneOrder.isBefore(earliestFinishTimeToPickOrder.plusMinutes(1))) {
                    assignedPicker = pickerEntry.getKey();
                    earliestFinishTimeToPickOrder = finishTimeToPickOneOrder;


                }
            }

            if (assignedPicker != null && (assignedOrders.isEmpty() || !checkTotalTime(orders, pickersData, i))) {
                assignedOrders.put(actualOrder, assignedPicker);
                pickerWorkTime.put(assignedPicker, earliestFinishTimeToPickOrder);
            }

        }
        return assignedOrders;
    }

    private boolean checkTotalTime(List<Order> orders, PickersData pickersData, int i) {
        long totalPickingTime = orders.get(i).getPickingTime().toMinutes();
        for (int j = (i + 1) + pickersData.getPickers().size(); j < orders.size(); j++) {
            totalPickingTime += orders.get(j).getPickingTime().toMinutes();
        }
        long pickingTimeLeft = Duration.between(pickersData.getPickingStartTime(), pickersData.getPickingEndTime()).plusMinutes(1).toMinutes();
        return totalPickingTime > pickingTimeLeft;
    }

}


