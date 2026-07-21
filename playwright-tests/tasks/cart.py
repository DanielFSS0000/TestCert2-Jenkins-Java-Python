from pages.inventory_page import InventoryPage


def capture_first_product_and_add_to_cart(page) -> dict:
    inventory = InventoryPage(page)
    inventory.wait_until_loaded()
    product = inventory.first_product_data()
    inventory.add_first_product_to_cart()
    return product
