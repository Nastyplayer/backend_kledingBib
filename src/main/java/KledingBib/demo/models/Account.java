package KledingBib.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;


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

    @OneToOne  ( mappedBy = "account")  //(cascade = CascadeType.ALL, orphanRemoval = true)
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

    @OneToOne( mappedBy = "account")   //nieuw
    @JsonIgnore
    private User user;


    //maybe ?







    }


