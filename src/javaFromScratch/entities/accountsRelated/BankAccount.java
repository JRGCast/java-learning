package javaFromScratch.entities.accountsRelated;

public class BankAccount {
  private Integer accId;
  private String accOwner;
  // o atributo protected é acessível pela própria classe e por outras que sejam
  // suas filhas (recebam por herança), no caso aqui, a PJAccount
  protected Double balance;

  // aqui é o exemplo de sobrecarga, existem dois construtores para a mesma
  // classe, um com argumentos e outro sem
  public BankAccount() {

  }

  public BankAccount(Integer accId, String accOwner, Double balance) {
    this.accId = accId;
    this.accOwner = accOwner;
    this.balance = balance;
  }

  public Integer getAccId() {
    return accId;
  }

  public void setAccId(Integer accId) {
    this.accId = accId;
  }

  public String getAccOwner() {
    return accOwner;
  }

  public void setAccOwner(String accOwner) {
    this.accOwner = accOwner;
  }

  public Double getBalance() {
    return balance;
  }

  public void withdraw(Double amount) {
    this.balance -= amount + 5.0;
  }

  public void deposit(Double amount) {
    this.balance += amount;
  }

  @Override
  public String toString() {
    return "BankAccount [accId=" + accId + ", accOwner=" + accOwner + ", balance=" + balance + "]";
  }
}
