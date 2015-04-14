package com.wang.michael.online_shop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.joda.time.DateTime;

@Entity
@Table(name = "news")
public class News {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  @Column(name = "title")
  private String title;
  @Column(name = "content")
  private String content;
  @Column(name = "createdDateTime")
  private DateTime createdDateTime;
  @Column(name = "updatedDateTime")
  private DateTime updatedDateTime;

  public News(int id, String title, String content) {
    this.id = id;
    this.title = title;
    this.content = content;
    this.createdDateTime = new DateTime(2015, 4, id, id, 00, 00);
    this.updatedDateTime = new DateTime(2015, 4, id * 2, id * 2, 00, 00);
  }

  public News() {
  }

  public long getId() {
    return id;
  }

  public void setId(Integer id) {
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
