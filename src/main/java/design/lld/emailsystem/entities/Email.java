package design.lld.emailsystem.entities;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
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
@Table(name = "emails")
public class Email {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long emailId;

  @Column(name = "sender_id")
  private Long senderId;

  @OneToMany(mappedBy = "email") // One-to-many relationship
  private List<EmailRecipient> recipients;

  @OneToMany(mappedBy = "email", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Attachment> attachments;

  @Column(name = "subject")
  private String subject;

  @Column(name = "timestamp")
  @Temporal(TemporalType.TIMESTAMP)
  private Date timestamp;

  @Column(name = "is_read")
  private boolean isRead;

  @Column(name = "s3_body_key")
  private String s3BodyKey;
}
