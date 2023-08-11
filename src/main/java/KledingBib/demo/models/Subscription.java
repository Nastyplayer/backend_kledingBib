package KledingBib.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.time.LocalDate;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor

@Getter
@Setter

/*annotatie   DONE!!!!*/
@Entity
@Table(name = "subscriptions")
public class Subscription {

    @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      @Column
    private Long id;
    private String typeSubscription;

    private LocalDate expirationDate;
   // private Subscription subscription;



 






    public Account getAccount() {
        return account;
    }
    public void setAccount(Account account) {
        this.account = account;
    }

    @OneToOne (targetEntity = Account.class,  cascade = {CascadeType.ALL}) //(mappedBy = "subscription")(cascade = {CascadeType.ALL})

    @JsonIgnore
    private Account account;


    public enum SubscriptionStatus {
        ACTIVE,
        EXPIRE,
        CANCELED
    }

    public void setSubscriptionStatus(List<SubscriptionStatus> subscriptionStatus) {
        this.subscriptionStatus = subscriptionStatus;
    }

    public List<SubscriptionStatus> getSubscriptionStatus() {
        return subscriptionStatus;
    }

    @ElementCollection(targetClass = SubscriptionStatus.class)
   // @CollectionTable
    @Enumerated(EnumType.STRING)
    @Fetch(FetchMode.JOIN)
    List<SubscriptionStatus> subscriptionStatus;


}

