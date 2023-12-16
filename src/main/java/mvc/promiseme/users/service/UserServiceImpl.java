package mvc.promiseme.users.service;

import lombok.RequiredArgsConstructor;
import mvc.promiseme.common.exception.ErrorCode;
import mvc.promiseme.common.exception.UserException;
import mvc.promiseme.users.dto.LoginRequestDTO;
import mvc.promiseme.users.dto.LoginResponseDTO;
import mvc.promiseme.users.dto.UserDTO;
import mvc.promiseme.users.entity.Users;
import mvc.promiseme.users.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    @Override
    public String register(UserDTO userDTO) {
        if(userRepository.findByEmailIgnoreCase(userDTO.getEmail()) != null) {
            throw new UserException(ErrorCode.DUPLICATE_USER);
        }
        Users users = Users.builder().
                email(userDTO.getEmail()).
                nickname(userDTO.getNickname()).
                password(userDTO.getPassword()).build();
        userRepository.save(users);
        return "success";
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {

        Users loginUser = userRepository.findByEmailIgnoreCase(loginRequestDTO.getEmail());
        if(loginUser == null)
            throw new UserException(ErrorCode.INVALID_User_Login);

        if(!loginRequestDTO.getPassword().equals(loginUser.getPassword()))
            throw new UserException(ErrorCode.INVALID_User_Password);

        return new LoginResponseDTO(loginUser.getUserId(),loginUser.getNickname());
    }

    @Override
    public String logout(String token) {
        return null;
    }

    @Override
    public Long check(String email) {
        System.out.println("email : " + email);
        Users checkUser = userRepository.findByEmailIgnoreCase(email);
        if(checkUser == null)
            throw new UserException(ErrorCode.INVALID_User_Login);


        return checkUser.getUserId();
    }
}
