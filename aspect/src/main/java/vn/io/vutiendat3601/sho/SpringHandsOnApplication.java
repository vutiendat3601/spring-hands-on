package vn.io.vutiendat3601.sho;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import vn.io.vutiendat3601.sho.entity.User;
import vn.io.vutiendat3601.sho.service.UserService;

@SpringBootApplication
public class SpringHandsOnApplication {

  public static void main(String[] args) {
    final ApplicationContext appContext =
        SpringApplication.run(SpringHandsOnApplication.class, args);
    appContext.getBean(UserService.class).createUser(new User());
    ;
  }
}
