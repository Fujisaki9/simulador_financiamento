package modelo;

import util.AumentoMaiorDoQueJurosException;
import util.InterfaceUsuario;
import util.Utilitarios;
import util.Validacoes;

import java.util.Locale;

public class Casa extends Financiamento {

    // Constantes
    private static final double AREA_MAXIMA = 10000;
    private static final long serialVersionUID = 1L;

    // Atributos
    private double tamanhoTerreno;
    private double areaConstruida;

    // Construtor
    /**
    * Construtor da Casa.
    *
    * @param valorCasa          valor da casa.
    * @param prazoFinanciamento prazo do financiamento em anos.
    * @param taxaJurosAnual     taxa de juros anual em porcentagem.
    * @param tamanhoTerreno     tamanho do terreno em m².
    * @param areaConstruida     área construída em m².
    */
    public Casa(double valorCasa, int prazoFinanciamento, double taxaJurosAnual, double tamanhoTerreno,
                double areaConstruida) {
        super(valorCasa, prazoFinanciamento, taxaJurosAnual);
        this.tamanhoTerreno = tamanhoTerreno;
        this.areaConstruida = areaConstruida;
    }

    // Métodos
    /**
    * Calcula o valor total do financiamento da casa.
    * Fórmula: parcela mensal * prazo em meses.
    *
    * @return valor total a ser pago ao final do financiamento.
    */
    @Override
    public double calcularPagamentoTotal() {return this.calcularPagamentoMensal() * this.prazoFinanciamento * 12;}

