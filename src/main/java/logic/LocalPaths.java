package logic;
public enum LocalPaths {

    ADVANCED_OPTIMIZE_COUNT_ORDERS("src/test/resources/test-data/advanced-optimize-order-count/orders.json"),
    ADVANCED_OPTIMIZE_COUNT_STORE("src/test/resources/test-data/advanced-optimize-order-count/store.json");
    /*OPTIMIZE_ORDER_COUNT_ORDERS("optimize-order-count/orders.json"),
    OPTIMIZE_ORDER_COUNT_STORE("advanced-optimize-order-count/store.json"),
    OPTIMIZE_ORDER_VALUE_ORDERS("advanced-optimize-order-value/orders.json"),
    OPTIMIZE_ORDER_VALUE_STORE("advanced-optimize-order-value/store.json"),
    ORDER_LENGTH_IS_OK_ORDERS("any-order-length-is-ok/orders.json"),
    ORDER_LENGTH_IS_OK_STORE("any-order-length-is-ok/store.json"),
    COMPLETE_BY_ORDERS("complete-by/orders.json"),
    COMPLETE_BY_STORE("complete-by/store.json"),
    ALLOCATION_STORE("advanced-allocation/store.json"),
    ALLOCATION_ORDERS("advanced-allocation/orders.json");*/



    private final String path;

    LocalPaths(String path) {
        this.path = path;
    }

    public String path() {
        return path;
    }
}


