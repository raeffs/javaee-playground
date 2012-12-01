package ch.raphaelfleischlin.playground.web;

import ch.raphaelfleischlin.playground.domain.User;
import ch.raphaelfleischlin.playground.domain.UserService;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author Raphael Fleischlin <raphael.fleischlin@stud.hslu.ch>
 */
@ManagedBean
@ViewScoped
public class UserBean {
    
    @Inject
    private UserService userService;
    
    private boolean isInEditMode;
    private User currentUser;
    
    public boolean isInEditMode() {
        return isInEditMode;
    }
    
    public User getCurrentUser() {
        return currentUser;
    }
    
    @PostConstruct
    public void init() {
        currentUser = new User();
        isInEditMode = false;
    }
    
    public List<User> list() {
        return userService.getUsers();
    }
    
    public void edit(int userId) {
        currentUser = userService.getUser(userId);
        isInEditMode = true;
    }
    
    public void save() {
        userService.editUser(currentUser);
        discard();
    }
    
    public void discard() {
        currentUser = new User();
        isInEditMode = false;
    }
    
    public void create() {
        currentUser = userService.createUser();
        isInEditMode = true;
    }
    
    public void delete(int userId) {
        userService.deleteUser(userId);
    }
    
}
