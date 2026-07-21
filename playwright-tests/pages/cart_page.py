class CartPage:
    def __init__(self, page):
        self.page = page
        self.cart_item = page.locator("[data-test='inventory-item']").first

    def product_data(self) -> dict:
        return {
            "name": self.cart_item.locator("[data-test='inventory-item-name']").inner_text(),
            "price": self.cart_item.locator("[data-test='inventory-item-price']").inner_text(),
        }
