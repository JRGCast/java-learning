package javaFromScratch.entities;

import java.util.Date;

import javaFromScratch.utils.enums.OrderStatusEnum;
  
public class OrderStatus{
  private Integer id;
  private Date moment;
  private OrderStatusEnum currStatus;
  
  public OrderStatus(Integer id, Date moment, OrderStatusEnum currStatus) {
    this.id = id;
    this.moment = moment;
    this.currStatus = currStatus;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Date getMoment() {
    return moment;
  }

  public void setMoment(Date moment) {
    this.moment = moment;
  }

  public OrderStatusEnum getCurrStatus() {
    return currStatus;
  }

  public void setCurrStatus(OrderStatusEnum currStatus) {
    this.currStatus = currStatus;
  }

  @Override
  public String toString() {
    return "OrderStatus [id=" + id + ", moment=" + moment + ", currStatus=" + currStatus + "]";
  }  
}
