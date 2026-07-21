from pages.inventory_page import InventoryPage


def sort_products_by_price_low_to_high(page):
    inventory = InventoryPage(page)
    inventory.wait_until_loaded()
    inventory.sort_by_price_low_to_high()
