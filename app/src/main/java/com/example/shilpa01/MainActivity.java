package com.example.shilpa01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    Pokemonadapter adapter;
    ArrayList<Pokemon> pokearray;

    //https://next.json-generator.com/Vy2abloQD    -- Directly access by array

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);

        /*Call<List<Pokemon>> call = service.getAllPokemon();

        call.enqueue(new Callback<List<Pokemon>>() {
            @Override
            public void onResponse(Call<List<Pokemon>> call, Response<List<Pokemon>> response) {

                System.out.println(response.body());

                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<Pokemon>> cal l, Throwable t) {

                System.out.println(t.getMessage());
                Toast.makeText(getApplicationContext(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });*/

        Call<Pokemonpojo> call = service.getAllPokemon();

        call.enqueue(new Callback<Pokemonpojo>() {
            @Override
            public void onResponse(Call<Pokemonpojo> call, Response<Pokemonpojo> response) {

                System.out.println(response.body());

                Pokemonpojo pkpojo = response.body();

                try
                {
                    pokearray = new ArrayList<>(pkpojo.getPokemon());

                    generateDataList(pokearray);
                }catch (NullPointerException e)
                {
                    System.out.println("From Arraylist : "+e.getMessage());
                }


            }

            @Override
            public void onFailure(Call<Pokemonpojo> call, Throwable t) {

                System.out.println(t.getMessage());
                Toast.makeText(getApplicationContext(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void generateDataList(ArrayList<Pokemon> pokemonList){

        adapter = new Pokemonadapter(pokemonList,getApplicationContext());
        @SuppressLint("WrongConstant") LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        RecyclerView recyclerView = findViewById(R.id.recycle_poke);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }
}
