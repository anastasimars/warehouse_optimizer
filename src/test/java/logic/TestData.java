package logic;

import logic.model.Order;
import logic.model.PickersData;

import java.util.List;

public interface TestData {
    List<Order> PREPARED_ORDERS_ADVANCED_OPTIMIZE_COUNT = FileParser.parseJsonToList(LocalPaths.ADVANCED_OPTIMIZE_COUNT_ORDERS.path(), Order.class);
    List<Order> PREPARED_ORDERS_COMPLETE_BY = FileParser.parseJsonToList(LocalPaths.COMPLETE_BY_ORDERS.path(), Order.class);
    List<Order> PREPARED_ORDERS_END_TIME = FileParser.parseJsonToList(LocalPaths.ISF_END_TIME_ORDERS.path(), Order.class);
    PickersData PREPARED_PICKERS_DATA_ADVANCED_OPTIMIZE_COUNT = FileParser.parseJsonToObject(LocalPaths.ADVANCED_OPTIMIZE_COUNT_STORE.path(), PickersData.class);
    PickersData PREPARED_PICKERS_DATA_COMPLETE_BY = FileParser.parseJsonToObject(LocalPaths.COMPLETE_BY_STORE.path(), PickersData.class);
    PickersData PREPARED_PICKERS_DATA_END_TIME = FileParser.parseJsonToObject(LocalPaths.ISF_END_TIME_STORE.path(), PickersData.class);

}
