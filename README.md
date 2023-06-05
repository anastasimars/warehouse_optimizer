# warehouse_optimizer

This application has been created as a tool for generating an order fulfillment schedule for warehouse employees. The employees are responsible for gathering items ordered by customers online.

Features
-------------------------
* Generating an order fulfillment schedule for pickers based on available orders.
* Taking into account the start and end time for order fulfillment in the warehouse.
* Assigning a list of orders to be fulfilled for each picker.
* Optimizing fulfilled orders by considering the quantity of orders, skipping orders that cannot be completed on time.
* Optimizing fulfilled orders by considering the value of the orders.
* 
System Requirements
-------------------------
* Java 17
* Two JSON configuration files: store.json and orders.json

Store Configuration (store.json)
-------------------------
The store.json file contains the store configuration where you can specify parameters related to the pickers and the start and end time for order fulfillment.
Sample file path: /home/tester/files/store.json

Attributes:

* pickers (List<String>) - A list of picker identifiers who fulfill the orders. The number of pickers does not exceed 50.
* pickingStartTime (LocalTime) - The time when order fulfillment starts in the store.
* pickingEndTime (LocalTime) - The time when order fulfillment ends in the store.

Order List (orders.json)
-------------------------
The orders.json file contains the list of orders for a given day, which will be used to generate the order fulfillment schedule.
Sample file path: /home/tester/files/orders.json

Order attributes:

* orderId (String) - The identifier of the order.
* orderValue (BigDecimal) - The value of the order.
* pickingTime (Duration) - The time needed to fulfill the order.
* completeBy (LocalTime) - The latest time by which the order must be fulfilled and ready for pickup.
  
Running the Application
-------------------------
To run the application, you need to provide the absolute paths to both configuration files (store.json and orders.json) as command-line arguments.
After running, the application will generate an order fulfillment schedule for the pickers, taking into account the available orders and store configuration parameters.
