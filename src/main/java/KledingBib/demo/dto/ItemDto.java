package KledingBib.demo.dto;


import KledingBib.demo.models.Item;
import KledingBib.demo.models.Order;
import KledingBib.demo.models.Upload;
import KledingBib.demo.models.User;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor

@Getter
@Setter


public class ItemDto {


    @Id


    private Long id;

    private String nameInfo;


    List<Item.Tags> tags;


    public Upload getUpload() {
        return upload;
    }

    public void setUpload(Upload upload) {
        this.upload = upload;
    }




    @JsonIncludeProperties({"url", "fileName", "textType"})
    private Upload upload;





    @JsonIncludeProperties({"id", "username", "password", "apikey", "email"})
    // @JsonIgnore
    private User user;
    // private Object user;


    @JsonIncludeProperties({"id", "dateInfo", "itemInfo"})
    private List<Order> order;

}

