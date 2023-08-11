//package KledingBib.demo.service;
//
//import KledingBib.demo.dto.OrderDto;
//import KledingBib.demo.models.Item;
//import KledingBib.demo.models.Order;
//import KledingBib.demo.models.User;
//import KledingBib.demo.repository.ItemRepository;
//import KledingBib.demo.repository.OrderRepository;
//import KledingBib.demo.repository.UserRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.ArgumentCaptor;
//import org.mockito.Captor;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.mockito.junit.jupiter.MockitoSettings;
//import org.mockito.quality.Strictness;
//import org.springframework.test.context.ContextConfiguration;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.when;
//
//
//@ContextConfiguration(classes = {OrderService.class})
//
//
//@ExtendWith(MockitoExtension.class)
//@MockitoSettings(strictness = Strictness.LENIENT)
//class OrderServiceTest {
//
//
//    @Mock
//    OrderRepository orderRepository;
//    @Mock
//    UserRepository userRepository;
//    @Mock
//    ItemRepository itemRepository;
//
//    OrderService orderService;
//
//    @Captor
//    ArgumentCaptor<Order> captor;
//
//    Order order1;
//    Order order2;
//
//    User user1;
//
//    User user2
//            ;
//    Item item1;
//
//    Item item2;
//
//    OrderDto OrderDto;
//
//    @BeforeEach
//    void setUp() {
//
//        void setUp() {
//            user1 = new User("PapaRoach", "roach", " ");
//            user2 = new User("Offspring", "spring", " ");
//
//            item1 = new Item(Long 45, "shirt", user1, null);
//            item2 = new Item(Long 46, "shirt", user2, null);
//
//            order1 = new Order(34 "rok", "ACTIVE", "11-07-2022", user1);
//            order2 = new Order(Long 35, "shirt", "ACTIVE", "12-07-2022", user2);
//        }
//
//
//
//
//    @Test
//    void getAllOrders() {
////        //Arrange
////        List<Order> orders = new ArrayList<>();
////        order1.add(order1);
////        orders.add(order1);
////
////        List<OrderDto> order = new ArrayList<>();
////        order.add(orderDto1);
////        order.add(orderDto2);
//
//        when(orderRepository.findAll()).thenReturn(List.of(order1, order2));
//
//        List<OrderDto> newList =  orderService.getAllOrders();
//
//        assertEquals(order1.getId(), newList.get(0).getId() );
//        assertEquals(order1.getItemInfo(), newList.get(0).getItemInfo() );
//
//        assertEquals(order2.getId(), newList.get(1).getId() );
//        assertEquals(order2.getItemInfo(), newList.get(1).getItemInfo() );
//
//
//    }
//
//    @Test
//    void getOrder() {
//        when(orderRepository.findById(order1.getId())).thenReturn(Optional.of(order2));
//        OrderDto orderDto = orderService.getOrder(order1.getId());
//
//        //act//assert
//        assertEquals( order2.getItemInfo(), orderDto.getItemInfo());
//
//    }
//
//
//}