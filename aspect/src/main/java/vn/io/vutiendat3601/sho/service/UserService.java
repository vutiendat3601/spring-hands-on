package vn.io.vutiendat3601.sho.service;

import org.springframework.stereotype.Service;
import vn.io.vutiendat3601.sho.entity.User;

@Service
public class UserService {
  public void createUser(User user) {
    System.out.println("User created: " + user.getName());
  }
}
