# Projeto web com SpringBoot API para helpdesk

## Sobre o projeto

1. O projeto tem como objetivo criar uma sistema backend para atender solicitadas pelos cliente e dire(helpdesk) 
## Tecnologias utilizadas 

1. segue as tecnologias utilizadas:
    1. Spring security
    1. jwt
    1. jdbc 
    1. jpa
    1. Lombok

## Camana de Modelo 

Definir as classes que serão persistidas seguindo o modelo abaixo como exemplo:

```java

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Produto{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    
    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotBlank(message = "Descrição é obrigatório")
    private String descricao;

}
```

## A Camada do Repositório

O Spring Data JPA é um componente-chave do spring-boot-starter-data-jpa do Spring Boot que torna mais fácil adicionar a funcionalidade CRUD 

Para fornecer ao nosso aplicativo a funcionalidade CRUD básica, tudo o que precisamos fazer é estender a interface do CrudRepository :


```java
@Repository
public interface ProdutoRepository extends CrudRepository<Produto, Long> {}
```


## A camada de controlador

A classe do controlador depende de alguns dos principais recursos do Spring MVC. Vamos começar com os métodos `mostrarFormularioProduto()` e `addProduto()` do controlador .


