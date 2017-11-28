package com.cybersoft.askmate.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class DataManager {

    private EntityManagerFactory entityManagerFactory;

    public DataManager() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("askmatePU");
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    public EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    public void persist(Object obj) {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();
        em.persist(obj);
        transaction.commit();
        em.close();
    }
}
