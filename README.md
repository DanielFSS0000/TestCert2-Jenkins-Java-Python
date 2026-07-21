# Reto Final - SauceDemo E2E con Serenity, Playwright y Jenkins

## Objetivo

Este repositorio implementa una estrategia de automatizaciÃƒÂ³n End-to-End sobre [SauceDemo](https://www.saucedemo.com/) usando dos enfoques complementarios:

- **Serenity BDD + Java + Screenplay + Cucumber** para escenarios de compra y login.
- **Playwright + Python + pytest-bdd** para validaciones dinÃƒÂ¡micas de precios y ordenamiento.

La soluciÃƒÂ³n incluye reportes automÃƒÂ¡ticos, screenshots ante fallos y un `Jenkinsfile` declarativo para integraciÃƒÂ³n continua.

## Arquitectura

```text
.
Ã¢â€Å“Ã¢â€â‚¬Ã¢â€â‚¬ serenity-tests/
Ã¢â€â€š   Ã¢â€Å“Ã¢â€â‚¬Ã¢â€â‚¬ src/test/java/com/saucedemo/
Ã¢â€â€š   Ã¢â€â€š   Ã¢â€Å“Ã¢â€â‚¬Ã¢â€â‚¬ questions/
Ã¢â€â€š   Ã¢â€â€š   Ã¢â€Å“Ã¢â€â‚¬Ã¢â€â‚¬ runner/
Ã¢â€â€š   Ã¢â€â€š   Ã¢â€Å“Ã¢â€â‚¬Ã¢â€â‚¬ stepdefinitions/
Ã¢â€â€š   Ã¢â€â€š   Ã¢â€Å“Ã¢â€â‚¬Ã¢â€â‚¬ tasks/
Ã¢â€â€š   Ã¢â€â€š   Ã¢â€â€Ã¢â€â‚¬Ã¢â€â‚¬ ui/
Ã¢â€â€š   Ã¢â€Å“Ã¢â€â‚¬Ã¢â€â‚¬ src/test/resources/features/
Ã¢â€â€š   Ã¢â€Å“Ã¢â€â‚¬Ã¢â€â‚¬ src/test/resources/serenity.conf
Ã¢â€â€š   Ã¢â€â€Ã¢â€â‚¬Ã¢â€â‚¬ build.gradle
Ã¢â€Å“Ã¢â€â‚¬Ã¢â€â‚¬ playwright-tests/
Ã¢â€â€š   Ã¢â€Å“Ã¢â€â‚¬Ã¢â€â‚¬ features/
Ã¢â€â€š   Ã¢â€Å“Ã¢â€â‚¬Ã¢â€â‚¬ pages/
Ã¢â€â€š   Ã¢â€Å“Ã¢â€â‚¬Ã¢â€â‚¬ questions/
Ã¢â€â€š   Ã¢â€Å“Ã¢â€â‚¬Ã¢â€â‚¬ tasks/
Ã¢â€â€š   Ã¢â€Å“Ã¢â€â‚¬Ã¢â€â‚¬ tests/
Ã¢â€â€š   Ã¢â€Å“Ã¢â€â‚¬Ã¢â€â‚¬ conftest.py
Ã¢â€â€š   Ã¢â€Å“Ã¢â€â‚¬Ã¢â€â‚¬ pytest.ini
Ã¢â€â€š   Ã¢â€â€Ã¢â€â‚¬Ã¢â€â‚¬ requirements.txt
Ã¢â€Å“Ã¢â€â‚¬Ã¢â€â‚¬ evidence/
Ã¢â€â€š   Ã¢â€â€Ã¢â€â‚¬Ã¢â€â‚¬ README.md
Ã¢â€Å“Ã¢â€â‚¬Ã¢â€â‚¬ Jenkinsfile
Ã¢â€â€Ã¢â€â‚¬Ã¢â€â‚¬ README.md
```

## DistribuciÃƒÂ³n de escenarios

| Escenario | Herramienta | Feature |
| --- | --- | --- |
| Compra completa exitosa | Serenity BDD + Java + Screenplay | `serenity-tests/src/test/resources/features/compra_exitosa.feature` |
| Inicio de sesiÃƒÂ³n con usuario bloqueado | Serenity BDD + Java + Screenplay | `serenity-tests/src/test/resources/features/login_bloqueado.feature` |
| ValidaciÃƒÂ³n de precios | Playwright + Python + pytest-bdd | `playwright-tests/features/precios.feature` |
| Ordenamiento de productos | Playwright + Python + pytest-bdd | `playwright-tests/features/ordenamiento.feature` |

## TecnologÃƒÂ­as

- Java 17 recomendado.
- Gradle con wrapper.
- Serenity BDD `5.3.9`.
- Cucumber `7.15.0`.
- Python 3.10+ recomendado.
- Playwright para Python.
- pytest, pytest-bdd y pytest-html.
- Jenkins Pipeline.

## EjecuciÃƒÂ³n local de Serenity

```powershell
cd serenity-tests
.\gradlew.bat clean test aggregate
```

Reporte: `serenity-tests/target/site/serenity/index.html`

## EjecuciÃƒÂ³n local de Playwright

```powershell
cd playwright-tests
python -m venv .venv
.\.venv\Scripts\python -m pip install --upgrade pip
.\.venv\Scripts\pip install -r requirements.txt
.\.venv\Scripts\python -m playwright install chromium
.\.venv\Scripts\python -m pytest
```

Reporte: `playwright-tests/reports/playwright-report.html`

## Jenkins

El pipeline estÃƒÂ¡ definido en `Jenkinsfile` y contiene checkout, preparaciÃƒÂ³n de Serenity, ejecuciÃƒÂ³n de Serenity, preparaciÃƒÂ³n de Python/Playwright, ejecuciÃƒÂ³n de Playwright y archivo/publicaciÃƒÂ³n de evidencias.

Requiere el plugin **HTML Publisher** para publicar reportes HTML. Los reportes tambiÃƒÂ©n se conservan con `archiveArtifacts`.

## Buenas prÃƒÂ¡cticas aplicadas

- SeparaciÃƒÂ³n entre Gherkin, Steps, Tasks, Questions y Page Objects/Targets.
- Selectores robustos basados en `data-test`.
- No se usan XPath absolutos.
- No se usa `time.sleep`.
- Las credenciales usadas son pÃƒÂºblicas de SauceDemo.
- UTF-8 configurado para evitar errores con tildes en features.
- Screenshots automÃƒÂ¡ticos ante fallo.

## Problemas frecuentes

| Problema | Causa probable | SoluciÃƒÂ³n |
| --- | --- | --- |
| `GradleWrapperMain` no encontrado | Falta `gradle-wrapper.jar` | Ejecutar `gradle wrapper` o versionar `gradle/wrapper/gradle-wrapper.jar` |
| Steps no definidos con textos raros como `pÃƒÆ’Ã‚Â¡gina` | Encoding incorrecto | Revisar UTF-8 en `build.gradle` |
| Playwright no encuentra Chromium | Navegador no instalado | Ejecutar `python -m playwright install chromium` |
| Jenkins no publica HTML | Falta plugin | Instalar **HTML Publisher Plugin** |
| Tests viejos aparecen en Jenkins | Workspace sucio | Limpiar workspace o usar checkout limpio |

## Limitaciones conocidas

- El `Jenkinsfile` estÃƒÂ¡ orientado a agentes Windows por el uso de `bat`.
- Las evidencias reales deben completarse despuÃƒÂ©s de ejecutar localmente o en Jenkins.
- No se incluyen capturas inventadas ni resultados manuales.

