package View;
import Model.InvoiceHeader;
import Model.InvoiceLine;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
public class GUI extends JFrame implements ActionListener {
    private DefaultTableModel Invoice;
    private JMenuBar menuBar;
    private JMenu filemenu;
    private JMenuItem loadMenuItem;
    private JMenuItem saveMenuItem;
    private JTable headerTable;
    private JTable lineTable;
    private JDialog dialog1;
    private JDialog dialog2;
    private Object obj;
    public void setSelectedRow(int selectedRow) {
        this.selectedRow = selectedRow;
    }
    private int selectedRow=-1;
    private ArrayList<InvoiceHeader> inhed = new ArrayList<>();
    private ArrayList<InvoiceLine> inhed1 = new ArrayList<>();
    private InvoiceHeaderTableModel invoiceHeaderTableModel;
    private InvoiceLineTableModel invoiceLineTableModel;
    private MyTableSelection myTableSelection;
    private JButton createNewInvoice;
    private JButton deleteInvoice;
    private JButton createNewItem;
    private JButton cancel;
    private JLabel label1;
    private JLabel label2;
    private JTextField customerName;
    private JTextField date;
    public GUI(Object obj) throws HeadlessException {
        super("Table test");
        this.obj = obj;
        menuBar = new JMenuBar();
        filemenu = new JMenu("File");
        menuBar.add(filemenu);
        setJMenuBar(menuBar);
        loadMenuItem = new JMenuItem("Load");
        loadMenuItem.addActionListener(this);
        loadMenuItem.setActionCommand("LO");


        saveMenuItem = new JMenuItem("Save");
        saveMenuItem.addActionListener(this);
        saveMenuItem.setActionCommand("SA");

        filemenu.add(loadMenuItem);
        filemenu.add(saveMenuItem);

        setLayout(new FlowLayout());

        invoiceHeaderTableModel = new InvoiceHeaderTableModel();
        headerTable = new JTable(invoiceHeaderTableModel);
        myTableSelection = new MyTableSelection(this);

        drawInvoiceHeaderTable();

        lineTable = new JTable();

        add(new JScrollPane(headerTable));
        add(new JScrollPane(lineTable));


        JButton createNewInvoice = new JButton(" Create New Invoice");
        createNewInvoice.addActionListener(e -> {
            Window parentWindow = SwingUtilities.windowForComponent(createNewInvoice);
            JDialog dialog1 = new JDialog(parentWindow);
            dialog1.setLocationRelativeTo(createNewInvoice);
            dialog1.setModal(true);
            dialog1.setLayout(new GridLayout(3,2));
            dialog1.setSize(350,150);
            dialog1.add(new JLabel("Customer Name"));
            JTextField tx=new JTextField(30);
            dialog1.add(tx);
            dialog1.add(new JLabel("Date"));
            JTextField tx1=new JTextField(35);
            dialog1.add(tx1);
            JButton btnAdd=new JButton("Add");
            dialog1.add(btnAdd);
            btnAdd.addActionListener(e2 ->
             {
              inhed.add(new InvoiceHeader(tx1.getText(),tx.getText()));
              invoiceHeaderTableModel = new InvoiceHeaderTableModel(inhed);
              headerTable.setModel(invoiceHeaderTableModel);
              dialog1.setVisible(false);
              });
            JButton btnCancel=new JButton("Cancel");
            dialog1.add(btnCancel);
            btnCancel.addActionListener(e1 -> {
                dialog1.setVisible(false);
            });
            dialog1.setVisible(true);
        });
           add(createNewInvoice);

        JButton deleteInvoice = new JButton("Delete Invoice");
        deleteInvoice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedRow>=0)
                {
                    inhed.remove(selectedRow);
                    JOptionPane.showMessageDialog(null,"Deleted Successfully");
                    invoiceHeaderTableModel = new InvoiceHeaderTableModel(inhed);
                    headerTable.setModel(invoiceHeaderTableModel);
                }
            }
        });




        add(deleteInvoice);

        JButton createNewItem = new JButton(" Create New Item");
        createNewItem.addActionListener(e -> {
            Window parentWindow = SwingUtilities.windowForComponent(createNewItem);
            JDialog dialog2 = new JDialog(parentWindow);
            dialog2.setLocationRelativeTo(createNewItem);
            dialog2.setModal(true);
            dialog2.setLayout(new GridLayout(4,2));
            dialog2.add(new JLabel("Item Name"));
            JTextField tx2=new JTextField(20);
            dialog2.add(tx2);
            dialog2.add(new JLabel("Count"));
            JTextField tx3=new JTextField(20);
            dialog2.add(tx3);
            dialog2.add(new JLabel("item Price"));
            JTextField tx4=new JTextField(20);
            dialog2.add(tx4);
            JButton add1=new JButton("Add");
            dialog2.add(add1);
            add1.addActionListener(e2 ->
            {
                inhed.get(selectedRow).addInvoiceLine(new InvoiceLine(inhed.get(selectedRow).getInvoiceHeaderNumber(),tx2.getText(),Integer.parseInt(tx4.getText()),Integer.parseInt(tx3.getText())));
                invoiceHeaderTableModel = new InvoiceHeaderTableModel(inhed);
                headerTable.setModel(invoiceHeaderTableModel);
                dialog2.setVisible(false);
            });
            JButton cancel1 = new JButton("Cancel");
            dialog2.add(cancel1);
             cancel1.addActionListener(e1 -> {
            dialog2.setVisible(false);
            });
            dialog2.pack();
            dialog2.setVisible(true);
        });

        add(createNewItem);

        JButton deleteItem = new JButton("Delete Item");
        deleteItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int x= lineTable.getSelectedRow();
                System.out.println(x);
                if (x>=0)
                {
                    inhed.get(selectedRow).getInvoiceLines().remove(x);
                    invoiceHeaderTableModel = new InvoiceHeaderTableModel(inhed);
                    headerTable.setModel(invoiceHeaderTableModel);
                    JOptionPane.showMessageDialog(null,"Deleted Successfully");

                }
            }
        });

        add(deleteItem);

        setSize(950, 550);
        setLocation(50, 50);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void drawInvoiceHeaderTable() {
        headerTable.setModel(invoiceHeaderTableModel);
        headerTable.getSelectionModel().addListSelectionListener(myTableSelection);
    }

    public JTable getHeaderTable() {
        return headerTable;
    }
    public JTable getLineTable() {
        return lineTable;
    }
    public ArrayList<InvoiceHeader> getInhed() {
        return inhed;
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {
            case "LO":
                openfile();
                break;
            case "SA":
                safefile();
              break;
        }
    }

    private void openfile() {
        JFileChooser fc = new JFileChooser();
        int result = fc.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                String path = fc.getSelectedFile().getPath();
                Path InvHedPath = Paths.get(path);
                List<String> InvHedLines = Files.lines(InvHedPath).collect(Collectors.toList());
                ArrayList<InvoiceHeader> tempInvhed=new ArrayList<>();
                for (String InvHedLine : InvHedLines) {
                    String[] InvHedParts = InvHedLine.split(",");
                    int InvoiceHeaderNumber = Integer.parseInt(InvHedParts[0]);
                    Date date2 = null;
                    try {
                        date2 = new SimpleDateFormat("dd-MM-yyyy").parse(InvHedParts[1]);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                    InvoiceHeader invhed = new InvoiceHeader(InvoiceHeaderNumber, date2, InvHedParts[2]);
                    tempInvhed.add(invhed);
                }
                JFileChooser x = new JFileChooser();
                int o = x.showOpenDialog(null);
                if (o == JFileChooser.APPROVE_OPTION) {
                    String InvLinePathStr = x.getSelectedFile().getAbsolutePath();
                    Path InvLinePath = Paths.get(InvLinePathStr);
                    List<String> InvLineLines = null;
                    try {
                        InvLineLines = Files.lines(InvLinePath).collect(Collectors.toList());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    for (String InvoiceLineLine : InvLineLines) {
                        String[] InvoiceLineParts = InvoiceLineLine.split(",");
                        int InvoiceLineNumber = Integer.parseInt(InvoiceLineParts[0]);
                        double ItemPrice = Double.parseDouble(InvoiceLineParts[2]);
                        double Count = Double.parseDouble(InvoiceLineParts[3]);
                        InvoiceHeader invoiceHeader = getInvoiceHeaderByInvoiceheaderNumber(tempInvhed, InvoiceLineNumber);
                        InvoiceLine invoiceLine = new InvoiceLine(InvoiceLineNumber, InvoiceLineParts[1], ItemPrice, Count);
                        invoiceHeader.getInvoiceLines().add(invoiceLine);
                    }
                    inhed=tempInvhed;
                    invoiceHeaderTableModel = new InvoiceHeaderTableModel(inhed);
                    drawInvoiceHeaderTable();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
    private void safefile()
    {
        JFileChooser fc1 =new JFileChooser();
        int result1 =fc1.showSaveDialog(this);
        if(result1==JFileChooser.APPROVE_OPTION) {
            String path = fc1.getSelectedFile().getPath();
            JFileChooser fc2 =new JFileChooser();
            int result2 =fc2.showSaveDialog(this);
            if(result2==JFileChooser.APPROVE_OPTION) {
                String path1 = fc2.getSelectedFile().getPath();
                try {
                    FileWriter myWriter1 = new FileWriter(path1);
                    FileWriter myWriter2=new FileWriter(path);
                    for(InvoiceHeader iheader:inhed)
                    {
                        int id = iheader.getNoInvoice();
                        for (InvoiceLine iLine:iheader.getInvoiceLines())
                            myWriter1.write(id+","+iLine.getItemName()+","+iLine.getItemPrice()+","+iLine.getCount()+"\n");
                        myWriter2.write(id+","+iheader.getDate()+","+iheader.getCustomerName()+"\n");
                    }
                    myWriter1.close();
                    myWriter2.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        }
    }
    private InvoiceHeader getInvoiceHeaderByInvoiceheaderNumber (ArrayList < InvoiceHeader > inhed,int id )
    {
        for (InvoiceHeader invoiceHeader : inhed) {
            if (invoiceHeader.getInvoiceHeaderNumber() == id) {
                return invoiceHeader;
            }
        }
        return null;
    }
}