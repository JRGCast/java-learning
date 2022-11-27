package javaFromScratch;

import static java.lang.System.in;
import static java.lang.System.out;

import java.util.Scanner;

import javaFromScratch.entities.mathRelated.Triangle;
import javaFromScratch.entities.mathRelated.Vectors01;
import javaFromScratch.entities.mathRelated.Vectors02;
import javaFromScratch.utils.Calculator;

public class MathProblems {

    Scanner sc = new Scanner(in);
    public void mathProblemas() {
        out.println("-----------------------------------------------------------------------------");

        out.println("RELAÇÕES MATEMÁTICAS");
        int x = 5, y = 2 * x; // é possível fazer atribuições semelhante ao Python e ao GO
        out.printf("x é %d, y é 2 vezes o x, logo %d%n", x,y);

        out.println("Calculando a área de um trapézio");
        double a,b,h,area;
        a = 6.0; // podemos declarar pontos flutuantes colocando um .0 (double)
        b = 8f; // ou um f ao final (flutuante simples)
        h = 5f;
        area = (a + b) / 2 * h;
        out.println(area);

        int c = 5;
        byte d = 2;
        int resultInt = c / d;

        double resultDouble;
        resultDouble = (double) c/d;

        out.println(resultInt); // veja que ao não declarar que deseja o result como double, ele sai como 2

        out.println(resultDouble); // é necessário declarar o (double) entre parênteses na atribuição, isso é o casting

//        theTriangle();
//        theCircle();
//        theVector01();
        theVector02();
    }

    public void theTriangle() {
        Triangle t1, t2;

        out.println("Digite as áreas do triangulo 1:\n");

        t1 = new Triangle(sc.nextDouble(), sc.nextDouble(), sc.nextDouble());

        out.println("Digite as áreas do triangulo 2:\n");

        t2 = new Triangle(sc.nextDouble(), sc.nextDouble(), sc.nextDouble());

       out.println("Area do triangulo 1: " + t1.triangleArea());
       out.println("Area do triangulo 2: " + t2.triangleArea());

       sc.close();

    }

    public void theCircle() {
        out.print("Enter radius value: ");
        Double radius = sc.nextDouble();

        Double circ = Calculator.circumference(radius);
        Double vol = Calculator.volume(radius);

        out.printf("With PI equals to %.5f and radius equals to %.2f%n", Calculator.PI, radius);
        out.printf("Circumference is: %.2f%n", circ);
        out.printf("Volume is: %.2f%n", vol);


        sc.close();

    }

    public void theVector01() {
        Vectors01 v01 = new Vectors01();
        v01.vectorArr01();
    }
    public void theVector02() {
        out.println("Quantos produtos no array?");
        int productArrSize = sc.nextInt();
        Vectors02[] vector02Arr = new Vectors02[productArrSize];

        for (int i = 0; i < vector02Arr.length; i++) {
            out.println("Nome do produto e preço da  posição " + i + "?");
            vector02Arr[i] = new Vectors02(sc.next(), sc.nextDouble());
        }

        double totalProductsPrice = 0.0;
        for (Vectors02 product: vector02Arr) {
            totalProductsPrice += product.getProductPrice();
        }

        out.printf("Total sum of all products prices: U$ %.2f", totalProductsPrice);

        sc.close();
    }
}
