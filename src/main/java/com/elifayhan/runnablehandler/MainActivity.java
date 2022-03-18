package com.elifayhan.runnablehandler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    int number;
    Runnable runnable;
    Handler handler;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.textView);
        button=findViewById(R.id.button);
        int number=0;

    }
    public void start(View view){
        //while(number<100){
        handler=  new Handler();
        runnable=new Runnable() {
            @Override
            public void run() {
                //bunun içerisine yazdığımız her şey bizim belirttiğimiz periyotta olacak demektir.
                textView.setText("Time:"+number);
                number++;
                textView.setText("Time:"+number);
                //handler ile de bu runnable ı çalıştırmamız gerekiyor.
                handler.postDelayed(runnable,1000);
                //parametre olarak runnabel ve kaç milisaniye sonra bu runnable ı çalıştırması gerektiğini soruyor.
            }

        };
        handler.post(runnable);
        button.setEnabled(false);


            //böyle yaptığımızda ve starta bastığımızda uygulama kendi içerisinde direkt olarak 100 ü gösterecek
            //çok hızlı bir şekilde saydı. sleep yapabiliriz. her loop sonunda uygulamayı uyutalım.
           /* try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            kullanıcının etkileşime girdiği yeri dondurmuş olduk. bunun yerine while da kullanmayıp
            başka bir threadde bu işlemleri gerçekleştirmemiz gerekiyor.
            bunun için Runnable ve Handlera ihtiyacımız var.
            */
        }
        public void stop(View view){
            //stopa basıldığında startı tekrar aktif edelim:
            button.setEnabled(true);
            //arkada çalışan runnable ı da kapatmamız lazım:
            handler.removeCallbacks(runnable);
            //ve aynı zamanda da numberı sıfırlayalım:
            number=0;
            textView.setText("Time: "+ number);
        }

    }
