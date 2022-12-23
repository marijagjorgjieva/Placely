[![Java CI with Maven](https://github.com/marijagjorgjieva/Software-Design-and-Architecture-Project/actions/workflows/maven.yml/badge.svg?branch=main)](https://github.com/marijagjorgjieva/Software-Design-and-Architecture-Project/actions/workflows/maven.yml)


# Архитектура и шаблони
- За имплементација на апликацијата користеме Spring рамка, со **MVC шаблон**. 
- Исто така се користи **Pipe-And-Filter** архитектура од типот **Data Flow** архитектура, односно преку принципот на цевки и филтри се филтрираат добиените податоци од Places API со филтри за категорија, растојание и стринг. 
- Се користи **клиент-сервер** архитектура. 
- Апликацијата ќе биде дистрибуирана преку контејнеризација со Docker и Kubernetes.

## Концептуална архитектура
![Концептуален дијаграм](https://raw.githubusercontent.com/marijagjorgjieva/Software-Design-and-Architecture-Project/main/Domasno2/Conceptual%20Diagram/conceptual_diagram.png)


## Извршна архитектура
![Извршен дијаграм](https://raw.githubusercontent.com/marijagjorgjieva/Software-Design-and-Architecture-Project/main/Domasno2/Execution%20Diagram/execution_diagram.jpg)


## Имплементациска архитектура
![Имплементациски дијаграм](https://raw.githubusercontent.com/marijagjorgjieva/Software-Design-and-Architecture-Project/main/Domasno2/Implementation%20Diagram/implement_diagram.png)
### Имплементациски бихејворијален дијаграм
![Имплементациски бихејворијален дијаграм](https://raw.githubusercontent.com/marijagjorgjieva/Software-Design-and-Architecture-Project/main/Domasno2/Implementation%20Diagram/implement_behavioral.png)


> Сите слики дадени во прилог се исто така достапни како датотеки во соодветните директориуми
