package util;

import java.util.Scanner;

public class Validacoes {

    // Atributos
    private static final Scanner INPUT = new Scanner(System.in);

    // Métodos
    /**
    * Solicita ao usuário um valor numérico inteiro e valida a entrada.
    * O valor deve ser maior ou igual ao valor minimo definido.
    *
    * @param texto mensagem exibida ao usuário.
    * @param valorMinimo valor mínimo aceito.
    * @return valor inteiro validado maior ou igual ao valorMinimo.
    */
    public static int validarInteger(String texto, int valorMinimo) {
        int valor = 0;
        boolean validar = false;
        do {
            try {
                System.out.print(texto);
                String escolha = INPUT.nextLine();
                valor = Integer.parseInt(escolha);

                if (valor >= valorMinimo) {
                    validar = true;
                } else {
                    Utilitarios.formatarMensagemErro("Insira um valor numérico maior que %d!",
                                              valorMinimo);
                }

            } catch (NumberFormatException e) {
                Utilitarios.formatarMensagemErro("Entrada inválida! Insira apenas números.");
            }
        } while (!validar);
        return valor;
    }

    /**
    * Solicita ao usuário um valor numérico decimal e valida a entrada.
    * O valor deve ser maior que zero.
    *
    * @param texto mensagem exibida ao usuário.
    * @return valor decimal validado maior que zero.
    */
    public static double validarDouble(String texto) {
        double valor = 0;
        boolean validar = false;
        do {
            try {
                System.out.print(texto);
                String escolha = INPUT.nextLine();
                valor = Double.parseDouble(escolha);
                if (valor > 0) {
                    validar = true;
                } else {
                    Utilitarios.formatarMensagemErro("Insira um valor numérico maior que zero!");
                }
            } catch (NumberFormatException e) {
                Utilitarios.formatarMensagemErro("Entrada inválida! Insira apenas números.");
            }
        } while (!validar);
        return valor;
    }
}