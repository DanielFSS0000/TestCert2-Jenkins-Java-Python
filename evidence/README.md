# Evidencias del reto

Documento de evidencias reales generadas durante la validacion local y en Jenkins.

## Informacion de ejecucion

| Campo | Valor |
| --- | --- |
| Fecha local | 2026-07-20 |
| Fecha Jenkins | 2026-07-21 |
| Equipo / agente | Windows local / Jenkins local |
| Java usado por Gradle | `JAVA_HOME=C:\Program Files\Java\jdk-17` |
| Python | Python 3.14.6 |
| Navegador Serenity | Chrome headless |
| Navegador Playwright | Chromium instalado por Playwright |
| Job Jenkins | `playrigth-serenity-test-litthinking-cert2` |
| Build Jenkins exitoso | `#8` |

## Escenarios

| # | Escenario | Framework | Feature | Resultado |
| --- | --- | --- | --- | --- |
| 1 | Compra completa exitosa | Serenity | `../serenity-tests/src/test/resources/features/compra_exitosa.feature` | PASSED |
| 2 | Inicio de sesion con usuario bloqueado | Serenity | `../serenity-tests/src/test/resources/features/login_bloqueado.feature` | PASSED |
| 3 | Validacion de precios | Playwright | `../playwright-tests/features/precios.feature` | PASSED |
| 4 | Ordenamiento de productos | Playwright | `../playwright-tests/features/ordenamiento.feature` | PASSED |

## Comandos ejecutados

```powershell
# Serenity
cd serenity-tests
.\gradlew.bat clean test aggregate

# Playwright
cd playwright-tests
python -m venv .venv
.\.venv\Scripts\python -m pip install --upgrade pip
.\.venv\Scripts\python -m pip install -r requirements.txt
.\.venv\Scripts\python -m playwright install chromium
.\.venv\Scripts\python -m pytest
```

## Resultados reales

| Suite | Resultado |
| --- | --- |
| Serenity local | `BUILD SUCCESSFUL`, 2 escenarios ejecutados correctamente |
| Playwright local | `2 passed` |
| Jenkins | Build `#8` finalizado correctamente, sin fallas de tests |

## Reportes generados

| Tipo | Ruta |
| --- | --- |
| Serenity HTML | `../serenity-tests/target/site/serenity/index.html` |
| pytest HTML | `../playwright-tests/reports/playwright-report.html` |
| pytest JUnit XML | `../playwright-tests/reports/junit-playwright.xml` |
| Jenkins Serenity | Link `Reporte Serenity` en el job |
| Jenkins Playwright | Link `Reporte Playwright pytest` en el job |
| Jenkins JUnit | Link `Resultado de los tests` en el job |

## Screenshots

No se generaron screenshots de fallo en la ejecucion final porque los cuatro escenarios pasaron. El mecanismo queda configurado para generar evidencias ante fallos en:

| Framework | Ruta |
| --- | --- |
| Serenity | `../serenity-tests/target/site/serenity/` |
| Playwright | `../playwright-tests/screenshots/` |

## Conclusiones

- Los cuatro escenarios requeridos existen y pasaron.
- Dos escenarios fueron implementados con Serenity BDD, Java, Cucumber y Screenplay.
- Dos escenarios fueron implementados con Playwright, Python, pytest y pytest-bdd.
- Jenkins ejecuta ambas suites, valida entorno, publica reportes y conserva artifacts.