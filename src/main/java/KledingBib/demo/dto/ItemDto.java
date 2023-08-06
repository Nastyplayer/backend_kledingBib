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


//
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getNameInfo() {
//        return nameInfo;
//    }
//
//    public void setNameInfo(String nameInfo) {
//        this.nameInfo = nameInfo;
//    }
//
//    public List<Item.Tags> getTags() {
//        return tags;
//    }
//
//    public void setTags(List<Item.Tags> tags) {
//        this.tags = tags;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//


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
    private Order order;

}

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        ItemDto that = (ItemDto) o;
//        return  Objects.equals(id, that.id) && Objects.equals(nameInfo, that.nameInfo)
//                && Objects.equals(user, that.user) && Objects.equals(tags, that.tags) && Objects.equals(order, that.order);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, nameInfo, user, tags, order);
//    }


