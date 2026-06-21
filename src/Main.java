import java.util.Scanner;

public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    ConversorIEEE754 conversor = new ConversorIEEE754();

    System.out.print("Digite o primeiro número decimal: ");
    double num1 = sc.nextDouble();

    System.out.print("Digite o segundo número decimal: ");
    double num2 = sc.nextDouble();

    System.out.println("------------------------------------");

    System.out.println("Convertendo: " + num1);
    conversor.converter(num1);

    System.out.println("------------------------------------");
    System.out.println("Convertendo: " + num2);
    conversor.converter(num2);

    float float1 = (float) num1;
    float float2 = (float) num2;
    float somaFloat = float1 + float2;

    double somaDouble = num1 + num2;

    System.out.println("------------------------------------");
    System.out.println("Resultado Calculadora (32 bits): " + somaFloat);
    System.out.println("Resultado Normal (64 bits): " + somaDouble);
    System.out.println("------------------------------------");

    System.out.println("Conversão do resultado: " + somaFloat);
    conversor.converter(somaFloat);

    System.out.println("------------------------------------");
    System.out.println("Diferenças");
    if(somaDouble == somaFloat){
        System.out.println("Resultados iguais em decimal");
    } else {
        System.out.println("Houve diferença entre os resultados");
        double diferenca = Math.abs(somaDouble - somaFloat);
        System.out.println("Diferença: " + diferenca);
    }

    sc.close();
}