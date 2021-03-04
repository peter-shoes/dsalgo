import java.util.ArrayList;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class AmazonOrderDetails {
    Random rd = new Random();
    public static void main(String[] args) {
        AmazonOrderDetails od = new AmazonOrderDetails();
        ArrayList<Product> products = new ArrayList<>();
        od.addProducts(products);
        Order order = od.createOrder(products);
        od.createShipment(order);
        od.createPayment(order);
        od.printOrderDetails(order);
        od.printInvoice(order);
    }

    public void addProducts(ArrayList<Product> products) {
//        this will take all hardcoded products and add them to the arraylist products
        products.add(new Product()) // put product data here it's fine
    }
    public Arraylist<OrderItem> createOrderItems(ArrayList<Product> products){
        ArrayList<OrderItem> orderItems = new ArrayList<OrderItem>();
        for (int i=0;i<4;i++){
            int randomIndex = rd.nextInt(5);
            if (!orderItems.contains(products[randomIndex])) orderItems.add(products[randomIndex]);
        }
        return orderItems;
    }
    public Order createOrder(ArrayList<Product> products) {
// this will create an order object
        Order order = null;
        int randomOrderPart = rd.nextInt(900)+100;
        String orderNumber = randomOrderPart+"-"+randomOrderPart+"-"+randomOrderPart;
        order = new Order(
                orderNumber,
                DATE,
                createOrderItems(products),


        );
    new
    }
    public void createShipment(Order order) {
// this will create the shipment object in the given order
    }
    public void createPayment(Order order) {
// this will create the payment object in the given order
    }
    public void printOrderDetails(Order order) {
// this will print the details of the order, including items, shipment, customer
    }
    public void printInvoice(Order order) {
// i guess this prints a formatted bill given the order
    }

    class Order {
        String orderNumber;
        Date orderDate;
        ArrayList<OrderItem> orderItems;
        double shippingHandling;
        double tax;
        double grandTotal;
        Customer customer;
        Shipment shipment;
        Payment payment;

        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy ");


        public String getOrderNumber() {
            return orderNumber;
        }
        public Date getOrderDate() {
            return dateFormat.format(orderDate);
        }
        public ArrayList<OrderItem> getOrderItems() {
            return orderItems;
        }
        public double getShippingHandling() {
            return formatter.format(shippingHandling);
        }
        public double getItemsSubtotal() {
            double subtotal = 0;
            for (OrderItem item : orderItems) {
                subtotal = subtotal + (item.quantity * item.product.price);
            }
            itemsSubtotal = subtotal;
            return subtotal;
        }
        public double getTax() {
            return tax;
        }
        public double getGrandTotal() {
            grandTotal = getItemsSubtotal() + shippingHandling + getEstimatedTaxes();
            return grandTotal;
        }
        public String getFormatttedItemsSubtotal() {
            return formatter.format(getItemsSubtotal());
        }
        public String getTotalBeforeTaxes() {
            return formatter.format(getItemsSubtotal() + shippingHandling);
        }
        public double getEstimatedTaxes() {
            tax = (getItemsSubtotal() + TAXRATE) + shippingHandling;
            return tax;
        }
        public String getFormattedEstimatedTaxes() {
            return formatter.format(getEstimatedTaxes());
        }
        public String getFomattedGrandTotal() {
            return formatter.format(getItemsSubtotal() + shippingHandling + getEstimatedTaxes());
        }
        public String getShipmentStatusAndDate() {
            if (shipment.shipmentStatus == ShipmentStatus.Delivered) {
                return shipment.shipmentStatus.name() + " " + dateFormat.format(shipment.deliveryDate);
            }
            return shipment.shipmentStatus.name() + " " + dateFormat.format(shipment.shippedDate);        }
        public Customer getCustomer() {
            return customer;
        }
        public Shipment getShipment() {
            return shipment;
        }
        public Payment getPayment() {
            return payment;
        }

        public Order(String orderNumber, Date orderDate, ArrayList<OrderItem> orderItems, double shippingHandling, Customer customer) {
            this.orderNumber = orderNumber;
            this.orderDate = orderDate;
            this.orderItems = orderItems;
            this.shippingHandling = shippingHandling;
            this.customer = customer;
        }
    }
    class OrderItem {
        int qty;
        Product product;

        public int getQty() {
            return qty;
        }

        public Product getProduct() {
            return product;
        }
    }
    class Product {
        String productid;
        String productDescription;
        String soldBy;
        double price;
        Condition condition;

        public String getProductid() {
            return productid;
        }

        public String getProductDescription() {
            return productDescription;
        }

        public String getSoldBy() {
            return soldBy;
        }

        public double getPrice() {
            return price;
        }

        public Condition getCondition() {
            return condition;
        }
    }
    class Customer {
        String customerName;
        String streetAddress;
        String contact;
        int customerid;
        String cityStateZip;
        String country;

        public String getCustomerName() {
            return customerName;
        }

        public String getStreetAddress() {
            return streetAddress;
        }

        public String getContact() {
            return contact;
        }

        public int getCustomerid() {
            return customerid;
        }

        public String getCityStateZip() {
            return cityStateZip;
        }

        public String getCountry() {
            return country;
        }
    }
    class Shipment {
        String shipmentid;
        String carrier;
        ShipmentStatus shipmentStatus;
        String trackingid;
        Date shippedDate;
        Date deliveryDate;
        ShipmentSpeed shipmentSpeed;

        public String getShipmentid() {
            return shipmentid;
        }

        public String getCarrier() {
            return carrier;
        }

        public ShipmentStatus getShipmentStatus() {
            return shipmentStatus;
        }

        public String getTrackingid() {
            return trackingid;
        }

        public Date getShippedDate() {
            return shippedDate;
        }

        public Date getDeliveryDate() {
            return deliveryDate;
        }

        public ShipmentSpeed getShipmentSpeed() {
            return shipmentSpeed;
        }
    }
    class Payment {
        PaymentType paymentType;
        String accountNumber;
        String bankOrIssuer;
        double paymentAmount;
        Date paymentDate;

        public PaymentType getPaymentType() {
            return paymentType;
        }
        public String getAccountNumber() {
            return accountNumber;
        }
        public String getBankOrIssuer() {
            return bankOrIssuer;
        }
        public double getPaymentAmount() {
            return paymentAmount;
        }
        public Date getPaymentDate() {
            return paymentDate;
        }

        public Payment(PaymentType paymentType, String accountNumber, String bankOrIssuer, double paymentAmount, Date paymentDate) {
            this.paymentType = paymentType;
            this.accountNumber = accountNumber;
            this.bankOrIssuer = bankOrIssuer;
            this.paymentAmount = paymentAmount;
            this.paymentDate = paymentDate;
        }
    }
    class Invoice {
        Order order;
        String invoiceNumber;

        public Order getOrder() {
            return order;
        }

        public String getInvoiceNumber() {
            return invoiceNumber;
        }
    }

    enum Condition {
        New,
        Used,
        Reconditioned
    }
    enum ShipmentStatus {
        InProcess,
        Shipped,
        Delivered
    }
    enum ShipmentSpeed {
        OneDay,
        TwoDay,
        Mail
    }
    enum PaymentType {
        CreditCard,
        BankTransfer,
        Mail,
        AmazonRewardsVisa
    }
}