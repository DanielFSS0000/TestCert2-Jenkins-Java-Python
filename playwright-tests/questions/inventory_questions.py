from pages.inventory_page import InventoryPage


def prices_are_sorted_ascending(page) -> bool:
    prices = InventoryPage(page).visible_prices()
    return prices == sorted(prices)
