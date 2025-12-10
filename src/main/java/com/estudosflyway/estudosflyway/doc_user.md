Primeiro Criamos a entidade User aonde ela recebeu 7 atributos
1- Id: vai ser a nossa chave primaria e ja vai com anotacao @Id pra dizer que ela vai ser a nossa chave primaria e @GeneratedValue com estrageia IDENTITY, que id vai ser gerado automaticamento pelo banco de forma sequencial 1 2 3 4 5 6 7 etc

2- Com username com @Column e dentro dos argumento nullable = flase e unique e length
3- password com nullable false e length
4- email com nullable = false com unique e length 
5- role com nullable = false e length 
6- LocalDateTime createdAt
7- LocalDateTime deletedAt

tem metodo protected e void e com anotacao PrePersit para executar acao antes de salvar no banco inicilizando ou createdAt e deletedAt com LocalDateTime.now

criamos um metodo publico e com retorno UserCreationDTO para dizer que o metodo retorna tordos atributos do record 
Serve para converter o objeto User para o DTO UserCreationDTO

Segundo criamos o dto de user passando username email password e role e para fazer a conversao com o nossa entidade 

Depos criamos uma interface chamada UserRepository qu extends o JpaRepository passando Nossa entidade User e long alem do metodos padrao de JpaRepository criamos algums metodos a mais  

Criamos um Optinal passando user chamados findByUser
