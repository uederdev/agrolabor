package br.com.ueder.agro_labor_api.entities.enums;

public enum Estado {

    AC(12, "Acre", "AC"),
    AL(27, "Alagoas", "AL"),
    AP(16, "Amapá", "AP"),
    AM(13, "Amazonas", "AM"),
    BA(29, "Bahia", "BA"),
    CE(23, "Ceará", "CE"),
    DF(53, "Distrito Federal", "DF"),
    ES(32, "Espírito Santo", "ES"),
    GO(52, "Goiás", "GO"),
    MA(21, "Maranhão", "MA"),
    MT(51, "Mato Grosso", "MT"),
    MS(50, "Mato Grosso do Sul", "MS"),
    MG(31, "Minas Gerais", "MG"),
    PA(15, "Pará", "PA"),
    PB(25, "Paraíba", "PB"),
    PR(41, "Paraná", "PR"),
    PE(26, "Pernambuco", "PE"),
    PI(22, "Piauí", "PI"),
    RJ(33, "Rio de Janeiro", "RJ"),
    RN(24, "Rio Grande do Norte", "RN"),
    RS(43, "Rio Grande do Sul", "RS"),
    RO(11, "Rondônia", "RO"),
    RR(14, "Roraima", "RR"),
    SC(42, "Santa Catarina", "SC"),
    SP(35, "São Paulo", "SP"),
    SE(28, "Sergipe", "SE"),
    TO(17, "Tocantins", "TO");

    private final int id;
    private final String descricao;
    private final String sigla;

    Estado(int id, String descricao, String sigla) {
        this.id = id;
        this.descricao = descricao;
        this.sigla = sigla;
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getSigla() {
        return sigla;
    }

    public static Estado fromSigla(String sigla) {
        for (Estado e : values()) {
            if (e.sigla.equalsIgnoreCase(sigla)) {
                return e;
            }
        }
        throw new IllegalArgumentException("Sigla inválida: " + sigla);
    }

    public static Estado fromId(int id) {
        for (Estado e : values()) {
            if (e.id == id) {
                return e;
            }
        }
        throw new IllegalArgumentException("ID IBGE inválido: " + id);
    }
}
