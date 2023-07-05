package KledingBib.demo.models;
import KledingBib.demo.dto.OrderDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.jsonwebtoken.lang.Collections;
import jakarta.persistence.*;

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

/*annotatie   DONE!!!!*/
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String itemInfo;

    private LocalDate dateInfo;

    public Long itemId;

    //  public String userUsername;

    @OneToMany(mappedBy = "order") //  , cascade = CascadeType.ALL, orphanRemoval = true)

    private List<Item> items;
    @ManyToOne
    @JsonIgnore
    private User user;


    /// item als entiteit en attribut??? check!!!
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order1 = (Order) o;
        return Objects.equals(id, order1.id) && Objects.equals(itemInfo, order1.itemInfo)
                && Objects.equals(dateInfo, order1.dateInfo) && Objects.equals(user, order1.user)
                && Objects.equals(items, order1.items)&& Objects.equals(itemId, order1.itemId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, itemInfo, dateInfo, user, items, itemId);
    }


   // public void setAccount(Account account) {
   // }


  //  public Collection<Object> getOrder() {
   //     return null;
 //   }

  //  public void setItem(Item item) {
   // }
}