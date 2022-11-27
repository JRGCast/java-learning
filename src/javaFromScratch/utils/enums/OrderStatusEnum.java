package javaFromScratch.utils.enums;
public enum OrderStatusEnum {
  // o enum é uma maneira de especificar de forma literal um conjunto de constantes relacionadas, semelhante a uma interface, só que ao invés de tipos, seria uma constrição relacionada a valores
  // É possível colocar coisas mais avançadas, mas, por hora, fazer o enum é simplesmente declarar um public enum, com as constantes desejadas
  // Como normalmente virá de um input de usuário, quando utilizar, não esqueça de usar o .valueOf('string') que aí o Java fará a associação com o enum
  PENDING_PAYMENT,
  PROCESSING,
  SHIPPED,
  DELIVERED,
}
