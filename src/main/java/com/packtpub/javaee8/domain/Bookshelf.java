package com.packtpub.javaee8.domain;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

/**
 * The Bookshelf implementation is used to find and managed books.
 */
@ApplicationScoped
public class Bookshelf {

    @Inject
    private EntityManager entityManager;

    /**
     * Returns the list of all books in the shelf.
     *
     * @return a collection of books
     */
    public Collection<Book> findAll() {
        TypedQuery<Book> findAll = entityManager.createNamedQuery(Book.FIND_ALL, Book.class);
        return Collections.unmodifiableCollection(findAll.getResultList());
    }

    /**
     * Creates a new book in the bookshelf.
     *
     * @param book a book to create
     */
    public void create(Book book) {
        Objects.requireNonNull(book);
        entityManager.persist(book);
    }

    /**
     * Creates a new book in the bookshelf.
     *
     * @param book a book to update
     */
    public void update(Book book) {
        Objects.requireNonNull(book);
        entityManager.merge(book);
    }

    /**
     * Deletes a book via ISBN.
     *
     * @param isbn the ISBN
     */
    public void delete(String isbn) {
        Objects.requireNonNull(isbn);
        Book reference = entityManager.getReference(Book.class, isbn);
        entityManager.remove(reference);
    }
}