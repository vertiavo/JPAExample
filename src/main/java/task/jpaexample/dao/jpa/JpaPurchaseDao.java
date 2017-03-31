/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package task.jpaexample.dao.jpa;

import task.jpaexample.dao.PurchaseDao;
import task.jpaexample.model.ProductDTO;
import task.jpaexample.model.PurchaseDTO;
import task.jpaexample.util.JpaFactory;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 *
 * @author user
 */
public class JpaPurchaseDao  extends GenericJpaDao<PurchaseDTO, Integer> implements PurchaseDao {
    @Override
    public List<PurchaseDTO> findAll() {
        EntityManager em = JpaFactory.getEntityManager();

        TypedQuery<PurchaseDTO> query = em.createQuery("SELECT DISTINCT p FROM PurchaseDTO p LEFT JOIN FETCH p.purchaseItems LEFT JOIN FETCH p.customer", PurchaseDTO.class);
        query.setHint("eclipselink.left-join-fetch", "p.purchaseItems.product");

        List<PurchaseDTO> result = query.getResultList();
        em.close();
        return result;
    }

    @Override
    public List<PurchaseDTO> findByClient(Integer id) {
        EntityManager em = JpaFactory.getEntityManager();

        TypedQuery<PurchaseDTO> query = em.createNamedQuery("PurchaseDTO.findByClient", PurchaseDTO.class);
        query.setParameter("pid", id);

        List<PurchaseDTO> result = query.getResultList();
        em.close();
        return result;
    }

    @Override
    public List<ProductDTO> findNonOrderedProducts() {
        EntityManager em = JpaFactory.getEntityManager();

        TypedQuery<ProductDTO> query = em.createNamedQuery("PurchaseDTO.findNonOrderedProducts", ProductDTO.class);

        List<ProductDTO> result = query.getResultList();
        em.close();
        return result;
    }

    @Override
    public List<ProductDTO> findBestsellers() {
        EntityManager em = JpaFactory.getEntityManager();

        TypedQuery<ProductDTO> query = em.createNamedQuery("PurchaseDTO.findBestsellers", ProductDTO.class);

        List<ProductDTO> result = query.getResultList();
        em.close();
        return result;
    }
}
