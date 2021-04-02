# Simple password validator

## Como executar

Para executar é necessário simplesmente executar a aplicação Spring. E fazer uma requisição <strong>POST</strong>
para <i>localhost:8080/validPassword</i>
enviando como <i>body</i> um JSON no formato.

```json
{
  "password": "AbXD1$%57"
}
```

O método retornará, um JSON no seguinte formato;

```json
{
  "valid" : true
}
```

Caso não esteja executando através de uma IDE, e sim compilado ele, é possível executar como um Jar normal.

<a href="https://docs.spring.io/spring-boot/docs/1.5.16.RELEASE/reference/html/using-boot-running-your-application.html">
Mais informações, clique aqui</a>

## Detalhes da Solução

Muito do código é melhor explicado, como comentário dentro das próprias classes. Então muitos dos detalhes de
implementação pode ser visto lá.

### Linguagem de Programação

Primeiramente, a tecnologia. Pessoalmente, sempre gostei muito de Java, e gosto bastante do conceito do Kotlin, e sendo
um "filho" do Java, mantém uma proximidade com os pontos que me agradam no Java. Então meu maior dilema no quesito
linguagem, foi entre Java e Kotlin. Java pode ser substituído por Kotlin em quase qualquer cenário, e o uso das
coroutines do Kotlin, seria um excelente substituto para implementação de Threads que utilizei para melhor performance
nas validações. Sem contar que os principais frameworks que considerei utilizar foram o Spring e o Quarkus, que vou
entrar em detalhes posteriormente, ambos podem ser utilizado tanto com o Java, quanto com o Kotlin. Dito tudo isso, no
final escolhi Java. Essa escolha, foi feito baseado unicamente, por familiaridade. O resultado com ambas as linguagens
seriam essencialmente o mesmo em performance, mas apesar de ter conhecimento, e já ter feito alguns trabalhos com Web,
não consigo dizer que tenho muita experiência ainda. E Kotlin, apesar de ser apaixonado pelo conceito da linguagem,
nunca cheguei realmente utilizar-la. Por isso, a escolha de Java como Linguagem, pois estou familiarizado com a
linguagem, e conseguiria focar meu tempo pra tentar polir mais as partes que não tenho tanta familiaridade, para tentar
demonstrar melhor meu potencial.

### Framework

A escolha do Spring como Framework, o processo de escolha foi bem semelhante com a escolha da linguagem. Quando estava
aprofundando meus estudos no Java como linguagem um bom tempo atrás, me deparei com o Spring, por ser o Framework de
Java mais popular atualmente. Aprendi os conceitos de Injeção de Dependência com ele, e apesar de não conseguir dizer
que domino o Framework 100%, é o que eu tenho mais conhecimento. Adicionalmente, o Quarkus como Framework está muito
recente, apesar de proporcionar uma performance impressionante, e ser excelente para o desenvolvimento por causa do Hot
Deploy nativo, é um framework, muito recente e não tão consolidado quanto o Spring ainda, apesar de apresentar um
potencial gigante, e que com certeza é um framework que merece ser estudado para futura utilização.

### Demonstração

Meu objetivo, era criar um endpoint, onde eu pudesse cumprir o objetivo do desafio, tentando demonstrar o meu potêncial
como desenvolvedor da melhor forma possível. Meu objetivo foi demonstrar no começo da aplicação o Framework fazendo a
injeção, seguir os conceitos de SOLID o melhor possível, manter o código clean, e com comentários bem explicativos,
ainda mais por tratar-se de uma avaliação, e criar os testes automatizados de maneira que fosse possível demonstrar
conhecimento dos conceitos dos teste principalmente.

Para ser honesto, o desenvolvimento em sí não tomou muito tempo, o que mais tomou tempo, foi tentando aperfeiçoar para
melhor representar meu conhecimento. Ao finalizar, a versão final é quase idêntica a original :( . E segue o seguinte
fluxo.

- Recebendo POST no endpoint <strong>/validPassword</strong>
- Framework Trata o JSON e transforma em um POJO

- Controller faz os tratamentos iniciais e passa a String com a senha para o microserviço, que retornará um boolean
  informando se a senha é válida ou não. Não foi desenvolvido como Serverless e nem considerando um container de Docker,
  porém é facilmente convertível.

- O microserviço começa seu trabalho de analisar a senha. Primeira validação é a mais simples, se existem pelo menos 9
  caracteres. Caso falhe nessa, que é uma simples comparação de tamanho, não é necessário fazer as validações que
  consomem mais recursos.

- A partir deste momento é criado uma lista de validadores, que é uma classe abstrata que terá subclasses que serão
  implementações únicas de cada tipo de validação. Desta forma é possível expandir regras as validação facilmente, sem
  precisar alterar nenhuma implementação existente, o que é um recurso que demonstra bem o SOLID, é perfeitamente
  extensivel.

- Cada uma das validações, são realizadas em paralelo, para ganhar em performance. E as únicas restrições para as senhas
  são as informadas na descrição do desafio. Isso significa, que caracteres incomun, como por exemplo, ♫♪↑♂♀, não serão
  considerados incorretos. Porém a forma que foi criado, é extremamente simples implementar uma nova regra, sendo
  necessário somente a criação nova classe extendendo Validator e Implementando ICheckExistInPassword, o segundo,
  somente se necessário. Usando a interface, é possível checar por um expressão Regex na senha.

  - Outra premissa, é que foi considerado que o retorno, enviaria somente um boolean, não enviando junto informações que
    normalmente são enviadas no objeto de retorno, como Timestamp, entre outros.

  - Outro detalhe que foi cogitado, porém não foi considerado, foi criptografia. Em um sistema real talvez a senha
    chegasse encriptada, e fosse necessário chamar outro microserviço para decriptar. Porém nesse caso foi considerado
    somente que a senha já estaria legível, a unica criptografia seria do próprio https

- Após as Threads finalizarem seu ciclo de vida, é validado se todos as regras retornaram verdadeiro, caso sim o retorno
  será verdadeiro, pois trata-se de uma senha válida. Caso uma delas em algum momento retornar falso, o processo já é
  abortado para economizar recursos, as Threads são interrompidas, caso necessário, e o retorno é falso, pois trata-se
  de uma senha inválida.

- Por último com o retorno, é montado o objeto para retornar. E o próprio Spring o converte para JSON para reponder o
  POST.

### Testes

Os testes unitários, foram feitos utilizando o JUnit5. Eles testam todos os métodos relevantes do código. Por tratar-se
de um processo simples, não foi necessário mockar nenhum retorno de Banco de Dados ou nenhuma classe para o teste. Porém
foi possível demonstrar como seriam feitos testes unitários de maneira bem resumida.

Quanto aos testes de integração, de novo, não existe uma base de dados nem nada similar, então não foi tão diferente na
prática dos testes unitários. Porém, para os testes de integração, utilizei o SpringBootTest. Onde simulei primeiramente
a criação do Controller. E na parte mais importei mockei requisições utilizando os exemplos de senha, passados no
desafio. Todos os testes retornaram o esperado. São em tese, poucos métodos, por tratar-se de uma demonstração, utilizei
casos de testes em massa, o que não é exatamente o ideal. Um único caso de teste, cobre diversas tentativas de senha.
Caso alguma dê errado, será mostrada no console, qual senha que falhou, para facilidade de manutenção. Com foi a
requisição propriamente dita que foi mockada. Isso essencialmente, faz a aplicação ser testada de ponta a ponta, como um
teste de integração deve ser feito.

