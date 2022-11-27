package javaFromScratch.entities.accountsRelated;

public class PJAccount extends BankAccount {

  private Double loanLimit;

  public PJAccount() {
    super();
  }

  public PJAccount(Integer accId, String accOwner, Double balance, Double loanLimit) {
    super(accId, accOwner, balance);
    this.loanLimit = loanLimit;
  }

  public void loan(Double loanValue) {
    if (loanValue <= loanLimit) {
      // caso o atributo da classe pai fosse 'private', não seria acessível por outras
      // classes. Como está como protected, é acessível por suas classes filhas
      balance += loanValue - 10.0;
    }
  }

  public Double getLoanLimit() {
    return loanLimit;
  }

  public void setLoanLimit(Double loanLimit) {
    this.loanLimit = loanLimit;
  }
}
