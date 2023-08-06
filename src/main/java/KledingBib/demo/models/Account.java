package KledingBib.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.Objects;


@NoArgsConstructor
@AllArgsConstructor

@Getter
@Setter

/*annotatie   DONE!!!!*/
@Entity
@Table(name = "accounts")
public class Account  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userInfo;

    private String subscriptionInfo;

    private String email;
    private String comment;


    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    @OneToOne   //(cascade = CascadeType.ALL, orphanRemoval = true)
    private Subscription subscription;

    public Upload getUpload() {
        return uploads;
    }

    public Collection<Object> getAccount() {
        return null;
    }
    public void setUpload(Upload upload) {
        this.uploads = uploads;
    }

    @OneToOne
    @JsonIgnore
    private Upload uploads;

    @OneToOne
    @JsonIgnore
    private User user;


    //maybe ?





    //VERGELIJKING
    @Override

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return   Objects.equals(id, account.id) &&  Objects.equals(userInfo, account.userInfo) &&
                Objects.equals(subscriptionInfo, account.subscriptionInfo ) && Objects.equals(email, account.email) &&
                Objects.equals(comment, account.comment) && Objects.equals(subscription, account.subscription)
                && Objects.equals(uploads, account.uploads ) && Objects.equals(user, account.user) ;

    }
    @Override
    public int hashCode() {
        return Objects.hash(id, userInfo, subscriptionInfo, email,comment, subscription, uploads, user);
    }


    public Long getUsername() {
        return null;
    }
}