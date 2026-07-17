package util;

public class InterfaceUsuario {

    // Constantes
    private static final double VALOR_MINIMO = 100_000.0;
    private static final double VALOR_MAXIMO = 5_000_000.0;
    private static final int PRAZO_MINIMO = 5;
    private static final int PRAZO_MAXIMO = 35;
    private static final double TAXA_MINIMA = 3.0;
    private static final double TAXA_MAXIMA = 15.0;

    // Métodos
    /**
    * Solicita ao usuário o valor do imóvel e verifica se o valor está dentro dos limites permitidos.
    * Mínimo: R$ 100.000,00.
    * Máximo: R$ 5.000.000,00.
    *
    * @return valor do imóvel validado.
    */
    public double pedirValorImovel() {
        double valorImovel;
        do {
            valorImovel = Validacoes.validarDouble
                          (Utilitarios.formatarPergunta(
                          "Digite o valor do imóvel (R$ 100.000,00 - 5.000.000,00): "));

            if (valorImovel < VALOR_MINIMO || valorImovel > VALOR_MAXIMO) {
                Utilitarios.formatarMensagemErro("Valor inválido! Digite entre R$ %,.2f e R$ %,.2f.",
                                          VALOR_MINIMO, VALOR_MAXIMO);
            }
        } while (valorImovel < VALOR_MINIMO || valorImovel > VALOR_MAXIMO);

        Utilitarios.formatarResposta("Valor Imovel: R$ %,.2f.", valorImovel);
        return valorImovel;
    }

    /**
    * Solicita ao usuário o prazo de financiamento e verifica se o valor está dentro dos limites permitidos.
    * Mínimo: 5 anos.
    * Máximo: 35 anos.
    *
    * @return prazo de financiamento validado.
    */
    public int pedirPrazoFinanciamento() {
        int prazoFinanciamento;
        do {
            prazoFinanciamento = Validacoes.validarInteger
                                 (Utilitarios.formatarPergunta(
                                 "Digite o prazo do financiamento (mín: 5 | máx: 35 anos): "),
                                 5);

            if (prazoFinanciamento < PRAZO_MINIMO || prazoFinanciamento > PRAZO_MAXIMO) {
                Utilitarios.formatarMensagemErro("Valor inválido! Digite entre %d e %d anos.",
                                          PRAZO_MINIMO, PRAZO_MAXIMO);
            }
        } while (prazoFinanciamento < PRAZO_MINIMO || prazoFinanciamento > PRAZO_MAXIMO);

        Utilitarios.formatarResposta("Prazo do Financiamento: %d anos.", prazoFinanciamento);
        return prazoFinanciamento;
    }

    /**
    * Solicita ao usuário a taxa de juros anual do financiamento e verifica se o valor está dentro dos limites
    * permitido.
    * Mínimo: 3%.
    * Máximo: 15%.
    *
    * @return taxa de juros anual validada.
    */
    public double pedirTaxaJuros() {
        double taxaJuros;
        do {
            taxaJuros = Validacoes.validarDouble(
                    Utilitarios.formatarPergunta("Digite a taxa do juros anual (3 a 15%): "));

            if (taxaJuros < TAXA_MINIMA || taxaJuros > TAXA_MAXIMA) {
                Utilitarios.formatarMensagemErro("Valor inválido! Digite entre %.0f%% e %.0f%%.",
                                          TAXA_MINIMA, TAXA_MAXIMA);
            }
        } while (taxaJuros < TAXA_MINIMA || taxaJuros > TAXA_MAXIMA);

        Utilitarios.formatarResposta("Taxa de Juros anual: %.1f%%", taxaJuros);
        return taxaJuros;
    }
}