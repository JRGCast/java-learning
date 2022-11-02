package javaFromScratch;

import static java.lang.System.out;
import javaFromScratch.entities.ProductsStock;
import javaFromScratch.MathProblems;
import javaFromScratch.DataInput;
import javaFromScratch.entities.OrderStatus;
import java.util.Locale;
import java.util.Scanner;

import javaFromScratch.MathProblems;
import javaFromScratch.entities.OrderStatus;
import javaFromScratch.entities.ProductsStock;
import javaFromScratch.entities.enums.OrderStatusEnum;

public class Main {

    private ProductsStock productStock;
    private MathProblems mathProblems;
    private DataInput dataInput;
    private OrderStatus orderEnum;
    private static final Scanner sc = new Scanner(System.in);

    public Main(MathProblems mathProblems, DataInput dataInput, ProductsStock productStock, OrderStatus orderEnum) {
        this.mathProblems = mathProblems;
        this.dataInput = dataInput;
        this.productStock = productStock;
        this.orderEnum = orderEnum;
    }

    public static void main(String[] args) { // aqui é o entry point do algoritmo Java, veja o init()
        init();
        // printMathProblems();
        // printInputtedData();
        // printProductsStock();
        // printUsingEnum();
    }

    public static void init() {
        byte num = 15; // no java, toda declaração vem como tipo nome = atribuição
        float floated = 15.25f; // os tipos primitivos presentes no JAVA são vários, tem mais a ver com a
                                // quantidade de memória que ocupam que qualquer outra coisa
        out.println("Olá Mundo!");
        out.println(num);
        out.println(floated);
        out.printf("%.3f%n", floated); // o printf indica para imprimir em determinado formato, igual no GO
        Locale.setDefault(Locale.US); // essa configuração do JAVA permite que todo código após tal configuração fique
                                      // em determinada linguagem, por exemplo em inglês,
        // em números, a vírgula separa o milhar, e o ponto as casas decimais, o exato
        // oposto do PT-BR;
        out.printf("%.3f%n", floated);
        String person = "Maria";
        byte age = 30;
        double salary = 3500.55;
        out.printf("%s tem %d anos e ganha R$ %.4f reais%n", person, age, salary); // colocar na respectiva ordem da %x

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
        out.print("Produto, preço e quantidade inicial: ");
        ProductsStock theProdStock = new ProductsStock(sc.next(), sc.nextDouble(), sc.nextInt());

        theProdStock.getTotalStockNValue();

        out.println("Insira a quantidade de produtos a remover do estoque:");
        theProdStock.removeQuantity(sc.nextInt());
        // theProdStock.getTotalStockNValue();
        out.println(theProdStock); // quando não indicado e dentro de um system.out, o java automaticamente
                                   // identificará o método toString do objeto para ser impresso

        out.println("Insira a quantidade de produtos a adicionar ao estoque:");
        theProdStock.addQuantity(sc.nextInt());
        // theProdStock.getTotalStockNValue();
        out.println(theProdStock);

    }

    public static void printUsingEnum() {
        out.print("Especificamos um objeto orderStatus");

        // veja que o status do pedido utilizando o enum já automaticamente indicará quais são os possíveis status
        OrderStatus orderStatus = new OrderStatus(500, new Date(), OrderStatusEnum.PROCESSING);

        out.print(orderStatus);
    }
}
