package ar.com.dccsoft.induccion.repository;

import ar.com.dccsoft.induccion.entity.Group;
import ar.com.dccsoft.induccion.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    @Query("FROM Group g LEFT JOIN FETCH g.users u")
    List<Group> getGroupsWithUsers();

    @Query("FROM Group g LEFT JOIN FETCH g.operations o")
    List<Group> getGroupsWithOperations();

}
