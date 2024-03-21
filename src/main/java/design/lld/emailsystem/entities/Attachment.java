package design.lld.emailsystem.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "attachments")
public class Attachment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long attachmentId;

  @ManyToOne
  @JoinColumn(name = "email_id")
  private Email email;

  @Column(name = "filename")
  private String filename;

  @Column(name = "content_type")
  private String contentType;

  @Column(name = "s3_object_key")
  private String s3ObjectKey;

  @Column(name = "size")
  private Long size;

}
