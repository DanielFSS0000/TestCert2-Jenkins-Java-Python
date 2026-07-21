from pages.cart_page import CartPage


def cart_product_matches(page, expected_product: dict) -> bool:
    actual_product = CartPage(page).product_data()
    return actual_product == expected_product