    /**
    * Retorna os dados do financiamento da casa formatados como String.
    *
    * @return String com os dados da casa.
    */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        double parcela = this.calcularPagamentoMensal();
        double valorTotal = parcela * getPrazoMeses();
        builder.append("========================================\n");
        builder.append(Utilitarios.centralizarMensagem("DADOS FINANCIAMENTO: CASA\n", 40));
        builder.append("========================================\n");
        builder.append(String.format(Locale.forLanguageTag("pt-BR"),
                "Valor da Casa: R$ %,.2f.\n", this.valorImovel));
        builder.append(String.format("Prazo Financiamento: %d anos.\n", this.prazoFinanciamento));
        builder.append(String.format("Taxa de Juros Anual: %.2f%%.\n", this.taxaJurosAnual));
        builder.append(String.format(Locale.forLanguageTag("pt-BR"),
                "Tamanho do Terreno: %,.2f m².\n", this.tamanhoTerreno));
        builder.append(String.format(Locale.forLanguageTag("pt-BR"),
                "Área Construída: %,.2f m².\n", this.areaConstruida));
        builder.append(String.format(Locale.forLanguageTag("pt-BR"),
                "Parcela Mensal: R$ %,.2f.\n", parcela));
        builder.append(String.format(Locale.forLanguageTag("pt-BR"),
                "Pagamento Total: R$ %,.2f.\n", valorTotal));
        builder.append("========================================\n\n");
        return builder.toString();
    }

    /**
     * Calcula o pagamento mensal do financiamento da casa
     * adicionando o seguro obrigatório à parcela base.
     *
     * @return valor da parcela mensal com seguro incluído
     */
    @Override
    public double calcularPagamentoMensal() {
        double valorJuros = super.calcularPagamentoMensal() - (this.valorImovel / getPrazoMeses());
        double valorAcrescimo = 80;
        try {
            validarLimiteAcrescimo(valorJuros, valorAcrescimo);
            return super.calcularPagamentoMensal() + 80;
        } catch (AumentoMaiorDoQueJurosException e) {
            System.out.println(e.getMessage());
            valorAcrescimo = valorJuros;
        }
        return super.calcularPagamentoMensal() + valorAcrescimo;
    }

    /**
     * Verifica se o acréscimo é superior à metade do valor dos juros mensais.
     * Caso o acréscimo exceda o limite, lança uma exceção informando o ocorrido.
     *
     * @param valorJuros     valor dos juros mensais.
     * @param valorAcrescimo valor do acréscimo a ser verificado.
     * @throws AumentoMaiorDoQueJurosException se o acréscimo for maior que a metade dos juros.
     */
    private void validarLimiteAcrescimo(double valorJuros, double valorAcrescimo)
            throws AumentoMaiorDoQueJurosException {
        String mensagem = String.format(Locale.forLanguageTag("pt-BR"),
                "\u001B[31mO acréscimo de R$ 80,00 excede o limite permitido. " +
                        "Será aplicado o valor de R$ %,.2f equivalente aos juros.\u001B[0m", valorJuros / 2);
        if (valorAcrescimo > (valorJuros / 2 )) {
            throw new AumentoMaiorDoQueJurosException(mensagem);
        }
    }

    /**
     * Solicita ao usuário o tamanho do terreno e verifica se está dentro do limite permitido.
     * Máximo: 10.000 m².
     *
     * @return tamanho do terreno validado.
     */
    private static double pedirTamanhoTerreno() {
        double tamanhoTerreno;
        do {
            tamanhoTerreno = Validacoes.validarDouble(
                             Utilitarios.formatarPergunta("Tamanho do terreno (m²): "));

            if (tamanhoTerreno > AREA_MAXIMA) {
                Utilitarios.formatarMensagemErro(
                        "Valor inválido! O limite máximo é %,.2f m². Digite novamente: ", AREA_MAXIMA);
            }

        } while (tamanhoTerreno > AREA_MAXIMA);

        Utilitarios.formatarResposta("Tamanho do Terreno: %,.2f m².", tamanhoTerreno);
        return tamanhoTerreno;
    }

    /**
     * Solicita ao usuário a área construída e verifica se está dentro do limite permitido.
     * A área construída não pode ser maior que o tamanho do terreno.
     *
     * @param tamanhoTerreno tamanho do terreno em m².
     * @return área construída validada.
     */
    private static double pedirAreaConstruida(double tamanhoTerreno) {
        double areaConstruida;
        do {
            areaConstruida = Validacoes.validarDouble(
                             Utilitarios.formatarPergunta("Área construída (m²): "));

            if (areaConstruida > tamanhoTerreno) {
                Utilitarios.formatarMensagemErro(
                        "Valor inválido! A área construída não pode ser maior que %,.2f m². " +
                                   "Digite novamente! ", tamanhoTerreno);
            }

        } while (areaConstruida > tamanhoTerreno);

        Utilitarios.formatarResposta("Área construída: %,.2f m².", areaConstruida);
        return areaConstruida;
    }

    // Factory Method
    /**
     * Coleta os dados necessários para criar uma Casa.
     * Solicita ao usuário as informações via terminal e retorna
     * um objeto Casa preenchido e pronto para uso.
     * A área construída é validada contra o tamanho do terreno.
     *
     * @return objeto Casa com todos os atributos preenchidos.
     */
    public static Casa coletarDados() {
        InterfaceUsuario input = new InterfaceUsuario();

        double valorImovel = input.pedirValorImovel();
        int prazoFinanciamento = input.pedirPrazoFinanciamento();
        double taxaJurosAnual = input.pedirTaxaJuros();
        double tamanhoTerreno = pedirTamanhoTerreno();
        double areaConstruida = pedirAreaConstruida(tamanhoTerreno);
        return new Casa(valorImovel, prazoFinanciamento, taxaJurosAnual, tamanhoTerreno, areaConstruida);
    }

    // Getters
    public double getTamanhoTerreno() {return tamanhoTerreno;}

    public double getAreaConstruida() {return areaConstruida;}

    // Setters
    public void setTamanhoTerreno(double tamanhoTerreno) {this.tamanhoTerreno = tamanhoTerreno;}

    public void setAreaConstruida(double areaConstruida) {this.areaConstruida = areaConstruida;}
}