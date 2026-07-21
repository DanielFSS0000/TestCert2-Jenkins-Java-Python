from pathlib import Path

import pytest
from playwright.sync_api import sync_playwright


BASE_URL = "https://www.saucedemo.com/"


@pytest.fixture(scope="session")
def browser():
    with sync_playwright() as playwright:
        browser_instance = playwright.chromium.launch(headless=True)
        yield browser_instance
        browser_instance.close()


@pytest.fixture()
def context(browser):
    context_instance = browser.new_context(viewport={"width": 1920, "height": 1080})
    yield context_instance
    context_instance.close()


@pytest.fixture()
def page(context):
    page_instance = context.new_page()
    page_instance.goto(BASE_URL)
    return page_instance


@pytest.hookimpl(hookwrapper=True)
def pytest_runtest_makereport(item, call):
    outcome = yield
    report = outcome.get_result()
    page = item.funcargs.get("page")

    if report.when == "call" and report.failed and page:
        screenshots_dir = Path("screenshots")
        screenshots_dir.mkdir(exist_ok=True)
        screenshot_path = screenshots_dir / f"{item.name}.png"
        page.screenshot(path=str(screenshot_path), full_page=True)
