package KledingBib.demo.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.*;


@NoArgsConstructor
@AllArgsConstructor

@Getter
@Setter

/*annotatie   DONE!!!!*/
@Entity
@Table(name = "users")
public class User {


    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column
 //   private Long id;
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, length = 255)
    private String password;

    @Column
    private String email;

//    @Column(nullable = false)
//    private boolean enabled = true;
//    public String apikey;

//    @Column
//    public String comment;

    public User ( String username, String password,  String email) {

        this.username = username;
        this.password = password;
        this.email = email;

    }

    // RELATIES
  @OneToMany(mappedBy = "user")
  @JsonIgnore
   List<Item> item;

  @OneToMany( fetch = FetchType.EAGER,mappedBy = "user")
  @JsonIgnore
 List<Order> order;

  @OneToOne(targetEntity = Account.class, mappedBy = "user")
    Account account;


  @OneToMany(
            targetEntity = Authority.class,
            mappedBy = "username",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
           fetch = FetchType.EAGER)

    // METHODEN
    private Set<Authority> authorities = new HashSet<>();




    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void addAuthority(Authority authority) {
        this.authorities.add(authority);
    }

    public void removeAuthority(Authority authority) {
        this.authorities.remove(authority);
    }

   // public Collection<Object> getUser() {
  ///      return null;
 //   }


    //  public void addItem(Item item)  { this.items.add(item);}

  //  public void addOrder(Order order){
   //     this.orders.add(order);
   // }

   // public void addAccount(Account account){
   ///     this.account.add(account);
   // }

   // public void setAccounts(Account account) {
   // }

    //VERGELIJKING
   // @Override
   /// public boolean equals(Object o) {
    //    if (this == o) return true;
    //    if (o == null || getClass() != o.getClass()) return false;
     //   User user = (User) o;
    //    return //Objects.equals(id, user.id) &&

       //         Objects.equals(username, user.username) &&
       //         Objects.equals(password, user.password) && Objects.equals(apikey, user.apikey) &&
      //          Objects.equals(email, user.email) && Objects.equals(tags, user.tags);
        //    Objects.equals(item, user.item) && Objects.equals(order, user.order) &&
        //    Objects.equals(account, user.account) && Objects.equals(authority, user.authority);
  //  }
  //  @Override
  //  public int hashCode() {
   //     return Objects.hash( username, password, apikey, email, tags); //item, order, account, authority);
  //  }


  //  public Collection<Object> getUser() {
   //   return null;
   // }

   // public void setEnabled(boolean b) {
   // }
}
