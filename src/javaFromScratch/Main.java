package javaFromScratch;

import java.io.File;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;

import javaFromScratch.db.DB;
import javaFromScratch.entities.Exceptions;
import javaFromScratch.entities.OrderStatus;
import javaFromScratch.entities.Product;
import javaFromScratch.entities.ProductsStock;
import javaFromScratch.entities.accountsRelated.BankAccount;
import javaFromScratch.entities.accountsRelated.PJAccount;
import javaFromScratch.entities.accountsRelated.SavingsAccount;
import javaFromScratch.services.CalculationService;
import javaFromScratch.services.ListHandler;
import javaFromScratch.utils.FileHandlerClass;
import javaFromScratch.utils.HandlerException;
import javaFromScratch.utils.SetNMapHandler;
import javaFromScratch.utils.enums.OrderStatusEnum;

public class Main {
	private static final Scanner sc = new Scanner(System.in);
	private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	// Ao usar o lixo do VSCODE não esqueça de estar na pasta SRC e limpar o
	// workspace para rodar
	public Main() {
	}

	public static void main(String[] args) { // aqui é o entry point do algoritmo Java, veja o init()
		Connection conn = DB.getConnection();
		try {
			// init();
			// printMathProblems();
			// printInputtedData();
			// printProductsStock();
			// printUsingEnum();
			// bankAccounts("upcasting");
			// bankAccounts("override");
			// exceptionsBreak("goodMethod");
			// tryFileHandler("listDir"); //
			// "scanner/tryCatchSimple/tryCatchResource/writeNew/writeAppend/listDir/newDir"
			// genericsNComparable();
			// setMapNComparator();
//			DB.getQueryDb("select * from department");
//			DB.insertNewSeller("Carl Purple", "carl@gmail.com", new java.sql.Date(sdf.parse("22/04/2019").getTime()),
//					10000.0, 4);
//			String[] departmentNames = { "Sales", "Marketing", "Finance", "Human Resources", "IT", "Operations" };
//			DB.insertNewDepartments(departmentNames);
//			DB.updateSellersSalaryByDepartment(4, 10000.00);
//			String[] testDps = { "TEST1", "TEST2" };
//			DB.insertNewDepartments(testDps);
//			DB.deleteById("department", 1);
			DB.transactionLearning();
		} catch (RuntimeException e) {
			System.err.println("Unexpected Error" + e);
		}
//		catch (ParseException e) {
//			System.err.println("Unexpected Parse Error" + e);
//		} 
		finally {
			DB.closeConnection();
		}
	}

	public static void init() {
		byte num = 15; // no java, toda declaração vem como tipo nome = atribuição
		float floated = 15.25f; // os tipos primitivos presentes no JAVA são vários, tem mais a ver com a
								// quantidade de memória que ocupam que qualquer outra coisa
		System.out.println("Olá Mundo!");
		System.out.println(num);
		System.out.println(floated);
		System.out.printf("%.3f%n", floated); // o printf indica para imprimir em determinado formato, igual no GO
		Locale.setDefault(Locale.US); // essa configuração do JAVA permite que todo código após tal configuração fique
										// em determinada linguagem, por exemplo em inglês,
		// em números, a vírgula separa o milhar, e o ponto as casas decimais, o exato
		// oposto do PT-BR;
		System.out.printf("%.3f%n", floated);
		String person = "Maria";
		byte age = 30;
		double salary = 3500.55;
		System.out.printf("%s tem %d anos e ganha R$ %.4f reais%n", person, age, salary); // colocar na respectiva ordem
																							// da %x

		/*
		 * %f => ponto flutuante/número com vírgula; %s => string/text; %d => dígito
		 * numérico; %n quebra de linha
		 */
	}

	public static void printMathProblems() {
		MathProblems theMathProb = new MathProblems();
		theMathProb.mathProblemas();
	}

	public static void printInputtedData() {
		// DataInput theDataInput = new DataInput();
		// theDataInput.userInput();
		// theDataInput.theDoWhile();
	}

