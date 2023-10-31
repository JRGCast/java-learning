package javaFromScratch.services;

import java.util.List;

public class CalculationService {
  // é necessário extends Comparable para que possamos dizer ao Java que o tipo T
  // terá o método compareTo.
  public static <T extends Comparable<T>> T findMax(List<T> list) {
    if (list.isEmpty()) {
      throw new IllegalStateException("Cannot find max of an empty list");
    }

    T theMax = list.get(0);
    for (T index : list) {
      // aqui usará o compareTo da própria classe
      if (index.compareTo(theMax) > 0) {
        theMax = index;
      }
    }
    System.out.println("The max number of your list is: " + theMax);
    return theMax;
  }
}
