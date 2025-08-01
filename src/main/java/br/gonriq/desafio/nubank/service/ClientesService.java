package br.gonriq.desafio.nubank.service;

import br.gonriq.desafio.nubank.dto.ClientesRequestDTO;
import br.gonriq.desafio.nubank.dto.ClientesReponseDTO;
import br.gonriq.desafio.nubank.dto.ContatoResponseDTO;
import br.gonriq.desafio.nubank.model.Cliente;
import br.gonriq.desafio.nubank.model.Contatos;
import br.gonriq.desafio.nubank.repository.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientesService {

    @Autowired
    private ClientesRepository clientesRepository;

    public Cliente CadastrarCliente(ClientesRequestDTO dto){
        Cliente clientes  = new Cliente();
        clientes.setNome(dto.getNome());

        //Verifica se já foi passada a lista de contatos ao fazer o cadastro do cliente.
        //Se sim, já associa os contatos ao cliente antes de salvar no banco.
        if (dto.getContatos() != null && !dto.getContatos().isEmpty()) {

            //Converte cada objeto de contato recebido no DTO para uma entidade Contato.
            List<Contatos> contatos = dto.getContatos().stream().map(c -> {
                Contatos contato = new Contatos();

                contato.setTelefone(c.getTelefone());
                contato.setEmail(c.getEmail());
                contato.setClientes(clientes); //Define o cliente associado a este contato

                return contato; //Retorna o objeto Contato para a lista
            }).collect(Collectors.toList()); //Coleta todos os contatos mapeados em uma lista

            //Associa a lista de contatos à entidade cliente antes de salvá-la
            clientes.setContatos(contatos);
        }

        //salva o cliente (e automaticamente os contatos associados, se houverem) no banco de dados
        return clientesRepository.save(clientes);
    }

    public List<ContatoResponseDTO> listarContatoPorCliente(Long idcliente)
    {
        Cliente clientes = clientesRepository.findById(idcliente)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado!"));//Se não, lançar: excessao de cliente nao encontrado

        //Pega a lista de contatos associada ao cliente (clientes.getContatos())
        //Abre um stream() para processar essa lista de forma funcional.
        //Para cada objeto Contato da lista, aplica a função lambda (->)de conversão para DTO.
        //*** Explicação do que é lamba e porque a estou utilizando, está no arquivo README do projeto ;)
        return clientes.getContatos().stream().map(c -> {
                ContatoResponseDTO contatoDto = new ContatoResponseDTO();
                contatoDto.setTelefone(c.getTelefone());
                contatoDto.setEmail(c.getEmail());
                contatoDto.setIdcontato(c.getIdcontato());
                return contatoDto;
            }).collect(Collectors.toList());

    }

    // Método que retorna todos os clientes em formato DTO
    public List<ClientesReponseDTO> ListarClientes() {
        return clientesRepository.findAll()        // 1. Busca todos os clientes no banco
                .stream()                          // 2. Cria um stream para processar cada cliente
                .map(this::toDto)                  // 3. Converte cada cliente para DTO usando o método toDto
                .collect(Collectors.toList());     // 4. Coleta os DTOs em uma lista
    }

    private ClientesReponseDTO toDto(Cliente clientes) {
        ClientesReponseDTO dto = new ClientesReponseDTO();
        dto.setIdcliente(clientes.getIdcliente());
        dto.setNome(clientes.getNome());

        // 4. Converte os contatos associados ao cliente para objetos ContatoResponseDTO
        List<ContatoResponseDTO> contatos = clientes.getContatos()
                .stream()
                .map(c -> {
                    ContatoResponseDTO contatoDto = new ContatoResponseDTO();  // 4.1 Cria um novo DTO de contato
                    contatoDto.setTelefone(c.getTelefone());
                    contatoDto.setEmail(c.getEmail());
                    contatoDto.setIdcontato(c.getIdcontato());
                    return contatoDto;
                })
                .collect(Collectors.toList());  // 5. Coleta os contatos em uma lista

        dto.setContatos(contatos);  // 6. Adiciona a lista de contatos ao cliente DTO
        return dto;  // 7. Retorna o DTO completo
    }


}
