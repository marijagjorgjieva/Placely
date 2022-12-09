[![Java CI with Maven](https://github.com/marijagjorgjieva/Software-Design-and-Architecture-Project/actions/workflows/maven.yml/badge.svg?branch=main)](https://github.com/marijagjorgjieva/Software-Design-and-Architecture-Project/actions/workflows/maven.yml)


# Архитектура и шаблони
- За имплементација на апликацијата користеме Spring рамка, со **MVC шаблон**. 
- Исто така се користи **Pipe-And-Filter** архитектура од типот **Data Flow** архитектура, односно преку принципот на цевки и филтри се филтрираат добиените податоци од Places API со филтри за категорија, растојание и стринг. 
- Се користи **клиент-сервер** архитектура. 
- Апликацијата ќе биде дистрибуирана преку контејнеризација со Docker и Kubernetes.
