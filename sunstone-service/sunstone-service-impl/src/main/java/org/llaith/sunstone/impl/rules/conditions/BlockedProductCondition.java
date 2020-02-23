package org.llaith.sunstone.impl.rules.conditions;

import org.jeasy.rules.api.Condition;
import org.jeasy.rules.api.Facts;
import org.llaith.sunstone.api.service.ordering.transfer.ProductConfig;

/**
 *
 */
public class BlockedProductCondition implements Condition {

    public static BlockedProductCondition IsBlockedProduct() {
        return new BlockedProductCondition();
    }

    @Override
    public boolean evaluate(final Facts facts) {

        final ProductConfig config = facts.get("config");

        return config.isBlocked();

    }

}
