package javaFromScratch;

import java.io.File;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import javaFromScratch.entities.Exceptions;
import javaFromScratch.entities.OrderStatus;
import javaFromScratch.entities.ProductsStock;
import javaFromScratch.entities.accountsRelated.BankAccount;
import javaFromScratch.entities.accountsRelated.PJAccount;
import javaFromScratch.entities.accountsRelated.SavingsAccount;
import javaFromScratch.utils.FileHandlerClass;
import javaFromScratch.utils.HandlerException;
import javaFromScratch.utils.enums.OrderStatusEnum;

public class Main {
    private static final Scanner sc = new Scanner(System.in);

    // Ao usar o lixo do VSCODE não esqueça de estar na pasta SRC e impar o
    // workspace para rodar
    public Main() {
    }

    public static void main(String[] args) { // aqui é o entry point do algoritmo Java, veja o init()
        try {     
            // init();
            // printMathProblems();
            // printInputtedData();
            // printProductsStock();
            // printUsingEnum();
            // bankAccounts("upcasting");
            // bankAccounts("override");
            exceptionsBreak("goodMethod");
            // tryFileHandler("listDir"); //
            // "scanner/tryCatchSimple/tryCatchResource/writeNew/writeAppend/listDir/newDir"
        } catch (RuntimeException e) {
            System.out.println("Unexpected Error");
        }
    }

    public static void init() {
        byte num = 15; // no java, toda declaração vem como tipo nome = atribuição
        float floated = 15.25f; // os tipos primitivos presentes no JAVA são vários, tem mais a ver com a
                                // quantidade de memória que ocupam que qualquer outra coisa
        System.out.println("Olá Mundo!");
        System.out.println(num);
        System.out.println(floated);
        System.out.printf("%.3f%n", floated); // o printf indica para imprimir em determinado formato, igual no GO
        Locale.setDefault(Locale.US); // essa configuração do JAVA permite que todo código após tal configuração fique
                                      // em determinada linguagem, por exemplo em inglês,
        // em números, a vírgula separa o milhar, e o ponto as casas decimais, o exato
        // oposto do PT-BR;
        System.out.printf("%.3f%n", floated);
        String person = "Maria";
        byte age = 30;
        double salary = 3500.55;
        System.out.printf("%s tem %d anos e ganha R$ %.4f reais%n", person, age, salary); // colocar na respectiva ordem
                                                                                          // da %x

        /*
         * %f => ponto flutuante/número com vírgula;
         * %s => string/text;
         * %d => dígito numérico;
         * %n quebra de linha
         */
    }

    public static void printMathProblems() {
        MathProblems theMathProb = new MathProblems();
        theMathProb.mathProblemas();
    }

    public static void printInputtedData() {
        DataInput theDataInput = new DataInput();
        // theDataInput.userInput();
        theDataInput.theDoWhile();
    }

    public static void printProductsStock() {
        System.out.print("Produto, preço e quantidade inicial: ");
        ProductsStock theProdStock = new ProductsStock(sc.next(), sc.nextDouble(), sc.nextInt());

        theProdStock.getTotalStockNValue();

        System.out.println("Insira a quantidade de produtos a remover do estoque:");
        theProdStock.removeQuantity(sc.nextInt());
        // theProdStock.getTotalStockNValue();
        System.out.println(theProdStock); // quando não indicado e dentro de um system.out, o java automaticamente
        // identificará o método toString do objeto para ser impresso

        System.out.println("Insira a quantidade de produtos a adicionar ao estoque:");
        theProdStock.addQuantity(sc.nextInt());
        // theProdStock.getTotalStockNValue();
        System.out.println(theProdStock);

    }

    public static void printUsingEnum() {
        System.out.print("Especificamos um objeto orderStatus");

        // veja que o status do pedido utilizando o enum já automaticamente indicará
        // quais são os possíveis status
        OrderStatus orderStatus = new OrderStatus(500, new Date(), OrderStatusEnum.PROCESSING);

        System.out.println(orderStatus);
        // note que o enum equivalerá a valueOf('enum em string'):
        OrderStatusEnum ose1 = OrderStatusEnum.SHIPPED;
        OrderStatusEnum ose2 = OrderStatusEnum.valueOf("SHIPPED"); // Atenção que é case sensitive, resultará em erro se
                                                                   // fora do padrão do enum

        System.out.println(
                "is OrderStatusEnum.SHIPPED equal to OrderStatusEnum.valueOf(\"SHIPPED\")? " + ose1.equals(ose2));
    }

