public class Calculadora {

    public void calcular(ConversorIEEE754 c1, ConversorIEEE754 c2, char operacao){

        // 1- Restauração do primeiro bit 1 da mantissa
        int s1 = c1.sinal;
        int e1 = c1.expoenteBias;
        double m1 = Integer.parseInt("1" + c1.mantissa, 2);

        int s2 = c2.sinal;
        int e2 = c2.expoenteBias;
        double m2 = Integer.parseInt("1" + c2.mantissa, 2);

        // 2- Subtração, inversão do bit de sinal do segundo número
        if (operacao == '-'){
            s2 = (s2 == 0) ? 1 : 0;
        }

        // 3- Alinhamento dos expoentes, o número com menor expoente terá a mantissa dividida por 2.0
        int eResultado = e1;
        if (e1 > e2){
            int diff =  e1 - e2;
            for (int i = 0; i < diff; i++){
                m2 /= 2.0;
            }
            eResultado = e1;
        } else if (e2 > e1){
            int diff =  e2 - e1;
            for (int i = 0; i < diff; i++){
                m1 /= 2.0;
            }
            eResultado = e2;
        }

        // 4- Operação das mantissas, se ambas tiverem o mesmo sinal, as mantissas são somadas. Caso contrário, vira uma subtração
        double mResultado = 0;
        int sResultado = 0;

        if (s1 == s2){
            mResultado = m1 + m2;
            sResultado = s1;
        } else {
            if (m1 >= m2){
                mResultado = m1 - m2;
                sResultado = s1;
            } else {
                mResultado = m2 - m1;
                sResultado = s2;
            }
        }

        if (mResultado == 0){
            exibir(0, 0, "00000000000000000000000");
            return;
        }

        /* 5- Normalização e isolamento dos bits. Se a soma estourar o limite de bits, é dividido por 2.0 e o expoente aumenta,
        caso a subtração deixe o número muito pequeno, é multiplicado por 2.0 e o expoente diminui.
         */
        while (mResultado >= 16777216.0){
            mResultado /= 2.0;
            eResultado++;
        }

        while (mResultado < 8388608.0){
            mResultado *= 2.0;
            eResultado--;
        }

        int mantissaFinal = (int) mResultado - 8388608;

        String mantissaFinalStr = Integer.toBinaryString(mantissaFinal);
        mantissaFinalStr = String.format("%23s", mantissaFinalStr).replace(" ", "0");

        exibir(sResultado, eResultado, mantissaFinalStr);
    }

    // 6- Conversão para decimal
    public double converterParaDecimal(int sinal, int expoenteBias, String mantissaStr) {
        double multiplicadorSinal = (sinal == 1) ? -1.0 : 1.0;
        int expoenteReal = expoenteBias - 127;
        double valorMantissa = 0.0;

        for (int i = 0; i < mantissaStr.length(); i++) {
            if (mantissaStr.charAt(i) == '1') {
                valorMantissa += Math.pow(2, -(i + 1));
            }
        }

        double mantissaCompleta = 1.0 + valorMantissa;
        return multiplicadorSinal * mantissaCompleta * Math.pow(2, expoenteReal);
    }

    // 7- Exibe o resultado da calculadora e a conversão em decimal
    private void exibir(int sinal, int expoenteBias, String mantissa){
        String expoStr = String.format("%8s", Integer.toBinaryString(expoenteBias)).replace(" ", "0");
        double resultadoDecimal = converterParaDecimal(sinal, expoenteBias, mantissa);

        System.out.println("------------------------------------");
        System.out.println("      RESULTADO DA CALCULADORA      ");
        System.out.println("------------------------------------");
        System.out.println(sinal + " | " + expoStr + " | " + mantissa);
        System.out.println("Sinal: " + sinal);
        System.out.println("Expoente (Bias): " + expoStr + " (" + expoenteBias + ")");
        System.out.println("Mantissa: " + mantissa);
        System.out.println("------------------------------------");
        System.out.println("Resultado em decimal: " + resultadoDecimal);
        System.out.println("------------------------------------");
    }
}