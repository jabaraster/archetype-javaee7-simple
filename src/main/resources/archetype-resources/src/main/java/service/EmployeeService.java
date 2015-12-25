/**
 *
 */
package ${package}.service;

import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import jabara.general.ArgUtil;
import jabara.general.NotFound;
import jabara.jpa.entity.EntityBase_;
import jabara.jpa.entity.Id;
import sandbox.entity.EEmployee;

/**
 *
 */
@Dependent
public class EmployeeService {

    private final EntityManager em;

    /**
     * @param pEntityManager -
     */
    @Inject
    public EmployeeService(final EntityManager pEntityManager) {
        this.em = pEntityManager;
    }

    /**
     * @param pDeleteTarget -
     */
    @Transactional
    public void delete(final EEmployee pDeleteTarget) {
        ArgUtil.checkNull(pDeleteTarget, "pDeleteTarget"); //$NON-NLS-1$
        this.em.remove(this.em.merge(pDeleteTarget));
    }

    /**
     * @return -
     */
    public List<EEmployee> getAll() {
        final CriteriaBuilder builder = this.em.getCriteriaBuilder();
        final CriteriaQuery<EEmployee> query = builder.createQuery(EEmployee.class);
        query.from(EEmployee.class);
        return this.em.createQuery(query).getResultList();
    }

    /**
     * @param pId -
     * @return -
     * @throws NotFound -
     */
    public EEmployee getById(final Id<EEmployee> pId) throws NotFound {
        final CriteriaBuilder builder = this.em.getCriteriaBuilder();
        final CriteriaQuery<EEmployee> query = builder.createQuery(EEmployee.class);
        final Root<EEmployee> root = query.from(EEmployee.class);
        query.where(builder.equal(root.get(EntityBase_.id), pId.getValueAsObject()));
        try {
            return this.em.createQuery(query).getSingleResult();
        } catch (@SuppressWarnings("unused") final NoResultException e) {
            throw NotFound.GLOBAL;
        }
    }

    /**
     * @param pName -
     * @return -
     */
    @Transactional
    public EEmployee insert(final String pName) {
        final EEmployee newEmp = new EEmployee();
        newEmp.setName(pName);
        this.em.persist(newEmp);
        return newEmp;
    }

}
