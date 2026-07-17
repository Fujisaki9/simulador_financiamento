package modelo;

import util.InterfaceUsuario;
import util.Utilitarios;

import java.util.Locale;
import java.util.Scanner;

public class Terreno extends Financiamento {

    // Constantes
    private static final long serialVersionUID = 1L;

    // Atributos
    private String tipoDeZona;

    // Construtor
    /**
    * Construtor do Terreno.
    *
    * @param valorTerreno       valor do terreno.
    * @param prazoFinanciamento prazo do financiamento em anos.
    * @param taxaJurosAnual     taxa de juros anual em porcentagem.
    * @param tipoDeZona         tipo de zona do terreno (RESIDENCIAL ou COMERCIAL).
    */
    public Terreno(double valorTerreno, int prazoFinanciamento, double taxaJurosAnual, String tipoDeZona) {
        super(valorTerreno, prazoFinanciamento, taxaJurosAnual);
        this.tipoDeZona = tipoDeZona;
    }

    // Métodos
    /**
    * Calcula o valor total do financiamento do terreno.
    * Fórmula: parcela mensal * prazo em meses.
    *
    * @return valor total a ser pago ao final do financiamento.
    */
    @Override
    public double calcularPagamentoTotal() {return this.calcularPagamentoMensal() * this.prazoFinanciamento * 12;}

    /**
    * Retorna os dados do financiamento do terreno formatados como String.
    *
    * @return String com os dados do terreno.
    */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("========================================\n");
        builder.append(Utilitarios.centralizarMensagem("DADOS FINANCIAMENTO: TERRENO\n", 40));
        builder.append("========================================\n");
        builder.append(String.format(Locale.forLanguageTag("pt-BR"),
                "Valor do Terreno: R$ %,.2f.\n", this.valorImovel));
        builder.append(String.format("Prazo Financiamento: %d anos.\n", this.prazoFinanciamento));
        builder.append(String.format("Taxa de Juros Anual: %.2f%%.\n", this.taxaJurosAnual));
        builder.append(String.format("Tipo de Zona: %s.\n", this.tipoDeZona));
        builder.append(String.format(Locale.forLanguageTag("pt-BR"),
                "Parcela Mensal: R$ %,.2f.\n", this.calcularPagamentoMensal()));
        builder.append(String.format(Locale.forLanguageTag("pt-BR"),
                "Pagamento Total: R$ %,.2f.\n", this.calcularPagamentoTotal()));
        builder.append("========================================\n\n");
        return builder.toString();
    }

    /**
    * Calcula o pagamento mensal do financiamento do terreno
    * adicionando um acréscimo de 2% sobre a parcela base.
    *
    *
    * @return valor da parcela mensal com acréscimo de 2%.
    */
    @Override
    public double calcularPagamentoMensal() {return super.calcularPagamentoMensal() * 1.02;}

    /**
    * Solicita ao usuário o tipo de zona do imóvel.
    * Valores aceitos: RESIDENCIAL ou COMERCIAL.
    *
    * @return tipo de zona validado em letras maiúsculas.
    */
    private static String pedirTipoDeZona() {
        String tipoDeZona = validarTipoDeZona(
                            Utilitarios.formatarPergunta("Tipo de Zona [Residencial ou Comercial]: "));

        Utilitarios.formatarResposta("Zona: %s.", tipoDeZona);
        return tipoDeZona;
    }

    /**
    * Solicita ao usuário o tipo de zona e valida a entrada.
    * Valores aceitos: RESIDENCIAL ou COMERCIAL.
    *
    * @param texto mensagem exibida ao usuário.
    * @return tipo de zona validado em letras maiúsculas.
    */
    private static String validarTipoDeZona(String texto) {
        Scanner input = new Scanner(System.in);
        String tipoDeZona;

        do {
            System.out.print(texto);
            tipoDeZona = input.nextLine().toUpperCase().trim();

            if (!tipoDeZona.equals("RESIDENCIAL") && !tipoDeZona.equals("COMERCIAL")) {
                Utilitarios.formatarMensagemErro("Valor inválido! Digite RESIDENCIAL ou COMERCIAL: ");
            }

        } while (!tipoDeZona.equals("RESIDENCIAL") && !tipoDeZona.equals("COMERCIAL"));

        return tipoDeZona;
    }

    // Factory Method
    /**
    * Coleta os dados necessários para criar um Terreno.
    * Solicita ao usuário as informações via terminal e retorna
    * um objeto Terreno preenchido e pronto para uso.
    *
    * @return objeto Terreno com todos os atributos preenchidos.
    */
    public static Terreno coletarDados() {
        InterfaceUsuario input = new InterfaceUsuario();

        double valorImovel = input.pedirValorImovel();
        int prazoFinanciamento = input.pedirPrazoFinanciamento();
        double taxaJurosAnual = input.pedirTaxaJuros();
        String tipoDeZona = pedirTipoDeZona();
        return new Terreno(valorImovel, prazoFinanciamento, taxaJurosAnual, tipoDeZona);
    }

    // Getters
    public String  getTipoDeZona() {return tipoDeZona;}

    // Setters
    public void setTipoDeZona(String tipoDeZona) {this.tipoDeZona = tipoDeZona;}
}
