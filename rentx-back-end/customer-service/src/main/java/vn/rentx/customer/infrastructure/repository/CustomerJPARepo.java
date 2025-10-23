package vn.rentx.customer.infrastructure.repository;

import org.springframework.data.repository.CrudRepository;
import vn.rentx.customer.infrastructure.entity.CustomerEntity;

import java.util.UUID;

public interface CustomerJPARepo extends CrudRepository<CustomerEntity, UUID> {
}
