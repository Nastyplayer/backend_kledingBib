package KledingBib.demo.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
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



/*annotatie   DONE!!!!*/
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String itemInfo;

    private LocalDate dateInfo;

    @JsonFormat(pattern = "dd-MM-yyyy")
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
//  public String userUsername;


    public void setItem(List<Item> item) {
        this.item = item;
    }
    public List<Item> getItem() {
        return item;
    }

    @OneToMany( fetch = FetchType.EAGER) //, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
            name = "order_itemss",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
     List<Item> item;


    @ManyToOne(cascade = CascadeType.ALL)
   // @JsonIgnore
   // @JoinColumn(name = "user_id")
    private User user;


}


    /// item als entiteit en attribut??? check!!!
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Order order1 = (Order) o;
//        return Objects.equals(id, order1.id) && Objects.equals(itemInfo, order1.itemInfo)
//                && Objects.equals(dateInfo, order1.dateInfo) && Objects.equals(user, order1.user)
//                && Objects.equals(items, order1.items) && Objects.equals(itemId, order1.itemId);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, itemInfo, dateInfo, user, items, itemId);
//    }





