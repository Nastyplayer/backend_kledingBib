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

    private String type;

    private LocalDate expirationDate;

    List<Subscription.Status> status;

    @JsonIncludeProperties({"id", "userInfo", "subscriptionInfo"})
    private Account account;

    public SubscriptionDto(long l, String subscription3, Object o) {
    }
    //private Object account;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubscriptionDto subscriptionDto1 = (SubscriptionDto) o;
        return Objects.equals(id, subscriptionDto1.id) && Objects.equals(type, subscriptionDto1.type) &&
                Objects.equals(expirationDate, subscriptionDto1.expirationDate) && Objects.equals(status, subscriptionDto1.status)
                && Objects.equals(account, subscriptionDto1.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, expirationDate, status, account);
    }

}


