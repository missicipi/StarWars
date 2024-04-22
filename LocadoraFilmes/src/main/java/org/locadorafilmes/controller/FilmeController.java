import org.locadorafilmes.domain.Filme;
import org.locadorafilmes.service.FilmeServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/filmes")
public class FilmeController {

    @Autowired
    private FilmeServico filmeServico;

    @GetMapping
    public List<Filme> listarTodosOsFilmes() {
        return filmeServico.listarFilmes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Filme> buscarFilmePorId(@PathVariable Long id) {
        Filme filme = filmeServico.buscarFilmePorId(id);
        return filme != null ? ResponseEntity.ok(filme) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Filme> atualizarDescricaoFilme(@PathVariable Long id, @RequestBody String novaDescricao) {
        Filme filme = filmeServico.atualizarDescricaoFilme(id, novaDescricao);
        return filme != null ? ResponseEntity.ok(filme) : ResponseEntity.notFound().build();
    }
}
