package logic;
public enum LocalPaths {

    ADVANCED_OPTIMIZE_COUNT_ORDERS("src/test/resources/test-data/advanced-optimize-order-count/orders.json"),
    ADVANCED_OPTIMIZE_COUNT_STORE("src/test/resources/test-data/advanced-optimize-order-count/store.json"),
    OPTIMIZE_ORDER_COUNT_ORDERS("src/test/resources/test-data/optimize-order-count/orders.json"),
    OPTIMIZE_ORDER_COUNT_STORE("src/test/resources/test-data/optimize-order-count/store.json"),
    OPTIMIZE_ORDER_VALUE_ORDERS("src/test/resources/test-data/advanced-optimize-order-value/orders.json"),
    OPTIMIZE_ORDER_VALUE_STORE("src/test/resources/test-data/advanced-optimize-order-value/store.json"),
    ISF_END_TIME_ORDERS("src/test/resources/test-data/isf-end-time/orders.json"),
    ISF_END_TIME_STORE("src/test/resources/test-data/isf-end-time/store.json"),
    COMPLETE_BY_ORDERS("src/test/resources/test-data/complete-by/orders.json"),
    COMPLETE_BY_STORE("src/test/resources/test-data/complete-by/store.json"),
    ORDER_LENGTH_IS_OK_ORDERS("src/test/resources/test-data/any-order-length-is-ok/orders.json"),
    ORDER_LENGTH_IS_OK_STORE("src/test/resources/test-data/any-order-length-is-ok/store.json"),
    ALLOCATION_ORDERS("src/test/resources/test-data/advanced-allocation/orders.json"),
    ALLOCATION_STORE("src/test/resources/test-data/advanced-allocation/orders.json"),
    LOGIC_BOMB_ORDERS("src/test/resources/test-data/logic-bomb/orders.json"),
    LOGIC_BOMB_STORE("src/test/resources/test-data/logic-bomb/store.json");



    private final String path;

    LocalPaths(String path) {
        this.path = path;
    }

    public String path() {
        return path;
    }
}


