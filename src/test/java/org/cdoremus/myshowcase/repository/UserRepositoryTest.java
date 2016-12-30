package org.cdoremus.myshowcase.repository;

import static org.junit.Assert.*;

import org.cdoremus.myshowcase.model.User;
import org.cdoremus.myshowcase.TestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * JUnit test of {@link UserRepository}.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes=TestConfig.class)
public class UserRepositoryTest {
    @Autowired
    private UserRepository repository;

    @Test
    public void testCreateUser() {
        User user = createUser();


        User found = repository.save(user);

        assertEquals(user.getFirstName(), found.getFirstName());
        assertEquals(user.getLastName(), found.getLastName());
    }

    @Test
    public void testFindAll() {
        List<User> found = repository.findAll();

//        System.out.println("Users found: " + found);
        assertEquals(3, found.size());
    }

    @Test
    public void testFindByLoginId() {
        String loginId = "craig123";
        String fname = "Craig";
        String lname = "Foobar";
//        User user = createUser();
//
//        repository.save(user);
        User found = repository.findByLoginId(loginId);
//        User found = repository.findByLoginId(user.getLoginId());

        assertEquals(fname, found.getFirstName());
        assertEquals(lname, found.getLastName());
//        assertEquals(user.getFirstName(), found.getFirstName());
//        assertEquals(user.getLastName(), found.getLastName());
    }

    private User createUser() {
        return new User("foobar", "Foo", "Bar", "foo@bar.com");
    }

}
