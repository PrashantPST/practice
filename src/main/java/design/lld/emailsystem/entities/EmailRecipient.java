package design.lld.emailsystem.entities;

import design.lld.emailsystem.enums.RecipientType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "email_recipients")
public class EmailRecipient {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long recipientId;

  @ManyToOne
  @JoinColumn(name = "email_id")
  private Email email;

  @Column(name = "recipient_email")
  private String recipientEmail; // Added field

  @Column(name = "recipient_type")
  @Enumerated(EnumType.STRING) // Store enum as its String value
  private RecipientType recipientType;

}