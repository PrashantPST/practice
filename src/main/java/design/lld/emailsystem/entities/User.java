package design.lld.emailsystem.entities;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long userId;

  @Column(name = "email", unique = true)
  private String email;

  @Column(name = "password_hash")
  private String passwordHash;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "account_created_date")
  @Temporal(TemporalType.TIMESTAMP)
  private Date accountCreatedDate;

  @OneToMany(mappedBy = "user")
  private List<InboxEntry> inbox;

  @OneToMany(mappedBy = "user")
  private List<OutboxEntry> outbox;
}

