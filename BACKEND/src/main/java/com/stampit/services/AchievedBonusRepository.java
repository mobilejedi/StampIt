package com.stampit.services;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.stampit.model.AchievedBonus;

@RepositoryRestResource(collectionResourceRel ="achievedBonus", path="achievedBonuses")
public interface AchievedBonusRepository extends PagingAndSortingRepository<AchievedBonus, Long> {
}


