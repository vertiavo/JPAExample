/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package task.jpaexample.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.*;

import static org.eclipse.persistence.jpa.jpql.parser.Expression.SELECT;

/**
 * @author m
 */
@NamedQueries({
        @NamedQuery(name = "PurchaseDTO.findByClient", query = "SELECT p FROM PurchaseDTO p LEFT JOIN FETCH p.customer WHERE p.customer.id = :pid"),
        @NamedQuery(name = "PurchaseDTO.findNonOrderedProducts", query = "SELECT p FROM ProductDTO p WHERE p.id NOT IN (SELECT p2.product.id FROM PurchaseItemDTO p2)"),
        @NamedQuery(name = "PurchaseDTO.findBestsellers", query =
                "SELECT p FROM ProductDTO p GROUP BY p HAVING p.id = (SELECT p2.product.id FROM PurchaseItemDTO p2 GROUP BY p2 HAVING COUNT(p2.quantity) = (SELECT MAX(COUNT(p3.quantity)) FROM PurchaseItemDTO p3 GROUP BY p3))")
})
@Entity
@Table(name = "PURCHASE", schema = "APP")
public class PurchaseDTO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "ID_CUSTOMER", referencedColumnName = "ID")
    private CustomerDTO customer;
    @JoinColumn()
    @OneToMany(mappedBy = "purchase", cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.REFRESH})
    private List<PurchaseItemDTO> purchaseItems = new LinkedList<PurchaseItemDTO>();
    private String deliverDestination;
    private Date date;
    @ManyToOne()
    private CourierDTO courier;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public void addPurchaseItem(PurchaseItemDTO pi) {
        purchaseItems.add(pi);
        pi.setPurchase(this);
    }

    public List<PurchaseItemDTO> getPurchaseItems() {
        return purchaseItems;
    }

    public String getDeliverDestination() {
        return deliverDestination;
    }

    public void setDeliverDestination(String deliverDestination) {
        this.deliverDestination = deliverDestination;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public CourierDTO getCourier() {
        return courier;
    }

    public void setCourier(CourierDTO courier) {
        this.courier = courier;
    }

    @Override
    public String toString() {
        return "[" + id + "," + customer + "," + deliverDestination + "," + date + "," + courier + "]";
    }
}
