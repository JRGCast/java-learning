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

### Tipos Generics e Wildcards
- Permite que classes, interfaces e métodos sejam parametrizados por seus tipos;
- Reuso, type safety, performance;
- O supertipo de qualquer coisa não é <Object>, mas sim <?>; ou seja;
- Mesmo que <Integer> seja um subtipo de <Object>, não é possível atribuir, por exemplo, um List<Integer> à um List<Object>;
`List<Object> myObjs = new ArrayList<Object>();
List<Integer> myNums = new ArrayList<Integer>();
myObjs = myNums // erro de compilação
`
- Mas atenção, isso é possível: 
 `Object obj;
  Integer int = 10;
 `obj = 10;
  
#### Covariância e contravariância (princípio GET/PUT)
- Há casos de listas em que seus tipos determinarão as possibilidades de ações GET e PUT:
`
// Covariância:
List<Integer> numList = new ArrayList<Integer>();
numList.add(1)
numList.add(2)
numList.add(3)
List<? extends Number> newList = numList; // logo, qualquer subtipo de Number;
newList.get(0) // operação possível
Number x = newList.add(10) // erro de compilação 
`
`
// Contravariância:
List<Object> objList = new ArrayList<Object>();
objList.add("Joe")
objList.add("Maria")

List<? super Number> newList = numList; // logo, qualquer supertipo de Number;
Number x = newList.add(10) // operação possível
Double d = newList.add(10.5) // operação possível
newList.get(0) // erro de compilação
`

#### HashCode e Equals
- São operações da classe Object para comparar se um objeto é igual ao outro;
- HashCode é uma operação mais rápida, porém há uma pequena chance de um falso positivo (mostrar como iguais ao comparar objetos diferentes);
* Devido à limitação de 32 bits, é possível que objetos diferentes acabem gerando um mesmo hashCode, apesar da chance ínfima; 
- Equals é mais lenta, porém nunca erra;
- Tipos comuns (String, Number, Double, etc) já possuem essas implementações, outras classes personalizadas precisam implementá-las;
- Dito isso, para a melhor performance, recomenda-se a utilização de ambos, ex.: Ao percorrer uma lista com milhões de resultados, utilize o hashCode, e quando encontrar hashCodes que coincidam, confirme utilizando o equals.
- É comum que as IDEs já possuam um 'generate' para gerar o hashCode e equals;

#### Set
- Não admite repetições;
- Elementos não possuem posição/index;
- Acesso, inserção e remoção de elementos são rápidos;
- Oferece operações eficientes de conjunto: interseção, união, diferença;
- Principais implementações:
* HashSet - mais rápido (operações O(1) em tabela hash) e não ordenado;
* Tree set - mais lento (operações O(log(n)) em árvore rubro-negra) e ordenado pelo compareTo do objeto (ou Comparator);
* LinkedHashSet - velocidade intermediária e elementos na ordem em que são adicionados;
- Operações add(obj), remove(obj), contains(obj) são baseadas em equals e hashCode, caso não existam, será utilizada comparação de ponteiros;
* Outras operações: clear(), size(), removelf(predicate) addAll(união), retainAll(interseção), removeAll(diferença);

#### Map
- É uma coleção de chave/valor;
- Muito semelhante ao Set:
* Não pode ter repetição de chave;
* Não possuem posição (os objetos são organizados pela chave);
* Operações rápidas de acesso, inserção e remoção;
* Implementações comuns HashMap, TreeMap, LinkedHashMap;
* Operações put(key,value) operação para inserir chave/valor, remove(key), containsKey(key), get(key) são baseadas em equals e hashCode, caso não existam, será utilizada comparação de ponteiros;
* Outras operações: clear(), size(), keySet() retorna um Set das chaves, values() retorna uma Collection de valores;
- Uso comum: cookies, localStorage, outros com modelo chave/valor;