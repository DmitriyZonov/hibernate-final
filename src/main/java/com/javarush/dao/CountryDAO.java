package com.javarush.dao;

import com.javarush.domain.Country;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Locale;

public class CountryDAO {
    private final SessionFactory sessionFactory;

    public CountryDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Country> getItems(int offset, int limit) {
        Query<Country> query = sessionFactory.getCurrentSession().createQuery("select c from Country c", Country.class);
        query.setFirstResult(offset);
        query.setMaxResults(limit);

        return query.list();
    }

    public int getTotalCount() {
        Query<Long> query = sessionFactory.getCurrentSession().createQuery("select count(c) from Country c", Long.class);

        return Math.toIntExact(query.uniqueResult());
    }

    public List<Country> getAll() {

        return sessionFactory.getCurrentSession().createQuery("select c from Country c join fetch c.languages", Country.class).list();
    }
}
