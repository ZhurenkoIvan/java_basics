import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.DoubleAccumulator;

public class Transaction {
    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yy");

    private String accountType;
    private String accountNumber;
    private String currency;
    private Date date;
    private String code;
    private String operationInfo;
    private double income;
    private double expenses;

    public Transaction(String[] transactionInfo) throws ParseException {

        this.accountType = transactionInfo[0];
        this.accountNumber = transactionInfo[1];
        this.currency = transactionInfo[2];
        this.date = dateFormat.parse(transactionInfo[3]);
        this.code = transactionInfo[4];
        this.operationInfo = transactionInfo[5];
        this.income = Double.valueOf(transactionInfo[6]);
        this.expenses = Double.valueOf(transactionInfo[7]);
    }

    public String getAccountType() {
        return accountType;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getCurrency() {
        return currency;
    }

    public Date getDate() {
        return date;
    }

    public String getCode() {
        return code;
    }

    public String getOperationInfo() {
        return operationInfo;
    }

    public double getIncome() {
        return income;
    }

    public double getExpenses() {
        return expenses;
    }
}
