package com.example.kevin.myfirstproject;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Arrays;

public class MyActivity extends AppCompatActivity {
    ImageButton imgButton;

    public final static String EXTRA_MESSAGE = "com.mycompany.myfirstapp.MESSAGE";
    int emptyPos;
    public static int[] curimg = new int[] {0,1,2,3,4,5,6,8,7};
    public static int[] winner = new int[] {0,1,2,3,4,5,6,7,8};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        curimg[0]=0;
        winner[0]=0;
        emptyPos=7;


    }
    public void click0(View view){
        clickhandler(0);
    }
    public void click1(View view){
        clickhandler(1);
    }
    public void click2(View view){
        clickhandler(2);
    }
    public void click3(View view){
        clickhandler(3);
    }
    public void click4(View view){
        clickhandler(4);
    }
    public void click5(View view){
        clickhandler(5);
    }
    public void click6(View view){
        clickhandler(6);
    }
    public void click7(View view){
        clickhandler(7);
    }
    public void click8(View view){
        clickhandler(8);
    }

    public void clicksettings1(View view){
        randomizer();
    }
    public void randomizer(){
        int randint=0;
        int randbutton = 0;
        for (int a =0; a<25;a++){
            randint = (int)(Math.random()*4);
            switch(randint){
                case 0: randbutton = emptyPos-3;break;
                case 1: randbutton = emptyPos+3;break;
                case 2: randbutton = emptyPos-1;break;
                case 3: randbutton = emptyPos+1;break;
            }
            if (randbutton >=0 && randbutton < 9){
                clickhandler(randbutton);
            }
        }
    }

    public void clickhandler (int pos){
        ImageButton img= (ImageButton) findViewById(R.id.button0);
        /*img.getTag(R.drawable.dotalogo2);*/
        /*img.getTag();*/
        if (Math.abs(pos- emptyPos) ==3||
                ((pos/ 3)==(emptyPos/ 3) && Math.abs(pos-emptyPos)==1)){
            swapimages(pos);
            if(Arrays.equals(winner,curimg)){
                /*win();*/
            }
        }
    }

    public void swapimages(int pos) {
        System.out.println("Pos: " + pos);
        System.out.println("EMpty:" + emptyPos);
        int imagenumber = curimg[pos];
        ImageButton imgbut1=getImageButton(pos);
        ImageButton imgbut2=getImageButton(emptyPos);
  /*
        LinearLayout.LayoutParams params1 = (LinearLayout.LayoutParams) imgbut1.getLayoutParams();
        params1.height = 450;
        params1.width = 500;
        imgbut1.setLayoutParams(params1);

        LinearLayout.LayoutParams params2 = (LinearLayout.LayoutParams) imgbut2.getLayoutParams();
        params1.height = 450;
        params1.width = 500;
        imgbut2.setLayoutParams(params2);
*/
        setImage(imgbut1, 9);
        setImage(imgbut2, imagenumber);
        curimg[pos] = 8;
        curimg[emptyPos]=imagenumber;
        emptyPos = pos;
        System.out.println("Pos:" + pos);
        System.out.println("EMpty: " + emptyPos);


    }
    public ImageButton getImageButton(int pos){
        switch(pos){
            case 0: return (ImageButton) findViewById(R.id.button0);
            case 1: return (ImageButton) findViewById(R.id.button1);
            case 2: return (ImageButton) findViewById(R.id.button2);
            case 3: return (ImageButton) findViewById(R.id.button3);
            case 4:  return (ImageButton) findViewById(R.id.button4);
            case 5: return (ImageButton) findViewById(R.id.button5);
            case 6:return (ImageButton) findViewById(R.id.button6);
            case 7: return (ImageButton) findViewById(R.id.button7);
            case 8: return (ImageButton) findViewById(R.id.button8);
            default: return (ImageButton) findViewById(R.id.button0);
        }

    }

    public void setImage(ImageButton img, int index){
        switch(index){
            case 0: img.setImageResource(R.drawable.rsz_dotalogo1); break;
            case 1: img.setImageResource(R.drawable.rsz_dotalogo2);break;
            case 2: img.setImageResource(R.drawable.rsz_dotalogo3);break;
            case 3: img.setImageResource(R.drawable.rsz_dotalogo4);break;
            case 4: img.setImageResource(R.drawable.rsz_dotalogo5);break;
            case 5: img.setImageResource(R.drawable.rsz_dotalogo6);break;
            case 6: img.setImageResource(R.drawable.rsz_dotalogo7);break;
            case 7: img.setImageResource(R.drawable.rsz_dotalogo8);break;
            /*case 8: index=8;img.setImageResource(R.drawable.dotalogo9);*/
            case 9: img.setImageResource(R.drawable.greyback);break;
            default: img.setImageResource(R.drawable.dota_logo);break;

        }
    }
    public void win(){
        boolean yes = true;
        ImageButton imgbut = (ImageButton) findViewById(R.id.button4);
        imgbut.setImageResource(R.drawable.youwin);
    }
    /*

    public void sendMessage(View view){
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editext = (EditText) findViewById(R.id.edit_message);
        String message = editext.getText().toString();
        intent.putExtra(EXTRA_MESSAGE,message);
        startActivity(intent);
    }*/
}
