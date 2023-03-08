package javaFromScratch.services;

import java.util.ArrayList;
import java.util.List;

// Ao parametrizar a classe, é possível ter o type safety para toda a classe,
// portanto, só será possível adicionar
public class ListHandler<ClassType> {
  private List<ClassType> list = new ArrayList<ClassType>();

  public void addTolist(ClassType t) {
    list.add(t);
  }

  public ClassType getFirstNum() {
    if (list.isEmpty()) {
      throw new IllegalStateException("List is empty");
    }
    return list.get(0);
  }

  public void printList() {
    if (list.isEmpty()) {
      throw new IllegalStateException("List is empty");
    }
    for (ClassType index : list) {
      System.out.println(index);
    }
  }
}