	public static void printProductsStock() {
		System.out.print("Produto, preço e quantidade inicial: ");
		ProductsStock theProdStock = new ProductsStock(sc.next(), sc.nextDouble(), sc.nextInt());

		theProdStock.getTotalStockNValue();

		System.out.println("Insira a quantidade de produtos a remover do estoque:");
		theProdStock.removeQuantity(sc.nextInt());
		// theProdStock.getTotalStockNValue();
		System.out.println(theProdStock); // quando não indicado e dentro de um system.out, o java automaticamente
		// identificará o método toString do objeto para ser impresso

		System.out.println("Insira a quantidade de produtos a adicionar ao estoque:");
		theProdStock.addQuantity(sc.nextInt());
		// theProdStock.getTotalStockNValue();
		System.out.println(theProdStock);

	}

	public static void printUsingEnum() {
		System.out.print("Especificamos um objeto orderStatus");

		// veja que o status do pedido utilizando o enum já automaticamente indicará
		// quais são os possíveis status
		OrderStatus orderStatus = new OrderStatus(500, new Date(), OrderStatusEnum.PROCESSING);

		System.out.println(orderStatus);
		// note que o enum equivalerá a valueOf('enum em string'):
		OrderStatusEnum ose1 = OrderStatusEnum.SHIPPED;
		OrderStatusEnum ose2 = OrderStatusEnum.valueOf("SHIPPED"); // Atenção que é case sensitive, resultará em erro se
																	// fora do padrão do enum

		System.out.println(
				"is OrderStatusEnum.SHIPPED equal to OrderStatusEnum.valueOf(\"SHIPPED\")? " + ose1.equals(ose2));
	}

	public static void bankAccounts(String upCastOrOverride) {
		switch (upCastOrOverride) {
		case "upcasting":
			BankAccount bc1 = new BankAccount(001, "Cliente1", 5000.0);
			PJAccount pj1 = new PJAccount(003, "PJCliente2", 3000.0, 8000.0);

			// UPCASTING:
			// veja que é possível atribuir um valor PJAccount a uma variável BankAccount
			BankAccount bc2 = pj1;

			// perceba também que é possível instanciar objetos das subclasses
			BankAccount bc3 = new PJAccount(005, "PJClienteMisto", 40.0, 9000.0);
			BankAccount bc4 = new SavingsAccount(006, "Clientemisto2", 80000.0, 5.1);

			// DOWNCASTING:
			// veja que é possível também atribuir uma variável do tipo da superclasse para
			// uma subclasse, porém, é necessário o casting (tipo da subclasse)superclasse:
			// PJAccount bc5 = (PJAccount) bc1; *erro estranho aqui
			// perceba que bc1.loan(40.0) não existe, mas aqui tem-se acesso ao método loan,
			// disponível apenas na PJAccount;
			// bc5.loan(500.0);

			// ATENÇÃO: o compilador não irá avisar um downcasting impossível, como esse:
			// PJAccount bc6 = (PJAccount) bc4; // bc4 é do tipo SavingsAccount;
			// por isso utiliza-se a verificação de segurança:
			if (bc4 instanceof PJAccount) {
				PJAccount bc6 = (PJAccount) bc4;
				bc6.loan(50.0);
				System.out.println("Is PJACCOUNT! Got loan!");
			} else if (bc4 instanceof SavingsAccount) {
				SavingsAccount bc6 = (SavingsAccount) bc4;
				bc6.updateBalance();
				System.out.println("Is SavingsAccount! Update balance.");
			}
			break;

		case "override":
			// Implementamos uma diferença no método withdraw das saving accounts, na
			// superclasse, desconta 5, na savings não, utilizamos o @Override
			BankAccount bac1 = new BankAccount(000, "Joseph", 800.0);
			bac1.withdraw(100.0);
			System.out.println("balance after whitdraw 100 bankAcc: " + bac1.getBalance()); // 695 (100 + taxa de //
																							// 5)
			BankAccount bac2 = new SavingsAccount(001, "Mary", 800.0, 5.0);
			bac2.withdraw(100.0);
			System.out.println("balance after whitdraw 100 savAcc: " + bac2.getBalance()); // 700 (100 puro)

			// Implementamos uma taxa de 10 apenas para o deposit do saving accounts, com a
			// mesma implementação da superclasse (super.deposit), vejamos:
			bac1.deposit(100.0);
			System.out.println("balance after deposit 100 bankAcc: " + bac1.getBalance()); // 795
			bac2.deposit(100.0);
			System.out.println("balance after deposit 100 savAcc: " + bac2.getBalance()); // 790 (100 - taxa 10 )

			break;
		default:
			break;
		}

	}

