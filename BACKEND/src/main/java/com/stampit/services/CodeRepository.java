package com.stampit.services;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.stampit.model.AchievedBonus;
import com.stampit.model.ActiveCard;
import com.stampit.model.Code;
import com.stampit.model.Customer;
import com.stampit.model.Log;

@RepositoryRestResource(collectionResourceRel ="code", path="codes")
public interface CodeRepository extends PagingAndSortingRepository<Code, Long> {
}


