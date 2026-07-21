# Reto Final - SauceDemo E2E con Serenity, Playwright y Jenkins

## Objetivo

Este repositorio implementa una estrategia de automatizacion End-to-End sobre [SauceDemo](https://www.saucedemo.com/) usando dos enfoques complementarios:

- **Serenity BDD + Java + Screenplay + Cucumber** para escenarios de compra y login.
- **Playwright + Python + pytest-bdd** para validaciones dinamicas de precios y ordenamiento.

La solucion incluye reportes automaticos, screenshots ante fallos y un `Jenkinsfile` declarativo para integracion continua.

## Arquitectura

```text
.
|-- serenity-tests/
|   |-- src/test/java/com/saucedemo/
|   |   |-- questions/
|   |   |-- runner/
|   |   |-- stepdefinitions/
|   |   |-- tasks/
|   |   `-- ui/
|   |-- src/test/resources/features/
|   |-- src/test/resources/serenity.conf
|   |-- build.gradle
|   |-- gradlew
|   `-- gradlew.bat
|-- playwright-tests/
|   |-- features/
|   |-- pages/
|   |-- questions/
|   |-- tasks/
|   |-- tests/
|   |-- conftest.py
|   |-- pytest.ini
|   `-- requirements.txt
|-- evidence/
|   `-- README.md
|-- Jenkinsfile
`-- README.md
```

## Escenarios automatizados

| # | Escenario | Herramienta | Feature |
| --- | --- | --- | --- |
| 1 | Compra completa exitosa | Serenity BDD + Java + Screenplay | `serenity-tests/src/test/resources/features/compra_exitosa.feature` |
| 2 | Inicio de sesion con usuario bloqueado | Serenity BDD + Java + Screenplay | `serenity-tests/src/test/resources/features/login_bloqueado.feature` |
| 3 | Validacion de precios | Playwright + Python + pytest-bdd | `playwright-tests/features/precios.feature` |
| 4 | Ordenamiento de productos | Playwright + Python + pytest-bdd | `playwright-tests/features/ordenamiento.feature` |

## Tecnologias

- Java 17 para Gradle/Serenity.
- Gradle Wrapper.
- Serenity BDD `5.3.9`.
- Cucumber `7.15.0`.
- Python 3.14.6 en el entorno local/Jenkins.
- Playwright `1.61.0`.
- pytest, pytest-bdd y pytest-html.
- Jenkins Pipeline.

## Ejecucion local de Serenity

```powershell
cd serenity-tests
.\gradlew.bat clean test aggregate
```

Reporte Serenity:

```text
serenity-tests/target/site/serenity/index.html
```

## Ejecucion local de Playwright

```powershell
cd playwright-tests
python -m venv .venv
.\.venv\Scripts\python -m pip install --upgrade pip
.\.venv\Scripts\python -m pip install -r requirements.txt
.\.venv\Scripts\python -m playwright install chromium
.\.venv\Scripts\python -m pytest
```

Reportes Playwright:

```text
playwright-tests/reports/playwright-report.html
playwright-tests/reports/junit-playwright.xml
```

## Jenkins

El pipeline esta definido en `Jenkinsfile` y ejecuta estas fases:

1. Checkout desde GitHub.
2. Validacion del entorno: Java, Gradle, Python y Git.
3. Preparacion y ejecucion de Serenity.
4. Preparacion y ejecucion de Playwright.
5. Validacion de reportes generados.
6. Publicacion de reportes HTML y archivo de artifacts.
7. Publicacion de resultados JUnit cuando estan disponibles.

El job Jenkins usado fue `playrigth-serenity-test-litthinking-cert2` y la ejecucion `#8` finalizo correctamente.

Plugins recomendados de Jenkins:

- HTML Publisher Plugin.
- JUnit Plugin.
- Git Plugin.

## Buenas practicas aplicadas

- Separacion entre Features, Steps, Tasks, Questions y Page Objects/Targets.
- Uso del patron Screenplay en Serenity.
- Locators robustos basados principalmente en `data-test`.
- No se usan XPath absolutos.
- No se usa `time.sleep`.
- Credenciales publicas de SauceDemo: `standard_user`, `locked_out_user` y `secret_sauce`.
- Gradle Wrapper versionado para no depender del Gradle global.
- Reportes HTML y artifacts publicados desde Jenkins.

## Problemas frecuentes

| Problema | Causa probable | Solucion |
| --- | --- | --- |
| `checkout scm` falla | Job configurado como `Pipeline script` manual | Usar `Pipeline script from SCM` o reemplazar por `git branch: 'main', url: ...` |
| Python no se reconoce en Jenkins | Jenkins corre como servicio y no ve el PATH del usuario | Usar `PYTHON_EXE` con ruta explicita en el `Jenkinsfile` |
| `GradleWrapperMain` no encontrado | Falta `gradle-wrapper.jar` | Versionar `serenity-tests/gradle/wrapper/gradle-wrapper.jar` |
| Playwright descarga navegadores | Primer build o cache vacio | Es normal; descarga Chromium, FFmpeg y Headless Shell |
| Reportes no aparecen | Tests no alcanzaron a ejecutarse o ruta incorrecta | Revisar stages previos y rutas en `publishHTML` |

## Limitaciones conocidas

- El `Jenkinsfile` esta orientado a agentes Windows por el uso de `bat`.
- La ruta `PYTHON_EXE` apunta al Python instalado en la maquina local de Jenkins.
- El primer build de Playwright puede tardar mas por descarga de navegadores.