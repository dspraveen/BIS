package com.bis.web.controller;

import com.bis.common.DateUtils;
import com.bis.core.services.ItemMasterService;
import com.bis.domain.ItemCycle;
import com.bis.domain.Stock;
import com.bis.inventory.services.StockService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/inventory")
public class InventoryController {

    protected final Logger logger = Logger.getLogger(getClass());

    private StockService stockService;
    private ItemMasterService itemMasterService;
    private Stock stocksService;

    protected InventoryController() {
    }

    @Autowired
    public InventoryController(StockService stockService) {
        this.stockService = stockService;
    }

    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public ModelAndView show() {
        List<Stock> stocks = stockService.getAllStock();
        return new ModelAndView("inventory/show", "stocks", stocks);
    }

    @RequestMapping(value = "/showExpiredStock", method = RequestMethod.GET)
    public ModelAndView showExpiredStock() {
        List<Stock> expiredStock = new ArrayList<Stock>();
        for (Stock stock : stockService.getAllStock()) {
            if (stock.getItem().getItemLife() == ItemCycle.WEEKLY.getCode()) {
                Date nextDateOfPublish = DateUtils.addDays(stock.getDateOfPublishing(), 7);
                if (DateUtils.isGreaterOrEqual(DateUtils.currentDate(), nextDateOfPublish)) {
                    expiredStock.add(stock);
                }
            } else if (stock.getItem().getItemLife() == ItemCycle.FORTNIGHT.getCode()) {
                Date nextDateOfPublish = DateUtils.addDays(stock.getDateOfPublishing(), 15);
                if (DateUtils.isGreaterOrEqual(DateUtils.currentDate(), nextDateOfPublish)) {
                    expiredStock.add(stock);
                }
            } else if (stock.getItem().getItemLife() == ItemCycle.MONTHLY.getCode()) {
                Date nextDateOfPublish = DateUtils.addMonth(stock.getDateOfPublishing(),1);
                if (DateUtils.isGreaterOrEqual(DateUtils.currentDate(), nextDateOfPublish)) {
                    expiredStock.add(stock);
                }
            }
        }
        return new ModelAndView("inventory/showExpiredStock", "stocks", expiredStock);
    }

}
