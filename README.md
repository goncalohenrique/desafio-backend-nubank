<h2>Desafio  Backend Nubank  com Java, SpringBoot, APIRestful e PostgreSQL como banco de dados.</h2>

Estou realizando essse projeto para estudar e treinar.<br>
Esse README possui explicações escritas por mim mesmo.

**Explicação da Estrutura e Arquitetura Escolhida**:

 Utilizei a Arquitetura em Camadas, com MVC(Model-View-Controller) + dto(requisitado no desafio);<br>
 Separei o projeto em 5 packages: controller, dto, model, repository e service, explicação de cada um: <br>
 
   **Controller**: <br>
     Responsabilidade: Receber requisições HTTP e retornar respostas. <br><br>
      * Ele faz a interface com o “mundo externo” (cliente, front-end, Postman, etc.). <br>
      * Cada método representa um endpoint REST.<br>
      * Não contém  a lógica de negócio (delega isso para os services).<br>
      * È quem comanda e direciona a API.

   Exemplo no Projeto: 


        @GetMapping("/{id}/contatos")
    public ResponseEntity<List<ContatoResponseDTO>> ListarContatos(@PathVariable Long id)
    {
        return ResponseEntity.ok(clientesService.listarContatoPorCliente(id));
    }
    
   <br><br>
   **DTO** (maior explicação na parte de aprendizados):<br>
        Responsabilidade: Regras de negócio, segurança e orquestração dos dados.<br><br>
        * Camada intermediária entre o controller e o repository.<br>
        * Aqui é onde se aplicam regras, validações, cálculos e decisões.<br>
        * Pode chamar múltiplos repositórios ou serviços auxiliares.<br>
        * Evita expor diretamente as entidades para o cliente.<br>
        * Pode ter validações com @NotNull, @Email, etc.<br>
        * Divide-se geralmente em RequestDTO (entrada) e ResponseDTO (saída).<br>

   Exemplo no projeto: 

    public class ClientesReponseDTO {
    private Long idcliente;
    private String nome;
    private List<ContatoResponseDTO> contatos;
    }
    
  <br><br>
  **Model (ou Entity)**: <br>
    Responsabilidade: Representar as tabelas do banco de dados.<br><br>
    * Cada classe nesse pacote é uma entidade mapeada com @Entity.<br>
    * Representa as colunas e relacionamentos no banco.<br>
    *  Usada internamente (Service/Repository), mas nunca exposta diretamente ao cliente.<br>
    *  Controla o banco de dados. <br>
  OBS: com @Table, ao executar o projeto, automaticamente gera a tabela no banco de acordo com as variáveis criadas, PKs(@Id), e colunas especificadas no código<br>

 Exemplo no projeto:

    @Entity
    @Table
    public class Contato {
      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long idcontato;
  
      private String telefone;
      private String email;
  
      @ManyToOne
      @JoinColumn(name = "clientes_idcliente")
      @JsonBackReference
      private Clientes clientes;
    }
<br><br>

 **Repository**: <br>
   Responsabilidade: Acesso ao banco de dados.<br><br>
   * Interface que estende JpaRepository ou CrudRepository.<br>
   * Não tem código de negócio, é apenas o “canal” entre Java e o banco.<br>
   * Ao estender JpaRepository ou CrudRepository, você ganha centenas de funcionalidades prontas, como paginação, ordenação e transações.<br>
   * Mantém o acesso ao banco isolado dentro de uma camada específica 
   
  Exemplo no projeto: 

    public interface ClientesRepository extends JpaRepository<Clientes, Long> {
    
    }
<br><br>
 **Service**:<br>
   Responsabilidade: Regras de negócio, métodos e orquestração.<br><br>
   * Camada intermediária entre o controller e o repository.<br>
   * Aqui é onde se aplicam regras, validações, cálculos e decisões.<br>
   * Pode chamar múltiplos repositórios ou serviços auxiliares.<br>
   * Reaproveitamento de lógica, poid pode ser usada por múltiplos controllers ou outros serviços.<br>
   * Onde crio métodos, além dos já existentes no JpaRepository do repository. <br>

   Exemplo no projeto: 

      private ClientesRepository clientesRepository;

      public Clientes CadastrarCliente(ClientesDTO dto){
        Clientes clientes  = new Clientes();
        clientes.setNome(dto.getNome());

        //Verifica se já foi passada a lista de contatos ao fazer o cadastro do cliente.
        //Se sim, já associa os contatos ao cliente antes de salvar no banco.
        if (dto.getContatos() != null && !dto.getContatos().isEmpty()) {

            //Converte cada objeto de contato recebido no DTO para uma entidade Contato.
            List<Contato> contatos = dto.getContatos().stream().map(c -> {
                Contato contato = new Contato();

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
   
 
  
<hr>

Coisas que aprendi ou que já sabia e obtive maior entendimento: 
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
  
<br>

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

  <hr>
  
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
