package View;
import Model.InvoiceHeader;
import java.util.ArrayList;
public class MainProject {
    private ArrayList<InvoiceHeader> inhed ;
    public MainProject()
    {
        inhed=new ArrayList<>();
    }
    public static void main(String[] args) {
        MainProject obj = new MainProject();
        GUI gui = new GUI(obj);
    }
        }

