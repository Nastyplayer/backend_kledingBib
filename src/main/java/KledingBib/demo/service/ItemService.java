package KledingBib.demo.service;

import KledingBib.demo.dto.ItemDto;
import KledingBib.demo.exceptions.RecordNotFoundException;
import KledingBib.demo.models.Item;
import KledingBib.demo.models.Order;
import KledingBib.demo.models.Upload;
import KledingBib.demo.repository.ItemRepository;
import KledingBib.demo.repository.OrderRepository;
import KledingBib.demo.repository.UploadRepository;
import KledingBib.demo.repository.UserRepository;
import org.hibernate.engine.spi.SessionDelegatorBaseImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {


    private final ItemRepository itemRepository;

    private final UserRepository UserRepository;
    private final OrderRepository OrderRepository;
    private Item Item;

    private UploadRepository uploadRepository;
    private Item item;
    private SessionDelegatorBaseImpl orderRepository;
    private List<Order> Order;

    public ItemService(ItemRepository itemRepository, OrderRepository orderRepository, UserRepository userRepository, UploadRepository uploadRepository, UploadRepository uploadRepository2) {

        this.itemRepository = itemRepository;
        this.OrderRepository = orderRepository;
        this.uploadRepository = uploadRepository;
        this.UserRepository = userRepository;


    }


    public List<ItemDto> getAllItems() {
        List<Item> items = itemRepository.findAll();
        List<ItemDto> itemDtos = new ArrayList<>();
        for (Item item : items) {
            ItemDto itemDto = transferItemToItemDto(item);
            itemDtos.add(itemDto);
        }
        return itemDtos;
    }
// return itemDtos;


    public ItemDto getItem(Long id) {
        Optional<Item> optionalItem = itemRepository.findById(id);
        if (optionalItem.isPresent()) {
            Item item1 = optionalItem.get();
            return transferItemToItemDto(item1);
        } else {
            throw new RecordNotFoundException("No Item found with id: " + id + ".");
        }
    }

    public Long createItem(ItemDto itemDto) {
        Item newItem;
        newItem = transferItemDtoToItem(itemDto);
        Item savedItem = itemRepository.save(newItem);
//        addOrderToItem(itemDto, savedItem);

//        Optional<Optional<Order>> optionalorder = Optional.ofNullable(OrderRepository.findById(id));
//        if (optionalorder.isPresent()){
//            Optional<Order> order = optionalorder.get();
//
//            newItem.setOrder(Order);
//        }
//        addOrderToItem(itemDto, savedItem);
        ////////
        return savedItem.getId();
    }

    public ItemDto putItem(Long id, ItemDto itemDto) {
        {
            if (itemRepository.findById(id).isPresent()) {
                Item item = itemRepository.findById(id).get();
                Item item1 = transferItemDtoToItem(itemDto);
                item1.setId(item.getId());
                itemRepository.save(item1);
                return transferItemToItemDto(item1);
            } else {
                throw new RecordNotFoundException("No Item found with id: " + id + ".");
            }
        }
    }

    public ItemDto patchItem(Long id, ItemDto itemDto) {
        Optional<Item> optionalItem = itemRepository.findById(id);
        if (itemRepository.existsById(id)) {
            Item itemToUpdate = optionalItem.get();

            if (itemDto.getNameInfo() != null) {
                itemToUpdate.setNameInfo(itemDto.getNameInfo());
            }
            if (itemDto.getTags() != null) {
                itemToUpdate.setTags(itemDto.getTags());
            }
            if (itemDto.getUser() != null) {
                itemToUpdate.setUser(itemDto.getUser());
            }


            Item savedItem = itemRepository.save(itemToUpdate);
            return transferItemToItemDto(savedItem);
        } else {
            throw new RecordNotFoundException("No item with id " + id);
        }
    }

    /////////////nuevo////////////

    public void deleteById(Long id) {
        Optional<Item> itemOptional = itemRepository.findById(id);
        if (itemOptional.isPresent()) {
            itemRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No Item found with id: " + id);
        }
    }

////////oude
//    public String deleteById(Long id) {
//        if (itemRepository.existsById(id)) {
//            Optional<Item> deletedItem = itemRepository.findById(id);
//            Item item1 = deletedItem.get();
//            itemRepository.delete(item1);
//            return "Item with id: " + id + " deleted.";
//        } else {
//            throw new RecordNotFoundException("No Item found with id: " + id + ".");
//        }
//    }


    public void assignPhotoToItem(String fileName) {  //, Long id) {
        // Optional<Item> optionalItem = itemRepository.findById(id);
        Optional<Upload> Upload = uploadRepository.findByFileName(fileName);
        ///////////// boven oude form met id //////////////////////


        //Optional<Upload> fileUploadResponse = uploadRepository.findByFileName(fileName);
        //if (optionalItem.isPresent() && fileUploadResponse.isPresent()) {
        // fileUploadResponse photo = fileUploadResponse.get();

        //////////////////// hier onder oude form //////////////
        //   if (optionalItem.isPresent() && UploadisPresent()) {
        if (UploadisPresent()) {
            Upload photo = Upload.get();
            //Item item = optionalItem.get();
            item.setUpload(photo);
            itemRepository.save(item);
        }
    }

    private boolean UploadisPresent() {
        return false;
    }


    //    helper methods.......................................................
    private ItemDto transferItemToItemDto(Item item) {
        ItemDto itemDto = new ItemDto();
        itemDto.setUser(item.getUser());
        itemDto.setId(item.getId());
        itemDto.setNameInfo(item.getNameInfo());
        //     itemDto.setOrder(item.getOrder());

        if (item.getUser() != null) {
            itemDto.setUser(item.getUser());
        }
        if (item.getOrder() != null) {
            List<Order> orderList = new ArrayList<>();
            orderList.add(item.getOrder());
            itemDto.setOrder(orderList);
        }
        if (item.getTags() != null) {
            itemDto.setTags(item.getTags());
        }
        if (item.getUpload() != null) {
            itemDto.setUpload(item.getUpload());
        }

        return itemDto;
    }

    private Item transferItemDtoToItem(ItemDto itemDto) {
        Item item = new Item();
        item.setUser(itemDto.getUser());
        item.setId(itemDto.getId());
        item.setNameInfo(itemDto.getNameInfo());
        item.setTags(itemDto.getTags());
        item.setOrder((Order)itemDto.getOrder());
        item.setUpload(itemDto.getUpload());

        return item;
    }

    public List<Item> transferItemDtoListToItemList(List<ItemDto> usersDtos) {
        List<Item> users = new ArrayList<>();
        for (ItemDto usersDto : usersDtos) {
            users.add(transferItemDtoToItem(usersDto));
        }
        return users;
    }

}

//    public static void assignOrderToItem(String id, String itemId) {
//        Optional<Order> optionalOrder = orderRepository.findById(id);
//        Optional<Item> optionalItem = itemRepository.findById(itemId);
//        if (optionalOrder.isPresent() && optionalItem.isPresent()) {
//            Order order = optionalOrder.get();
//            Item item = optionalItem.get();
//            order.setItem(item);
//            orderRepository.save(order);
//        } else {
//            throw new RecordNotFoundException();
//
//public Collection<OrderDto> getOrderByItemId(Long itemId) {
//    Collection<OrderDto> orderDtos = new HashSet<>();
//    Collection<Order> order = orderRepository.Item(itemId);
//    for (Order order : order) {
//        Order order = order.getOrder();
//        OrderDto orderDto = new OrderDto();
//
//        orderDto.setId(order.getId());
//



//// deze werkt//////////////////////////
//    public void addOrderToItem(ItemDto itemDto, Item item) {
//        for (Order order : itemDto.getOrder()) {
//            if (!order.getOrder().isEmpty()) {
//             List<Item> List = null;
//                order.setItem(List);
//                orderRepository.save(order);
//            }
//        }
//    }
