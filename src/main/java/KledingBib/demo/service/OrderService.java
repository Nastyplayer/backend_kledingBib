package KledingBib.demo.service;

import KledingBib.demo.dto.OrderDto;
import KledingBib.demo.exceptions.RecordNotFoundException;
import KledingBib.demo.models.Order;
import KledingBib.demo.repository.AccountRepository;
import KledingBib.demo.repository.ItemRepository;
import KledingBib.demo.repository.OrderRepository;
import KledingBib.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {


    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final UserRepository UserRepository;
    private final AccountRepository AccountRepository;
    private final AccountService AccountService;


    public OrderService(OrderRepository orderRepository, ItemRepository itemRepository,
                        UserRepository userRepository, AccountRepository accountRepository, AccountService accountService) {
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
        this.UserRepository = userRepository;
        this.AccountRepository = accountRepository;
        this.AccountService = accountService;

    }


    public List<OrderDto> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        ArrayList<OrderDto> orderDtos = new ArrayList<>();
        for (Order Order : orders) {
            OrderDto orderDto = transferOrderToOrderDto(Order);
            orderDtos.add(orderDto);
        }
        return orderDtos;
    }



    public OrderDto getOrder(Long id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isPresent()) {
            Order order1 = optionalOrder.get();
            return transferOrderToOrderDto(order1);
        } else {
            throw new RecordNotFoundException("No order found with id: " + id + ".");
        }
    }

    public Long createOrder(OrderDto orderDto) {
        Order newOrder;
        newOrder = transferOrderDtoToOrder(orderDto);
        Order savedOrder = orderRepository.save(newOrder);

     //   addItemToOrder(orderDto, savedOrder);

        return savedOrder.getId();
    }




    public OrderDto putOrder(Long id, OrderDto orderDto) {
        {
            if (orderRepository.findById(id).isPresent()) {
                Order order = orderRepository.findById(id).get();
                Order order1 = transferOrderDtoToOrder(orderDto);
                order1.setId(order.getId());
                orderRepository.save(order1);
                return transferOrderToOrderDto(order1);
            } else {
                throw new RecordNotFoundException("No Order found with id: " + id + ".");
            }
        }
    }

    public OrderDto patchOrder(Long id, OrderDto orderDto) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (orderRepository.existsById(id)) {
            Order orderToUpdate = optionalOrder.get();

            if (orderDto.getItemInfo() != null) {
                orderToUpdate.setItemInfo(orderDto.getItemInfo());
            }
            if (orderDto.getDateInfo() != null) {
                orderToUpdate.setDateInfo(orderDto.getDateInfo());
            }
            if (orderDto.getUser() != null) {
                orderToUpdate.setUser(orderDto.getUser());
            }


            Order savedOrder = orderRepository.save(orderToUpdate);
            return transferOrderToOrderDto(savedOrder);
        } else {
            throw new RecordNotFoundException("No Order with id " + id);
        }
    }

    public String deleteById(Long id) {
        if (orderRepository.existsById(id)) {
            Optional<Order> deletedOrder = orderRepository.findById(id);
            Order order1 = deletedOrder.get();
            orderRepository.delete(order1);
            return "Order with id: " + id + " deleted.";
        } else {
            throw new RecordNotFoundException("No Order found with id: " + id + ".");
        }
    }


    //    helper methods.......................................................
    private OrderDto transferOrderToOrderDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setUser(order.getUser());
        orderDto.setItem(order.getItem());
        orderDto.setId(order.getId());
        orderDto.setItemInfo(order.getItemInfo());
        orderDto.setDateInfo(order.getDateInfo());

        if (order.getUser() != null) {
            orderDto.setUser(order.getUser());
        }


        return orderDto;
    }

    private Order transferOrderDtoToOrder(OrderDto orderDto) {

        Order order = new Order();
        order.setUser(orderDto.getUser());
        order.setItem(orderDto.getItem());
        order.setId(orderDto.getId());
        order.setItemInfo(orderDto.getItemInfo());
        order.setDateInfo(orderDto.getDateInfo());


        return order;
    }}