	public static void exceptionsBreak(String breakOrNot) {
		Exceptions exc = new Exceptions();
		switch (breakOrNot) {
		case "break":
			exc.breakApp();
			break;
		case "tryCatch":

			exc.tryCatched();
			break;
		case "goodMethod":
			try {
				exc.theGoodMethod("Hey yah!");
			} catch (HandlerException e) {
				// handling exception
				System.out.println("Error thrown:");
				System.out.println(e);
			}
			break;
		default:
			System.out.println("Pick 'break', 'tryCatch' or 'goodMethod'");
			break;
		}
		if (breakOrNot == "break") {
		} else {
		}
	}

	public static void tryFileHandler(String mode) {
		FileHandlerClass fr = new FileHandlerClass();
		switch (mode) {
		case "scanner":
			fr.scanReading();
			break;
		case "tryCatchSimple":
			fr.fileReader();
			break;
		case "tryCatchResource":
			fr.tryFileReaderWithResources();
			break;
		case "writeNew":
			String[] linesToWrite = new String[] { "Good Morning", "Good Afternoon", "Good Night" };
			fr.writeNewFile(new File("files/fileNew01.txt"), linesToWrite);
			break;
		case "writeAppend":
			String[] linesToAppend = new String[] { "Appended Line 01", "Appended Line 02" };
			fr.writeAppendToFile(linesToAppend);
			break;
		case "listDir":
			fr.listDirs();
			break;
		case "newDir":
			fr.newDir();
			break;
		default:
			break;
		}
	}

	public static void genericsNComparable() {
		ListHandler<Integer> numList = new ListHandler<>();
		ListHandler<Double> dList = new ListHandler<>();
		ListHandler<String> strList = new ListHandler<>();

		Product prod1 = new Product("TV", 500.1);
		Product prod2 = new Product("Abajur", 200.1);
		Product prod3 = new Product("Car", 20000.1);

		ProductsStock productsStock = new ProductsStock(prod1);
		productsStock.addProductToStock(prod2);
		productsStock.addProductToStock(prod3);

		// veja que só é possível adicionar à lista o mesmo tipo que foi instanciada
		numList.addTolist(0);
		dList.addTolist(1.0);
		strList.addTolist("John");

		numList.printThisList();
		dList.printThisList();
		strList.printThisList();

		numList.addTolist(1);
		numList.addTolist(2);
		numList.addTolist(4);
		numList.addTolist(20);

		CalculationService.findMax(numList.getList());
		CalculationService.findMax(productsStock.getProductList());

		strList.addTolist("Joe");
		strList.addTolist("maria");
		ListHandler.printAnyList(strList.getList());

		dList.addTolist(1.5);
		dList.addTolist(2.5);
		dList.addTolist(4.5);
		dList.addTolist(20.5);

		List<Object> objList = new ArrayList<Object>();
		// é possível adicionar qualquer lista subtipo de number para a lista de object
		ListHandler.copyNumToSuperNum(numList.getList(), objList);
		ListHandler.copyNumToSuperNum(dList.getList(), objList);
	}

