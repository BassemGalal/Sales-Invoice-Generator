package View;

import Model.InvoiceLine;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class InvoiceLineTableModel extends AbstractTableModel {
    private ArrayList<InvoiceLine>invoiceLines;
    private String[] cols={"No.","Item Name","Item Price","Count","Total"};

    public InvoiceLineTableModel(ArrayList<InvoiceLine> invoiceLines) {
        this.invoiceLines = invoiceLines;
    }

    @Override
    public int getRowCount() {
        return invoiceLines.size();
    }

    @Override
    public int getColumnCount() {
        return cols.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        InvoiceLine invoiceLine=invoiceLines.get(rowIndex);
        switch (columnIndex)
        {
            case 0:
            return invoiceLine.getInvoiceLineNumber();
            case 1 :
                return invoiceLine.getItemName();
            case 2 :
                return invoiceLine.getItemPrice();
            case 3 :
                return invoiceLine.getCount();
            case 4 :
                return invoiceLine.getItemTotal();

        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        return cols[column];
    }
}
