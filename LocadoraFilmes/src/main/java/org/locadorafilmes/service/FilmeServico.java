import org.locadorafilmes.domain.Filme;
import org.locadorafilmes.repository.FilmeRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class FilmeServico {

    @Autowired
    private FilmeRepositorio filmeRepositorio;

    @PostConstruct
    public void inicializar() {
        filmeRepositorio.inicializar();
    }

    public List<Filme> listarFilmes() {
        return filmeRepositorio.listarTodos();
    }

    public Filme buscarFilmePorId(Long id) {
        return filmeRepositorio.buscarPorId(id);
    }

    public Filme atualizarDescricaoFilme(Long id, String novaDescricao) {
        Filme filme = filmeRepositorio.buscarPorId(id);
        if (filme != null) {
            filme.setDescricao(novaDescricao);
            filme.setVersao(filme.getVersao() + 1);
            filmeRepositorio.salvar(id, filme);
        }
        return filme;
    }
}
