//package KledingBib.demo.service;
//
//import KledingBib.demo.dto.ItemDto;
//import KledingBib.demo.exceptions.RecordNotFoundException;
//import KledingBib.demo.models.Item;
//import KledingBib.demo.repository.ItemRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.Optional;
//
//
//@SpringBootTest
//
//class ItemServiceTest {
//
//
//    @Mock
//    private ItemRepository itemRepository;
//
//    @InjectMocks
//    private ItemService itemService;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//
//
//
//
//    @Test
//    void getAllItems() {
//
//        Long itemId = 1L;
//        Item mockItem = new Item();
//        // Set up mockItem with necessary data
//        when(itemRepository.findById(itemId)).thenReturn(Optional.of(mockItem));
//
//        ItemDto result = itemService.getItem(itemId);
//
//        assertEquals(mockItem.getUser(), result.getUser());
//        // Add more assertions as needed
//    }
//
//
//
//
//
//    @Test
//    void getItem() {
//
//        Long nonExistingItemId = 99L;
//
//        when(itemRepository.findById(nonExistingItemId)).thenReturn(Optional.empty());
//
//        assertThrows(RecordNotFoundException.class, () -> itemService.getItem(nonExistingItemId));
//    }
//
//
//
//
//
//
//
//    @Test
//    void createItem() {
//        ItemDto itemDto = new ItemDto();
//        // Set up itemDto with necessary data
//
//        Item mockNewItem = new Item();
//        // Set up mockNewItem based on itemDto
//
//        when(itemRepository.save(any(Item.class))).thenReturn(mockNewItem);
//
//        Long createdItemId = itemService.createItem(itemDto);
//
//        assertNotNull(createdItemId);
//        // Add more assertions as needed
//
//    }
//
//    @Test
//    void putItem() {
//    }
//
//    @Test
//    void patchItem() {
//    }
//
//    @Test
//    void deleteById() {
//    }
//
//    @Test
//    void assignPhotoToItem() {
//    }
//
//    @Test
//    void transferItemDtoListToItemList() {
//    }
//}