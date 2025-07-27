<h2>Desafio  Backend Nubank  com Java, SpringBoot, APIRestful e PostgreSQL como banco de dados.</h2>

Estou realizando essse projeto para estudar e treinar.
(Esse README possui explicações escritas por mim mesmo e partes por IA)

Coisas que aprendi ou que já sabia e agora tenho maior entendimento: 
* Função Lambda(Utilizadas nos Services do projeto)

  **O que é uma lambda expression?** <br>
    Uma lambda é uma forma simplificada de representar uma função anônima — ou seja, um pedaço de código que você pode passar como argumento, guardar em variável, etc.
    Foi introduzida no Java 8 para deixar o código mais conciso e expressivo, principalmente quando se trabalha com coleções e operações funcionais.

  **Por que usar lambda?**
   1. Código mais enxuto e legível
   Antes do Java 8, para passar um pedaço de código, você precisava criar uma classe anônima, por exemplo:

            List<String> nomes = Arrays.asList("Ana", "Carlos", "Beatriz");

            nomes.sort(new Comparator<String>() {
                @Override
                public int compare(String a, String b) {
                    return a.compareTo(b);
                }
            });

   Com lambda:
  
            nomes.sort((a, b) -> a.compareTo(b));
  
   muito mais limpo e direto.
  
<hr>


  <h3>O símbolo -> em Java representa uma função lambda </h3> — uma forma compacta e mais
  legível de escrever implementações de interfaces funcionais (como Function, Consumer, Predicate, etc).
  Exemplo no código do meu projeto:
  
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
  
  Esse trecho está convertendo uma lista de entidades Contato em uma lista de DTOs ContatoResponseDTO. 
  Isso é comum quando você retorna dados de uma entidade para o frontend,
  mas não quer ou não pode expor toda a estrutura da entidade original.
  
* DTOs (Data Transfer Objects):
   — é uma boa prática no desenvolvimento, especialmente em aplicações backend.
   **O que é DTO?**
  DTO significa Data Transfer Object (Objeto de Transferência de Dados).
  
  É uma classe simples que carrega dados entre processos — geralmente entre a camada de backend e frontend, ou entre diferentes camadas da aplicação.
  Normalmente contém apenas atributos, sem lógica de negócio.
  
  **Por que usar DTO?**
  1. Separação clara das camadas
  Entidades JPA (modelos de banco) têm lógica, relacionamentos, e muitas vezes campos sensíveis ou desnecessários para o cliente.
  DTOs isolam o modelo de domínio da interface externa, expondo apenas os dados que interessam.
  
  2. Segurança
  Evita que dados sensíveis (como senhas, tokens, ou informações internas) sejam enviados para o cliente acidentalmente.
  Controla exatamente quais campos são expostos.
  
  3. Customização da resposta
  Permite criar respostas específicas para cada endpoint, por exemplo:
  Mostrar nome e email no resumo de usuário. 
  Mostrar dados completos em detalhes.
  Você pode agregar dados de múltiplas entidades em um único DTO.
  
  4. Desempenho
  Transfere só o que é necessário, reduzindo o volume de dados trafegados pela rede.
  Ajuda a evitar problemas de serialização com relacionamentos complexos do banco.
  
  5. Facilita manutenção e evolução
  Mudanças no modelo de banco não quebram contratos com clientes externos.  
  Você pode versionar e modificar DTOs sem afetar a estrutura interna da aplicação.
