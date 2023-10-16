package com.project.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.ecommerce.entity.Address;

public interface AddressRepo extends JpaRepository<Address, Long> {

}
