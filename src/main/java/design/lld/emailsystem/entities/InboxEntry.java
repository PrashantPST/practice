package design.lld.emailsystem.entities;


import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "inbox_entries")
public class InboxEntry {

  @Id
  private String id;

  private String userId;

  private String emailId;

  private String sender;

  private String subject;

  private String preview;

  @Temporal(TemporalType.TIMESTAMP)
  private Date timestamp;

  private boolean isRead;

  private String recipientType; // 'TO', 'CC', 'BCC'

  // Constructors, Getters and Setters ...
}