	public static void setMapNComparator() {
		// SET
		List<Integer> numList1 = Arrays.asList(1, 2, 3, 4, 5);
		List<Integer> numList2 = Arrays.asList(8, 5, 9, 6, 8, 4);

		List<String> strList1 = Arrays.asList("Ana", "Bernardo", "Caio", "Division", "Elantra", "Hash");
		List<String> strList2 = Arrays.asList("Hash", "Google", "Face", "Entailed", "Xablau");

		SetNMapHandler<Integer> shInt = new SetNMapHandler<Integer>();
		SetNMapHandler<String> shStr = new SetNMapHandler<String>();

		Set<Integer> hashInt1 = shInt.transformToHashSet(numList1);
		Set<Integer> hashInt2 = shInt.transformToHashSet(numList2);

		Set<String> hashStr1 = shStr.transformToHashSet(strList1);
		Set<String> hashStr2 = shStr.transformToHashSet(strList2);
		Set<String> treeStr2 = shStr.transformToTreeSet(strList2);
		Set<String> linkedStr2 = shStr.transformToLinkedHashSet(strList2);

		// Veja que o HashSet não garante ordem
		System.out.println(hashStr2);
		// O linkedTree continua na mesma ordem que colocado
		System.out.println(linkedStr2);
		// E o treeSet garante ordem alfabética
		System.out.println(treeStr2);
		// sendo todos filhos do mesmo Set, eles conseguem operar entre si
		hashStr1.addAll(linkedStr2);
		// predicate são operações lambda
		System.out.println(shStr.removeIfPredicate(treeStr2, a -> a.contains("e")));

		// União (adiciona tudo o que tem no outro set, excluindo repetições, claro)
		hashInt1.addAll(hashInt2);
		System.out.println(hashInt1);

		// Interseção (mantém somente o que é igual nos dois set)
		hashInt1.retainAll(hashInt2);
		System.out.println(hashInt1);

		// Diferença (mantém somente o que não está igual)
		hashInt1.removeAll(hashInt2);
		System.out.println(hashInt1);

		SetNMapHandler<Product> shProd = new SetNMapHandler<Product>();
		Product prod1 = new Product("TV", 500.1);
		Product prod2 = new Product("Abajur", 200.1);
		Product prod3 = new Product("Car", 20000.1);

		ProductsStock productsStock = new ProductsStock(prod1);
		productsStock.addProductToStock(prod2);
		productsStock.addProductToStock(prod3);

		Set<Product> productSet = shProd.transformToTreeSet(productsStock.getProductList());

		System.out.println(productSet);

		Product prod4 = new Product("Car", 20000.1);

		// dará false caso a classe product não tenha o hashCode e
		// equals, uma vez que a comparação é feita por ponteiros
		System.out.println(productSet.contains(prod4)); // No caso, a implementação que fizemos compara tanto o nome
														// quanto o preço do produto para dizer se são iguais

		// veja que o treeSet organiza pelos preços justamente porque o método
		// compareTo, na classe Product, faz a comparação por preços
		for (Product p : productSet) {
			System.out.println(p);
		}

		// MAP
		SetNMapHandler<String> map1 = new SetNMapHandler<String>("username", "Joe");
		map1.addToMap("email", "joe@g.com");
		map1.addToMap("age", 123);
		System.out.println(map1.getListMap().keySet());
		System.out.println(map1.getListMap().values());
		System.out.println(map1.getListMap().containsKey("email"));
		map1.removeFromMap("email");
		System.out.println(map1.getListMap().containsKey("email"));
		System.out.println(map1.getListMap().entrySet());
		// quando o elemento não existir, ele retorna como nulo
		System.out.println(map1.getListMap().get("email"));
		// ao acrescentar uma chave já existente, o valor da mesma será sobrescrito
		map1.addToMap("age", 50);
		System.out.println(map1.getListMap().entrySet());

		// COMPARATOR
		// O comparator é uma forma de ordenarmos as listas sem que precisemos
		// implementar um método compareTo;
		// O comparator pode utilizado:
		// 1- instanciado em uma classe (criaria-se uma classe igual à classe anônima
		// abaixo);
		// 2- instanciado em uma classe anônima:
		Comparator<Product> compAnonymousClass = new Comparator<Product>() {
			@Override
			public int compare(Product p1, Product p2) {
				return p1.getName().toUpperCase().compareTo(p2.getName().toUpperCase());
			};
		};
		// e chamar assim productsStock.getProductList().sort(compAnonymousClass);

		// 3- expressão lamba (com ou sem chaves);
		Comparator<Product> compLambda1 = (Product p1, Product p2) -> {
			return p1.getName().toUpperCase().compareTo(p2.getName().toUpperCase());
		};
		// Perceba que os argumentos da expressão lambda tornam a tipagem opcional
		Comparator<Product> compLambda2 = (p1, p2) -> p1.getName().toUpperCase().compareTo(p2.getName().toUpperCase());
		// e chamar assim productsStock.getProductList().sort(compLambda1 ou
		// compLambda2);

		// 4- diretamente no argumento do list.sort;
		productsStock.getProductList()
				.sort((p1, p2) -> p1.getName().toUpperCase().compareTo(p2.getName().toUpperCase()));

		// curiosidade da syntaxe Java -> passar a referência da função da própria
		// classe é feita com ::
		productsStock.getProductList().sort(Main::compareRef);
		productsStock.getProductList().forEach(System.out::println);
	}

	public static int compareRef(Product p1, Product p2) {
		return p1.getName().toUpperCase().compareTo(p2.getName().toUpperCase());
	}
}
