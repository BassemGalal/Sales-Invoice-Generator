package Model;
public class InvoiceLine {
    private int InvoiceLineNumber;
    private String ItemName;
    private double ItemPrice;
    private double Count;
    private double ItemTotal;
    private static int id;
    public InvoiceLine(int invoiceLineNumber, String itemName, double itemPrice, double count) {
        InvoiceLineNumber = invoiceLineNumber;
        id=invoiceLineNumber;
        ItemName = itemName;
        ItemPrice = itemPrice;
        Count = count;
        ItemTotal = count*itemPrice;
    }
    public int getInvoiceLineNumber() {
        return InvoiceLineNumber;
    }
    public void setInvoiceLineNumber(int invoiceLineNumber) {
        InvoiceLineNumber = invoiceLineNumber;
    }
    public String getItemName() {
        return ItemName;
    }
    public void setItemName(String itemName) {
        ItemName = itemName;
    }
    public double getCount() {
        return Count;
    }
    public void setCount(double count) {
        Count = count;
    }
    public double getItemTotal() {
        return ItemTotal;
    }
    public void setItemTotal(double itemTotal) {
        ItemTotal = itemTotal;
    }
    public double getItemPrice() {
        return ItemPrice;
    }
    public static int getSelectedRow() {
        return 0;
    }

    public void setItemPrice(double itemPrice) {
        ItemPrice = itemPrice;
    }
    @Override
    public String toString() {
        return "InvoiceLine{" +
                "InvoiceLineNumber=" + InvoiceLineNumber +
                ", ItemName='" + ItemName + '\'' +
                ", ItemPrice=" + ItemPrice +
                ", Count=" + Count +
                ", ItemTotal=" + ItemTotal +
                '}';
    }
}