/**
 *
 */
package ${package}.javaee.cdi;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ${package}.Environment;

/**
 *
 */
@RequestScoped
class EntityManagerProducer {

    @PersistenceContext(unitName = Environment.APPLICATION_NAME)
    @Produces
    EntityManager entityManager;
}
