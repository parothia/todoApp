package com.hatio.projectApp.Repo;

import com.hatio.projectApp.entity.Task;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepo extends CrudRepository<Task, Long> {

}
