package ch.raphaelfleischlin.playground.domain;

import ch.raphaelfleischlin.playground.aspects.logging.ExceptionLogged;
import ch.raphaelfleischlin.playground.aspects.logging.MethodCallLogged;
import ch.raphaelfleischlin.playground.database.MigrationStartupBean;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Raphael Fleischlin <raphael.fleischlin@stud.hslu.ch>
 */
@Stateless
@MethodCallLogged
@ExceptionLogged
public class UserService {
    
    @Inject
    private MigrationStartupBean bean;
    
    private List<User> users;
    private int nextUserId = 1;
    
    public UserService() {
        users = new ArrayList<User>();
        for (int i = 1; i <= 5; i++) {
            users.add(getDummyUser());
        }
    }
    
    private User getDummyUser() {
        int userId = getNextUserId();
        User user = new User();
        user.setUserId(userId);
        user.setUserName(String.format("dummy%s", userId));
        user.setFirstName("User");
        user.setLastName("Dummy");
        user.setEmail(String.format("dummy%s@user.ch", userId));
        return user;
    }
    
    private int getNextUserId() {
        return nextUserId++;
    }
    
    public List<User> getUsers() {
        return users;
    }
    
    public User getUser(int userId) {
        for (User user : users) {
            if (user.getUserId() == userId) {
                return user;
            }
        }
        return null;
    }
    
    public void editUser(User user) {
        // nothing to do
    }
    
    public User createUser() {
        int userId = getNextUserId();
        User user = new User();
        user.setUserId(userId);
        users.add(user);
        return user;
    }
    
    public void deleteUser(int userId) {
        User toDelete = null;
        for (User user : users) {
            if (user.getUserId() == userId) {
                toDelete = user;
                break;
            }
        }
        if (toDelete != null) {
            users.remove(toDelete);
        }
    }
    
}
