public enum OrderStatusEnum {
  // o enum é uma maneira de especificar de forma literal um conjunto de constantes relacionadas, semelhante a uma interface, só que ao invés de tipos, seria uma constrição relacionada a valores
  // É possível colocar coisas mais avançadas, mas, por hora, fazer o enum é simplesmente declarar um public enum, com as constantes desejadas
  PENDING_PAYMENT,
  PROCESSING,
  SHIPPED,
  DELIVERED,
}
