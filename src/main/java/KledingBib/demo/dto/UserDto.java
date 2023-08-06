package KledingBib.demo.dto;

import KledingBib.demo.models.Account;
import KledingBib.demo.models.Authority;
import KledingBib.demo.models.Item;
import KledingBib.demo.models.Order;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;



@NoArgsConstructor
@AllArgsConstructor

@Getter
@Setter

public class UserDto {
  //  private Long id;
    public String username;
    public String password;

   public String email;
//    public String comment;

  public UserDto(String username, String password,  String email ) {
     // this.id = id;
      this.username = username;
      this.password = password;

   this.email = email;
//      this.comment = comment;


  }

    @JsonSerialize
    public Set<Authority> authorities;



  //@JsonIncludeProperties({"id", "nameInfo"})
  @JsonIgnore
  public
  List<Item> item;


  //  @JsonIncludeProperties({"id", "date", "itemInfo"})
  @JsonIgnore
  public List<Order> order;


   // @JsonIncludeProperties({"id", "userInfo", "subscriptionInfo"})
   @JsonIgnore
    Account account;





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