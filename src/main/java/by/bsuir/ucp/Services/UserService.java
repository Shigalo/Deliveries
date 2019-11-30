package by.bsuir.ucp.Services;

import by.bsuir.ucp.Entities.Role;
import by.bsuir.ucp.Entities.User;
import by.bsuir.ucp.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    private User findByName(String name) {
        return userRepository.findByName(name);
    }

    public User getCurrentUser(){
        try {
            org.springframework.security.core.userdetails.User authUser =
                    (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext()
                            .getAuthentication().getPrincipal();

            return findByName(authUser.getUsername());
        }catch (Exception e){
//            e.printStackTrace();
            return null;
        }
    }

    public boolean isAdmin() {
        if(getCurrentUser() != null) {
            return getCurrentUser().getRoles().contains(Role.ADMIN);
        }
        return false;
    }
    public boolean isLogin() {
        return getCurrentUser() != null;
    }
}
