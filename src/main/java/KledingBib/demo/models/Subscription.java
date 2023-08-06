package KledingBib.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;


//@NoArgsConstructor
//@AllArgsConstructor
//
//@Getter
//@Setter

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


    public Subscription() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeSubscription() {
        return typeSubscription;
    }

    public void setTypeSubscription(String typeSubscription) {
        this.typeSubscription = typeSubscription;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

 



    public Subscription(SubscriptionStatus Expire) {
    }


//    public Collection<Object> getSubscription() {
//        return null;
//    }

    public Account getAccount() {
        return account;
    }
    public void setAccount(Account account) {
        this.account = account;
    }

    @OneToOne (targetEntity = Account.class, mappedBy = "subscription", cascade = {CascadeType.ALL}) //(mappedBy = "subscription")(cascade = {CascadeType.ALL})

   // @JoinColumn(name = "account_id")
    @JsonIgnore
    private Account account;

//    public Subscription getSubscription() {
//
//        return subscription;
//    }

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



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subscription subscription1 = (Subscription) o;
        return Objects.equals(id, subscription1.id) && Objects.equals(typeSubscription, subscription1.typeSubscription) &&
                Objects.equals(expirationDate, subscription1.expirationDate) &&
                Objects.equals(subscriptionStatus, subscription1.subscriptionStatus) && Objects.equals(account, subscription1.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, typeSubscription, expirationDate, subscriptionStatus, account);
    }

}

