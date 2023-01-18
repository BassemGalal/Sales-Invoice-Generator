package View;
import Model.InvoiceHeader;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
public class MyTableSelection implements ListSelectionListener {
    private GUI gui;
    public MyTableSelection(GUI gui) {
        this.gui = gui;
    }
    @Override
    public void valueChanged(ListSelectionEvent e) {
        System.out.println("Row selected");
        int selectedRow=gui.getHeaderTable().getSelectedRow();
        System.out.println("row"+selectedRow);
        if (selectedRow!=-1) {
            InvoiceHeader invoiceHeader = gui.getInhed().get(selectedRow);
            gui.setSelectedRow(selectedRow);
            InvoiceLineTableModel invoiceLineTableModel = new InvoiceLineTableModel(invoiceHeader.getInvoiceLines());
            gui.getLineTable().setModel(invoiceLineTableModel);
        }
    }
}