package com.example.task1.ui.main.Tab3.data;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Base64;

import com.example.task1.ui.main.Tab3.adapter.RecipeAdapter;
import com.example.task1.ui.main.Tab3.model.RecipeList;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class SampleData {

    ArrayList<RecipeList> recipes = new ArrayList<>();

    public ArrayList<RecipeList> getRecipes() {

        RecipeList menu1 = new RecipeList("계란찜", "1. 계란 2개 기준, 물 100mL를 넣고 소금을 기호에 맞게 넣어준다. 이 때 취향에 따라 쪽파, 당근 등의 야채를 넣어도 된다.\n\n" +
                "2. 노른자 흰자 알끈이 모두 풀릴 때까지 잘 풀어준다.\n\n" +
                "3. 계란물이 용기의 2/3정도만 채워지도록 하고, 랩을 씌운 뒤 포크나 젓가락으로 구멍을 뚫어준다.\n\n" +
                "4. 전자레인지로 4-5분간 조리해주면 완성!");

        recipes.add(menu1);

        RecipeList menu2 = new RecipeList("황금계란볶음밥", "1. 팬에 식용유 3큰술을 넣고 중불로 달궈둔 후 썰은 대파를 넣는다.\n\n" +
                "2. 파가 익으면 불을 약간 줄이고 소금 한꼬집과 계란을 넣어준다.\n\n" +
                "3. 계란이 다 익으면 자신이 먹는 양의 밥을 1공기 넣는다.\n\n" +
                "4. 골고루 섞어지면 팬의 빈 자리에 간장을 따로 끓이다가 볶음밥과 섞어준다.\n\n" +
                "5. 후추를 조금 넣고 소금으로 간을 맞춰준다.\n\n" +
                "6. 참기름을 한큰술 넣고 섞어주듯 한번 볶아주면 완성!\n\n" +
                "★ 후추와 함께 굴소스를 한큰술 넣어주면 중국집의 맛을!");
        recipes.add(menu2);

        RecipeList menu3 = new RecipeList("버터간장계란밥", "1. 버터가 녹을 수 있게 1큰술을 밥 아래에 넣어둔다.\n\n" +
                "2. 계란 후라이를 구워 밥 위에 올린다.\n\n" +
                "3. 간장을 넣고 잘 비벼주면 완성!\n\n" +
                "★ 타마고 간장이 맛있다!");
        recipes.add(menu3);

        RecipeList menu4 = new RecipeList("치즈밥", "1. 케찹 1.5큰술, 고추장 1큰술, 설탕 0.5큰술을 넣어 소스를 만든다.\n\n" +
                "2. 전자레인지 용기에 밥을 넣고 소스를 올린다.\n\n" +
                "3. 모두 덮일 정도로 치즈를 올려준다.\n\n" +
                "4. 전자레인지로 2-3분간 조리해주면 완성!\n\n" +
                "★ 토핑으로 옥수수콘이나 햄을 넣으면 더 맛있다!");

        recipes.add(menu4);

        RecipeList menu5 = new RecipeList("김치볶음밥", "1. 김치 한 줌 정도를 숭덩숭덩 썰어서 참기름으로 볶아준다.\n\n" +
                "2. 센 불에서 볶다가 밥을 넣어주고 마저 볶아주면 완성!\n\n" +
                "★ 취향에 따라 계란후라이를 올리거나 넣어서 볶아도 맛있다!");

        recipes.add(menu5);

        RecipeList menu6 = new RecipeList("참치김치볶음밥", "1. 김치 한 줌 정도를 숭덩숭덩 썰어서 참기름으로 볶아준다.\n\n" +
                "2. 김치가 조금 익을 때 참치캔을 넣어준다.\n\n" +
                "3. 센 불에서 볶다가 밥을 넣어주고 마저 볶아주면 완성!\n\n" +
                "★ 취향에 따라 계란후라이를 올리거나 넣어서 볶아도 맛있다!");

        recipes.add(menu6);

        RecipeList menu7 = new RecipeList("인절미 토스트", "1. 식빵 사이에 말랑한 인절미를 끼워넣고 팬에서 약불에 굽는다.\n\n" +
                "2. 아랫쪽 빵이 다 익으면 뒤집어서 굽는다.\n\n" +
                "3. 빵이 다 구워졌으면 접시에 옮겨 자른 뒤 콩고물을 올려준다.\n\n" +
                "4. 아이스크림을 올리면 완성!\n\n" +
                "★ 취향에 따라 아이스크림 대신 연유를 사용해도 맛있다!");

        recipes.add(menu7);

        RecipeList menu8 = new RecipeList("까르보나라", "1. 계란 1개와 계란 노른자 1개를, 파마산치즈가루 반컵을 뭉치지 않게 섞어 소스를 만든다.\n\n" +
                "2. 스파게티면을 삶고 면수를 1컵 남겨둔다.\n\n" +
                "3. 팬에 올리브유를 두른 후 다진마늘, 베이컨을 볶는다.\n\n" +
                "4. 스파게티면과 면수 1컵을 넣고 마저 볶아준다.\n\n" +
                "5. 면을 식힌 후 소스를 넣어주면 완성!\n\n" +
                "★ 홈플러스 스파게티면이 싸다!");

        recipes.add(menu8);

        RecipeList menu9 = new RecipeList("참치덮밥", "1. 참치캔 (200g)에서 기름을 짜내어 준다.\n\n" +
                "2. 냄비에 육수 2컵 반, 간장 반 컵, 맛술 반 컵, 설탕 3큰술을 넣고 끓인다.\n\n" +
                "3. 끓인 양념장을 다른 곳으로 옮기고 팬에 참치캔에서 짜낸 기름을 넣는다.\n\n" +
                "4. 채 썬 양파를 팬에 넣고 익힌다.\n\n" +
                "5. 양파가 반 정도 익었을 때 양념장 350mL를 넣고 끓인다.\n\n" +
                "6. 양파가 익으면 참치를 넣고 끓여준다.\n\n" +
                "7. 계란 2개를 풀어서 넣어준다.\n\n" +
                "8. 밥 위에 참치덮밥 양념을 올리면 완성!");

        recipes.add(menu9);

        return recipes;
    }
}