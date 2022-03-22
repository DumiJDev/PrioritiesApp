package com.devj.prioritiesapp.repository;

import com.devj.prioritiesapp.models.ItemPriority;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemPriorityRepository extends CrudRepository<ItemPriority, Long> {
    ItemPriority findById(long id);

    void deleteById(long id);

    List<ItemPriority> findAllByIdAfterOrderByPriorityOrderNumberDesc(long id);

    Integer countAllByIdAfter(long id);

}
