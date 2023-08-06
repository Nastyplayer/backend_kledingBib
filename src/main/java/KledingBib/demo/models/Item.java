package KledingBib.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;


//@NoArgsConstructor
//@AllArgsConstructor
//
//@Getter
//@Setter

/*annotatie   DONE!!!!*/
@Entity
@Table(name = "items")
public class Item {


    @Id

  @GeneratedValue(strategy = GenerationType.AUTO)
   @Column

    private Long id;
    @Column(unique = true, nullable = false)
    private String nameInfo;

   // public String uploadFileName;


    @ManyToOne
    @JsonIgnore
 //  @JoinColumn(name = "user")
    private User user;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @ManyToOne
   // @JoinColumn(name = "item_id")
    @JsonIgnore
    private Order order;


    @OneToOne
    Upload uploads;

    public void setUpload(Upload photo) {
    }
    public Upload getUpload() {
        return uploads;
    }




    public enum Tags {

        SUSTAINABLE,
        BIOLOGICAL,
        ORGANIC,
        PESTICIDE_FREE,
        ADDITIVE_FREE,
        NON_CHEMICAL,
        WOOL_,
        LINEN_,
        LEATHER_,
        SILK_,
        COTTON_,
        MINIMALISTIC,
        ORGANISCH,
        WOL_,
        LINNEN_

    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getNameInfo() {
        return nameInfo;
    }

    public void setNameInfo(String nameInfo) {
        this.nameInfo = nameInfo;
    }

//    public Order getOrder() { return order;}
//    public void setOrder(Order order) {
//        this.order = order;
//    }
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public List<Tags> getTags() {
        return tags;
    }

    public void setTags(List<Tags> tags) {
        this.tags = tags;
    }

    @ElementCollection(targetClass = Tags.class)
    //@LazyCollection
    @Enumerated(EnumType.STRING)
    @Fetch(FetchMode.JOIN)
    List<Tags> tags;

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Item item1 = (Item) o;
//        return  Objects.equals(id, item1.id) && Objects.equals(nameInfo, item1.nameInfo)
//                && Objects.equals(user, item1.user)&& Objects.equals(order, item1.order)
//                && Objects.equals(tags, item1.tags); //&& Objects.equals(uploadFileName, item1.uploadFileName);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, nameInfo, user, order, tags);//,uploadFileName );
//    }

}


///Objects.equals(id, item1.id) &&