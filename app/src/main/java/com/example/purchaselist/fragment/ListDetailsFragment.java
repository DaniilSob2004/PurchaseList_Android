package com.example.purchaselist.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.purchaselist.R;
import com.example.purchaselist.adapter.ProductAdapter;
import com.example.purchaselist.db.DatabaseManager;
import com.example.purchaselist.models.MyList;
import com.example.purchaselist.models.Product;
import com.example.purchaselist.utils.AppConstant;
import com.example.purchaselist.utils.TestUtils;

import java.util.List;

public class ListDetailsFragment extends Fragment {

    private ImageButton backBtn;
    private TextView nameTextView;
    private TextView descriptionTextView;
    private TextView dateTextView;
    private TextView totalItemsTextView;
    private TextView itemsCheckedTextView;

    private MyList myList;
    private List<Product> products;

    private ProductAdapter productAdapter;
    private ListView productsList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_details, container, false);

        // инициализация
        init(view);

        // получение аргументов
        getAllArguments();

        // настройка адаптера коллекции "products"
        setProductsAdapter(view);

        return view;
    }


    public void clickBack() {
        FragmentActivity fragmentActivity = getActivity();
        if (fragmentActivity != null) {
            fragmentActivity.getSupportFragmentManager().popBackStack();
        }
    }


    private void displayListDetails() {
        nameTextView.setText(myList.getName());
        descriptionTextView.setText(myList.getDescription());
        dateTextView.setText(TestUtils.getFormattedDate(myList.getDate()));
        totalItemsTextView.setText(String.valueOf(products.size()));
        itemsCheckedTextView.setText(String.valueOf(getCountProductsChecked()));
    }

    private void getAllArguments() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            myList = bundle.getParcelable(AppConstant.MY_LIST_DETAILS_KEY);
            products = getProducts();
            displayListDetails();
        }
    }

    private List<Product> getProducts() {
        DatabaseManager dbManager = new DatabaseManager(getContext());
        dbManager.open();
        List<Product> products = dbManager.getProductsByListId(myList.getId());
        dbManager.close();
        return products;
    }

    private int getCountProductsChecked() {
        DatabaseManager dbManager = new DatabaseManager(getContext());
        dbManager.open();
        List<Product> products = dbManager.getProductsCheckedByListId(myList.getId());
        dbManager.close();
        return products.size();
    }


    private void setProductsAdapter(View view) {
        // получаем элемент ListView
        productsList = view.findViewById(R.id.products_list);

        // создаём адаптер
        List<Product> products = getProducts();
        productAdapter = new ProductAdapter(getContext(), R.layout.product_item, products);

        // устанавливаем адаптер в список
        productsList.setAdapter(productAdapter);
    }

    private void init(View view) {
        nameTextView = view.findViewById(R.id.list_name_text_view);
        descriptionTextView = view.findViewById(R.id.list_description_text_view);
        dateTextView = view.findViewById(R.id.list_date_text_view);
        totalItemsTextView = view.findViewById(R.id.total_items_text_view);
        itemsCheckedTextView = view.findViewById(R.id.items_checked_text_view);

        backBtn = view.findViewById(R.id.back_btn);
        backBtn.setOnClickListener(v -> clickBack());
    }
}