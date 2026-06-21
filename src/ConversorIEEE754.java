public class ConversorIEEE754 {
    int sinal;
    int expoenteReal;
    int expoenteBias;
    double numDecimal;
    String mantissa;

    public void bitSinal(double numDecimal) {
        if(numDecimal < 0){
            sinal = 1;
        } else {
            sinal = 0;
        }
    }

    private String inteiroBinario(int num){
        if (num == 0) return "0";
        StringBuilder binario = new StringBuilder();
        while (num > 0) {
            binario.insert(0, num % 2);
            num /= 2;
        }
        return binario.toString();
    }

    public void converter(double numDecimal){
        bitSinal(numDecimal);
        double valorAbsoluto = Math.abs(numDecimal);

        int parteInteira = (int) valorAbsoluto;
        double parteFracao = valorAbsoluto - parteInteira;

        String strInteiroBinario = inteiroBinario(parteInteira);
        StringBuilder strFracaoBinario = new StringBuilder();
        while (parteFracao > 0 && strFracaoBinario.length() < 50) {
            parteFracao *= 2;
            int bit = (int) parteFracao;
            strFracaoBinario.append(bit);
            parteFracao -= bit;
        }

        String binCompleto = strInteiroBinario + "."  + strFracaoBinario.toString();

        if (parteInteira > 0){
            this.expoenteReal = strInteiroBinario.length() -1;
        } else {
            this.expoenteReal = -(strFracaoBinario.indexOf("1") + 1);
        }

        this.expoenteBias = this.expoenteReal + 127;
        String expoBinario = inteiroBinario(this.expoenteBias);
        expoBinario = String.format("%8s", expoBinario).replace(" ", "0");

        String apenasBits = binCompleto.replace(".", "");
        int primeiroUm = apenasBits.indexOf("1");

        String mantissaBruta = "";
        if (primeiroUm != -1 && primeiroUm + 1 < apenasBits.length()) {
            mantissaBruta = apenasBits.substring(primeiroUm + 1);
        }

        if(mantissaBruta.length() > 23){
            this.mantissa = mantissaBruta.substring(0, 23);
        } else {
            this.mantissa = String.format("%-23s", mantissaBruta).replace(" ", "0");
        }

        System.out.println(this.sinal + " | " + expoBinario + " | " + mantissa);
        System.out.println("Sinal: " + this.sinal);
        System.out.println("Expoente (Bias): " + expoBinario + " (" + this.expoenteBias + ")");
        System.out.println("Mantissa: " + this.mantissa);
    }

}
