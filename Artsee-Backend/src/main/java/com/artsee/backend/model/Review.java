package com.artsee.backend.model;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Review{
private Integer review_id;
   
   public void setReview_id(Integer value) {
this.review_id = value;
    }
@Id
public Integer getReview_id() {
return this.review_id;
    }
private Integer rating;

public void setRating(Integer value) {
this.rating = value;
    }
public Integer getRating() {
return this.rating;
    }
private String comment;

public void setComment(String value) {
this.comment = value;
    }
public String getComment() {
return this.comment;
    }
private Boolean wouldRecommend;

public void setWouldRecommend(Boolean value) {
this.wouldRecommend = value;
    }
public Boolean getWouldRecommend() {
return this.wouldRecommend;
    }
private Customer customer;

@ManyToOne(optional=false)
public Customer getCustomer() {
   return this.customer;
}

public void setCustomer(Customer customer) {
   this.customer = customer;
}

}