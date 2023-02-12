[![Java CI with Maven](https://github.com/marijagjorgjieva/Software-Design-and-Architecture-Project/actions/workflows/maven.yml/badge.svg?branch=main)](https://github.com/marijagjorgjieva/Software-Design-and-Architecture-Project/actions/workflows/maven.yml)

  
  

# Домашно 4

  

- Извршена контејнеризација: Направени се **docker containers** за *frontend* и *backend* **микросервисите**, како и  **docker-compose** фајл.
[Линк до dockerhub repository](https://hub.docker.com/r/adaskalov/placely)
- Извршено **рефакторирање** на *React*:
	1. Рефакторирана е секоја компонента да биде функциска компонента, наместо класна
	2. Компонентите сега користат *Hooks* на место од *React lifecycle methods*
	3. Додаден е нов *Loading pop-up* за подобро разбирање и користење на апликацијата
![Loading popup](https://user-images.githubusercontent.com/41647331/217871819-99774ac4-0bc4-4322-83fd-8d20d98b4901.png)
<div align="center"><i><b>Loading pop-up</bold></i></div>

- Извршено **рефакторирање** во *service* и *web* слоевите:  
	1.	Рефакториран  е методот за филтрирање на места и преместен од *web* слојот во *service* слојот
	2. Избришан е *ListAllById* методот во *service* слојот, бидејќи никаде не беше искористен
- Дополнително **рефакторирање**:
	1. избришани фајлови кои не се користат од *Prototype*
	2. рефакторирана *Location::calculateDistance*
	3. рефакторирана *Location* во *record*
	4. рефакторирани *Place* гетери и сетери
	5. *API key* и *routes* се чуваат во *environment variables*

> Апликацијата е хостирана и достапна на следниов линк: [линк до апликација](https://placely.social)

