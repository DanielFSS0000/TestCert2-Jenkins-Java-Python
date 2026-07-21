pipeline {
    agent any

    options {
        timestamps()
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
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
                    bat 'python -m venv .venv'
                    bat '.\\.venv\\Scripts\\python -m pip install --upgrade pip'
                    bat '.\\.venv\\Scripts\\pip install -r requirements.txt'
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
