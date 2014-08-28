package com.stampit.services;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.stampit.model.Merchant;

@RepositoryRestResource(collectionResourceRel ="merchant", path="merchants")
public interface MerchantRepository extends PagingAndSortingRepository<Merchant, Long> {
}


