package com.poly.utils;

import com.poly.common.QueryParameter;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class JpaUtils {

    public static final int NAME_QUERY = 0;
    public static final int JPQL_QUERY = 1;
    public static final int NATIVE_QUERY = 2;
    public static final String UNIT_NAME = "asm";

    @SuppressWarnings("unchecked")
    public static <T> List<T> excuteQuery(String queryValue, List<QueryParameter> queryParameters, int queryType) {
        List<T> list = new ArrayList<>();
        EntityManager em = getEntityManager();
        EntityTransaction tran = em.getTransaction();
        try {
            tran.begin();
            switch (queryType) {
                case NAME_QUERY: {
                    TypedQuery<Object> query = em.createNamedQuery(queryValue, Object.class);
                    if (queryParameters != null) {
                        setParamemter(query, queryParameters);
                    }
                    list = (List<T>) query.getResultList();
                    break;
                }
                case JPQL_QUERY: {
                    TypedQuery<Object> query = em.createQuery(queryValue, Object.class);
                    if (queryParameters != null) {
                        setParamemter(query, queryParameters);
                    }
                    list = (List<T>) query.getResultList();
                    break;
                }
                case NATIVE_QUERY: {
                    Query query = em.createNamedQuery(queryValue, Object.class);
                    if (queryParameters != null) {
                        setParamemter(query, queryParameters);
                    }
                    list = query.getResultList();
                    break;
                }
                default:
                    throw new IllegalArgumentException("JpaUtils.excuteQuery: " + queryType + " not type of query!");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            em.close();
        }
        return list;
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> findWithPagination(String nameQuery, List<QueryParameter> queryParameters, int offset,
                                                 int limit) {
        List<T> list = new ArrayList<>();
        EntityManager em = getEntityManager();
        EntityTransaction tran = em.getTransaction();
        try {
            tran.begin();
            TypedQuery<Object> query = em.createNamedQuery(nameQuery, Object.class);
            if (queryParameters != null) {
                setParamemter(query, queryParameters);
            }
            query.setFirstResult(offset);
            query.setMaxResults(limit);
            list = (List<T>) query.getResultList();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            em.close();
        }
        return list;

    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> findAll(String entityName) {
        List<T> list = new ArrayList<>();
        EntityManager em = getEntityManager();
        EntityTransaction tran = em.getTransaction();
        String jpql = "SELECT o FROM " + entityName + " o";
        try {
            tran.begin();
            Query query = em.createQuery(jpql, Object.class);
            list = (List<T>) query.getResultList();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            em.close();
        }
        return list;

    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> excuteStoredProcedure(String name, List<QueryParameter> queryParameters) {
        List<T> list = new ArrayList<>();
        EntityManager em = getEntityManager();
        EntityTransaction tran = em.getTransaction();
        try {
            tran.begin();
            StoredProcedureQuery query = em.createNamedStoredProcedureQuery(name);
            setParamemter(query, queryParameters);
            list = query.getResultList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            em.close();
        }
        return list;
    }

    public static int excuteUpdate(String queryValue, List<QueryParameter> queryParameters) {
        int rows = -1;
        EntityManager em = getEntityManager();
        EntityTransaction tran = em.getTransaction();
        try {
            tran.begin();
            Query query = em.createQuery(queryValue);
            setParamemter(query, queryParameters);
            rows = query.executeUpdate();
            tran.commit();
        } catch (Exception e) {
            tran.rollback();
            System.out.println(e.getMessage());
        } finally {
            em.close();
        }
        return rows;
    }

    public static <T> T findById(Class<T> clazz, Object id) {
        EntityManager em = getEntityManager();
        EntityTransaction tran = em.getTransaction();
        try {
            tran.begin();
            return em.find(clazz, id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            em.close();
        }
        return null;
    }

    public static <T> T persist(Class<T> clazz, T entity) {
        EntityManager em = getEntityManager();
        EntityTransaction tran = em.getTransaction();
        try {
            tran.begin();
            em.persist(entity);
            tran.commit();
            return entity;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            tran.rollback();
        } finally {
            em.close();
        }
        return null;
    }

    public static <T> T merge(Class<T> clazz, T entity) {
        EntityManager em = getEntityManager();
        EntityTransaction tran = em.getTransaction();
        try {
            tran.begin();
            em.merge(entity);
            tran.commit();
            return entity;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            tran.rollback();
        } finally {
            em.close();
        }
        return null;
    }

    public static <T> void deleteById(Class<T> clazz, Object id) {
        EntityManager em = getEntityManager();
        EntityTransaction tran = em.getTransaction();
        try {
            tran.begin();
            T entity = em.find(clazz, id);
            if (entity != null) {
                em.remove(entity);
            }
            tran.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            tran.rollback();
        } finally {
            em.close();
        }
    }

    public static int count(String entityName) {
        int count = 0;
        EntityManager em = getEntityManager();
        EntityTransaction tran = em.getTransaction();
        try {
            String jpql = "SELECT COUNT(o) FROM " + entityName + " o";
            tran.begin();
            Query query = em.createQuery(jpql);
            Object obj = query.getSingleResult();
            if (obj != null) {
                count = Integer.parseInt(obj.toString());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            em.close();
        }
        return count;
    }

    public static EntityManager getEntityManager() {
        return Persistence.createEntityManagerFactory(UNIT_NAME).createEntityManager();
    }

    public static TypedQuery<Object> setParamemter(TypedQuery<Object> query, List<QueryParameter> queryParameters) {
        for (QueryParameter queryParameter : queryParameters) {
            if (queryParameter.getValue() instanceof Date) {
                query.setParameter(queryParameter.getKey(), (Date) queryParameter.getValue(),
                        queryParameter.getTemporalType());
            } else if (queryParameter.getValue() instanceof Calendar) {
                query.setParameter(queryParameter.getKey(), (Calendar) queryParameter.getValue(),
                        queryParameter.getTemporalType());
            } else {
                query.setParameter(queryParameter.getKey(), queryParameter.getValue());
            }
        }
        return query;
    }

    public static Query setParamemter(Query query, List<QueryParameter> queryParameters) {
        for (QueryParameter queryParameter : queryParameters) {
            if (queryParameter.getKey() == null) {
                query.setParameter(queryParameter.getPosition(), queryParameter.getValue());
            } else {
                query.setParameter(queryParameter.getKey(), queryParameter.getValue());
            }
        }
        return query;
    }

    public static StoredProcedureQuery setParamemter(StoredProcedureQuery query,
                                                     List<QueryParameter> queryParameters) {
        for (QueryParameter queryParameter : queryParameters) {
            query.setParameter(queryParameter.getKey(), queryParameter.getValue());
        }
        return query;
    }
}