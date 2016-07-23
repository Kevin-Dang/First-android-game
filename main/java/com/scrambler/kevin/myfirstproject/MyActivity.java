package com.scrambler.kevin.myfirstproject;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import com.scrambler.kevin.myfirstproject.R;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;


public class MyActivity extends AppCompatActivity {

    // Best values for size of each image piece
    public int height;
    public int width;

    public Bitmap bm0, bm1, bm2, bm3,bm4,bm5,bm6,bm7,bm8,bmGrey;

    //Location of the empty piece
    public int emptyPos;


    public static int[] curimg; //current image
    public static int[] winner = new int[] {0,1,2,3,4,5,6,7,8};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);


        initialize();
    }

    public void testing(){

    }

    //Sets up the image variables and sets up the game
    public void initialize(){
        emptyPos=7;
        curimg = new int[] {6,4,7,3,5,1,0,8,2};

        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE); // the results will be higher than using the activity context object or the getWindowManager() shortcut
        wm.getDefaultDisplay().getMetrics(displayMetrics);
        width = (displayMetrics.widthPixels)/3;
        height = (displayMetrics.heightPixels)*224/1000;

        Bitmap im1 = BitmapFactory.decodeResource(getResources(), R.drawable.rsz_dotalogo1);
        Bitmap im2 = BitmapFactory.decodeResource(getResources(), R.drawable.rsz_dotalogo2);
        Bitmap im3 = BitmapFactory.decodeResource(getResources(), R.drawable.rsz_dotalogo3);
        Bitmap im4 = BitmapFactory.decodeResource(getResources(), R.drawable.rsz_dotalogo4);
        Bitmap im5 = BitmapFactory.decodeResource(getResources(), R.drawable.rsz_dotalogo5);
        Bitmap im6 = BitmapFactory.decodeResource(getResources(), R.drawable.rsz_dotalogo6);
        Bitmap im7 = BitmapFactory.decodeResource(getResources(), R.drawable.rsz_dotalogo7);
        Bitmap im8 = BitmapFactory.decodeResource(getResources(), R.drawable.rsz_dotalogo8);
        Bitmap imGrey = BitmapFactory.decodeResource(getResources(), R.drawable.greyback);

        bm1 = Bitmap.createScaledBitmap(im1, width, height, true);
        bm2 = Bitmap.createScaledBitmap(im2, width, height, true);
        bm3 = Bitmap.createScaledBitmap(im3, width, height, true);
        bm4 = Bitmap.createScaledBitmap(im4, width, height, true);
        bm5 = Bitmap.createScaledBitmap(im5, width, height, true);
        bm6 = Bitmap.createScaledBitmap(im6, width, height, true);
        bm7 = Bitmap.createScaledBitmap(im7, width, height, true);
        bm8 = Bitmap.createScaledBitmap(im8, width, height, true);
        bmGrey = Bitmap.createScaledBitmap(imGrey, width, height, true);

        //Sets up the values and randomizes moves
        getImageButton(0).setImageBitmap(bm7);
        getImageButton(1).setImageBitmap(bm5);
        getImageButton(2).setImageBitmap(bm8);
        getImageButton(3).setImageBitmap(bm4);
        getImageButton(4).setImageBitmap(bm6);
        getImageButton(5).setImageBitmap(bm2);
        getImageButton(6).setImageBitmap(bm1);
        getImageButton(7).setImageBitmap(bmGrey);
        getImageButton(8).setImageBitmap(bm3);
        randomizer();

    }

    //Almost win function/button
    public void secret(){
        emptyPos=7;
        curimg = new int[] {0,1,2,3,4,5,6,8,7};
        setImage(getImageButton(0),0);
        setImage(getImageButton(1),1);
        setImage(getImageButton(2),2);
        setImage(getImageButton(3),3);
        setImage(getImageButton(4),4);
        setImage(getImageButton(5),5);
        setImage(getImageButton(6),6);
        setImage(getImageButton(7),9);
        setImage(getImageButton(8),7);
    }

    //Click listener
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
    public void clicksettings2(View view){
        secret();
    }
    public void clicksettings3(View view){
        Intent goback = new Intent(this,TitleScreen.class);
        startActivity(goback);
    }

    //Random
    public void randomizer(){
        int randint=0;
        int randbutton = 0;
        for (int a =0; a<40;a++){
            //Reduce the moves to 4, the directions from the empty space
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
        //Only works if move is valid/ if button pressed is above,below,left,right
        //of the grey space
        if (Math.abs(pos- emptyPos) ==3||
                ((pos/ 3)==(emptyPos/ 3) && Math.abs(pos-emptyPos)==1)){
            swapimages(pos);
            if(Arrays.equals(winner,curimg)){
                /*win();*/
                Intent winIntent = new Intent(this,DisplayWinner.class);
                winIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(winIntent);
                System.out.println("WINNER    ");
            }
        }
    }

    public void swapimages(int pos) {
        System.out.println("Pos: " + pos);
        System.out.println("EMpty:" + emptyPos);
        int imagenumber = curimg[pos];
        ImageButton imgbut1=getImageButton(pos);
        ImageButton imgbut2=getImageButton(emptyPos);

        setImage(imgbut1, 9);
        setImage(imgbut2, imagenumber);
        curimg[pos] = 8;
        curimg[emptyPos]=imagenumber;
        emptyPos = pos;


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
            case 0: img.setImageBitmap(bm1); break;
            case 1: img.setImageBitmap(bm2);break;
            case 2: img.setImageBitmap(bm3);break;
            case 3: img.setImageBitmap(bm4);break;
            case 4: img.setImageBitmap(bm5);break;
            case 5: img.setImageBitmap(bm6);break;
            case 6: img.setImageBitmap(bm7);break;
            case 7: img.setImageBitmap(bm8);break;
            case 9: img.setImageBitmap(bmGrey);break;
            default: img.setImageBitmap(bmGrey);break;
              }
    }
    public void win(){
        boolean yes = true;
        ImageButton imgbut = (ImageButton) findViewById(R.id.button4);
        imgbut.setImageResource(R.drawable.youwin);
    }

}
