package com.kib.weblab3.repository;

import com.kib.weblab3.beans.HitCheckResult;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.*;
import java.util.List;

@ManagedBean(eager = true, name="repoBean")
@ApplicationScoped
public class HitCheckRepoImpl implements HitCheckRepo {

    private static final String table_name = "HitCheckResult";
    private static final String deleteAllBySessionIdQuery = "DELETE FROM " + table_name + " h WHERE h.sessionId = :sessionId";
    private static final String getAllResultsBySessionIdQuery = "select h from " + table_name + " h where h.sessionId = :sessionId";
    private final EntityManager entityManager;
    private final EntityTransaction entityTransaction;

    public HitCheckRepoImpl() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("hitCheck");
        entityManager = factory.createEntityManager();
        entityTransaction = entityManager.getTransaction();
    }

    @Override
    public void save(String sessionId, HitCheckResult hitCheckResult) {
        entityTransaction.begin();
        entityManager.persist(hitCheckResult);
        entityTransaction.commit();
    }

    @Override
    public void deleteAllBySessionId(String sessionId) {
        entityTransaction.begin();
        Query query = entityManager.createQuery(deleteAllBySessionIdQuery);
        query.setParameter("sessionId", sessionId);
        query.executeUpdate();
        entityTransaction.commit();
    }

    @Override
    public List<HitCheckResult> getAllResultsBySessionId(String sessionId) {
        entityTransaction.begin();
        Query query = entityManager.createQuery(getAllResultsBySessionIdQuery);
        query.setParameter("sessionId", sessionId);
        List<HitCheckResult> results = query.getResultList();
        entityTransaction.commit();
        return results;
    }
}
