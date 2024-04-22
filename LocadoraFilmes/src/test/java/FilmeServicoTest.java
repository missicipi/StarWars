import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

class FilmeServicoTest {

    @Mock
    private FilmeRepositorio filmeRepositorio;

    @InjectMocks
    private FilmeServico filmeServico;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listarFilmes_DeveRetornarListaDeFilmes() {
        Filme filme1 = new Filme(1L, "Filme 1", "Descrição 1", 1);
        Filme filme2 = new Filme(2L, "Filme 2", "Descrição 2", 1);
        when(filmeRepositorio.listarTodos()).thenReturn(Arrays.asList(filme1, filme2));

        List<Filme> filmes = filmeServico.listarFilmes();

        assertNotNull(filmes);
        assertEquals(2, filmes.size());
        verify(filmeRepositorio, times(1)).listarTodos();
    }

    @Test
    void buscarFilmePorId_DeveRetornarFilmeCorrespondente() {
        Filme filme = new Filme(1L, "Filme 1", "Descrição 1", 1);
        when(filmeRepositorio.buscarPorId(1L)).thenReturn(filme);

        Filme resultado = filmeServico.buscarFilmePorId(1L);

        assertNotNull(resultado);
        assertEquals("Filme 1", resultado.getTitulo());
        verify(filmeRepositorio, times(1)).buscarPorId(1L);
    }

    @Test
    void atualizarDescricaoFilme_DeveAtualizarDescricaoEIncrementarVersao() {
        Filme filme = new Filme(1L, "Filme 1", "Descrição Antiga", 1);
        when(filmeRepositorio.buscarPorId(1L)).thenReturn(filme);
        when(filmeRepositorio.salvar(eq(1L), any(Filme.class))).thenAnswer(i -> i.getArguments()[1]);

        Filme atualizado = filmeServico.atualizarDescricaoFilme(1L, "Nova Descrição");

        assertNotNull(atualizado);
        assertEquals("Nova Descrição", atualizado.getDescricao());
        assertEquals(2, atualizado.getVersao());
        verify(filmeRepositorio, times(1)).salvar(1L, filme);
    }
}
