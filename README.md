This repo contains the implementation of the Star Wars API

FEATURES 
CHARACTER SEARCH 
CHARACTER DETAILS 

ARCHITECTURE 
MODEL VIEW VIEWMODEL (MVVM)

LIBRARIES

Viewmodel - Presenter for persisting view state across config changes

Databinding - converts traditional view click listeners and callbacks to Kotlin flow

Room - Provides abstraction layer over SQLite

Retrofit - type-safe HTTP client and supports coroutines out of the box.

Moshi - JSON Parser, used to parse requests on the data layer for Entities and understands Kotlin non-nullable and default parameters

okhttp-logging-interceptor - logs HTTP request and response data.

kotlinx.coroutines - Library Support for coroutines, provides run-blocking coroutine builder used in tests

Truth - Assertions Library, provides readability as far as assertions are concerned

Dagger Hilt - handles dependency injection

TECHNICAL DECISIONS
FIlE STRUCTURING 

TOP Level 
The top-level packages are used across the board and need to be at the top so data can flow down from them. The top-level packages include the following 
  - apiService
  - cache
  - core
  - data
  - domain
  - network
  - entryPoint
  - features
  
FEATUREs
Feature search contains three packages 
  - featureSearch contains the UI implementation of the search
  - featureCharacterDetails contains the UI implementation of the character details
  - viewmodel contains implementation that is shared across the other features 


SCREENSHOTS 
|LANDING PAGE| SEARCH RESULT | RECENT SEARCH | CHARACTER DETAILS | FILMS | FILMS DETAILS|

|<img src="https://github.com/Ayoolamasha/StarWars/assets/30448980/b70174ea-8143-4370-b77d-304fc5f823f8" width="320"/> 

| <img src="https://github.com/Ayoolamasha/StarWars/assets/30448980/b8fd4e66-59aa-45e8-8894-5cca3015f40c" width="320"/> 

| <img src="https://github.com/Ayoolamasha/StarWars/assets/30448980/e18b06b9-18d4-47c8-b4bd-04af0a5f643c" width="320"/>

| <img src="https://github.com/Ayoolamasha/StarWars/assets/30448980/4dbed6d9-a4e1-4261-9682-6d30110f4f0a" width="320"/>

| <img src="https://github.com/Ayoolamasha/StarWars/assets/30448980/52f5a687-b44e-4324-a782-744fcc478290" width="320"/>

| <img src="https://github.com/Ayoolamasha/StarWars/assets/30448980/549faac7-e769-4d2b-a384-ed63505bbbe7" width="320"/>

