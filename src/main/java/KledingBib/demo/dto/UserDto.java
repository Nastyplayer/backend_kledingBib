package KledingBib.demo.dto;

import KledingBib.demo.models.*;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;
import java.util.Set;



@NoArgsConstructor
@AllArgsConstructor

@Getter
@Setter

public class UserDto {
  //  private Long id;
    public String username;
    public String password;
   public Boolean enabled;
    public String apikey;
    public String email;


  // List<User.Tags> tags;


    @JsonSerialize
    public Set<Authority> authorities;

    @JsonIncludeProperties({"id", "nameInfo"})
    private List<Item> items;


    @JsonIncludeProperties({"id", "date", "itemInfo"})
    private List<Order> orders;


    @JsonIncludeProperties({"id", "user", "subscription"})
    private Account account;



    // private Object order;
 //   private Object item;
  //  private Object account;


//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        UserDto userDto = (UserDto) o;
//        return //Objects.equals(id, userDto.id) &&
//                Objects.equals(username, userDto.username) &&
//                Objects.equals(password, userDto.password) && Objects.equals(apikey, userDto.apikey) &&
//                Objects.equals(email, userDto.email ) ;
//               // && Objects.equals(items, userDto.items) && Objects.equals(orders, userDto.orders) &&
//              // Objects.equals(account, userDto.account);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash( username, password,   apikey,  email); //,  items, orders, account);
//    }


}