package com.example.eat.domain;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@Accessors(chain = true)
@Entity
@Getter
@Setter
@ToString(exclude = {"category"})
@NoArgsConstructor  //기본 생성자가 없어도 알아서 해준다.
@AllArgsConstructor //생성자에 있는 필드를 알아서 restaurant(1004L,name,busan) 이런것을 알아서 만들어준다.
                    //하지만 List에대한 것도 만들어 주기때문에 이부분은 조정해주어야한다.
                    //그래서 bilder를 사용해서 4개의 필드중 몇개의 필드를 지정해서 만들수 있다.
@Builder //builder를 만들어 줄때는 field의 내용은 상관이 없다.
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Setter
    private Long id;

   /* @NotNull
    private Long categoryId;*/

    @ManyToOne
    @NotNull
    //@JoinColumn(name = "category_id")
    private Category category;

    @NotEmpty
    private String name;

    @NotEmpty
    private String address;

    @Transient //데이터를 임시적으로 제외시킬수 있다. -> DB에 저장할때 이부분은 테이블에서 제외된다.
    @JsonInclude(JsonInclude.Include.NON_NULL) //null이 아닐때만 출력한다.
    private List<MenuItem> menuItems;

    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Review> reviews;

    public String getImformation() {
        return name+ " in "+address;
    }

    public void updateInformation(String name, String address) {
        this.name=name;
        this.address=address;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems=new ArrayList<>(menuItems);
    }

    public void setReviews(List<Review> reviews) {
        this.reviews=new ArrayList<>(reviews);
    }

   @OneToMany(fetch = FetchType.LAZY,mappedBy = "restaurant")
    private List<MenuItem> menuItemList;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "restaurant")
    private List<Review> reviewList;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "restaurant")
    private List<Reservation> reservationList;

    /*
    public Restaurant(Long id,String name,String address){
        this.name=name;
        this.address=address;
        this.id=id;
    }

    public Restaurant(String name, String address) {
        this.name=name;
        this.address=address;
    }
    */

   /* public void setId(long id)
    {
        this.id=id;
    }*/
    /*
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getAddress() {
        return address;
    }
    */
    //public List<MenuItem> getMenuItems() {
    //    return menuItems;

    //}
    /*public void addMenuItem(MenuItem menuItem) {
        menuItems.add(menuItem);
    }
    */
    /*
    public void setName(String name) {
        this.name=name;
    }
    */
}
