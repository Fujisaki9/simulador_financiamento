package util;

import java.util.Locale;

public class Utilitarios {

    // Métodos
    /**
    * Exibe o menu principal de opções do sistema.
    */
    public static void mostrarMenuOpcoes() {
        System.out.println("\u001B[33m=========================\n" +
                           centralizarMensagem("MENU FINANCIAMENTO\n", 25) +
                           "=========================\n" +
                           "1 - Financiar Apartamento\n" +
                           "2 - Financiar Casa\n" +
                           "3 - Financiar Terreno\n" +
                           "4 - Mostrar dados salvos\n" +
                           "5 - Sair do programa\n" +
                           "=========================\u001B[0m");
    }

    /**
    * Centraliza uma mensagem dentro de uma largura definida.
    * @param mensagem mensagem a ser centralizada.
    * @param largura  largura total em caracteres.
    * @return mensagem centralizada com espaços.
    */
    public static String centralizarMensagem(String mensagem, int largura) {
        int espacos = (largura - mensagem.length()) / 2;
        return " ".repeat(espacos) + mensagem;
    }

    /**
     * Pausa a execução do programa pelo tempo definido.
     * @param valor tempo em milissegundos.
     */
    public static void sleep(int valor) {
        try {
            Thread.sleep(valor);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
    * Exibe uma mensagem de erro formatada em vermelho.
    * @param mensagem mensagem a ser exibida.
    * @param args     argumentos opcionais para formatação.
    */
    public static void formatarMensagemErro(String mensagem, Object... args) {
        System.out.println("\u001B[31m" +
                String.format(Locale.forLanguageTag("pt-BR"), mensagem, args) +
                "\u001B[0m");
    }

    /**
    * Formata uma pergunta com a cor laranja.
    * @param mensagem mensagem a ser formatada.
    * @return mensagem formatada com cor laranja.
    */
    public static String formatarPergunta(String mensagem) {
        return "\u001B[38;5;208m" + mensagem + "\u001B[0m";
    }

    /**
    * Exibe uma mensagem de resposta formatada em verde.
    * @param mensagem mensagem a ser exibida.
    * @param args     argumentos opcionais para formatação.
    */
    public static void formatarResposta(String mensagem, Object... args) {
        System.out.println("\u001B[32m" +
                String.format(Locale.forLanguageTag("pt-BR"), mensagem, args) +
                "\u001B[0m");
    }

    /**
    * Exibe uma mensagem de encerramento formatada em ciano.
    * @param mensagem mensagem a ser exibida.
    */
    public static void formatarMensagemSaida(String mensagem) {System.out.println("\u001B[36m" + mensagem + "\u001B[0m");
    }
}