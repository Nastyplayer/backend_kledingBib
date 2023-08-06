package KledingBib.demo.dto;

import KledingBib.demo.models.Account;
import KledingBib.demo.models.Subscription;
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

public class SubscriptionDto {

    @Id
    private Long id;

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getTypeSubscription() {
//        return typeSubscription;
//    }
//
//    public void setTypeSubscription(String typeSubscription) {
//        this.typeSubscription = typeSubscription;
//    }
//
//    public LocalDate getExpirationDate() {
//        return expirationDate;
//    }
//
//    public void setExpirationDate(LocalDate expirationDate) {
//        this.expirationDate = expirationDate;
//    }

    private String typeSubscription;

    private LocalDate expirationDate;


//    public List<Subscription.SubscriptionStatus> getSubscriptionStatus() {
//        return subscriptionStatus;
//    }
//
//    public void setSubscriptionStatus(List<Subscription.SubscriptionStatus> subscriptionStatus) {
//        this.subscriptionStatus = subscriptionStatus;
//    }

    List<Subscription.SubscriptionStatus> subscriptionStatus;

//    public Account [] getAccounts() {
//        return new account [0];
//    }
//
//    public void setAccount(Account account) {
//        this.account = account;
//    }

    @JsonIncludeProperties({"id", "userInfo", "subscriptionInfo"})
    private Account account;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubscriptionDto that = (SubscriptionDto) o;
        return Objects.equals(id, that.id) && Objects.equals(typeSubscription, that.typeSubscription) &&
                Objects.equals(expirationDate, that.expirationDate) && Objects.equals(subscriptionStatus, that.subscriptionStatus)
                && Objects.equals(account, that.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, typeSubscription, expirationDate, subscriptionStatus, account);
    }

    public Account[] getAccounts() {
        return new Account[0];
    }
}


