package com.example.coindesk.repository;

import com.example.coindesk.model.BitcoinEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface BitcoinEntityRep extends JpaRepository<BitcoinEntity,Integer> {
}
