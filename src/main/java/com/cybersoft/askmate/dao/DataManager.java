package com.cybersoft.askmate.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class DataManager {

    private static DataManager instance;

    private EntityManagerFactory entityManagerFactory;

    public static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }

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
