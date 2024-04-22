package org.locadorafilmes.repository;

import org.locadorafilmes.domain.Filme;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class FilmeRepositorio {

    private final ConcurrentHashMap<Long, Filme> filmes = new ConcurrentHashMap<>();
    private Long idCounter = 1L;

    public void inicializar() {
        adicionarFilme(new Filme(idCounter++, "Star Wars: Episode IV – A New Hope", "Descrição do filme.", 1));
        adicionarFilme(new Filme(idCounter++, "Star Wars: Episode V – The Empire Strikes Back", "Descrição do filme.", 1));
        adicionarFilme(new Filme(idCounter++, "Star Wars: Episode VI – Return of the Jedi", "Descrição do filme.", 1));
    }

    public List<Filme> listarTodos() {
        return new ArrayList<>(filmes.values());
    }

    public Filme buscarPorId(Long id) {
        return filmes.get(id);
    }

    public Filme salvar(Long id, Filme filme) {
        filmes.put(id, filme);
        return filme;
    }

    private void adicionarFilme(Filme filme) {
        filmes.put(filme.getId(), filme);
    }
}
