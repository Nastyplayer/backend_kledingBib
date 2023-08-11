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

@NoArgsConstructor
@AllArgsConstructor

@Getter
@Setter


public class OrderDto {


    @Id


    private Long id;
    private String itemInfo;

    private LocalDate dateInfo;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }




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


}