import java.util.Scanner;

public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    ConversorIEEE754 conversor1 = new ConversorIEEE754();
    ConversorIEEE754 conversor2 = new ConversorIEEE754();

    System.out.print("Digite o primeiro número decimal: ");
    double num1 = sc.nextDouble();

    System.out.print("Digite o segundo número decimal: ");
    double num2 = sc.nextDouble();

    System.out.println("------------------------------------");

    System.out.println("Convertendo: " + num1);
    conversor1.converter(num1);

    System.out.println("------------------------------------");
    System.out.println("Convertendo: " + num2);
    conversor2.converter(num2);

    System.out.println("------------------------------------");
    System.out.print("Digite a operação (+/-):");
    char operacao = sc.next().charAt(0);

    Calculadora calculadora = new Calculadora();
    calculadora.calcular(conversor1, conversor2, operacao);

    sc.close();
}