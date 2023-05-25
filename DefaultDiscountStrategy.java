public class DefaultDiscountStrategy implements DiscountStrategy {
    @Override
    public double calculateDiscount(double totalPrice) {
        return totalPrice;
    }
}


