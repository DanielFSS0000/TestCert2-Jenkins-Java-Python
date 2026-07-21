pipeline {
    agent any

    environment {
        PYTHON_EXE = 'C:\\Users\\danie\\AppData\\Local\\Python\\bin\\python.exe'
    }

    options {
        timestamps()
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }


        stage('Validar entorno') {
            steps {
                bat 'java -version'
                bat '.\\serenity-tests\\gradlew.bat --version'
                bat '"%PYTHON_EXE%" --version'
                bat 'where git'
            }
        }
        stage('Preparacion Serenity') {
            steps {
                dir('serenity-tests') {
                    bat '.\\gradlew.bat --version'
                }
            }
        }

        stage('Ejecucion Serenity') {
            steps {
                catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                    dir('serenity-tests') {
                        bat '.\\gradlew.bat clean test aggregate'
                    }
                }
            }
        }

        stage('Preparacion Python y Playwright') {
            steps {
                dir('playwright-tests') {
                    bat '"%PYTHON_EXE%" -m venv .venv'
                    bat '.\\.venv\\Scripts\\python -m pip install --upgrade pip'
                    bat '.\\.venv\\Scripts\\python -m pip install -r requirements.txt'
                    bat '.\\.venv\\Scripts\\python -m playwright install chromium'
                }
            }
        }

        stage('Ejecucion Playwright') {
            steps {
                catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                    dir('playwright-tests') {
                        bat '.\\.venv\\Scripts\\python -m pytest'
                    }
                }
            }
        }
        stage('Validar reportes') {
            steps {
                bat 'if exist serenity-tests\\target\\site\\serenity\\index.html (echo Reporte Serenity encontrado) else (echo Reporte Serenity no encontrado)'
                bat 'if exist playwright-tests\\reports\\playwright-report.html (echo Reporte Playwright encontrado) else (echo Reporte Playwright no encontrado)'
                bat 'if exist playwright-tests\\reports\\junit-playwright.xml (echo JUnit Playwright encontrado) else (echo JUnit Playwright no encontrado)'
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'serenity-tests/target/site/serenity/**', allowEmptyArchive: true, fingerprint: true
            archiveArtifacts artifacts: 'playwright-tests/reports/**', allowEmptyArchive: true, fingerprint: true
            archiveArtifacts artifacts: 'playwright-tests/screenshots/**', allowEmptyArchive: true, fingerprint: true

            publishHTML([
                reportDir: 'serenity-tests/target/site/serenity',
                reportFiles: 'index.html',
                reportName: 'Reporte Serenity',
                keepAll: true,
                alwaysLinkToLastBuild: true,
                allowMissing: true
            ])

            publishHTML([
                reportDir: 'playwright-tests/reports',
                reportFiles: 'playwright-report.html',
                reportName: 'Reporte Playwright pytest',
                keepAll: true,
                alwaysLinkToLastBuild: true,
                allowMissing: true
            ])

            junit allowEmptyResults: true, testResults: 'playwright-tests/reports/junit-playwright.xml'
            junit allowEmptyResults: true, testResults: 'serenity-tests/target/test-results/**/*.xml'
        }
    }
}
