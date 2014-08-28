package com.stampit.services;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.stampit.model.ActiveCard;

@RepositoryRestResource(collectionResourceRel ="activeCard", path="activeCards")
public interface ActiveCardRepository extends PagingAndSortingRepository<ActiveCard, Long> {
}


