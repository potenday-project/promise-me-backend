package mvc.promiseme.users.service;

import mvc.promiseme.users.dto.LoginRequestDTO;
import mvc.promiseme.users.dto.LoginResponseDTO;
import mvc.promiseme.users.dto.UserDTO;

public interface UserService {

    public String register(UserDTO userDTO);
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO);
    public String logout(String token);
    public Long check(String email);
}
