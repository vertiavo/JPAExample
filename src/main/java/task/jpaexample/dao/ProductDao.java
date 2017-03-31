/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package task.jpaexample.dao;
import task.jpaexample.model.ProductDTO;

import java.util.List;

public interface ProductDao extends GenericDao<ProductDTO,Integer>  {
    List<ProductDTO> findAll();
    List<ProductDTO> findByPriceLowerThan(double price);
    List<ProductDTO> findByPriceBetween(double priceLow, double priceHi);
    List<ProductDTO> findMostExpensive();
    void changeProductPrice(double percent);
}
