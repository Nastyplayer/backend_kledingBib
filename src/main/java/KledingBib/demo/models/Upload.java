package KledingBib.demo.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor

@Getter
@Setter

@Entity
@Table(name = "uploads")

public class Upload {
    @Id
    private String fileName;
    private String textType;     ///contentType;
    private String url;

    public Upload(String fileName, String textType, String url) {
    }

    public void setAccount(Account account) {
    }

    public void setItem(Item item) {
    }



  ////////////////////////////////////////////

    @OneToOne(orphanRemoval = true)
    @JoinTable(name = "uploads_item",
            joinColumns = @JoinColumn(name = "uploads_file_name"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private Item item;

    //////////////////////////////////////////////////////
    @OneToOne(orphanRemoval = true)
    @JoinTable(name = "uploads_account",
            joinColumns = @JoinColumn(name = "uploads_file_name"),
            inverseJoinColumns = @JoinColumn(name = "account_id"))
    private Account account;
}