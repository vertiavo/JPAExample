/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package task.jpaexample.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author user
 */
@NamedQuery(name = "CustomerDTO.findCustomerBySurname", query = "SELECT c FROM CustomerDTO c WHERE c.lastName LIKE :psurname")
@Entity
@Table(name="CUSTOMER", schema="APP")
public class CustomerDTO implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }  

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "["+id+","+firstName+","+lastName+","+email+"]";
    }
}
