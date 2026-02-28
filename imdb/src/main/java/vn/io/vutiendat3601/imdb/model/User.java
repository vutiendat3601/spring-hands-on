package vn.io.vutiendat3601.imdb.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class User {
  private long id;
  private String email;
  private String password;
}
