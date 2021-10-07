import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Transaction {
    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yy");

    private String accountType;
    private String accountNumber;
    private String currency;
    private Date date;
    private String code;
    private String transactionInfo;
    private double income;
    private double expenses;
    private String organization;



    public Transaction(String[] transactionInfo) throws ParseException {

        this.accountType = transactionInfo[0];
        this.accountNumber = transactionInfo[1];
        this.currency = transactionInfo[2];
        this.date = dateFormat.parse(transactionInfo[3]);
        this.code = transactionInfo[4];
        this.transactionInfo = transactionInfo[5];
        this.income = Double.parseDouble(transactionInfo[6]);
        this.expenses = Double.parseDouble(transactionInfo[7]);
        String substring = transactionInfo[5];
        Pattern pattern = Pattern.compile("[0-9]{2}[.][0-9]{2}[.][0-9]{2}");
        Matcher matcher = pattern.matcher(substring);
        if (matcher.find()) {
            int start = matcher.start();
            substring = substring.substring(0,start);
            if (substring.contains("/")) {
                substring = substring.substring(substring.lastIndexOf('/') + 1).trim();
                this.organization = substring;
            }
            if (substring.contains("\\")) {
                substring = substring.substring(substring.lastIndexOf("\\") + 1).trim();
                this.organization = substring;
            }
        }
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

    public String getTransactionInfo() {
        return transactionInfo;
    }

    public double getIncome() {
        return income;
    }

    public double getExpenses() {
        return expenses;
    }

    public String getOrganization() {return organization;}
}
