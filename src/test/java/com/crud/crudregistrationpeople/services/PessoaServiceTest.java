package com.crud.crudregistrationpeople.services;

import com.crud.crudregistrationpeople.domain.Contato;
import com.crud.crudregistrationpeople.domain.Pessoa;
import com.crud.crudregistrationpeople.repositories.ContatoRepository;
import com.crud.crudregistrationpeople.repositories.PessoaRepository;
import com.crud.crudregistrationpeople.requests.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest
class PessoaServiceTest {

    @Mock
    private PessoaRepository pessoaRepository;

    @Mock
    private ContatoRepository contatoRepository;

    @InjectMocks
    private PessoaService pessoaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createPessoaComContatos() {
        PessoaRequest pessoaRequest = new PessoaRequest();
        pessoaRequest.setNome("John Doe");
        pessoaRequest.setCpf("123.456.789-00");
        pessoaRequest.setDataNascimento(LocalDate.of(1990, 1, 1));

        ContatoRequest contatoRequest = new ContatoRequest();
        contatoRequest.setEmail("john@example.com");
        contatoRequest.setTelefone("123-456-7890");
        pessoaRequest.setContatos(Collections.singletonList(contatoRequest));

        Pessoa pessoaMock = new Pessoa();
        pessoaMock.setId(1L);
        pessoaMock.setNome("John Doe");
        pessoaMock.setCpf("123.456.789-00");
        pessoaMock.setDataNascimento(LocalDate.of(1990, 1, 1));

        Contato contatoMock = new Contato();
        contatoMock.setId(1L);
        contatoMock.setEmail("john@example.com");
        contatoMock.setTelefone("123-456-7890");
        contatoMock.setPessoa(pessoaMock);

        pessoaMock.setContatos(Collections.singletonList(contatoMock));

        when(pessoaRepository.save(any(Pessoa.class))).thenReturn(pessoaMock);

        Pessoa pessoaSalva = pessoaService.createPessoaComContatos(pessoaRequest);

        assertNotNull(pessoaSalva);
        assertEquals(1L, pessoaSalva.getId());
        assertEquals("John Doe", pessoaSalva.getNome());
        assertEquals("123.456.789-00", pessoaSalva.getCpf());
        assertEquals(LocalDate.of(1990, 1, 1), pessoaSalva.getDataNascimento());
        assertEquals(1, pessoaSalva.getContatos().size());
        assertEquals("john@example.com", pessoaSalva.getContatos().get(0).getEmail());
        assertEquals("123-456-7890", pessoaSalva.getContatos().get(0).getTelefone());

        verify(pessoaRepository, times(1)).save(any(Pessoa.class));
        verify(contatoRepository, times(1)).save(any(Contato.class));
    }

    @Test
    void updatePessoa() {
        Pessoa pessoaExistente = new Pessoa();
        pessoaExistente.setId(1L);
        pessoaExistente.setNome("John Doe");
        pessoaExistente.setCpf("123.456.789-00");
        pessoaExistente.setDataNascimento(LocalDate.of(1990, 1, 1));

        Contato contatoExistente = new Contato();
        contatoExistente.setId(1L);
        contatoExistente.setEmail("john@example.com");
        contatoExistente.setTelefone("123-456-7890");
        contatoExistente.setPessoa(pessoaExistente);

        pessoaExistente.setContatos(Collections.singletonList(contatoExistente));

        PessoaRequest pessoaRequest = new PessoaRequest();
        pessoaRequest.setNome("Updated Name");
        pessoaRequest.setCpf("987.654.321-00");
        pessoaRequest.setDataNascimento(LocalDate.of(1995, 5, 5));

        ContatoRequest contatoRequest = new ContatoRequest();
        contatoRequest.setEmail("updated@example.com");
        contatoRequest.setTelefone("987-654-3210");
        pessoaRequest.setContatos(Collections.singletonList(contatoRequest));

        when(pessoaRepository.findById(1L)).thenReturn(java.util.Optional.of(pessoaExistente));
        when(pessoaRepository.save(any(Pessoa.class))).thenReturn(pessoaExistente);

        Pessoa pessoaAtualizada = pessoaService.updatePessoa(1L, pessoaRequest);

        assertNotNull(pessoaAtualizada);
        assertEquals(1L, pessoaAtualizada.getId());
        assertEquals("Updated Name", pessoaAtualizada.getNome());
        assertEquals("987.654.321-00", pessoaAtualizada.getCpf());
        assertEquals(LocalDate.of(1995, 5, 5), pessoaAtualizada.getDataNascimento());
        assertEquals(1, pessoaAtualizada.getContatos().size());
        assertEquals("updated@example.com", pessoaAtualizada.getContatos().get(0).getEmail());
        assertEquals("987-654-3210", pessoaAtualizada.getContatos().get(0).getTelefone());

        verify(pessoaRepository, times(1)).findById(1L);
        verify(pessoaRepository, times(1)).save(any(Pessoa.class));
    }

    @Test
    void testGetPessoaById_ExistingId() {
        Long pessoaId = 1L;
        Pessoa pessoa = new Pessoa();
        pessoa.setId(pessoaId);

        when(pessoaRepository.findById(pessoaId)).thenReturn(Optional.of(pessoa));

        Pessoa result = pessoaService.getPessoaById(pessoaId);

        assertNotNull(result);
        assertEquals(pessoaId, result.getId());

        verify(pessoaRepository, times(1)).findById(pessoaId);
    }

    @Test
    void testGetPessoaById_NonExistingId() {
        Long pessoaId = 1L;

        when(pessoaRepository.findById(pessoaId)).thenReturn(Optional.empty());

        Pessoa result = pessoaService.getPessoaById(pessoaId);

        assertNull(result);

        verify(pessoaRepository, times(1)).findById(pessoaId);
    }

    @Test
    void testDeletePessoa_ExistingId() {
        Long pessoaId = 1L;

        when(pessoaRepository.findById(pessoaId)).thenReturn(Optional.of(new Pessoa()));

        boolean result = pessoaService.deletePessoa(pessoaId);

        assertTrue(result);

        verify(pessoaRepository, times(1)).findById(pessoaId);
        verify(pessoaRepository, times(1)).delete(any());
    }

    @Test
    void testDeletePessoa_NonExistingId() {
        Long pessoaId = 1L;

        when(pessoaRepository.findById(pessoaId)).thenReturn(Optional.empty());

        boolean result = pessoaService.deletePessoa(pessoaId);

        assertFalse(result);

        verify(pessoaRepository, times(1)).findById(pessoaId);
        verify(pessoaRepository, never()).delete(any());
    }

}