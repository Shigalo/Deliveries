/*
package DataBase;

import by.bsuir.ucp.Application;
import by.bsuir.ucp.Entities.User;
import by.bsuir.ucp.Repositories.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class UserWork {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void getUsers() {
        for (User user : userRepository.findAll()) {
            System.out.println(user);
        }
    }

    @Test
    public void addUser() {
        User testUser = new User("Test_name");
        userRepository.save(testUser);
        getUsers();
    }


}
*/
