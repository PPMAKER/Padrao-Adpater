
import com.jimmoores.quandl.DataSetRequest;
import com.jimmoores.quandl.Row;
import com.jimmoores.quandl.TabularResult;
import com.jimmoores.quandl.classic.ClassicQuandlSession;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author ppmaker
 */
public class QuandlAdapter implements AlvoServicoCotacao{
    private double preco = 0.0;

    @Override
    public double cotacao(String codigoEmpresa) {
        System.out.printf("Cotação da Empresa %s obtida pelo serviço Quandl: http://quandl.com/%n", codigoEmpresa);
        ClassicQuandlSession session = ClassicQuandlSession.create();
        DataSetRequest request = DataSetRequest.Builder
                                        .of(codigoEmpresa)
                                        .withMaxRows(1)
                                        .build();
        TabularResult result = session.getDataSet(request);
        if(result.size() > 0) {
            Row row = result.get(0);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String date = formatter.format(row.getLocalDate("Date"));
            System.out.printf("Data: %s Preço: %s%n", date, row.getDouble("Close"));
            //System.out.println(result.toPrettyPrintedString());
            preco = row.getDouble("Close");
        }
        System.out.println("---------------------------------------------------------------------");
        return preco;
        
    }
    
}
