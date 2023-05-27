package com.example.pizzadatamanagementapi.service;

import com.example.pizzadatamanagementapi.payload.PizzaDTO;
import com.example.pizzadatamanagementapi.model.*;
import com.example.pizzadatamanagementapi.repository.OrderDetailsRepository;
import com.example.pizzadatamanagementapi.repository.OrderRepository;
import com.example.pizzadatamanagementapi.repository.PizzaRepository;
import com.example.pizzadatamanagementapi.repository.PizzaTypeRepository;
import jakarta.transaction.Transactional;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DataImportService {
    private final PizzaTypeRepository pizzaTypeRepository;
    private final PizzaRepository pizzaRepository;
    private final OrderRepository orderRepository;
    private final OrderDetailsRepository orderDetailsRepository;
    private static final String FILE_PATH_PIZZA_TYPES =  "src/main/resources/data/pizza_types.csv";
    private static final String FILE_PATH_ORDER_DETAILS =  "src/main/resources/data/order_details.csv";
    private static final String FILE_PATH_ORDER =  "src/main/resources/data/orders.csv";
    private static final String FILE_PATH_PIZZA =  "src/main/resources/data/pizzas.csv";
    private static final DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    @Autowired
    public DataImportService(PizzaTypeRepository pizzaTypeRepository,PizzaRepository pizzaRepository,OrderRepository orderRepository,OrderDetailsRepository orderDetailsRepository) {
        this.pizzaTypeRepository = pizzaTypeRepository;
        this.pizzaRepository = pizzaRepository;
        this.orderRepository = orderRepository;
        this.orderDetailsRepository = orderDetailsRepository;
    }

    @Transactional
    public void importData(String filename) throws IOException, ParseException {
        if(filename.equalsIgnoreCase("all")){
            importDataFromCSV(FILE_PATH_PIZZA_TYPES);

            importDataFromCSV(FILE_PATH_PIZZA);

            importDataFromCSV(FILE_PATH_ORDER);

            importDataFromCSV(FILE_PATH_ORDER_DETAILS);

            System.out.println("All data imported successfully!");
        }else{
            importDataFromCSV(filename);
        }
    }
    private void importDataFromCSV(String csvFilePath) throws IOException, ParseException {
        FileReader fileReader;
        CSVParser csvParser ;
        switch (csvFilePath) {
            case "pizza_types":
                fileReader = new FileReader(FILE_PATH_PIZZA_TYPES);
                csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT);
                List<PizzaType> pizzaTypes = parsePizzaTypeDataFromCSV(csvParser);
                pizzaTypeRepository.saveAll(pizzaTypes);
                System.out.println("Pizza Type data imported successfully!");
            break;

            case "pizzas":
                fileReader = new FileReader(FILE_PATH_PIZZA);
                csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT);
                List<Pizza> pizzas = parsePizzaDataFromCSV(csvParser);
                pizzaRepository.saveAll(pizzas);
                System.out.println("Pizza data imported successfully!");
            break;

            case "orders":
                fileReader = new FileReader(FILE_PATH_ORDER);
                csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT);
                List<Order> orders = parseOrderDataFromCSV(csvParser);
                orderRepository.saveAll(orders);
                System.out.println("Order imported successfully!");
            break;

            case "order_details":
                fileReader = new FileReader(FILE_PATH_ORDER_DETAILS);
                csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT);
                List<OrderDetail> orderDetails = parseOrderDetailDataFromCSV(csvParser);
                orderDetailsRepository.saveAll(orderDetails);
                System.out.println("Order Details imported successfully!");
                break;

            default:
                throw new IllegalArgumentException("Unsupported file: " + csvFilePath);
        }
    }
    private List<OrderDetail> parseOrderDetailDataFromCSV(CSVParser csvParser) {
        List<OrderDetail> orderDetails = new ArrayList<>();
        boolean isFirstRecord = true;
        for (CSVRecord csvRecord : csvParser) {
            if (isFirstRecord) {
                isFirstRecord = false;
                continue;
            }
            Long orderDetailsId = Long.valueOf(csvRecord.get(0));
            Order order = getOrderId(Long.valueOf(csvRecord.get(1)));
            Pizza pizza = getPizzaId(csvRecord.get(2));
            Integer quantity = Integer.valueOf(csvRecord.get(3));
            OrderDetail orderDetail = new OrderDetail(orderDetailsId,order, pizza,quantity);
            orderDetails.add(orderDetail);
        }
        return orderDetails;
    }
    private List<PizzaType> parsePizzaTypeDataFromCSV(CSVParser csvParser) {
        List<PizzaType> pizzaType = new ArrayList<>();
        boolean isFirstRecord = true;
        for (CSVRecord csvRecord : csvParser) {
            if (isFirstRecord) {
                isFirstRecord = false;
                continue;
            }
            String pizzaTypeId = csvRecord.get(0);
            String name = csvRecord.get(1);
            String category = csvRecord.get(2);
            String ingredients = csvRecord.get(3);

            pizzaType.add(new PizzaType(pizzaTypeId,name,category,ingredients));
        }
        return pizzaType;
    }
    private List<Pizza> parsePizzaDataFromCSV(CSVParser csvParser) {
        List<Pizza> pizza = new ArrayList<>();
        boolean isFirstRecord = true;
        for (CSVRecord csvRecord : csvParser) {
            if (isFirstRecord) {
                isFirstRecord = false;
                continue;
            }
            String pizzaId = csvRecord.get(0);
            PizzaType pizzaTypeId = getPizzaTypeId(csvRecord.get(1));
            PizzaSize size = PizzaDTO.getSizeEnum(csvRecord.get(2));
            BigDecimal price = new BigDecimal(csvRecord.get(3));

            pizza.add(new Pizza(pizzaId,pizzaTypeId,size,price));
        }
        return pizza;
    }
    private List<Order> parseOrderDataFromCSV(CSVParser csvParser) throws ParseException {
        List<Order> order = new ArrayList<>();
        LocalDate date;
        LocalTime time;
        boolean isFirstRecord = true;
        for (CSVRecord csvRecord : csvParser) {
            if (isFirstRecord) {
                isFirstRecord = false;
                continue;
            }

            try {
                date = LocalDate.parse(csvRecord.get(1).trim(),dateFormat);
                time = LocalTime.parse(csvRecord.get(2).trim(), timeFormat);
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException("Invalid date/time format: " + csvRecord.get(1).trim()+"/"+csvRecord.get(2).trim(), e);
            }
            order.add(new Order(date, time));
        }
        return order;
    }
    private Order getOrderId(Long orderId) {
        Optional<Order> orderIdOptional = orderRepository.findById(orderId);
        if (orderIdOptional.isPresent()) {
            return orderIdOptional.get();
        }else {
            throw new IllegalArgumentException("Unknown order_Id: " + orderId);
        }
    }
    private Pizza getPizzaId(String pizzaId) {
        Optional<Pizza> pizzaIdOptional = pizzaRepository.findByPizzaId(pizzaId);
        if (pizzaIdOptional.isPresent()) {
            return pizzaIdOptional.get();
        }else {
            throw new IllegalArgumentException("Unknown pizza_id: " + pizzaId);
        }
    }
    private PizzaType getPizzaTypeId(String pizzaTypeId) {
        Optional<PizzaType> byPizzaTypeId = pizzaTypeRepository.findByPizzaTypeId(pizzaTypeId);
        if (byPizzaTypeId.isPresent()) {
            return byPizzaTypeId.get();
        }else {
            throw new IllegalArgumentException("Unknown pizza_type_id: " + pizzaTypeId);
        }
    }

}
