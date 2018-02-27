package com.arvest.app.domain;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import org.assertj.core.util.Arrays;
import org.junit.Test;

import java.time.ZonedDateTime;
import java.util.Date;

import static org.junit.Assert.*;


public class PositionsTest {
    @Test
    public void testPositions() {
        Positions positions = new Positions();

        Position position = new Position();
        position.setAccount("abce");
        position.setAverage_buy_price(1.2);
        position.setCreated_at(ZonedDateTime.now().toString());
        position.setUpdated_at(ZonedDateTime.now().toString());
        position.setShares_held_for_buys(2.0);
        position.setQuantity(1.0);

        Position position1 = new Position();
        position1.setAccount("abce");
        position1.setAverage_buy_price(1.2);
        position1.setCreated_at(ZonedDateTime.now().toString());
        position1.setUpdated_at(ZonedDateTime.now().toString());
        position1.setShares_held_for_buys(2.0);
        position1.setQuantity(1.0);

        positions.setNext(null);
        positions.setPrevious(null);
        positions.setResults(Arrays.array(position, position1));
        Gson gson = new Gson();
        System.out.println(gson.toJson(positions));

    }
}