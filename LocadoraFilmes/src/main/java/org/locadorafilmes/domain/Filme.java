package org.locadorafilmes.domain;


@Entity
@Data
public class Filme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String descricao;
    private int versao;

    public Filme(Long id, String titulo, String descricao, int versao) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.versao = versao;
    }

}
