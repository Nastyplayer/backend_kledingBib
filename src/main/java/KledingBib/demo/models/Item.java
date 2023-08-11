package KledingBib.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor

@Getter
@Setter

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



    public void setOrder(Order order) {
        this.order = order;
    }


    @ManyToOne //(mappedBy = "item")
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

//    public List<Order> getOrder() {
//        return orders;
//    }



//    public void setOrder(Order order) {
//    }

//    public Order getOrder() {
//        return null;
//    }


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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


//    public List<Tags> getTags() {
//        return tags;
//    }
//
//    public void setTags(List<Tags> tags) {
//        this.tags = tags;
//    }

    @ElementCollection(targetClass = Tags.class)
    //@LazyCollection
    @Enumerated(EnumType.STRING)
    @Fetch(FetchMode.JOIN)
    List<Tags> tags;
}