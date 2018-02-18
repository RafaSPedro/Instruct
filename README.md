# Instruct Seleção
Desenvolvi a tarefa solicitada em Java usando o Retrofit para as requisições HTTP como uma Interface, para a desserialização do ResponseBody(de JSon para Java Object) utilizei o Gson Converter. Caso a conexão com servidor não seja possível, será mostrado um toast informando indisponibilidade do serviço.

## Questões

### Listar os Websites de todos Usuários
Para deixar um visual mais agradável crei um listview no fragment MainView com layout personalizado combinando o username com os respectivos Websites.

![alt text](https://image.ibb.co/mKdi5n/img1.jpg)

### Mostrar apenas o email de um User específico
O layout e o adaptador do listview era composto por 3 TextViews, username, url do website e email. Porém o Sr. solicitou que apenas mostrasse o email da usuária que tivesse o username igual a "Samantha". Por isso nas outras situações deixei o TextView "email" como GONE para que não ocupasse espaço em tela.
![alt text](https://image.ibb.co/dbm0J7/img2.jpg)

### Mostrar o total de usuários do Hemisfério Sul
Criei um contador para todas situações em que a latitude fosse negativa no fragment StatisticView. Para melhor visualização dos dados criei um menu para separando a lista de websites da quantidade de usuários de cada hemisfério.
![alt text](https://image.ibb.co/cxsFJ7/img3.jpg) ![alt text](https://image.ibb.co/jLL7WS/img4.jpg)
