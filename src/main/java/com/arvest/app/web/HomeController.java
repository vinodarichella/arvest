package com.arvest.app.web;

import com.arvest.app.domain.CurrentPositions;
import com.arvest.app.domain.Portfolio;
import com.arvest.app.domain.Token;
import com.arvest.app.service.AuthenticationService;
import com.arvest.app.service.EmailService;
import com.arvest.app.service.PortfolioService;
import com.arvest.app.service.PositionsService;
import com.google.gson.Gson;
import org.apache.commons.math3.util.Precision;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    PositionsService positionsService;

    @Autowired
    PortfolioService portfolioService;

    @Autowired
    EmailService emailService;

    @RequestMapping(value = "/authenticate",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Token authenticate() {

        try {
            return authenticationService.authenticate();
        } catch (Exception e) {
            e.printStackTrace();
            return new Token().setToken("Error");
        }
    }

    @RequestMapping(value = "/positions",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<CurrentPositions> getCurrentPositions() {
        List<CurrentPositions> currentPoistions = null;

        Token token = authenticate();
        try {
            Portfolio portfolio = getPortfolio(token);
            currentPoistions = positionsService.getCurrentPoistions(token, portfolio);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return currentPoistions;
    }

    private Portfolio getPortfolio(Token token) {
        if (token == null || token.getToken() == null) {
            token = authenticate();
        }
        return portfolioService.getPortfolio(token);
    }

    @RequestMapping(value = "/performance",
            method = RequestMethod.GET
    )
    public String getPortfolioPerformance(Map<String, Object> model) {
        CurrentPositions currentPositions = new CurrentPositions();
        List<CurrentPositions> currentPositionsList = getCurrentPositions();
        if (currentPositionsList != null) {
            Double profit = currentPositionsList.stream()
                    .map(p -> p.getProfitOrLoss())
                    .reduce(0.0, (x, y) -> x + y);

            Double totalBuyPrice = currentPositionsList.stream()
                    .map(p -> p.getAverageBuyPrice() * p.getQuantity())
                    .reduce(0.0, (x, y) -> x + y);

            Double currentValue = currentPositionsList.stream()
                    .map(p -> p.getTotalValue())
                    .reduce(0.0, (x,y) -> x+y);

            currentPositions.setProfitOrLoss(Precision.round(profit, 2));
            currentPositions.setPercent(100.00);
            currentPositions.setStockName("All Stocks");
            currentPositions.setTotalValue(Precision.round(getPortfolio(null).getMarket_value(), 2));
            currentPositions.setCurrentValue(currentValue);
            currentPositions.setAverageBuyPrice(Precision.round(totalBuyPrice, 2));
        }

//        mod map = new HashMap();
        model.put("currentPositions", currentPositions);
        model.put("currentPositionsList", currentPositionsList);
//        try {
//            emailService.sendHtmlEmail(model, "performance", "Performance Report");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return "performance";
    }
}
