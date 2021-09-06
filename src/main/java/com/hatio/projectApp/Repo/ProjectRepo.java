package com.hatio.projectApp.Repo;


import com.hatio.projectApp.entity.Project;
import com.hatio.projectApp.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface ProjectRepo extends CrudRepository<Project,Long> {

}
