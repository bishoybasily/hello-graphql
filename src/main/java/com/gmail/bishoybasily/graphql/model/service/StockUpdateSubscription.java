package com.gmail.bishoybasily.graphql.model.service;

import com.coxautodev.graphql.tools.GraphQLSubscriptionResolver;
import com.gmail.bishoybasily.graphql.model.entity.StockPriceUpdate;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;


@Component
class StockUpdateSubscription implements GraphQLSubscriptionResolver {

    private static final Logger LOG = LoggerFactory.getLogger(StockUpdateSubscription.class);
    private final static Map<String, BigDecimal> CURRENT_STOCK_PRICES = new ConcurrentHashMap<>();
    private final static Random rand = new Random();

    static {
        CURRENT_STOCK_PRICES.put("TEAM", dollars(39, 64));
        CURRENT_STOCK_PRICES.put("IBM", dollars(147, 10));
        CURRENT_STOCK_PRICES.put("AMZN", dollars(1002, 94));
        CURRENT_STOCK_PRICES.put("MSFT", dollars(77, 49));
        CURRENT_STOCK_PRICES.put("GOOGL", dollars(1007, 87));
    }

    private final Flux<StockPriceUpdate> flux;

    public StockUpdateSubscription() {
        flux = Flux.create((Consumer<FluxSink<StockPriceUpdate>>) sink -> {
            ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
            executorService.scheduleAtFixedRate(newStockTickPR(sink), 0, 2, TimeUnit.SECONDS);
        }, FluxSink.OverflowStrategy.BUFFER).share();
    }

    private static BigDecimal dollars(int dollars, int cents) {
        return truncate("" + dollars + "." + cents);
    }

    private static BigDecimal truncate(final String text) {
        BigDecimal bigDecimal = new BigDecimal(text);
        if (bigDecimal.scale() > 2)
            bigDecimal = new BigDecimal(text).setScale(2, RoundingMode.HALF_UP);
        return bigDecimal.stripTrailingZeros();
    }

    private static int rollDice(int min, int max) {
        return rand.nextInt((max - min) + 1) + min;
    }

    //    @PreAuthorize("hasAnyAuthority('USER')")
    public Publisher<StockPriceUpdate> stockQuotes(List<String> stockCodes) {
        if (stockCodes != null)
            return flux.filter(stockPriceUpdate -> stockCodes.contains(stockPriceUpdate.getStockCode()));
        return flux;
    }

    private Runnable newStockTickPR(FluxSink<StockPriceUpdate> sink) {
        return () -> {
            List<StockPriceUpdate> stockPriceUpdates = getUpdates(rollDice(0, 5));
            if (stockPriceUpdates != null) {
                for (StockPriceUpdate stockPriceUpdate : stockPriceUpdates) {
                    try {
                        sink.next(stockPriceUpdate);
                    } catch (RuntimeException e) {
                        LOG.error("Cannot send StockUpdate", e);
                    }
                }
            }
        };
    }

    private List<StockPriceUpdate> getUpdates(int number) {
        List<StockPriceUpdate> updates = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            updates.add(rollUpdate());
        }
        return updates;
    }

    private StockPriceUpdate rollUpdate() {
        ArrayList<String> STOCK_CODES = new ArrayList<>(CURRENT_STOCK_PRICES.keySet());

        String stockCode = STOCK_CODES.get(rollDice(0, STOCK_CODES.size() - 1));
        BigDecimal currentPrice = CURRENT_STOCK_PRICES.get(stockCode);


        BigDecimal incrementDollars = dollars(rollDice(0, 1), rollDice(0, 99));
        if (rollDice(0, 10) > 7) {
            // 0.3 of the time go down
            incrementDollars = incrementDollars.negate();
        }
        BigDecimal newPrice = currentPrice.add(incrementDollars);

        CURRENT_STOCK_PRICES.put(stockCode, newPrice);
        return new StockPriceUpdate(stockCode, LocalDateTime.now(), newPrice, incrementDollars);
    }

}
