package com.dily.repositories;

import com.dily.entities.Memory;
import com.dily.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by rusum on 25.05.2017.
 */
public interface IMemoryRepository extends CrudRepository<Memory, Integer>,JpaRepository<Memory,Integer> {
}
