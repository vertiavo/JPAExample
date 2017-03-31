/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package task.jpaexample.dao.jpa;

import task.jpaexample.dao.ProductDao;
import task.jpaexample.model.ProductDTO;
import task.jpaexample.util.JpaFactory;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 *
 * @author user
 */
public class JpaProductDao extends GenericJpaDao<ProductDTO, Integer> implements ProductDao {
    @Override
    public List<ProductDTO> findAll() {
        EntityManager em = JpaFactory.getEntityManager();
        TypedQuery<ProductDTO> query = em.createQuery("SELECT p FROM ProductDTO p", ProductDTO.class);
        List<ProductDTO> result = query.getResultList();
        em.close();
        return result;
    }

    @Override
    public List<ProductDTO> findByPriceLowerThan(double price) {
        EntityManager em = JpaFactory.getEntityManager();

        TypedQuery<ProductDTO> query = em.createNamedQuery("ProductDTO.findByPriceLowerThan", ProductDTO.class);
        query.setParameter("pprice", price);

        List<ProductDTO> result = query.getResultList();
        em.close();
        return result;
    }

    @Override
    public List<ProductDTO> findByPriceBetween(double priceLow, double priceHi) {
        EntityManager em = JpaFactory.getEntityManager();

        TypedQuery<ProductDTO> query = em.createNamedQuery("ProductDTO.findByPriceBetween", ProductDTO.class);
        query.setParameter("plow", priceLow);
        query.setParameter("phigh", priceHi);

        List<ProductDTO> result = query.getResultList();
        em.close();
        return result;
    }

    @Override
    public List<ProductDTO> findMostExpensive() {
        EntityManager em = JpaFactory.getEntityManager();

        TypedQuery<ProductDTO> query = em.createNamedQuery("ProductDTO.findMostExpensive", ProductDTO.class);

        List<ProductDTO> result = query.getResultList();
        em.close();
        return result;
    }

    @Override
    public void changeProductPrice(double percent) {
        EntityManager em = JpaFactory.getEntityManager();

        TypedQuery query = em.createNamedQuery("ProductDTO.changeProductPrice", ProductDTO.class);
        if (percent < 0) {
            percent = (100 - percent) / 100;
        } else {
            percent = (100 + percent) / 100;
        }
        query.setParameter("ppercent", percent);

        em.getTransaction().begin();
        query.executeUpdate();
        em.getTransaction().commit();

        em.close();
    }
}
