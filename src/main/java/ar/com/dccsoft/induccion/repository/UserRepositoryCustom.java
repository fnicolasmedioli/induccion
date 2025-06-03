package ar.com.dccsoft.induccion.repository;

import ar.com.dccsoft.induccion.entity.User;

import java.util.List;

public interface UserRepositoryCustom {

    List<User> findWithPermissionsCriteria();

}
