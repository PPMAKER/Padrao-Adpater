
import io.github.mainstringargs.yahooFinance.YahooFinanceData;
import io.github.mainstringargs.yahooFinance.YahooFinanceModules;
import io.github.mainstringargs.yahooFinance.YahooFinanceRequest;
import io.github.mainstringargs.yahooFinance.YahooFinanceUrlBuilder;
import io.github.mainstringargs.yahooFinance.domain.FinancialData;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ppmaker
 */
public class YahooAdapter implements AlvoServicoCotacao{
    private double preco = 0.0;
    
    @Override
    public double cotacao(String codigoEmpresa) {
        
        System.out.printf("Cotação da Empresa %s obtida pelo serviço Yahoo Finance: https://finance.yahoo.com%n", codigoEmpresa);
        YahooFinanceUrlBuilder builder =
                new YahooFinanceUrlBuilder().modules(YahooFinanceModules.values()).symbol(codigoEmpresa);

        YahooFinanceRequest request = new YahooFinanceRequest();
        YahooFinanceData financeData = request.getFinanceData(request.invoke(builder));

        FinancialData financials = financeData.getFinancialData();
        if (financials != null) {
            System.out.printf("Preço: %s %s%n", financials.getFinancialCurrency(), financials.getCurrentPrice().getRaw());
            preco = financials.getCurrentPrice().getRaw().doubleValue();
        }

        System.out.println(builder.getURL());
        System.out.println("https://query1.finance.yahoo.com/v8/finance/chart/"+codigoEmpresa+"?period1=1546311600&period2=1556593200&interval=1d&includePrePost=False");
        System.out.println("---------------------------------------------------------------------");
        return preco;
    }
    
}
