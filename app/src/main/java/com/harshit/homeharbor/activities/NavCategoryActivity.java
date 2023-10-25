package com.harshit.homeharbor.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.harshit.homeharbor.R;


import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.harshit.homeharbor.adapters.NavCategoryDetailedAdapter;
import com.harshit.homeharbor.models.NavCategoryDetailedModel;

import java.util.ArrayList;
import java.util.List;

public class NavCategoryActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    NavCategoryDetailedAdapter adapter;
    List<NavCategoryDetailedModel> list;
    FirebaseFirestore db;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_category);

        db = FirebaseFirestore.getInstance();
        progressBar=findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);

        String type = getIntent().getStringExtra("type");


        recyclerView = findViewById(R.id.nav_cat_det_rec);
        recyclerView.setVisibility(View.GONE);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list=new ArrayList<>();

        adapter=new NavCategoryDetailedAdapter(this,list);
        recyclerView.setAdapter(adapter);


        if(type != null && type.equalsIgnoreCase("bed"))
        {
            db.collection("AllProducts").whereEqualTo("type","bed").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments())
                    {
                        NavCategoryDetailedModel navCategoryDetailedModel=documentSnapshot.toObject(NavCategoryDetailedModel.class);
                        list.add(navCategoryDetailedModel);
                        adapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                    }
                }
            });
        }

        if(type != null && type.equalsIgnoreCase("sofa"))
        {
            db.collection("AllProducts").whereEqualTo("type","sofa").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments())
                    {
                        NavCategoryDetailedModel navCategoryDetailedModel=documentSnapshot.toObject(NavCategoryDetailedModel.class);
                        list.add(navCategoryDetailedModel);
                        adapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                    }
                }
            });
        }

        if(type != null && type.equalsIgnoreCase("tv"))
        {
            db.collection("AllProducts").whereEqualTo("type","tv").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments())
                    {
                        NavCategoryDetailedModel navCategoryDetailedModel=documentSnapshot.toObject(NavCategoryDetailedModel.class);
                        list.add(navCategoryDetailedModel);
                        adapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                    }
                }
            });
        }

        if(type != null && type.equalsIgnoreCase("coffeetable"))
        {
            db.collection("AllProducts").whereEqualTo("type","coffeetable").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments())
                    {
                        NavCategoryDetailedModel navCategoryDetailedModel=documentSnapshot.toObject(NavCategoryDetailedModel.class);
                        list.add(navCategoryDetailedModel);
                        adapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                    }
                }
            });
        }

        if(type != null && type.equalsIgnoreCase("dressingtable"))
        {
            db.collection("AllProducts").whereEqualTo("type","dressingtable").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments())
                    {
                        NavCategoryDetailedModel navCategoryDetailedModel=documentSnapshot.toObject(NavCategoryDetailedModel.class);
                        list.add(navCategoryDetailedModel);
                        adapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                    }
                }
            });
        }

        if(type != null && type.equalsIgnoreCase("chair"))
        {
            db.collection("AllProducts").whereEqualTo("type","chair").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments())
                    {
                        NavCategoryDetailedModel navCategoryDetailedModel=documentSnapshot.toObject(NavCategoryDetailedModel.class);
                        list.add(navCategoryDetailedModel);
                        adapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                    }
                }
            });
        }

        if(type != null && type.equalsIgnoreCase("wardrobe"))
        {
            db.collection("AllProducts").whereEqualTo("type","wardrobe").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments())
                    {
                        NavCategoryDetailedModel navCategoryDetailedModel=documentSnapshot.toObject(NavCategoryDetailedModel.class);
                        list.add(navCategoryDetailedModel);
                        adapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
    }
}