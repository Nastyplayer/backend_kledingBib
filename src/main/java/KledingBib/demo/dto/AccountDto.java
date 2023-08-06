package KledingBib.demo.dto;

import KledingBib.demo.models.Subscription;
import KledingBib.demo.models.Upload;
import KledingBib.demo.models.User;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;


@NoArgsConstructor
@AllArgsConstructor

@Getter
@Setter


public class AccountDto {


    @Id
    private Long id;


    private String userInfo;


    private String subscriptionInfo;
    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;
    private String comment;


//    public Upload getUpload() {
//        return upload;
//    }
//
//    public void setUpload(Upload upload) {
//        this.upload = upload;
//    }

    @JsonIncludeProperties({"url", "fileName", "textType"})
    private Upload upload;


//    public Subscription getSubscription() {
//        return subscription;
//    }
//
//    public void setSubscription(Subscription subscription) {
//        this.subscription = subscription;
//    }

    @JsonIncludeProperties({"date", "type"})
    private Subscription subscription;

    @JsonIncludeProperties({"username", "password", "apikey", "email","comment" })
    private User user;

//    public AccountDto(String userInfo, String subscriptionInfo) {
//        this.userInfo = userInfo;
//        this.subscriptionInfo = subscriptionInfo;
//
//    }


    //VERGELIJKING
    @Override

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountDto accountDto = (AccountDto) o;

        return  Objects.equals(id, accountDto.id) &&   Objects.equals(userInfo, accountDto.userInfo) &&
                Objects.equals(subscriptionInfo, accountDto.subscriptionInfo) && Objects.equals(subscription, accountDto.subscription) &&
                Objects.equals(email, accountDto.email) && Objects.equals(comment, accountDto.comment) &&
                Objects.equals(upload, accountDto.upload) && Objects.equals(user, accountDto.user);

    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userInfo, subscriptionInfo, email, comment, subscription, upload, user);
    }

  //  public Subscription[] getSubscriptions() {
  //      return new Subscription[0];
 //   }


//    public void setSubscriptionDto(SubscriptionDto transferAccountToAccountDto) {
//    }

//
//    public Subscription[] getSubscriptions() {
//        return new Subscription[0];
//    }
//
//
//    public User[] getUsers() {
//        return new User[0];
//    }
//
//    public Upload[] getUploads() {
//        return new Upload[0];
//    }
}
