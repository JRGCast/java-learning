### Herança:

- Toda classe que 'extends' é uma classe do mesmo tipo do pai (ex.: PJAccount 'é um' BankAccount);
- A classe 'pai' é a generalização, enquanto o 'filho' deverá ser uma especialização;
- A classe 'pai' é a super classe, as classes que são suas derivações são chamadas subclasses;
- Herança é uma relação entre classes, não objetos;

#### Níveis de acesso de atributos:

- Private: A única classe que tem acesso ao atributo é a própria classe que o define, ou seja, se uma classe Pessoa declara um atributo privado chamado nome, somente a classe Pessoa terá acesso a ele.

- Default: Tem acesso a um atributo default (identificado pela ausência de modificadores) todas as classes que estiverem no mesmo pacote que a classe que possui o atributo.

- Protected: Esse é o que pega mais gente, ele é praticamente igual ao default, com a diferença de que se uma classe (mesmo que esteja fora do pacote) estende da classe com o atributo protected, ela terá acesso a ele. Então o acesso é por pacote e por herança.

- Public: Esse é fácil, todos tem acesso :)

### Upcasting e Downcasting:

- Upcasting: Casting da subclasse para a superclasse;
* Um uso comum é no polimorfismo;
  
- Downcasting: Casting da superclasse para a subclasse;
* Um uso comum é em métodos que recebem parâmetros genéricos (ex.: Equals);
* Atente-se a usar o 'instanceof' para evitar erros de compilação;
  

### Sobreposição ou sobrescrita
- É a implementação de um método da superclasse na subclasse;
- Deve-se utilizar a anotação @Override sempre que for utilizar a sobrescrição de um método, pois:
* Facilita a leitura;
* 'Avisa' o compilador;

#### Classes e métodos 'final':
- A palavra 'final' na classe, evita que a mesma seja herdada. No método, evita que o mesmo seja sobreposto;
* Ex.: se a classe SavingsAccount estivesse escrita assim: ``` public final class SavingsAccount extends BankAccount { ```
Não seria possível fazer uma classe que ```extends``` a SavingsAccount.
De maneira semelhante, se a BankAccount tivesse o método withdraw escrito assim: ```public final void withdraw(Double amount) {```
Não seria possível fazer o @Override;
- A palavra final faz com que o aplicativo tenha uma performance levemente melhor, pois, os atributos de uma classe final, por exemplo, terão uma análise mais rápida em tempo de execução;
* Recomenda-se a utilização para classes que não devam ser herdadas ou em métodos já sobrepostos, pois, no exemplo de métodos, isso evita novas sobreposições (algo que pode gerar inconsistências);

### Polimorfismo:
- Em POO, polimorfismo é um recurso que permite que variáveis de um mesmo tipo mais genérico possam apontar para objetos de tipos específicos diferentes, tendo assim comportamentos diferentes conforme cada tipo específico apontado.
- Em outras palavras, podemos ver um 'exemplo' quando fazemos:
``` 
BankAccount x = new BankAccount(001, "Owner1", 1000.0); // variável tipo BankAccount apontando para tipo BankAccount
BankAccount y = new SavingsAccount(002, "Owner2", 1000.0, 0.05) / variável tipo BankAccount apontando para tipo SavingsAccount
x.withdraw(50.0); 
y.withdraw(50.0); // comportamento diferente do acima
```

### Classes e métodos abstratos:
- Classes abstratas são classes que não podem ser instanciadas;
- É uma maneira de garantir a herança em sua totalidade, uma vez que apenas as subclasses poderão ser instanciadas, a superclasse abstrata em si jamais poderá ser instanciada;
- Serve para que o reuso da classe seja melhorado, padronizado, incentiva o polimorfismo também;

### Exceções
- Uma exceção é qualquer condição de erro ou comportamento inesperado encontrado por um programa em execução
- Em Java, uma exceção é um objeto herdado da classe:
* java.lang.Exception - o compilador obriga a tratar ou propagar
* java.lang.RuntimeException - o compilador não obriga a tratar ou propagar
* Quando lançada, uma exceção é propagada na pilha de chamadas de métodos em execução, até que seja capturada (tratada) ou o programa seja encerrado

### Interfaces
- Interfaces estabelecem os 'moldes' das classes, podemos dizer que estabelece um 'contrato' que a classe deve cumprir;
- Tem como objetivo criar um sistema com baixo acoplamento e flexível;

### Tipos Generics
- Permite que classes, interfaces e métodos sejam parametrizados por seus tipos;
- Reuso, type safety, performance;
- 