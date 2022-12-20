package javaFromScratch.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileHandlerClass {
  private Scanner sc = null; // também lê textos e arquivos
  private File file = new File("files/file01.txt"); // representação abstrata de um arquivo e seu caminho
  // A classe File possui UMA PENCA de gets e outras coisas, desde nome até o tamanho do arquivo, e muito mais

  private FileReader fileReader = null; // stream de leitura de caracteres a partir de arquivos
  private BufferedReader bufferedReader = null; // é instanciado a partir do fileReader, e possui algumas otimizações de
                                                // performance

  // o FileWriter possui 2 constructors, o append como true fará com que haja
  // acréscimo do pretendido ao arquivo,
  // já o constructor sem o append, fará com que o arquivo seja criado ou recriado
  private FileWriter fileWriter = null; // stream de escrita de caracteres a partir de arquivos
  private BufferedWriter bufferedWriter = null; // é instanciado a partir do fileWriter, e possui algumas otimizações de
                                                // performance

  public FileHandlerClass() {
  }

  public FileHandlerClass(File file) {
    this.file = file;
  }

  public void scanReading() {
    try {
      sc = new Scanner(file);
      while (sc.hasNextLine()) {
        System.out.println(sc.nextLine());
      }
    } catch (IOException e) {
      System.out.println("Error: " + e);
    } finally {
      if (sc != null) {
        sc.close();
      }
    }
  }

  public void fileReader() {
    try {
      this.fileReader = new FileReader(file);
      this.bufferedReader = new BufferedReader(fileReader);

      String line = bufferedReader.readLine();
      while (line != null) {
        System.out.println(line);
        line = bufferedReader.readLine(); // sem essa reatribuição fica num loop infinito
      }
    } catch (IOException e) {
      System.out.println("Error: " + e.getMessage());
    } finally {
      try {
        if (fileReader != null) {
          fileReader.close();
        }
        if (bufferedReader != null) {
          bufferedReader.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  public void tryFileReaderWithResources() {
    // esse é o tryWithResources, ele automaticamente fechará (.close()) esses
    // recursos declarados no constructor dele (o bf)
    try (BufferedReader bf = new BufferedReader(new FileReader(this.file))) {
      String line = bufferedReader.readLine();
      while (line != null) {
        System.out.println(line);
        line = bufferedReader.readLine(); // sem essa reatribuição fica num loop infinito
      }
    } catch (IOException e) {
      System.out.println("Error: " + e);
    }
  }

  public void writeAppendToFile(String[] lines) {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(this.file, true))) {
      bw.newLine();
      for (String line : lines) {
        bw.write(line);
        bw.newLine();
      }
    } catch (IOException e) {
      System.out.println("Error: " + e);
    }
  }

  public void writeNewFile(File file, String[] lines) {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
      for (String line : lines) {
        bw.write(line);
        bw.newLine();
      }
    } catch (IOException e) {
      System.out.println("Error: " + e);
    }
  }

  public void listDirs() {
    sc = new Scanner(System.in);
    System.out.println("Enter the directory to be listed: ");
    String dirPath = sc.nextLine();
    File filePath = new File(dirPath);

    File[] allFolders = filePath.listFiles(File::isDirectory);
    System.out.println("FOLDERS: ");
    for (File folder : allFolders) {
      System.out.println(folder);
    }

    File[] allFiles = filePath.listFiles(File::isFile);
    System.out.println("FILES: ");
    for (File files : allFiles) {
      System.out.println(files);
    }
    sc.close();
  }

  public void newDir() {
    sc = new Scanner(System.in);
    System.out.println("Enter the directory where dir will be created: ");
    String dirPath = sc.nextLine();
    System.out.println("Enter the new directory name: ");
    String newDirPath = sc.nextLine();

    File filePath = new File(dirPath + "/" + newDirPath);

    File[] allFolders = filePath.listFiles(File::isDirectory);
    System.out.println("FOLDERS: ");
    for (File folder : allFolders) {
      System.out.println(folder);
    }

    File[] allFiles = filePath.listFiles(File::isFile);
    System.out.println("FILES: ");
    for (File files : allFiles) {
      System.out.println(files);
    }
    sc.close();
  }
}
