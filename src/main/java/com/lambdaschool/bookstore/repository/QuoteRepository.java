package com.lambdaschool.bookstore.repository;

import com.lambdaschool.bookstore.models.Quote;
import org.springframework.data.repository.CrudRepository;

public interface QuoteRepository extends CrudRepository<Quote, Long>
{

}
