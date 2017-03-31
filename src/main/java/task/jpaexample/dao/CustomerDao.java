/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package task.jpaexample.dao;
import task.jpaexample.model.CustomerDTO;

import java.util.List;

public interface CustomerDao extends GenericDao<CustomerDTO,Integer>  {
    List<CustomerDTO> findCustomerBySurname(String surname);
}
