package modelo;

import util.InterfaceUsuario;
import util.Utilitarios;
import util.Validacoes;

import java.util.Locale;

public class Apartamento extends Financiamento {

    // Constantes
    private static final long serialVersionUID = 1L;
    private static final int ANDAR_MAXIMO = 30;
    private static final int MAX_VAGAS_GARAGEM = 3;

    // Atributos
    private int numeroAndar;
    private int vagasGaragem;

    // Construtor
    /**
    * Construtor do Apartamento.
    *
    * @param valorApartamento  valor do apartamento.
    * @param prazoFinanciamento prazo do financiamento em anos.
    * @param taxaJurosAnual    taxa de juros anual em porcentagem.
    * @param numeroAndar       número do andar do apartamento.
    * @param vagasGaragem      número de vagas na garagem.
    */
    public Apartamento(double valorApartamento, int prazoFinanciamento, double taxaJurosAnual, int numeroAndar,
                       int vagasGaragem) {
        super(valorApartamento, prazoFinanciamento, taxaJurosAnual);
        this.numeroAndar = numeroAndar;
        this.vagasGaragem = vagasGaragem;

    }

    // Métodos
    /**
    * Calcula o valor total do financiamento do apartamento.
    * Fórmula: valorImovel * (1 + (taxaJurosMensal * prazoMeses)).
    *
    * @return valor total a ser pago ao final do financiamento.
    */
    @Override
    public double calcularPagamentoTotal() {
        return this.valorImovel * (1 + (getTaxaJurosMensal() * getPrazoMeses()));
    }

    /**
    * Retorna os dados do financiamento do apartamento formatados como String.
    *
    * @return String com os dados do apartamento.
    */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("========================================\n");
        builder.append(Utilitarios.centralizarMensagem("DADOS FINANCIAMENTO: APARTAMENTO\n", 40));
        builder.append("========================================\n");
        builder.append(String.format(Locale.forLanguageTag("pt-BR"),
                "Valor do Apartamento: R$ %,.2f.\n", this.valorImovel));
        builder.append(String.format("Prazo Financiamento: %d anos.\n", this.prazoFinanciamento));
        builder.append(String.format("Taxa de Juros Anual: %.2f%%.\n", this.taxaJurosAnual));
        builder.append(String.format("Número do Andar: %dº.\n", this.numeroAndar));
        builder.append(String.format("Vagas na Garagem: %d vaga(s).\n", this.vagasGaragem));
        builder.append(String.format(Locale.forLanguageTag("pt-BR"),
                "Parcela Mensal: R$ %,.2f.\n", this.calcularPagamentoMensal()));
        builder.append(String.format(Locale.forLanguageTag("pt-BR"),
                "Pagamento Total: R$ %,.2f.\n", this.calcularPagamentoTotal()));
        builder.append("========================================\n\n");

        return builder.toString();
    }

    /**
    * Calcula o pagamento mensal do financiamento do apartamento.
    * dividindo o total pelo prazo convertido em meses.
    *
    * @return valor da parcela mensal.
    */
    @Override
    public double calcularPagamentoMensal() {return (this.calcularPagamentoTotal() / getPrazoMeses());}

    /**
    * Solicita ao usuário o número do andar e verifica se está dentro do limite permitido.
    * Mínimo: 1. Máximo: 30 andares.
    *
    * @return número do andar validado.
    */
    private static int pedirNumeroAndar() {
        int numeroAndar;
        do {
            numeroAndar = Validacoes.validarInteger(
                    Utilitarios.formatarPergunta("Número Andar: "), 1);

            if (numeroAndar > ANDAR_MAXIMO) {
                Utilitarios.formatarMensagemErro(
                        "Valor inválido! O limite máximo é %d andares. Digite novamente: ",
                        ANDAR_MAXIMO);
            }
        } while (numeroAndar > ANDAR_MAXIMO);

        Utilitarios.formatarResposta("Andar: %dº.",  numeroAndar);
        return numeroAndar;
    }

    /**
    * Solicita ao usuário o número de vagas na garagem e verifica se está dentro do limite permitido.
    * Mínimo: 0 vagas. Máximo: 3 vagas.
    *
    * @return número de vagas na garagem validado.
    */
    private static int pedirVagasGaragem() {
        int vagasGaragem;
        do {
            vagasGaragem = Validacoes.validarInteger(
                    Utilitarios.formatarPergunta("Vagas garagem: "), 0);

            if (vagasGaragem > MAX_VAGAS_GARAGEM) {
                Utilitarios.formatarMensagemErro(
                        "Valor inválido! O limite máximo é %d vagas. Digite novamente: ",
                        MAX_VAGAS_GARAGEM);
            }

        } while (vagasGaragem > MAX_VAGAS_GARAGEM);

        Utilitarios.formatarResposta("Vagas na garagem: %d.",  vagasGaragem);
        return vagasGaragem;
    }

    // Factory method
    /**
    * Coleta os dados necessários para criar um Apartamento.
    * Solicita ao usuário as informações via terminal e retorna
    * um objeto Apartamento preenchido e pronto para uso.
    *
    * @return objeto Apartamento com todos os atributos preenchidos.
    */
    public static Apartamento coletarDados() {
        InterfaceUsuario input = new InterfaceUsuario();

        double valorImovel = input.pedirValorImovel();
        int prazoFinanciamento = input.pedirPrazoFinanciamento();
        double taxaJurosAnual = input.pedirTaxaJuros();
        int numeroAndar = pedirNumeroAndar();
        int vagasGaragem = pedirVagasGaragem();
        return new Apartamento(valorImovel, prazoFinanciamento, taxaJurosAnual, numeroAndar,
                               vagasGaragem);
    }

    // Getters
    public int getVagasGaragem() {return vagasGaragem;}

    public void setVagasGaragem(int vagasGaragem) {this.vagasGaragem = vagasGaragem;}

    // Setters
    public int getNumeroAndar() {return numeroAndar;}

    public void setNumeroAndar(int numeroAndar) {this.numeroAndar = numeroAndar;}
}




