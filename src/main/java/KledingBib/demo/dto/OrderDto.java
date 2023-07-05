package KledingBib.demo.dto;
import KledingBib.demo.models.Item;

import KledingBib.demo.models.User;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import jakarta.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor

@Getter
@Setter


public class OrderDto {


    @Id


    private Long id;

    private String itemInfo;

    private LocalDate dateInfo;

    public Object itemId;


    @JsonIncludeProperties({"username", "password", "apikey", "email"})
    private User user;
 //   private Object user;

    @JsonIncludeProperties({"id", "nameInfo"})
    private List<Item> items;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDto that = (OrderDto) o;
        return Objects.equals(id, that.id) && Objects.equals(itemInfo, that.itemInfo)
                && Objects.equals(dateInfo, that.dateInfo)  && Objects.equals(itemId, that.itemId)
                && Objects.equals(user, that.user)  && Objects.equals(items, that.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, itemInfo, dateInfo, user, items, itemId);
    }


    //public Object getUser() {
   //     return null;
   // }

   // public void setUser(User user) {
   // }
}