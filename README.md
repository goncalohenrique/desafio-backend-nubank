<h2>Desafio  Backend Nubank  com Java, SpringBoot, APIRestful e PostgreSQL como banco de dados.</h2>

Estou realizando essse projeto para estudar e treinar.

Coisas que aprendi ou que já sabia e agora tenho maior entendimento: 
* Função Lambda(Utilizadas nos Services do projeto):
  <h3>O símbolo -> em Java representa uma função lambda </h3> — uma forma compacta e mais
  legível de escrever implementações de interfaces funcionais (como Function, Consumer, Predicate, etc).
  Exemplo no código:
  
    A seta -> indica que você está dizendo: "Para cada c(Contato), execute este bloco de código."
           
            .map(c -> {
            ContatoResponseDTO contatoDto = new ContatoResponseDTO();
            contatoDto.setTelefone(c.getTelefone());            
            contatoDto.setEmail(c.getEmail());                 
            contatoDto.setIdcontato(c.getIdcontato());     
            return contatoDto;
        })
        .collect(Collectors.toList()); // Coleta todos os DTOs em uma lista


  Sem lambda, teria que escrever algo bem mais longo:

        .map(new Function<Contato, ContatoResponseDTO>() {
            @Override
            public ContatoResponseDTO apply(Contato c) {
                ContatoResponseDTO dto = new ContatoResponseDTO();
                dto.setTelefone(c.getTelefone());
                dto.setEmail(c.getEmail());
                dto.setIdcontato(c.getIdcontato());
                return dto;
            }
        })
