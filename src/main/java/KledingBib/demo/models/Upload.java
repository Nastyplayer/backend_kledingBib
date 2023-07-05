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

//    public Upload(String fileName, String contentType, String url) {
//        this.fileName = fileName;
//        this.contentType = contentType;
//        this.url = url;
//    }
//
//
//    public Upload(String contentType) {
//
//        this.contentType = contentType;
//    }
//
//    public Upload() {

 //   }
}



//////////////////////////////////////////////////////
    // @OneToOne(orphanRemoval = true)
   // @JoinTable(name = "uploads_account",
   //         joinColumns = @JoinColumn(name = "upload_file_name"),
    //        inverseJoinColumns = @JoinColumn(name = "account_id"))
  //  private Account account;

