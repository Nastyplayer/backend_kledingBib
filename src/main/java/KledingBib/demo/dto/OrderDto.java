package KledingBib.demo.dto;

import KledingBib.demo.models.Item;
import KledingBib.demo.models.User;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.util.List;

//@NoArgsConstructor
//@AllArgsConstructor
//
//@Getter
//@Setter


public class OrderDto {


    @Id


    private Long id;
    private String itemInfo;

    private LocalDate dateInfo;





    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemInfo() {
        return itemInfo;
    }

    public void setItemInfo(String itemInfo) {
        this.itemInfo = itemInfo;
    }

    public LocalDate getDateInfo() {
        return dateInfo;
    }

    public void setDateInfo(LocalDate dateInfo) {
        this.dateInfo = dateInfo;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }





//    public Item getItem () {
//        return item;
//    }
//
//    public void setItem(Item item) {
//        this.item = item;
//    }


//private Item item;





    @JsonIncludeProperties({"username", "password", "apikey", "email"})
    private User user;
 //   private Object user;

    public List<Item> getItem() {
        return item;
    }

    public void setItem(List<Item> item) {
        this.item = item;
    }

    @JsonIncludeProperties({"id", "nameInfo"})
    private List<Item> item;



//        @Override
//        public boolean equals (Object o){
//            if (this == o) return true;
//            if (o == null || getClass() != o.getClass()) return false;
//            OrderDto that = (OrderDto) o;
//            return Objects.equals(id, that.id) && Objects.equals(itemInfo, that.itemInfo)
//                    && Objects.equals(dateInfo, that.dateInfo) && Objects.equals(itemId, that.itemId)
//                    && Objects.equals(user, that.user) && Objects.equals(items, that.items);
//        }
//
//        @Override
//        public int hashCode () {
//            return Objects.hash(id, itemInfo, dateInfo, user, items, itemId);
//        }

    }
//    public Object getUser() {
//        return null;
//    }

   // public void setUser(User user) {
   // }
