package javaFromScratch.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class List01 {
    /*
     O List se trata de uma interface, portanto, não pode ser instanciado.
     O ArrayList é uma combinação das melhores partes do Array e da Lista.
    */
    List<String> theStrList = new ArrayList<String>(); // o generics (entre o <>) <String>, a partir da versão 8 do Java, tornou-se opcional;

    // para simplesmente adicionar algo à lista, utiliza-se o add:
    public void addToList(String theAdd) {
        theStrList.add(theAdd);
    }

    public void addIntoListPosition(int position, String theAddition) {
        theStrList.add(position, theAddition); // o add contém uma sobrecarga, na qual é possível adicionar na posição desejada
    }

    public int obtainListSize() { // para obter o tamanho da lista, basta utilizar o listaNome.size();
        return theStrList.size();
    }

    public void removeByIndex(int theIndex) { // é possível remover da lista pela posição, passando apenas um integer para o nomeLista.remove()
        theStrList.remove(theIndex);
    }

    public void removeByName(String theName) { // o remove também consegue fazer um "find" e removerá o valor que se encaixe, por exemplo, na string pedida
        theStrList.remove(theName);
    }

    public void removeAllByFirstChar(char theChar) {
        // também é possível remover vários ou fazer um filtro de acordo com o nomeLista.removeIf() contendo uma expressão lambda, que aqui chamam de predicado;
        theStrList.removeIf(name -> name.charAt(0) == theChar);
    }
    
    public int findByIndex(String theName) { // também é possível fazer um "find" e achar a posição. Se não existir na lista, retornará -1
        return theStrList.indexOf(theName);
    }
    
    public List<String> filterByFirstChar(char fstChar) { 
        // para utilizar filter e parecidos, é necessário utilizar o tipo stream da lista, pois, apenas dentro dessa função, temos as funções de filtragem e manipulação que aceitam lambda
        Stream<String> theFiltering = theStrList.stream().filter(theName -> theName.charAt(0) == fstChar); // aqui temos o tipo stream filtrando
        return theFiltering.collect(Collectors.toList()); // e aqui o tipo stream é novamente convertido para List
    }

    public String findFirstFilteredByFstChar(char fstChar) {
        String notFound = "Não achou quem comece com a letra " + fstChar;
        Stream<String> theFiltering = theStrList.stream().filter(theName -> theName.charAt(0) == fstChar);
        return theFiltering.findFirst().orElse(notFound);
        // aqui ele achará o primeiro da lista, caso não exista, retornará a frase de erro notFound
    }
    

}
