from pages.login_page import LoginPage


def login_as_standard_user(page):
    LoginPage(page).login("standard_user", "secret_sauce")
