package data;

import model.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerDB {
private static final List<Customer> CUSTOMER_LIST = new ArrayList<Customer>();
    static {
        initData();
    }

    private static void initData(){
        Customer cus = new Customer("Truong Dinh Duy Dat","10-4-1996","Hue","https://pbs.twimg.com/profile_images/919398021367918592/mv2OOERy_200x200.jpg");
        Customer cus1 = new Customer("Cao Thanh Binh","28-7-1996","Quang Binh","https://ca.slack-edge.com/TEZB2M9GC-U01UTHR6RMJ-48400b7be3b6-512");
        Customer cus2 = new Customer("Tran Minh Loc","6-1-2000","Hue","https://ca.slack-edge.com/TEZB2M9GC-U01UEKEQ5RU-5610a52c0c53-512");
        Customer cus3 = new Customer("Le Luong Hong Son","24-7-1996","Hue","https://ca.slack-edge.com/TEZB2M9GC-U01UTHR6Z7W-d7131e4a0cae-192");
        CUSTOMER_LIST.add(cus);
        CUSTOMER_LIST.add(cus1);
        CUSTOMER_LIST.add(cus2);
        CUSTOMER_LIST.add(cus3);

    }

    public static List<Customer> queryCustomer(){
        return CUSTOMER_LIST;
    }
}
