package design.lld.emailsystem.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "outbox")
public class OutboxEntry {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long outboxId;

  @ManyToOne
  @JoinColumn(name = "user_id") // Link the outbox entry to the sender
  private User user;

  @ManyToOne
  @JoinColumn(name = "email_id")
  private Email email;

}