package KledingBib.demo.models;
import KledingBib.demo.dto.SubscriptionDto;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Objects;


@NoArgsConstructor
@AllArgsConstructor

@Getter
@Setter

/*annotatie   DONE!!!!*/
@Entity
@Table(name = "subscriptions")
public class Subscription {

    @Id
    //  @GeneratedValue(strategy = GenerationType.IDENTITY)
    //  @Column
    private Long id;


    private String type;

    private LocalDate expirationDate;

    public Subscription(long l, String subscription2, Object o) {
    }

    public enum Status {
        ACTIVE,
        EXPIRE,
        CANCELED
    }

    @ElementCollection(targetClass = Status.class)
    @CollectionTable
    @Enumerated(EnumType.STRING)
    List<Status> status;

    @OneToOne
    //         (orphanRemoval = true)
    // @JoinTable(name = "subscriptions_account",
    //       joinColumns = @JoinColumn(name = "subscription_id"),
    //       inverseJoinColumns = @JoinColumn(name = "account_id"))
    //  private


    Account account;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subscription subscription1 = (Subscription) o;
        return Objects.equals(id, subscription1.id) && Objects.equals(type, subscription1.type) &&
                Objects.equals(expirationDate, subscription1.expirationDate) && Objects.equals(status, subscription1.status) && Objects.equals(account, subscription1.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, expirationDate, status, account);
    }

}

    //  public Collection<Object> getSubscription() {
   //     return null;
 //   }

    //  public Collection<Object> getSubscription() {
   //     return null;
  //  }}

