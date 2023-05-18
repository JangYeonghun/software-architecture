public class GroupDiscountStrategy implements DiscountStrategy {
    private static final double GROUP_DISCOUNT_PERCENTAGE = 0.1;

    @Override
    public double calculateDiscount(double totalPrice) {
        return totalPrice * (1 - GROUP_DISCOUNT_PERCENTAGE);
    }
}