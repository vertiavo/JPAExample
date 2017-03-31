package task.jpaexample.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vertiavo on 24.03.17.
 */

@Entity
@Table(name="COURIER", schema="APP")
public class CourierDTO implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String address;
    private String email;
    @OneToMany(mappedBy = "courier", cascade = CascadeType.PERSIST)
    private List<PurchaseDTO> purchases = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void addPurchase(PurchaseDTO purchase) {
        purchases.add(purchase);
        purchase.setCourier(this);
    }

    public List<PurchaseDTO> getPurchases() {
        return purchases;
    }

    @Override
    public String toString() {
        return "["+id+","+name+","+address+","+email+"]";
    }
}
