package logic;

import logic.model.Order;
import logic.model.PickersData;

import java.util.List;
import java.util.Map;

public class WarehouseApp {
    public static void main(String[] args) {
        List<Order> orders = FileParser.parseJsonToList(LocalPaths.OPTIMIZE_ORDER_COUNT_ORDERS.path(), Order.class);
        for (Order order : orders) {
            System.out.println(order);
        }
        PickersData store = FileParser.parseJsonToObject(LocalPaths.OPTIMIZE_ORDER_COUNT_STORE.path(), PickersData.class);
        System.out.println(store);

        OrderCountOptimizer orderCountOptimizer = new OrderCountOptimizer();

        Map<String, List<Order>> stringListMap = orderCountOptimizer.optimizeOrderCount(store, orders);

        for (Map.Entry<String, List<Order>> entry : stringListMap.entrySet()) {
            for (Order order : entry.getValue()) {
                System.out.println(entry.getKey() + " - " + order);
            }

        }

    }
}
