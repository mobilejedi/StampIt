package com.stampit.services;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.stampit.model.Bonus;

@RepositoryRestResource(collectionResourceRel ="bonus", path="bonuses")
public interface BonusRepository extends PagingAndSortingRepository<Bonus, Long> {
}


