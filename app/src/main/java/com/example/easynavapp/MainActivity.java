package com.example.easynavapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private BottomAppBar bottomAppBar;
    private SearchView searchView;
    private ImageButton searchButton;
    private FloatingActionButton fabOption1, fabOption2, fabMain;
    private boolean isFabOpen = false;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomAppBar = findViewById(R.id.bottom_app_bar);
        setSupportActionBar(bottomAppBar);

        searchView = findViewById(R.id.search_view);
        searchButton = findViewById(R.id.search_button);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (searchView.getVisibility() == View.GONE) {
                    searchView.setVisibility(View.VISIBLE);
                    searchButton.setVisibility(View.GONE);
                }
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.isEmpty()) {
                    searchView.setVisibility(View.GONE);
                    searchButton.setVisibility(View.VISIBLE);
                }
                return false;
            }
        });

        bottomAppBar.setOnMenuItemClickListener(new BottomAppBar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.action_menu) {
                    showPopupMenu(bottomAppBar);
                    return true;
                }
                return false;
            }
        });

        ImageButton profileButton = findViewById(R.id.ic_action_name); // Bind ImageButton
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Launch ProfileActivity when the button is clicked
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        fabMain = findViewById(R.id.fab_main);
        fabOption1 = findViewById(R.id.fab_option_1);
        fabOption2 = findViewById(R.id.fab_option_2);

        fabOption1.setVisibility(View.GONE);
        fabOption2.setVisibility(View.GONE);

        fabMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleFABMenu();
            }
        });

        fabOption1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                shareAction();
            }
        });

        fabOption2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cameraAction();
            }
        });
    }

    private void toggleFABMenu() {
        if (isFabOpen) {
            fabOption1.setVisibility(View.GONE);
            fabOption2.setVisibility(View.GONE);
            isFabOpen = false;
        } else {
            fabOption1.setVisibility(View.VISIBLE);
            fabOption2.setVisibility(View.VISIBLE);
            isFabOpen = true;
        }
    }

    private void shareAction() {

    }
    private void cameraAction() {

    }
    private void showPopupMenu(View anchor) {
        PopupMenu popupMenu = new PopupMenu(this, anchor);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
        popupMenu.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bottom_app_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}

