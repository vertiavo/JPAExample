/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package task.jpaexample.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author m
 */
@NamedQueries({
        @NamedQuery(name = "ProductDTO.findByPriceLowerThan", query = "SELECT p FROM ProductDTO p WHERE p.price < :pprice"),
        @NamedQuery(name = "ProductDTO.findByPriceBetween", query = "SELECT p FROM ProductDTO p WHERE p.price BETWEEN :plow AND :phigh"),
        @NamedQuery(name = "ProductDTO.findMostExpensive", query = "SELECT p FROM ProductDTO p WHERE p.price = (SELECT MAX(p2.price) FROM ProductDTO p2)"),
        @NamedQuery(name = "ProductDTO.changeProductPrice", query = "UPDATE ProductDTO p SET p.price = p.price*:ppercent")
})
@Entity
@Table(name="PRODUCT", schema="APP")
public class ProductDTO implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Double price;

    public ProductDTO() {}

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

     @Override
    public String toString() {
        return "["+id+","+name+","+price+"]";
    }
}
