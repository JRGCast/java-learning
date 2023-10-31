package javaFromScratch.utils;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.function.Predicate;

public class SetNMapHandler<T> {
  private Map<String, Object> listMap = new TreeMap<>();

  public SetNMapHandler() {
  }

  public SetNMapHandler(String key, Object value) {
    listMap.put(key, value);
  }

  public void addToMap(String key, Object value) {
    listMap.put(key, value);
  }

  public void removeFromMap(String key) {
    listMap.remove(key);
  }

  public void printMap() {
    for (String key : listMap.keySet()) {
      System.out.println(key + ":" + listMap.get(key));
    }
  }

  public Map<String, Object> getListMap() {
    return listMap;
  }

  public HashSet<T> transformToHashSet(List<T> receivedList) {
    HashSet<T> newSet = new HashSet<T>(receivedList);
    return newSet;
  }

  public TreeSet<T> transformToTreeSet(List<T> receivedList) {
    TreeSet<T> newSet = new TreeSet<T>(receivedList);
    return newSet;
  }

  public LinkedHashSet<T> transformToLinkedHashSet(List<T> receivedList) {
    LinkedHashSet<T> newSet = new LinkedHashSet<T>(receivedList);
    return newSet;
  }

  public Set<T> uniteSets(Set<T> sourceSet, Set<T> additionSet) {
    sourceSet.addAll(additionSet);
    return sourceSet;
  }

  public Set<T> intersectSets(Set<T> sourceSet, Set<T> intersectSet) {
    sourceSet.retainAll(intersectSet);
    return sourceSet;
  }

  public Set<T> differenceOfSets(Set<T> sourceSet, Set<T> comparisonSet) {
    sourceSet.removeAll(comparisonSet);
    return sourceSet;
  }

  public Set<T> removeIfPredicate(Set<T> receivedSet, Predicate<? super T> predicate) {
    receivedSet.removeIf(predicate);
    return receivedSet;
  }

  @Override
  public String toString() {
    return "SetNMapHandler [listMap=" + listMap + "]";
  }
}
