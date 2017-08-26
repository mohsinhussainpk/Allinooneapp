package io.branch.referral.util;


/**
 * <p>
 * Branch Defined user events. Please see {@link io.branch.referral.Branch#userCompletedAction(String)} and
 * {@link io.branch.indexing.BranchUniversalObject#userCompletedAction(String)}
 * </p>
 */
public class BranchEvent {
    public static final String VIEW = "View";
    public static final String ADD_TO_WISH_LIST = "Add to Wishlist";
    public static final String ADD_TO_CART = "Add to Cart";
    public static final String PURCHASE_STARTED = "Purchase Started";
    public static final String PURCHASED = "Purchased";
    public static final String SHARE_STARTED = "Share Started";
    public static final String SHARE_COMPLETED = "Share Completed";
    public static final String CANONICAL_ID_LIST = "$canonical_identifier_list";
    public static final String PURCHASE_AMOUNT = "$amount";
    public static final String PURCHASE_CURRENCY = "$currency";
}
