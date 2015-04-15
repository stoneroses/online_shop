package com.wang.michael.online_shop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@Entity
@Table(name = "news")
public class News {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @Column(name = "title")
  private String title;
  @Column(name = "content")
  private String content;
  @Column(name = "createdDateTime")
  @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
  private DateTime createdDateTime;
  @Column(name = "updatedDateTime")
  @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
  private DateTime updatedDateTime;

  public News(Long id, String title, String content) {
    this.id = id;
    this.title = title;
    this.content = content;
    this.createdDateTime = new DateTime(2015, 4, id.intValue(), id.intValue(), 00, 00);
    this.updatedDateTime = new DateTime(2015, 4, id.intValue() * 2, id.intValue() * 2, 00, 00);
  }

  public News() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public DateTime getCreatedDateTime() {
    return createdDateTime;
  }

  public void setCreatedTime(DateTime createdDateTime) {
    this.createdDateTime = createdDateTime;
  }

  public DateTime getUpdatedDateTime() {
    return updatedDateTime;
  }

  public void setUpdatedDateTime(DateTime updatedDateTime) {
    this.updatedDateTime = updatedDateTime;
  }

}
