package br.gonriq.desafio.nubank.mapper;

public class ClientesMapper {
 /*
    private ClientesReponseDTO toDto(Clientes clientes) {
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

  */
}
