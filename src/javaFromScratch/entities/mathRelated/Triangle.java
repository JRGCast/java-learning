package javaFromScratch.entities.mathRelated;

public class Triangle {
    public Double sideA;
    public Double sideB;
    public Double sideC;

    public Triangle(Double sideA, Double sideB, Double sideC) {
        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
    }

    public Double triangleArea() {
        Double p = (sideA + sideB + sideC) / 2.0;
        double result = Math.sqrt(p * (p - sideA) * (p - sideB) * (p - sideC));
        return result;
    }
}
