package Model;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
public class InvoiceHeader {
    private int InvoiceHeaderNumber;
    private Date date1;
    private String CustomerName;
    private double Total;
    private static int id;
    private ArrayList<InvoiceLine>InvoiceLines=new ArrayList<>();
    public InvoiceHeader(int invoiceHeaderNumber, Date date1, String customerName) {
        InvoiceHeaderNumber = invoiceHeaderNumber;
        id =invoiceHeaderNumber;
        this.date1 = date1;
        CustomerName = customerName;
    }
    public InvoiceHeader(String date1, String customerName) {

        try {
           this.date1 = new SimpleDateFormat("dd-MM-yyyy").parse(date1);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        CustomerName = customerName;
        id++;
        InvoiceHeaderNumber =id;
    }
    public InvoiceHeader(int noInvoice, Date date, String customerName, double total) {
        InvoiceHeaderNumber = noInvoice;
        this.date1 = date;
        CustomerName = customerName;
        Total = total;

    }

    public static int getSelectedRow() {
        return 0;
    }

    public int getNoInvoice() {
        return InvoiceHeaderNumber;
    }
    public void setNoInvoice(int noInvoice) {
        InvoiceHeaderNumber = noInvoice;
    }
    public Date getDate() {
        return date1;
    }
    public void setDate(Date date) {
        this.date1 = date;
    }
    public String getCustomerName() {
        return CustomerName;
    }
    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }
    public double getTotal() {
        return Total;
    }
    public void setTotal(double total) {
        Total = total;
    }
    public int getInvoiceHeaderNumber() {
        return InvoiceHeaderNumber;
    }
    public ArrayList<InvoiceLine> getInvoiceLines() {
        return InvoiceLines;
    }
    public void addInvoiceLine(InvoiceLine line)
    {
        InvoiceLines.add(line);
    }
    public void setInvoiceHeaderNumber(int invoiceHeaderNumber) {
        InvoiceHeaderNumber = invoiceHeaderNumber;
    }
    @Override
    public String toString() {
        return "InvoiceHeader{" +
                "NoInvoice=" + InvoiceHeaderNumber +
                ", date=" + date1 +
                ", CustomerName='" + CustomerName + '\'' +
                ", Total=" + Total +
                '}';
    }
}