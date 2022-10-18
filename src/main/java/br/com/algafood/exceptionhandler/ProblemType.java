package br.com.algafood.exceptionhandler;

public enum ProblemType {
    ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada", "Entidade não encontrada"),
    ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
    ERRO_NEGOCIO("/erro-negocio", "Violação das regras de negócio");


    private String uri;
    private String title;

    ProblemType(String path, String title) {
        this.uri = "https://algafood.com.br" + path;
        this.title = title;
    }

    public String getUri() {
        return uri;
    }

    public String getTitle() {
        return title;
    }
}