    public static void bankAccounts(String upCastOrOverride) {
        switch (upCastOrOverride) {
            case "upcasting":
                BankAccount bc1 = new BankAccount(001, "Cliente1", 5000.0);
                PJAccount pj1 = new PJAccount(003, "PJCliente2", 3000.0, 8000.0);

                // UPCASTING:
                // veja que é possível atribuir um valor PJAccount a uma variável BankAccount
                BankAccount bc2 = pj1;

                // perceba também que é possível instanciar objetos das subclasses
                BankAccount bc3 = new PJAccount(005, "PJClienteMisto", 40.0, 9000.0);
                BankAccount bc4 = new SavingsAccount(006, "Clientemisto2", 80000.0, 5.1);

                // DOWNCASTING:
                // veja que é possível também atribuir uma variável do tipo da superclasse para
                // uma subclasse, porém, é necessário o casting (tipo da subclasse)superclasse:
                // PJAccount bc5 = (PJAccount) bc1; *erro estranho aqui
                // perceba que bc1.loan(40.0) não existe, mas aqui tem-se acesso ao método loan,
                // disponível apenas na PJAccount;
                // bc5.loan(500.0);

                // ATENÇÃO: o compilador não irá avisar um downcasting impossível, como esse:
                // PJAccount bc6 = (PJAccount) bc4; // bc4 é do tipo SavingsAccount;
                // por isso utiliza-se a verificação de segurança:
                if (bc4 instanceof PJAccount) {
                    PJAccount bc6 = (PJAccount) bc4;
                    bc6.loan(50.0);
                    System.out.println("Is PJACCOUNT! Got loan!");
                } else if (bc4 instanceof SavingsAccount) {
                    SavingsAccount bc6 = (SavingsAccount) bc4;
                    bc6.updateBalance();
                    System.out.println("Is SavingsAccount! Update balance.");
                }
                break;

            case "override":
                // Implementamos uma diferença no método withdraw das saving accounts, na
                // superclasse, desconta 5, na savings não, utilizamos o @Override
                BankAccount bac1 = new BankAccount(000, "Joseph", 800.0);
                bac1.withdraw(100.0);
                System.out.println("balance after whitdraw 100 bankAcc: " + bac1.getBalance()); // 695 (100 + taxa de //
                                                                                                // 5)
                BankAccount bac2 = new SavingsAccount(001, "Mary", 800.0, 5.0);
                bac2.withdraw(100.0);
                System.out.println("balance after whitdraw 100 savAcc: " + bac2.getBalance()); // 700 (100 puro)

                // Implementamos uma taxa de 10 apenas para o deposit do saving accounts, com a
                // mesma implementação da superclasse (super.deposit), vejamos:
                bac1.deposit(100.0);
                System.out.println("balance after deposit 100 bankAcc: " + bac1.getBalance()); // 795
                bac2.deposit(100.0);
                System.out.println("balance after deposit 100 savAcc: " + bac2.getBalance()); // 790 (100 - taxa 10 )

                break;
            default:
                break;
        }

    }

    public static void exceptionsBreak(String breakOrNot) {
        Exceptions exc = new Exceptions();
        switch (breakOrNot) {
            case "break":
                exc.breakApp();
                break;
            case "tryCatch":

                exc.tryCatched();
                break;
            case "goodMethod":
                try {
                    exc.theGoodMethod("Hey yah!");
                } catch (HandlerException e) {
                    // handling exception
                    System.out.println("Error thrown:");
                    System.out.println(e);
                }
                break;
            default:
                System.out.println("Pick 'break', 'tryCatch' or 'goodMethod'");
                break;
        }
        if (breakOrNot == "break") {
        } else {
        }
    }

    public static void tryFileHandler(String mode) {
        FileHandlerClass fr = new FileHandlerClass();
        switch (mode) {
            case "scanner":
                fr.scanReading();
                break;
            case "tryCatchSimple":
                fr.fileReader();
                break;
            case "tryCatchResource":
                fr.tryFileReaderWithResources();
                break;
            case "writeNew":
                String[] linesToWrite = new String[] { "Good Morning", "Good Afternoon", "Good Night" };
                fr.writeNewFile(new File("files/fileNew01.txt"), linesToWrite);
                break;
            case "writeAppend":
                String[] linesToAppend = new String[] { "Appended Line 01", "Appended Line 02" };
                fr.writeAppendToFile(linesToAppend);
                break;
            case "listDir":
                fr.listDirs();
                break;
            case "newDir":
                fr.newDir();
                break;
            default:
                break;
        }
    }
}
