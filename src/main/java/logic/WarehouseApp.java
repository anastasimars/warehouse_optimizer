package logic;

import logic.model.Order;
import logic.model.PickersData;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class WarehouseApp {
    public static void main(String[] args) {
        List<Order> orders = FileParser.parseJsonToList(LocalPaths.ADVANCED_OPTIMIZE_COUNT_ORDERS.path(), Order.class);
        PickersData pickersData = FileParser.parseJsonToObject(LocalPaths.ADVANCED_OPTIMIZE_COUNT_STORE.path(), PickersData.class);

        OrderCountOptimizer orderCountOptimizer = new OrderCountOptimizer();


        Map<Order, String> stringListMap = orderCountOptimizer.optimizeOrderCount(pickersData, orders);

        for (Map.Entry<Order, String> entry : stringListMap.entrySet()) {
            System.out.println(entry.getValue() + " " + entry.getKey().getOrderId() + " " +
                    entry.getKey().getCompleteBy().minus(entry.getKey().getPickingTime()));
        }


    }
}
