package View;
import Model.InvoiceHeader;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
public class InvoiceHeaderTableModel extends AbstractTableModel {
    private ArrayList<InvoiceHeader> inhed =new ArrayList<>();
    private String[] cols={"No.","Date","Customer Name"};
    public ArrayList<InvoiceHeader> getInhed() {
        return inhed;
    }
    public void setInhed(ArrayList<InvoiceHeader> inhed) {
        this.inhed = inhed;
    }
    public InvoiceHeaderTableModel(){}
    public InvoiceHeaderTableModel(ArrayList<InvoiceHeader> inhed) {
        System.out.println("xswsdqd");
        this.inhed = inhed;
    }
    @Override
    public int getRowCount() {
        return inhed.size();
    }
    @Override
    public int getColumnCount() {
        return cols.length;
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        InvoiceHeader invoiceHeader=inhed.get(rowIndex);
        switch (columnIndex)
        {
            case 0 :
                return invoiceHeader.getInvoiceHeaderNumber();
            case 1 :
                return invoiceHeader.getDate();
            case 2 :
                return invoiceHeader.getCustomerName();
        }
        return null;
    }
    @Override
    public String getColumnName(int column) {
        return cols[column];
    }

    public void removeRow(int i) {
    }
}