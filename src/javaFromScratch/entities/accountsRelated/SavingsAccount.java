package javaFromScratch.entities.accountsRelated;

public class SavingsAccount extends BankAccount {
  
  private Double interestRate;

  public SavingsAccount() {
    super();
  }

  public SavingsAccount(Integer accId, String accOwner, Double balance, Double interestRate) {
    super(accId, accOwner, balance);
    this.interestRate = interestRate;
  }

  public Double getInterestRate() {
    return interestRate;
  }

  public void setInterestRate(Double interestRate) {
    this.interestRate = interestRate;
  }

  public void updateBalance() {
    balance += balance * interestRate;
  }

  /* (non-Javadoc)
   * @see javaFromScratch.entities.accountsRelated.BankAccount#withDraw(java.lang.Double)
   */
  @Override
  public void withdraw(Double amount) {
    balance -= amount;
  }

  @Override
  public void deposit(Double amount) {
    // é possível chamar a implementação da superclasse com a palavra super
    // veja que agora para depositar em saving accounts, há uma taxa de 10
    super.deposit(amount - 10.0);
  }
}
