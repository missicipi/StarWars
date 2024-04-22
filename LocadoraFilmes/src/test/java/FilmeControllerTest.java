import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.ArrayList;

class FilmeControllerTest {

    @Mock
    private FilmeServico filmeServico;

    @InjectMocks
    private FilmeControlador filmeControlador;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listarTodosOsFilmes_DeveRetornarListaDeFilmes() {
        Filme filme1 = new Filme(1L, "Filme 1", "Descrição 1", 1);
        Filme filme2 = new Filme(2L, "Filme 2", "Descrição 2", 1);
        when(filmeServico.listarFilmes()).thenReturn(List.of(filme1, filme2));

        ResponseEntity<List<Filme>> response = filmeControlador.listarTodosOsFilmes();

        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void buscarFilmePorId_DeveRetornarFilme_QuandoFilmeExiste() {
        Filme filme = new Filme(1L, "Filme 1", "Descrição 1", 1);
        when(filmeServico.buscarFilmePorId(1L)).thenReturn(filme);

        ResponseEntity<Filme> response = filmeControlador.buscarFilmePorId(1L);

        assertNotNull(response.getBody());
        assertEquals("Filme 1", response.getBody().getTitulo());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void buscarFilmePorId_DeveRetornarNotFound_QuandoFilmeNaoExiste() {
        when(filmeServico.buscarFilmePorId(1L)).thenReturn(null);

        ResponseEntity<Filme> response = filmeControlador.buscarFilmePorId(1L);

        assertNull(response.getBody());
        assertEquals(404, response.getStatusCodeValue());
    }
}
