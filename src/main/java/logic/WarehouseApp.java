package logic;

import logic.model.Order;
import logic.model.PickersData;
import java.util.List;
import java.util.Map;

public class WarehouseApp {
    public static void main(String[] args) {
        List<Order> orders = FileParser.parseJsonToList(LocalPaths.ADVANCED_OPTIMIZE_COUNT_ORDERS.path(), Order.class);
        PickersData pickersData = FileParser.parseJsonToObject(LocalPaths.ADVANCED_OPTIMIZE_COUNT_STORE.path(), PickersData.class);

        OrderCountOptimizerAlternative orderCountOptimizer = new OrderCountOptimizerAlternative();


        Map<String, List<Order>> stringListMap = orderCountOptimizer.optimizeOrderCount(pickersData, orders);

        System.out.println("_________________");
        for (Order o : orders) {
            System.out.println(o);
        }

        System.out.println("_________________");

        for (Map.Entry<String, List<Order>> entry : stringListMap.entrySet()) {
            System.out.println("Picker: " + entry.getKey());
            System.out.println(entry.getValue());
        }


        System.out.println("_________________");


    }


}
