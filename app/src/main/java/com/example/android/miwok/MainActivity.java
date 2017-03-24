/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.miwok;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);

            myClickers();
    }


    public void myClickers(){
        //find the view to show by category=ies;
        TextView numbers = (TextView) findViewById(R.id.numbers);
        TextView familyMembers = (TextView) findViewById(R.id.family);
        TextView colors = (TextView) findViewById(R.id.colors);
        TextView phrases = (TextView) findViewById(R.id.phrases);

        // set the OnClickListeneter for the NUMBERS category
        numbers.setOnClickListener(new View.OnClickListener(){

            // execute this method when the NUMBERS category is been clicked.
            @Override
            public void onClick(View v){
                Intent numbersIntent = new Intent(MainActivity.this, NumbersActivity.class);
                startActivity(numbersIntent);
            }

        });


        // set the OnClickListener for FAMILYMEMBERS category.
        familyMembers.setOnClickListener( new View.OnClickListener(){

          // method to be executed when the family members category is been clicked on.
            @Override
            public void onClick(View v){
               Intent familyIntent =  new Intent(MainActivity.this, FamilyMembersActivity.class);
                startActivity(familyIntent);
            }

        });



        //set OnClickListener for the category COLORS.
        colors.setOnClickListener( new View.OnClickListener(){

            // method to be executed when the COLORS category is been clicked on.
            @Override
            public void onClick(View v){
                Intent colorsIntent  = new Intent(MainActivity.this, ColorsActivity.class);
                startActivity(colorsIntent);
            }
        });



        // set OnClickListener for the category PHRASES
        phrases.setOnClickListener( new View.OnClickListener(){

            // method to tbe executed when the category PHRASES  is been clicked on.
            @Override
            public void onClick(View v){
                Intent phrasesIntent =  new Intent(MainActivity.this, PhrasesActivity.class);
                startActivity(phrasesIntent);
            }
        });
    }


}
