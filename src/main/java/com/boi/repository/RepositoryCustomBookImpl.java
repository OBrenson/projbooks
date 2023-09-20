package com.boi.repository;

import com.boi.domain.Book;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.query.QueryUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RepositoryCustomBookImpl implements RepositoryCustomBook {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Book> findByColumn(String column, Object value, PageRequest pageable) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> query = cb.createQuery(Book.class);
        Root<Book> bookRoot = query.from(Book.class);

        String[] columns = column.split("_");
        Path<Object> path = null;
        if(columns.length == 2) {
            path = bookRoot.join(columns[0]).get(columns[1]);
        } else {
            path = bookRoot.get(columns[0]);
        }
        if(columns[0].equals("authors")) {
            List<UUID> tmp = new ArrayList<>();
            tmp.add(UUID.fromString((String)value));
            value = tmp;
        }
        query = query.select(bookRoot).where(cb.equal(path, value));
        if(!pageable.getSort().isEmpty()) {
            query = query.orderBy(QueryUtils.toOrders(pageable.getSort(), bookRoot, cb));
        }
        int from = (pageable.getPageNumber()) * pageable.getPageSize();
        int to = from + pageable.getPageSize();
        return entityManager.createQuery(query)
                .setFirstResult(from)
                .setMaxResults(pageable.getPageSize())
                .getResultList();

    }

}
