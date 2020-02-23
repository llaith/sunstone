package org.llaith.sunstone.impl.rules.actions;

import org.jeasy.rules.api.Action;
import org.jeasy.rules.api.Facts;
import org.llaith.sunstone.impl.rules.OrderAmount;

/**
 *
 */
public class BlockProductAction implements Action {

    @Override
    public void execute(final Facts facts) {

        OrderAmount order = facts.get("order");

        order.setBlocked(true);

    }

}
