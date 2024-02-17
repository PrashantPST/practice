package design.lld.bookmyshow;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "city")
@Data
public class City {

  @Id
  private Long id;
  private String name;
  @OneToMany(mappedBy = "city")
  private Set<Theater> theaters;
}
