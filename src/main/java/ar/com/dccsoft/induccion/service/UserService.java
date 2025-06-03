package ar.com.dccsoft.induccion.service;

import ar.com.dccsoft.induccion.dto.UserRequest;
import ar.com.dccsoft.induccion.entity.User;
import ar.com.dccsoft.induccion.exception.UserAlreadyExistsException;
import ar.com.dccsoft.induccion.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getFullUsers() {
        return this.userRepository.findWithPermissionsCriteria();
    }

    public void createUser(UserRequest userRequest) {

        if (userRepository.findByLogin(userRequest.getLogin()) != null) {
            throw new UserAlreadyExistsException();
        }

        User newUser = new User();
        newUser.setLogin(userRequest.getLogin());
        newUser.setPassword(userRequest.getPassword());

        userRepository.saveAndFlush(newUser);
    }

}
