package com.spring_boot.sBoot.Services;

import com.spring_boot.sBoot.dao.RoleDao;
import com.spring_boot.sBoot.dao.UserDao;
import com.spring_boot.sBoot.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import com.spring_boot.sBoot.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final RoleDao roleDao;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDao userDao, RoleDao roleDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User passwordCoder(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return user;
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User getById(long id) {
        return userDao.getById(id);
    }

    @Override
    public void save(User user) {
        userDao.save(passwordCoder(user));
    }

    @Override
    public void deleteById(long id) {
        userDao.deleteById(id);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    @PostConstruct
    public void addDefaultUser() {
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(roleDao.findById(1L).get());
        Set<Role> roleSet2 = new HashSet<>();
        roleSet2.add(roleDao.findById(1L).get());
        roleSet2.add(roleDao.findById(2L).get());
        User user1 = new User("Ara","root","root",roleSet);
        User user2 = new User("Petuh", "crud","crud",roleSet2);
        save(user1);
        save(user2);
    }
}
