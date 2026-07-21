class InventoryPage:
    def __init__(self, page):
        self.page = page
        self.title = page.locator("[data-test='title']")
        self.product_cards = page.locator("[data-test='inventory-item']")
        self.sort_select = page.locator("[data-test='product-sort-container']")
        self.cart_link = page.locator("[data-test='shopping-cart-link']")

    def wait_until_loaded(self):
        self.title.wait_for()

    def first_product_data(self) -> dict:
        first_card = self.product_cards.first
        return {
            "name": first_card.locator("[data-test='inventory-item-name']").inner_text(),
            "price": first_card.locator("[data-test='inventory-item-price']").inner_text(),
        }

    def add_first_product_to_cart(self):
        self.product_cards.first.locator("button", has_text="Add to cart").click()
        self.cart_link.click()

    def sort_by_price_low_to_high(self):
        self.sort_select.select_option("lohi")

    def visible_prices(self) -> list[float]:
        prices = self.page.locator("[data-test='inventory-item-price']").all_inner_texts()
        return [float(price.replace("$", "")) for price in prices]
