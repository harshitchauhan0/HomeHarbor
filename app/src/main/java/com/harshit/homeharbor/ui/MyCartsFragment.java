package com.harshit.homeharbor.ui;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.harshit.homeharbor.OrderPlaced;
import com.harshit.homeharbor.R;
import com.harshit.homeharbor.adapters.MyCartAdapter;
import com.harshit.homeharbor.models.MyCartModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyCartsFragment extends Fragment {

    FirebaseFirestore db;
    FirebaseAuth auth;
    TextView overTotalAmount;

    RecyclerView recyclerView;
    MyCartAdapter cartAdapter;
    List<MyCartModel> cartModelList;
    Button buyNow;
    ProgressBar progressBar;


    public MyCartsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_my_carts, container, false);

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();

        progressBar = root.findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);

        recyclerView = root.findViewById(R.id.recyclerview);
        recyclerView.setVisibility(View.GONE);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        buyNow = root.findViewById(R.id.buy_now);

        overTotalAmount = root.findViewById(R.id.textView7);


        cartModelList = new ArrayList<>();
        cartAdapter = new MyCartAdapter(getActivity(), cartModelList);
        recyclerView.setAdapter(cartAdapter);


        db.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                .collection("AddToCart").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {

                                String documentId = documentSnapshot.getId();


                                MyCartModel cartModel = documentSnapshot.toObject(MyCartModel.class);
                                cartModel.setDocumentId(documentId);
                                cartModelList.add(cartModel);
                                cartAdapter.notifyDataSetChanged();
                                progressBar.setVisibility(View.GONE);
                                recyclerView.setVisibility(View.VISIBLE);
                            }

                            calculateTotalAmount(cartModelList);
                        }
                    }
                });

        buyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getContext(), PlaceOrderActivity.class);
//                intent.putExtra("itemList", (Serializable) cartModelList);
//                startActivity(intent);
//                Toast.makeText(getContext(),"You clicked",Toast.LENGTH_SHORT).show();
                List<MyCartModel> list = cartModelList;


                if (list != null && list.size() > 0) {
                    for (MyCartModel model : list) {
                        final HashMap<String, Object> cartMap = new HashMap<>();

                        cartMap.put("productName", model.getProductName());
                        cartMap.put("productPrice", model.getProductPrice());
                        cartMap.put("currentDate", model.getCurrentDate());
                        cartMap.put("currentTime", model.getCurrentTime());
                        cartMap.put("totalQuantity", model.getTotalQuantity());
                        cartMap.put("totalPrice", model.getTotalPrice());

                        firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                                .collection("MyOrder").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DocumentReference> task) {
                                        Toast.makeText(getContext(), "Your Order Has Been Placed", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                }

                startActivity(new Intent(getContext(), OrderPlaced.class));
            }
        });

        return root;
    }



    private void calculateTotalAmount(List<MyCartModel> cartModelList) {

        double totalAmount = 0.0;
        for (MyCartModel myCartModel : cartModelList) {
            totalAmount += myCartModel.getTotalPrice();
        }

        overTotalAmount.setText("Total Amount: " + totalAmount);
    }

}