package com.sda.mierloiubogdan.petclinic.repository;

import com.sda.mierloiubogdan.petclinic.utils.SessionManager;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class BaseRepositoryImpl<T> implements BaseRepository<T> {
    private final Class<T> entityClass;

    public BaseRepositoryImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public List<T> getAll() {
        try (Session session = SessionManager.getSessionFactory().openSession()) {
            return session.createQuery(
                    "FROM " + entityClass.getSimpleName(),
                    entityClass
            ).getResultList();
        }
    }

    @Override
    public Optional<T> findById(int id) {
        try (Session session = SessionManager.getSessionFactory().openSession()) {
            T entity = session.find(entityClass, id);
            return Optional.ofNullable(entity);
        }
    }

    @Override
    public void create(T entity) {
        Transaction transaction = null;
        try (Session session = SessionManager.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.persist(entity);
            transaction.commit();

        } catch (Exception e) {
            System.err.println(e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void updateById(int id, T entity) {
        try (Session session = SessionManager.getSessionFactory().openSession()) {

            T entityFromDB = session.find(entityClass, id);
            if (entityFromDB != null) {
                Transaction transaction = session.beginTransaction();
                try {
                    session.clear();
                    session.merge(entity);
                    transaction.commit();
                } catch (Exception e) {
                    transaction.rollback();
                    throw new IllegalStateException(e);
                }
            } else {
                throw new IllegalArgumentException(
                        entityClass.getSimpleName() + " ID NOT FOUND IN DATABASE"
                );
            }
        }
    }

    @Override
    public void deleteById(int id) {
        try (Session session = SessionManager.getSessionFactory().openSession()) {
            T entity = session.find(entityClass, id);
            if (entity != null) {
                Transaction transaction = session.beginTransaction();
                try {
                    session.delete(entity);
                    transaction.commit();
                } catch (Exception e) {
                    transaction.rollback();
                    throw new IllegalStateException(e);
                }
            } else {
                throw new IllegalArgumentException(
                        entityClass.getSimpleName() + " ID NOT FOUND IN DATABASE"
                );
            }
        }
    }

    @Override
    public void deleteAll() {
        try (Session session = SessionManager.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Query<T> query = session.createNativeQuery(
                    "DELETE FROM " + entityClass.getSimpleName(), entityClass);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            System.err.println("INTERNAL SERVER ERROR" + e.getMessage());
        }
    }
}
